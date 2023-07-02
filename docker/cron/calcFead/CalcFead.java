import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.concurrent.atomic.AtomicInteger;

public class CalcFead {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private Connection con ;
    public static void main(String[] args) {
        CalcFead myCalcFeadObj = new CalcFead();
        System.out.printf("%n%s CalcFead Started", LocalDateTime.now().format(formatter));

        // System.out.println("connection String: " + connectionString + " " + "user: " + user + "password: " + password );
        try {
            myCalcFeadObj.con = myCalcFeadObj.getDbConnection();
            myCalcFeadObj.CalcFeadOneYear("2022");
        } catch (SQLException e) {
            System.out.printf("%n%s CalcFead a SQL Error Occurred", LocalDateTime.now().format(formatter));
            e.printStackTrace();
        } catch (Exception e) {
            System.out.printf("%n%s CalcFead a System Error Occurred", LocalDateTime.now().format(formatter));
            System.out.println(e);
        } finally {
            System.out.printf("%n%s CalcFead Ended%n", LocalDateTime.now().format(formatter));
        }
    }

    private Connection getDbConnection() throws Exception {
        String host = System.getenv("MYSQL_HOST");
        String user = System.getenv("MYSQL_USER");
        String password = System.getenv("MYSQL_PASSWORD");
        String database = System.getenv("MYSQL_DATABASE");
        String connectionString = String.format("jdbc:mysql://%s:3306/%s", host, database);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(connectionString, user, password);
        return con;
    }

    private int executeUpdateQuery(Connection con,String query) {
        try (
             Statement statement = con.createStatement();
             ) {
            return statement.executeUpdate(query);
        } catch (Exception ex) {
            System.out.printf("%n%] CalcFead a SQL Error Occurred: %s\n", LocalDateTime.now().format(formatter), ex.getMessage());
            ex.printStackTrace();
            return -1;
        }
    }

    private void executeQuery(Connection con,String query, Consumer<ResultSet> resultSetConsumer) {
        try (
             Statement statement = con.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                resultSetConsumer.accept(resultSet);
            } catch (Exception ex) {
                System.out.printf("%n%] CalcFead a SQL Error Occurred: %s\n", LocalDateTime.now().format(formatter), ex.getMessage());
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            System.out.printf("%n%] CalcFead a SQL Error Occurred: %s\n", LocalDateTime.now().format(formatter), ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void CalcFeadOneYear(String annee) throws Exception {

        String query = String.format("delete from campagne_fead where campagne='CUMUL' and annee = '%s'", annee);
        int numrows = executeUpdateQuery(con,query);
        System.out.printf("%n%s CalcFead Deleted %d CUMUL rows from  campagne_fead table for year %s .",
                LocalDateTime.now().format(formatter), numrows, annee);

        query = String.format("update campagne_fead set envoye=0,cession=0,qte=init where annee = '%s'", annee);
        numrows = executeUpdateQuery(con,query);
        System.out.printf("%n%s CalcFead Reinitialized %d rows from  campagne_fead table for year %s .",
                LocalDateTime.now().format(formatter), numrows, annee);

        majCessions(annee);

        query = String.format("update campagne_fead set qte=init+cession where annee= '%s' and campagne<>'cumul'", annee);
        numrows = executeUpdateQuery(con,query);
        System.out.printf("%n%s CalcFead Updated %d rows with qte = qte=init+cession for non-cumul from  campagne_fead table for year %s .",
                LocalDateTime.now().format(formatter), numrows, annee);

        majEnvoye(annee);

        query = String.format("update campagne_fead set qte=init+cession  where annee= '%s'", annee);
        numrows = executeUpdateQuery(con,query);
        System.out.printf("%n%s CalcFead Updated %d rows with qte = qte=init+cession for year and cumul from  campagne_fead table for year %s .",
                LocalDateTime.now().format(formatter), numrows, annee);

        query = String.format("select annee,'CUMUL' as campagne,id_article,id_asso,sum(coalesce(init,0)) as init,sum(coalesce(qte,0)) as qte,sum(coalesce(expedie,0)) as expedie,sum(coalesce(envoye,0)) as envoye,sum(coalesce(cession,0)) as cession from campagne_fead where campagne<>'cumul' and annee='%s' group by annee,id_article,id_asso", annee);
        executeQuery(con,query, rs -> {
            try {
                String updateCumulRowQuery = "insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,init,qte,expedie,envoye,cession)" +
                     " values(?,?,?,?,?,?,?,?,?,?,?)  on duplicate key update init = ?, qte = ?, expedie= ?, envoye = ?, cession = ?"   ;
                PreparedStatement updateCumulRowQueryStmt =con.prepareStatement(updateCumulRowQuery);
                var rowsNum = 0;
                while (rs.next()) {
                    updateCumulRowQueryStmt.setString(1,annee);
                    updateCumulRowQueryStmt.setString(2,"CUMUL");
                    updateCumulRowQueryStmt.setString(3, rs.getString("id_article"));
                    updateCumulRowQueryStmt.setString(4, rs.getString("id_asso"));
                    updateCumulRowQueryStmt.setString(5, annee + "-01-01");
                    updateCumulRowQueryStmt.setString(6, annee + "-12-31");
                    updateCumulRowQueryStmt.setInt(7,rs.getInt("init"));
                    updateCumulRowQueryStmt.setInt(8,rs.getInt("qte"));
                    updateCumulRowQueryStmt.setInt(9,rs.getInt("expedie"));
                    updateCumulRowQueryStmt.setInt(10,rs.getInt("envoye"));
                    updateCumulRowQueryStmt.setInt(11,rs.getInt("cession"));
                    updateCumulRowQueryStmt.setInt(12,rs.getInt("init"));
                    updateCumulRowQueryStmt.setInt(13,rs.getInt("qte"));
                    updateCumulRowQueryStmt.setInt(14,rs.getInt("expedie"));
                    updateCumulRowQueryStmt.setInt(15,rs.getInt("envoye"));
                    updateCumulRowQueryStmt.setInt(16,rs.getInt("cession"));
                    updateCumulRowQueryStmt.executeUpdate();
                    rowsNum ++;
                    if (rowsNum % 1000 == 0) {
                        System.out.printf("%n%s Already Reloaded %d CUMUL rows from  campagne_fead table for year %s .",
                                LocalDateTime.now().format(formatter), rowsNum, annee);
                    }
                }
                System.out.printf("%n%s CalcFead Reloaded %d CUMUL rows from  campagne_fead table for year %s .", LocalDateTime.now().format(formatter), rowsNum, annee);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        //Maj réceptions par les assos
        query = "select m.id_article,o.birbcode as id_asso,round(sum(coalesce(quantite*1000/POIDS_UNITE,0)),0) as expedie from mvtasso m join organisations o on (o.id_dis=m.id_asso) " +
                " join articles a on (a.id_article=m.id_article) ";
        query += String.format(" where a.annee_fead= '%s'  and coalesce(o.birbcode,'')<>''  group by id_article,id_asso ", annee);
        executeQuery(con,query, rs -> {
            try {
                var rowsnum = 0;
                while (rs.next()) {
                    var insertStatement = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,expedie) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                            rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                            rs.getInt("expedie"));
                    insertStatement += String.format(" on duplicate key update expedie= %d",
                            rs.getInt("expedie"));
                    rowsnum += executeUpdateQuery(con,insertStatement);
                }
                System.out.printf("%n%s CalcFead Updated %d EXPEDIE rows from  mvtasso table for year %s .", LocalDateTime.now().format(formatter), rowsnum, annee);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


        //Maj stock assos

        query = "select m.id_article,o.birbcode as id_asso,round(sum(coalesce(quantite*1000/POIDS_UNITE,0)),0) as STOCK " +
                " from stoasso m join organisations o on (o.id_dis=m.id_asso) " +
                " join articles a on (a.id_article=m.id_article) ";
        query += String.format(" where a.annee_fead= '%s'  and coalesce(o.birbcode,'')<>''  group by id_article,id_asso ", annee);
        executeQuery(con,query, rs -> {
            try {
                var rowsNum = 0;
                while (rs.next()) {
                    var insertStatement = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,stock) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                            rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                            rs.getInt("stock"));
                    insertStatement += String.format(" on duplicate key update stock = %d",
                            rs.getInt("Stock"));
                    rowsNum += executeUpdateQuery(con,insertStatement);
                }

                System.out.printf("%n%s CalcFead Updated %d STOCK rows from  stoasso table for year %s .",
                        LocalDateTime.now().format(formatter), rowsNum, annee);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        //Maj en attente par les assos
        query = "select m.id_article,o.birbcode as id_asso" +
                ",round(sum(coalesce(quantite*1000/POIDS_UNITE,0)),0) as ATTENTE " +
                " from stoasso_prev m join organisations o on (o.id_dis=m.id_asso) " +
                " join articles a on (a.id_article=m.id_article) ";
        query += String.format(" where a.annee_fead= '%s' and status= 0 and coalesce(o.birbcode,'')<>''  group by id_article,id_asso ", annee);
        executeQuery(con,query, rs -> {
            try {
                var rowsNum = 0;
                while (rs.next()) {
                    var insertStatement = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,attente) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                            rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                            rs.getInt("attente"));
                    insertStatement += String.format(" on duplicate key update attente = %d",
                            rs.getInt("attente"));
                    rowsNum += executeUpdateQuery(con,insertStatement);
                }
                System.out.printf("%n%s CalcFead Updated %d attente rows from  stoasso_prev table for year %s .",
                        LocalDateTime.now().format(formatter), rowsNum, annee);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        //Maj refus par les assos
        query = "select m.id_article,o.birbcode as id_asso" +
                ",round(sum(coalesce(quantite*1000/POIDS_UNITE,0)),0) as refus " +
                " from stoasso_prev m join organisations o on (o.id_dis=m.id_asso) " +
                " join articles a on (a.id_article=m.id_article) ";
        query += String.format(" where a.annee_fead= '%s' and status=2  and coalesce(o.birbcode,'')<>''  group by id_article,id_asso ", annee);
        executeQuery(con,query, rs -> {
            try {
                var rowsNum = 0;
                while (rs.next()) {
                    var insertStatement = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,refus) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                            rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                            rs.getInt("refus"));
                    insertStatement += String.format(" on duplicate key update refus = %d",
                            rs.getInt("refus"));
                    rowsNum += executeUpdateQuery(con,insertStatement);
                }

                System.out.printf("%n%s CalcFead Updated %d refus rows from  stoasso_prev table for year %s .",
                        LocalDateTime.now().format(formatter), rowsNum, annee);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


    //Mise à jour des envoyés par les banques
    private void majEnvoye(String annee) throws Exception {
        String queryMajEnvoyeArticle = "insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,envoye) values(?,?,?,?,?,?,?)";
        queryMajEnvoyeArticle += " on duplicate key update envoye = ?";
        PreparedStatement majEnvoyeArticleStmt =con.prepareStatement(queryMajEnvoyeArticle);
        String query = "select m.id_article,m.id_asso,a.fead_pds_unit,o.birbcode,sum(coalesce(m.quantite * -1,0)) as poids,round(sum(coalesce(m.quantite * -1,0)) *1000/a.fead_pds_unit ,0) as nbunit,o.lien_depot " +
                " from mouvements m " +
                " join articles a on (a.id_article=m.id_article) " +
                " join organisations o on (o.id_dis=m.id_asso) " +
                " left join depots d on (d.id_depot=m.id_asso) ";
        query += String.format(" where a.annee_fead=%s and d.id_depot is null  group by o.birbcode,a.id_article order by o.birbcode,a.id_article ", annee);
        executeQuery(con,query, rs1 -> {
            try {
               int count = 0;
               while (rs1.next()){
                    this.majEnvoyeArticle(majEnvoyeArticleStmt ,annee, rs1.getString("id_article"), rs1.getString("birbcode"), rs1.getInt("nbunit"));
                    count++;
                    if (count % 1000 == 0) {
                        System.out.printf("%n%s Already Processed %d articles from  mouvements table for year %s .",
                                LocalDateTime.now().format(formatter), count, annee);
                    }
                }
                System.out.printf("%n%s CalcFead Processed %d records from  mouvements table for year %s .",
                        LocalDateTime.now().format(formatter), count, annee);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void majEnvoyeArticle(PreparedStatement majEnvoyeArticleStmt,String annee, String article, String asso, int qte) throws Exception {

        String query = String.format("select * from campagne_fead where annee=%s and campagne=%s and id_asso=%s and id_article=%s  order by debut", annee, annee, asso, article);
        executeQuery(con,query, rs1 -> {
            try {
                int localQte = qte;
                while (rs1.next() && qte > 0) {
                    int dispo = rs1.getInt("qte");
                    int envoye = rs1.getInt("envoye");
                    // Alain getRow() returns row number of current row
                    // Alain old statement if(dispo>=qte || getRowCount(rs1)==rs1.getRow()) {
                    if (dispo >= localQte) {
                        envoye = localQte;
                        localQte = 0;
                    } else {
                        envoye = dispo;
                        localQte -= dispo;
                    }
                    majEnvoyeArticleStmt.setString(1,annee);
                    majEnvoyeArticleStmt.setString(2,annee);
                    majEnvoyeArticleStmt.setString(3,rs1.getString("id_article"));
                    majEnvoyeArticleStmt.setString(4,rs1.getString("id_asso"));
                    majEnvoyeArticleStmt.setString(5,annee + "-01-01");
                    majEnvoyeArticleStmt.setString(6,annee + "-12-31");
                    majEnvoyeArticleStmt.setInt(7,rs1.getInt("envoye"));
                    majEnvoyeArticleStmt.setInt(8,rs1.getInt("envoye"));
                    majEnvoyeArticleStmt.executeUpdate();

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }


    private void majCessions(String annee) throws Exception {

        String query = "select asso_origin,o1.birbcode as asso1,asso_destination,o2.birbcode as asso2,a.id_article,  coalesce(quantite,0) as qte " +
                " from cession_fead f  join organisations o1 on (o1.id_dis=f.asso_origin)  join organisations o2 on (o2.id_dis=f.asso_destination) " +
                " join articles a on (a.id_article=f.id_article) ";
        query += String.format(" where annee=%s order by id_cession ", annee);
        executeQuery(con,query, rs -> {
            try {
                int count = 0;
                while (rs.next()) {
                    this.majUneCessionOrigin(annee, rs.getString("asso1"), rs.getString("asso2"), rs.getString("id_article"), rs.getInt("qte"));
                        count++;
                        if (count % 100 == 0) {
                            System.out.printf("%n%s Already Processed %d association article cessions from  cession_fead table for year %s .",
                                    LocalDateTime.now().format(formatter), count, annee);
                        }
                 }
                 System.out.printf("%n%s CalcFead Processed %d association cessions from  cession_fead table for year %s .",
                            LocalDateTime.now().format(formatter),  count, annee);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void majUneCessionOrigin(String annee, String origin, String destination, String article, int qte) throws Exception {
        String query = String.format("select fead_ucart from articles where id_article=%s", article).trim();
        executeQuery(con,query, rs -> {
            try {
                int ucart;

                if (rs.next()) {
                    ucart = rs.getInt("fead_ucart");

                } else {
                    return;
                }
                if ((qte != 0) && (ucart != 0)) {
                    String exped = "0";
                    var secondQuery = String.format("select * from campagne_fead where annee=%s and id_article=%s and id_asso=%s and campagne<>'CUMUL' and coalesce(init,0)-coalesce(envoye,0)+coalesce(cession,0)>0 ", annee, article, origin);
                    AtomicInteger atomicQte =new AtomicInteger(qte * ucart);
                    executeQuery(con,secondQuery, rs1 -> {
                        try {
                            String finalQuery = "";
                            int intQte= atomicQte.get();
                            int intInit;
                            int intEnvoye;
                            int intDispo;
                            int intCession;
                            String campagne;
                            String debut;
                            String idArticle;
                            String idAsso;
                            String fin;


                            int numRowsUpdated = 0;

                            if (rs1.next()) {
                                intInit = rs1.getInt("init");
                                intCession= rs1.getInt("cession");
                                intEnvoye = rs1.getInt("envoye");
                                intDispo = intInit + intCession - intEnvoye;
                                campagne = rs1.getString("campagne");
                                debut = rs1.getString("debut");
                                fin = rs1.getString("fin");

                                if (intDispo > 0) {
                                    if (intDispo - intQte >= 0f) {
                                        intDispo -= intQte;
                                        intCession += intQte;
                                        majUneCessionsDestination(annee, campagne, article, destination, debut, fin, intQte);
                                        intQte = 0;
                                    } else {
                                        majUneCessionsDestination(annee, campagne, article, destination, debut, fin, intDispo);
                                        // intQte -= intDispo; not needed cfr original source
                                        intCession -= intDispo;
                                    }
                                    finalQuery = String.format("update campagne_fead set cession = %d  where annee = '%s' and campagne = '%s' and id_article = '%s' and id_asso = '%s' and debut = '%s' and fin = '%s' ",
                                       intCession, annee,annee,article, origin, annee + "-01-01", annee + "-12-31" );
                                    executeUpdateQuery(con,finalQuery);
                                    numRowsUpdated++;

                                }



                            }

                           // System.out.printf("%n%s CalcFead Updated %d cession values in rows for article %s for year %s .",
                           //         LocalDateTime.now().format(formatter),  numRowsCession.get(), article, annee);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    });

                }
               // return numRowsCession.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
    private void majUneCessionsDestination(String annee, String campagne, String article, String asso, String debut, String fin, int intQte) throws Exception {
        // AtomicInteger numRowsCession =new AtomicInteger(); // need Class as effectively final to increase value in lambda expression
        String query = String.format("select * from campagne_fead where annee=%s and campagne=%s and id_article=%s and id_asso=%s and debut='%s' and fin='%s'",
                annee, campagne, article, asso, debut, fin);
        executeQuery(con,query, rs -> {
            try {
                if (!rs.next()) {
                    int intCession = intQte;
                    String insertStatement = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,cession,qte) values(%s,%s,'%s','%s','%s','%s',%d,%d)",
                            annee,annee, article, asso, annee + "-01-01", annee + "-12-31",
                            intCession, intQte);
                    executeUpdateQuery(con,insertStatement);
                }
                else {
                    int cession = intQte + rs.getInt("cession");
                    int qte = intQte + rs.getInt("qte");

                    String updateStatement = String.format("update campagne_fead set cession = %d , qte = %d  where annee = '%s' and campagne = '%s' and id_article = '%s' and id_asso = '%s' and debut = '%s' and fin = '%s' ",
                            cession, qte, annee, annee, article, asso, annee + "-01-01", annee + "-12-31");
                    executeUpdateQuery(con,updateStatement);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }



}
