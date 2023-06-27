import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.concurrent.atomic.AtomicInteger;

public class CalcFead {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        CalcFead myCalcFeadObj = new CalcFead();
        System.out.printf("%n%s CalcFead Started", LocalDateTime.now().format(formatter));

        // System.out.println("connection String: " + connectionString + " " + "user: " + user + "password: " + password );
        try {
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

    private int executeUpdateQuery(String query) {
        try (Connection con = this.getDbConnection();
             Statement statement = con.createStatement();) {
            return statement.executeUpdate(query);
        } catch (Exception ex) {
            System.out.printf("%n%] CalcFead a SQL Error Occurred: %s\n", LocalDateTime.now().format(formatter), ex.getMessage());
            ex.printStackTrace();
            return -1;
        }
    }

    private void executeQuery(String query, Consumer<ResultSet> resultSetConsumer) {
        try (Connection con = this.getDbConnection();
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
        int numrows = executeUpdateQuery(query);
        System.out.printf("%n%s CalcFead Deleted %d CUMUL rows from  campagne_fead table for year %s .",
                LocalDateTime.now().format(formatter), numrows, annee);

        query = String.format("update campagne_fead set envoye=0,cession=0,qte=init where annee = '%s'", annee);
        numrows = executeUpdateQuery(query);
        System.out.printf("%n%s CalcFead Reinitialized %d rows from  campagne_fead table for year %s .",
                LocalDateTime.now().format(formatter), numrows, annee);

        majCessions(annee);

        query = String.format("update campagne_fead set qte=init+cession where annee= '%s' and campagne<>'cumul'", annee);
        numrows = executeUpdateQuery(query);
        System.out.printf("%n%s CalcFead Updated %d rows with qte = qte=init+cession for non-cumul from  campagne_fead table for year %s .",
                LocalDateTime.now().format(formatter), numrows, annee);

        majEnvoye(annee);

        query = String.format("update campagne_fead set qte=init+cession  where annee= '%s'", annee);
        numrows = executeUpdateQuery(query);
        System.out.printf("%n%s CalcFead Updated %d rows with qte = qte=init+cession for year and cumul from  campagne_fead table for year %s .",
                LocalDateTime.now().format(formatter), numrows, annee);

        query = String.format("select annee,'CUMUL' as campagne,id_article,id_asso,sum(coalesce(init,0)) as init,sum(coalesce(qte,0)) as qte,sum(coalesce(expedie,0)) as expedie,sum(coalesce(envoye,0)) as envoye,sum(coalesce(cession,0)) as cession from campagne_fead where campagne<>'cumul' and annee='%s' group by annee,id_article,id_asso", annee);
        executeQuery(query, rs -> {
            try {
                var rowsNum = 0;
                while (rs.next()) {
                    var insertStatement = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,init,qte,expedie,envoye,cession) values(annee,'CUMUL',%s,'%s','%s','%s','%s',%d,%d,%d,%d)",
                            rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                            rs.getInt("init"), rs.getInt("qte"), rs.getInt("expedie"), rs.getInt("envoye"), rs.getInt("cession"));
                    insertStatement += String.format(" on duplicate key update init = %d, qte = %d, expedie= %d, envoye = %d, cession = %d",
                            rs.getInt("init"), rs.getInt("qte"), rs.getInt("expedie"), rs.getInt("envoye"), rs.getInt("cession"));

                    rowsNum += executeUpdateQuery(insertStatement);
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
        executeQuery(query, rs -> {
            try {
                var rowsnum = 0;
                while (rs.next()) {
                    var insertStatement = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,expedie) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                            rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                            rs.getInt("expedie"));
                    insertStatement += String.format(" on duplicate key update expedie= %d",
                            rs.getInt("expedie"));
                    rowsnum += executeUpdateQuery(insertStatement);
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
        executeQuery(query, rs -> {
            try {
                var rowsNum = 0;
                while (rs.next()) {
                    var insertStatement = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,stock) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                            rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                            rs.getInt("stock"));
                    insertStatement += String.format(" on duplicate key update stock = %d",
                            rs.getInt("Stock"));
                    rowsNum += executeUpdateQuery(insertStatement);
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
        executeQuery(query, rs -> {
            try {
                var rowsNum = 0;
                while (rs.next()) {
                    var insertStatement = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,attente) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                            rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                            rs.getInt("attente"));
                    insertStatement += String.format(" on duplicate key update attente = %d",
                            rs.getInt("attente"));
                    rowsNum += executeUpdateQuery(insertStatement);
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
        executeQuery(query, rs -> {
            try {
                var rowsNum = 0;
                while (rs.next()) {
                    var insertStatement = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,refus) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                            rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                            rs.getInt("refus"));
                    insertStatement += String.format(" on duplicate key update refus = %d",
                            rs.getInt("refus"));
                    rowsNum += executeUpdateQuery(insertStatement);
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
        AtomicInteger numrowsEnvoye=new AtomicInteger(); // need Class as effectively final to increase value in lambda expression
        String query = "select m.id_article,m.id_asso,a.fead_pds_unit,o.birbcode,sum(coalesce(m.quantite * -1,0)) as poids,round(sum(coalesce(m.quantite * -1,0)) *1000/a.fead_pds_unit ,0) as nbunit,o.lien_depot " +
                " from mouvements m " +
                " join articles a on (a.id_article=m.id_article) " +
                " join organisations o on (o.id_dis=m.id_asso) " +
                " left join depots d on (d.id_depot=m.id_asso) ";
        query += String.format(" where a.annee_fead=%s and d.id_depot is null  group by o.birbcode,a.id_article order by o.birbcode,a.id_article ", annee);
        executeQuery(query, rs1 -> {
            try {
                ArrayList<Integer> qtes = new ArrayList<>();
                ArrayList<String> articles = new ArrayList<>();
                ArrayList<String> assos = new ArrayList<>();
                while (rs1.next()) {
                    qtes.add(rs1.getInt("nbunit"));
                    articles.add(rs1.getString("id_article"));
                    assos.add(rs1.getString("birbcode"));
                }

                int count = 0;
                System.out.printf("%n%s CalcFead Will Process %d association articles from  mouvements table for year %s .",
                        LocalDateTime.now().format(formatter), qtes.size(), annee);
                while (qtes.size() > count) {
                    numrowsEnvoye.addAndGet(this.majEnvoyeArticle(annee, articles.get(count), assos.get(count), qtes.get(count)));
                    count++;
                    if (count % 100 == 0) {
                        System.out.printf("%n%s Already Processed %d articles from  mouvements table for year %s .",
                                LocalDateTime.now().format(formatter), count, annee);
                    }
                }
                System.out.printf("%n%s CalcFead Processed %d envoyes for %d articles from  mouvements table for year %s .",
                        LocalDateTime.now().format(formatter), numrowsEnvoye.get(), count, annee);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private int majEnvoyeArticle(String annee, String article, String asso, int qte) throws Exception {
        AtomicInteger numRowsEnvoyeArticle=new AtomicInteger(); // need Class as effectively final to increase value in lambda expression
        String query = String.format("select * from campagne_fead where annee=%s and campagne=%s and id_asso=%s and id_article=%s  order by debut", annee, annee, asso, article);
        executeQuery(query, rs1 -> {
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
                    var insert = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,envoye) values(annee,annee,'%s','%s','%s','%s',%d)",
                            rs1.getString("id_article"), rs1.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                            rs1.getInt("envoye"));
                    insert += String.format(" on duplicate key update envoye = %d",
                            envoye);
                    numRowsEnvoyeArticle.addAndGet(executeUpdateQuery(insert));

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return numRowsEnvoyeArticle.get();
    }


    private void majCessions(String annee) throws Exception {

        //réinitialisation qte=init
        AtomicInteger numRowsCession =new AtomicInteger(); // need Class as effectively final to increase value in lambda expression
        if (annee.startsWith("20")) {
            //creation des assos manquantes (destination)
            String query = "select a.annee_fead as annee,a.annee_fead as campagne,c.id_article,o.birbcode as id_asso,'' as debut ,'' as fin " +
                    ",0 as qte,0 as expedie,0 as stock,0 as attente,0 as refus,1 as tournee,0 as init,0 as envoye " +
                    " from cession_fead c " +
                    " join organisations o on (o.id_dis=c.asso_destination) " +
                    " join articles a on (a.id_article=c.id_article) " +
                    " left join campagne_fead f on ( f.id_asso=o.birbcode and f.id_article=c.id_article and f.campagne<>'cumul') ";
            query += String.format(" where c.annee=%s and f.annee is null and o.birbcode>0", annee);
            executeQuery(query, rs -> {
                try {
                    while (rs.next()) {
                        addCampagneRow(annee, rs.getString("id_article"), rs.getString("id_asso"), false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            //creation des assos manquantes (origine)
            var query2 = "select a.annee_fead as annee,a.annee_fead as campagne,c.id_article,o.birbcode as id_asso, concat(a.annee_fead,'-01-01') as debut ,concat(a.annee_fead,'-12-31') as fin " +
                    ",0 as qte,0 as expedie,0 as stock,0 as attente,0 as refus,1 as tournee,0 as init,0 as envoye " +
                    " from cession_fead c " +
                    " join organisations o on (o.id_dis=c.asso_origin) " +
                    " join articles a on (a.id_article=c.id_article) " +
                    " left join campagne_fead f on ( f.id_asso=o.birbcode and f.id_article=c.id_article and f.campagne<>'CUMUL') ";
            query2 += String.format(" where c.annee=%s and f.annee is null and o.birbcode>0", annee);
            executeQuery(query2, rs2 -> {
                try {
                    while (rs2.next()) {
                        addCampagneRow(annee, rs2.getString("id_article"), rs2.getString("id_asso"), false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }


        String query = "select asso_origin,o1.birbcode as asso1,asso_destination,o2.birbcode as asso2,a.id_article,  coalesce(quantite,0) as qte " +
                " from cession_fead f  join organisations o1 on (o1.id_dis=f.asso_origin)  join organisations o2 on (o2.id_dis=f.asso_destination) " +
                " join articles a on (a.id_article=f.id_article) ";
        query += String.format(" where annee=%s order by id_cession ", annee);
        executeQuery(query, rs -> {
            try {
                int qte = 0;
                String article = "";
                String origin = "";
                String destination = "";
                ArrayList<Integer> qtes = new ArrayList<Integer>();
                ArrayList<String> articles = new ArrayList<String>();
                ArrayList<String> origins = new ArrayList<String>();
                ArrayList<String> destinations = new ArrayList<String>();
                while (rs.next()) {
                    qtes.add(rs.getInt("qte"));
                    articles.add(rs.getString("id_article"));
                    origins.add(rs.getString("asso1"));
                    destinations.add(rs.getString("asso2"));
                }
                int count = 0;
                System.out.printf("%n%s CalcFead Will Process %d association article cessions from  cession_fead table for year %s .",
                        LocalDateTime.now().format(formatter), qtes.size(), annee);

                while (qtes.size() > count) {
                    numRowsCession.addAndGet(this.majUneCessionOrigin(annee, origins.get(count), destinations.get(count), articles.get(count), qtes.get(count)));
                        count++;
                        if (count % 100 == 0) {
                            System.out.printf("%n%s Already Processed %d association article cessions from  cession_fead table for year %s .",
                                    LocalDateTime.now().format(formatter), count, annee);
                        }
                 }
                 System.out.printf("%n%s CalcFead Processed %d association cessions  for %d association articles from  cession_fead table for year %s .",
                            LocalDateTime.now().format(formatter), numRowsCession.get(), count, annee);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private int majUneCessionOrigin(String annee, String origin, String destination, String article, int qte) throws Exception {
        AtomicInteger numRowsCession =new AtomicInteger(); // need Class as effectively final to increase value in lambda expression
        String query = String.format("select fead_ucart from articles where id_article=%s", article).trim();
        executeQuery(query, rs -> {
            try {
                int ucart;

                if (rs.next()) {
                    ucart = rs.getInt("fead_ucart");

                } else {
                    return;
                }
//                rs.close();

                if ((qte != 0) && (ucart != 0)) {
                    String exped = "0";
                    var secondQuery = String.format("select * from campagne_fead where annee=%s and id_article=%s and id_asso=%s and campagne<>'CUMUL' and coalesce(init,0)-coalesce(envoye,0)+coalesce(cession,0)>0 ", annee, article, origin);
                    int tempIntQte = qte * ucart;
                    executeQuery(secondQuery, rs1 -> {
                        try {
                            var intQte = tempIntQte;
                            String finalQuery = "";

                            int intInit;
                            int intCessionTemp;
                            int intEnvoye;
                            int intDispoTemp;
                            String campagne;
                            String debut;
                            String idArticle;
                            String idAsso;
                            String fin;
                            intInit = 0; // rs1.getInt("init"); // DOES NOT EXIST
                            intCessionTemp = rs1.getInt("cession");
                            intEnvoye = rs1.getInt("envoye");
                            intDispoTemp = intInit + intCessionTemp - intEnvoye;
                            campagne = rs1.getString("campagne");
                            debut = rs1.getString("debut");
                            fin = rs1.getString("fin");
                            idArticle = rs1.getString("id_article");
                            idAsso = rs1.getString("id_asso");

                            int numRowsUpdated = 0;

                            while (rs1.next()) {
                                int intCession = intCessionTemp;
                                int intDispo = intDispoTemp;
                                if (intQte > 0) {
                                    if (intDispo > 0) {
                                        if (intDispo - intQte >= 0f) {
                                            intDispo -= intQte;
                                            intCession += intQte;
                                            numRowsUpdated += majUneCessionsDestination(annee, campagne, article, destination, debut, fin, intQte);
                                            intQte = 0;
                                        } else {
                                            numRowsUpdated += majUneCessionsDestination(annee, campagne, article, destination, debut, fin, intDispo);
                                            // intQte -= intDispo; not needed cfr original source
                                            intCession -= intDispo;
                                        }
                                    }
                                }

                                finalQuery = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,cession) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                                        idArticle, idAsso, annee + "-01-01", annee + "-12-31", intCession);
                                finalQuery += String.format(" on duplicate key update cession = %d", intCession);
                                numRowsCession.addAndGet(executeUpdateQuery(finalQuery));
                            }

                            System.out.printf("%n%s CalcFead Updated %d cession values in rows for article %s for year %s .",
                                    LocalDateTime.now().format(formatter), numRowsUpdated, article, annee);
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
        return numRowsCession.get();
    }

    private int majUneCessionsDestination(String annee, String campagne, String article, String asso, String debut, String fin, int intQte) throws Exception {
        AtomicInteger numRowsCession =new AtomicInteger(); // need Class as effectively final to increase value in lambda expression
        String query = String.format("select * from campagne_fead where annee=%s and campagne=%s and id_article=%s and id_asso=%s and debut='%s' and fin='%s'", annee, campagne, article, asso, debut, fin);
        executeQuery(query, rs -> {
            try {
                if (!rs.next()) {
                    int tournee = 1;
                    addResultSetRow(rs, annee, campagne, article, asso, debut, fin, tournee);
                }
                int intCession = rs.getInt("cession");
                var insertStatement = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,cession,qte) values(annee,'CUMUL','%s','%s','%s','%s',%d,%d)",
                        rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                        intCession, intQte);
                insertStatement += String.format(" on duplicate key update cession = %d, qte = %d",
                        intCession, intQte);
                int numrows = executeUpdateQuery(insertStatement);

                System.out.printf("%n%s CalcFead Updated %d cessiondestination values in rows for article %s for year %s .",
                        LocalDateTime.now().format(formatter), numrows, article, annee);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return numRowsCession.get();
    }

    /**
     * Ajout d'une ligne dans campagne_fead si la ligne de cumul est inconnue
     */
    private void addCampagneRow(String annee, String article, String asso, boolean genCumulRow) throws Exception {

        //génération des lignes

        try (Connection con = this.getDbConnection();
             Statement stmt = con.createStatement();
             Statement stmt2 = con.createStatement();
             Statement stmt3 = con.createStatement();
             Statement stmt4 = con.createStatement()) {
            //génération des lignes
            //ResultSet rs = stmt.executeQuery("select * from campagne_fead where false"
            ResultSet rs = stmt.executeQuery("select * from campagne_fead where false");
            String query = String.format("select distinct debut,fin,tournee from campagne_fead where id_article=%s and campagne<>'cumul' and tournee<>0", article);
            ResultSet rs2 = stmt2.executeQuery(query);
            while (rs2.next()) {
                addResultSetRow(rs, annee, annee, article, asso, rs2.getString("debut"), rs2.getString("fin"), rs2.getInt("tournee"));
            }
            rs.beforeFirst();

            int numrows = 0;
            while (rs.next()) {
                query = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,tournee) values(annee,%s,'%s','%s','%s','%s',%d)",
                        rs.getString("campagne"), rs.getString("id_article"), rs.getString("id_asso"), rs.getString("debut"), rs.getString("fin"),
                        rs.getInt("tournee"));
                query += String.format(" on duplicate key update tournee = %d",
                        rs.getInt("tournee"));
                numrows += executeUpdateQuery(query);
            }

            System.out.printf("%n%s CalcFead Updated %d tournee values in rows for article %s for year %s .",
                    LocalDateTime.now().format(formatter), numrows, article, annee);
            if (genCumulRow) {
                var rs3 = stmt3.executeQuery("select * from campagne_fead where false");
                query = String.format("select debut,fin from campagne_fead where id_article=%s and campagne='CUMUL' AND annee=%s limit 1", article, annee);
                var rs4 = stmt.executeQuery(query);
                if (rs4.next()) {
                    int tournee = 0;
                    addResultSetRow(rs3, annee, "CUMUL", article, asso, rs4.getString("debut"), rs4.getString("fin"), tournee);

                    query = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,tournee) values(annee,%s,'%s','%s','%s','%s',%d)",
                            rs3.getString("campagne"), rs3.getString("id_article"), rs3.getString("id_asso"), rs3.getString("debut"), rs3.getString("fin"),
                            rs3.getInt("tournee"));
                    query += String.format(" on duplicate key update tournee = %d",
                            rs3.getInt("tournee"));
                    numrows = stmt.executeUpdate(query);

                    System.out.printf("%n%s CalcFead Updated %d tournee values in CUMUL rows for article %s for year %s .",
                            LocalDateTime.now().format(formatter), numrows, article, annee);
                }
            }
        }
    }

    private void addResultSetRow(ResultSet rs, String annee, String campagne, String article, String asso, String debut, String fin, int tournee) throws Exception {
        rs.moveToInsertRow();
        rs.updateString("ANNEE", annee);
        rs.updateString("CAMPAGNE", campagne);
        rs.updateString("ID_ARTICLE", article);
        rs.updateString("ID_ASSO", asso);
        rs.updateString("DEBUT", debut);
        rs.updateString("FIN", fin);
        rs.updateInt("TOURNEE", tournee);
        rs.updateInt("QTE", 0);
        rs.insertRow();
        rs.moveToCurrentRow();
    }

}
