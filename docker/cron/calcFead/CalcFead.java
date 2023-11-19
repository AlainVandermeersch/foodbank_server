import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Consumer;
import java.util.concurrent.atomic.AtomicInteger;

public class CalcFead {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private Connection con;
    private int nbCumulRows;
    private int nbExpedieRows;
    private int nbStockRows;
    private int nbAttenteRows;
    private int nbRefusRows;
    private int nbCessionRows;
    private int nbEnvoyeRows;
    private boolean success;

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

    private int executeUpdateQuery(Connection con, String query) {
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

    private void executeQuery(Connection con, String query, Consumer<ResultSet> resultSetConsumer) {
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
        LocalDateTime beginTime = LocalDateTime.now();
        this.nbCumulRows = 0;
        this.nbExpedieRows = 0;
        this.nbStockRows = 0;
        this.nbAttenteRows = 0;
        this.nbRefusRows = 0;
        this.nbCessionRows = 0;
        this.nbEnvoyeRows = 0;
        this.success = true;
        String query = String.format("delete from campagne_fead where campagne='CUMUL' and annee = '%s'", annee);
        int numrows = executeUpdateQuery(con, query);
        System.out.printf("%n%s CalcFead Deleted %d CUMUL rows from  campagne_fead table for year %s .",
                LocalDateTime.now().format(formatter), numrows, annee);

        query = String.format("update campagne_fead set envoye=0,cession=0,qte=init where annee = '%s'", annee);
        numrows = executeUpdateQuery(con, query);
        System.out.printf("%n%s CalcFead Reinitialized %d rows from  campagne_fead table for year %s .",
                LocalDateTime.now().format(formatter), numrows, annee);

        query = "select asso_origin,o1.birbcode as asso1,asso_destination,o2.birbcode as asso2,a.id_article,  coalesce(quantite,0) as qte " +
                " from cession_fead f  join organisations o1 on (o1.id_dis=f.asso_origin)  join organisations o2 on (o2.id_dis=f.asso_destination) " +
                " join articles a on (a.id_article=f.id_article) ";
        query += String.format(" where annee=%s order by id_cession ", annee);
        executeQuery(con, query, rs -> {
            try {
                int count = 0;
                String queryUpdateCession = "update campagne_fead set cession = cession + ?  where annee = ? and campagne = ? and id_article = ? and id_asso = ? and Tournee = 0 ";
                PreparedStatement updateCessionStmt = con.prepareStatement(queryUpdateCession);
                while (rs.next()) {
                    updateCessionStmt.setInt(1, -rs.getInt("qte"));
                    updateCessionStmt.setString(2, annee);
                    updateCessionStmt.setString(3, annee);
                    updateCessionStmt.setString(4, rs.getString("id_article"));
                    updateCessionStmt.setString(5, rs.getString("asso1"));
                    updateCessionStmt.executeUpdate();
                    updateCessionStmt.setInt(1, rs.getInt("qte"));
                    updateCessionStmt.setString(5, rs.getString("asso2"));
                    updateCessionStmt.executeUpdate();
                }
                System.out.printf("%n%s CalcFead Processed %d association cessions from  cession_fead table for year %s .",
                        LocalDateTime.now().format(formatter), count, annee);
                this.nbCessionRows = count;

            } catch (Exception e) {
                this.success = false;
                e.printStackTrace();
            }
        });

        query = String.format("update campagne_fead set qte=init+cession where annee= '%s' and campagne<>'cumul'", annee);
        numrows = executeUpdateQuery(con, query);
        System.out.printf("%n%s CalcFead Updated %d rows with qte = qte=init+cession for non-cumul from  campagne_fead table for year %s .",
                LocalDateTime.now().format(formatter), numrows, annee);

        query = "select m.id_article,m.id_asso,a.fead_pds_unit,o.birbcode,sum(coalesce(m.quantite * -1,0)) as poids,round(sum(coalesce(m.quantite * -1,0)) *1000/a.fead_pds_unit ,0) as nbunit,o.lien_depot " +
                " from mouvements m " +
                " join articles a on (a.id_article=m.id_article) " +
                " join organisations o on (o.id_dis=m.id_asso) " +
                " left join depots d on (d.id_depot=m.id_asso) ";
        query += String.format(" where a.annee_fead=%s and d.id_depot is null  group by o.birbcode,a.id_article order by o.birbcode,a.id_article ", annee);
        executeQuery(con,query, rs -> {
            try {
                int count = 0;
                while (rs.next()){
                    String queryUpdateEnvoye = "update campagne_fead set envoye = envoye + ?  where annee = ? and campagne = ? and id_article = ? and id_asso = ? and Tournee = 0 ";
                    PreparedStatement updateEnvoyeStmt =con.prepareStatement(queryUpdateEnvoye);
                    while (rs.next()) {
                        updateEnvoyeStmt.setInt(1,rs.getInt("nbunit"));
                        updateEnvoyeStmt.setString(2,annee);
                        updateEnvoyeStmt.setString(3,annee);
                        updateEnvoyeStmt.setString(4,rs.getString("id_article"));
                        updateEnvoyeStmt.setString(5,rs.getString("birbcode"));
                        updateEnvoyeStmt.executeUpdate();
                    }
                    count++;
                    if (count % 1000 == 0) {
                        System.out.printf("%n%s Already Processed %d articles from  mouvements table for year %s .",
                                LocalDateTime.now().format(formatter), count, annee);
                    }
                }
                System.out.printf("%n%s CalcFead Processed %d records from  mouvements table for year %s .",
                        LocalDateTime.now().format(formatter), count, annee);
                this.nbEnvoyeRows = count;
            } catch (Exception e) {
                this.success = false;
                e.printStackTrace();
            }
        });


        query = String.format("update campagne_fead set qte=init+cession  where annee= '%s'", annee);
        numrows = executeUpdateQuery(con, query);
        System.out.printf("%n%s CalcFead Updated %d rows with qte = qte=init+cession for year and cumul from  campagne_fead table for year %s .",
                LocalDateTime.now().format(formatter), numrows, annee);

        query = String.format("select annee,'CUMUL' as campagne,id_article,id_asso,sum(coalesce(init,0)) as init,sum(coalesce(qte,0)) as qte,sum(coalesce(expedie,0)) as expedie,sum(coalesce(envoye,0)) as envoye,sum(coalesce(cession,0)) as cession from campagne_fead where campagne<>'cumul' and annee='%s' group by annee,id_article,id_asso", annee);
        executeQuery(con, query, rs -> {
            try {
                String updateCumulRowQuery = "insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,init,qte,expedie,envoye,cession)" +
                        " values(?,?,?,?,?,?,?,?,?,?,?)  on duplicate key update init = ?, qte = ?, expedie= ?, envoye = ?, cession = ?";
                PreparedStatement updateCumulRowQueryStmt = con.prepareStatement(updateCumulRowQuery);
                var rowsNum = 0;
                while (rs.next()) {
                    updateCumulRowQueryStmt.setString(1, annee);
                    updateCumulRowQueryStmt.setString(2, "CUMUL");
                    updateCumulRowQueryStmt.setString(3, rs.getString("id_article"));
                    updateCumulRowQueryStmt.setString(4, rs.getString("id_asso"));
                    updateCumulRowQueryStmt.setString(5, annee + "-01-01");
                    updateCumulRowQueryStmt.setString(6, annee + "-12-31");
                    updateCumulRowQueryStmt.setInt(7, rs.getInt("init"));
                    updateCumulRowQueryStmt.setInt(8, rs.getInt("qte"));
                    updateCumulRowQueryStmt.setInt(9, rs.getInt("expedie"));
                    updateCumulRowQueryStmt.setInt(10, rs.getInt("envoye"));
                    updateCumulRowQueryStmt.setInt(11, rs.getInt("cession"));
                    updateCumulRowQueryStmt.setInt(12, rs.getInt("init"));
                    updateCumulRowQueryStmt.setInt(13, rs.getInt("qte"));
                    updateCumulRowQueryStmt.setInt(14, rs.getInt("expedie"));
                    updateCumulRowQueryStmt.setInt(15, rs.getInt("envoye"));
                    updateCumulRowQueryStmt.setInt(16, rs.getInt("cession"));
                    updateCumulRowQueryStmt.executeUpdate();
                    rowsNum++;
                    if (rowsNum % 1000 == 0) {
                        System.out.printf("%n%s Already Reloaded %d CUMUL rows from  campagne_fead table for year %s .",
                                LocalDateTime.now().format(formatter), rowsNum, annee);
                    }
                }
                System.out.printf("%n%s CalcFead Reloaded %d CUMUL rows from  campagne_fead table for year %s .", LocalDateTime.now().format(formatter), rowsNum, annee);
                this.nbCumulRows = rowsNum;
            } catch (SQLException e) {
                this.success = false;
                e.printStackTrace();
            }
        });
        //Maj réceptions par les assos

        query = "select m.id_article,o.birbcode as id_asso,round(sum(coalesce(quantite*1000/POIDS_UNITE,0)),0) as expedie from mvtasso m join organisations o on (o.id_dis=m.id_asso) " +
                " join articles a on (a.id_article=m.id_article) ";
        query += String.format(" where a.annee_fead= '%s' and id_mouv ='REC' and coalesce(o.birbcode,'')<>''  group by id_article,id_asso ", annee);
        executeQuery(con, query, rs -> {
            try {
                var rowsnum = 0;
                while (rs.next()) {
                    var insertStatement = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,expedie) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                            rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                            rs.getInt("expedie"));
                    insertStatement += String.format(" on duplicate key update expedie= %d",
                            rs.getInt("expedie"));
                    rowsnum += executeUpdateQuery(con, insertStatement);
                }
                System.out.printf("%n%s CalcFead Updated %d EXPEDIE rows from  mvtasso table for year %s .", LocalDateTime.now().format(formatter), rowsnum, annee);
                this.nbExpedieRows = rowsnum;
            } catch (SQLException e) {
                this.success = false;
                e.printStackTrace();
            }
        });


        //Maj stock assos

        query = "select m.id_article,o.birbcode as id_asso,round(sum(coalesce(quantite*1000/POIDS_UNITE,0)),0) as stock " +
                " from stoasso m join organisations o on (o.id_dis=m.id_asso) " +
                " join articles a on (a.id_article=m.id_article) ";
        query += String.format(" where a.annee_fead= '%s'  and coalesce(o.birbcode,'')<>''  group by id_article,id_asso ", annee);
        executeQuery(con, query, rs -> {
            try {
                var rowsNum = 0;
                while (rs.next()) {
                    var insertStatement = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,stock) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                            rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                            rs.getInt("stock"));
                    insertStatement += String.format(" on duplicate key update stock = %d",
                            rs.getInt("Stock"));
                    rowsNum += executeUpdateQuery(con, insertStatement);
                }

                System.out.printf("%n%s CalcFead Updated %d STOCK rows from  stoasso table for year %s .",
                        LocalDateTime.now().format(formatter), rowsNum, annee);
                this.nbStockRows = rowsNum;
            } catch (SQLException e) {
                this.success = false;
                e.printStackTrace();
            }
        });

        //Maj en attente par les assos
        query = "select m.id_article,o.birbcode as id_asso" +
                ",round(sum(coalesce(quantite*1000/POIDS_UNITE,0)),0) as ATTENTE " +
                " from stoasso_prev m join organisations o on (o.id_dis=m.id_asso) " +
                " join articles a on (a.id_article=m.id_article) ";
        query += String.format(" where a.annee_fead= '%s' and status= 0 and coalesce(o.birbcode,'')<>''  group by id_article,id_asso ", annee);

        executeQuery(con, query, rs -> {
            try {
                var rowsNum = 0;
                String insertAttenteQuery = "insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,attente) values(?,?,?,?,?,?,?)" +
                        " on duplicate key update attente = ?";
                PreparedStatement insertAttenteStmt = con.prepareStatement(insertAttenteQuery);
                while (rs.next()) {
                    insertAttenteStmt.setString(1, annee);
                    insertAttenteStmt.setString(2, "CUMUL");
                    insertAttenteStmt.setString(3, rs.getString("id_article"));
                    insertAttenteStmt.setString(4, rs.getString("id_asso"));
                    insertAttenteStmt.setString(5, annee + "-01-01");
                    insertAttenteStmt.setString(6, annee + "-12-31");
                    insertAttenteStmt.setInt(7, rs.getInt("attente"));
                    insertAttenteStmt.setInt(8, rs.getInt("attente"));
                    insertAttenteStmt.executeUpdate();
                    rowsNum++;
                }
                System.out.printf("%n%s CalcFead Updated %d attente rows from  stoasso_prev table for year %s .",
                        LocalDateTime.now().format(formatter), rowsNum, annee);
                this.nbAttenteRows = rowsNum;
            } catch (SQLException e) {
                this.success = false;
                e.printStackTrace();
            }
        });
        //Maj refus par les assos
        query = "select m.id_article,o.birbcode as id_asso" +
                ",round(sum(coalesce(quantite*1000/POIDS_UNITE,0)),0) as refus " +
                " from stoasso_prev m join organisations o on (o.id_dis=m.id_asso) " +
                " join articles a on (a.id_article=m.id_article) ";
        query += String.format(" where a.annee_fead= '%s' and status=2  and coalesce(o.birbcode,'')<>''  group by id_article,id_asso ", annee);
        executeQuery(con, query, rs -> {
            try {
                var rowsNum = 0;
                while (rs.next()) {
                    var insertStatement = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,refus) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                            rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                            rs.getInt("refus"));
                    insertStatement += String.format(" on duplicate key update refus = %d",
                            rs.getInt("refus"));
                    rowsNum += executeUpdateQuery(con, insertStatement);
                }
                this.nbRefusRows = rowsNum;
                System.out.printf("%n%s CalcFead Updated %d refus rows from  stoasso_prev table for year %s .",
                        LocalDateTime.now().format(formatter), rowsNum, annee);
            } catch (SQLException e) {
                this.success = false;
                e.printStackTrace();
            }
        });
        LocalDateTime endTime = LocalDateTime.now();
        int elapsedMinutes = (int) ChronoUnit.MINUTES.between(beginTime, endTime);
        String ok = "OK";
        if (!success) {
            ok = "Failed";
        }
        String summaryMessage = String.format("%s year %s in %d minutes", ok, annee, elapsedMinutes);
        System.out.printf("%n%s CalcFead summary: %s",
                LocalDateTime.now().format(formatter), summaryMessage);
        query = String.format("INSERT INTO `auditchanges` (user,bank_id,id_dis,entity,entity_key,action) VALUES ('avdmadmin',10,0,'calcfead','%s','Update') ", summaryMessage);
        executeUpdateQuery(con, query);
        summaryMessage = String.format("Updates: %d Cumul,%d Sent,%d Cessions",
                nbCumulRows, nbEnvoyeRows, nbCessionRows);
        System.out.printf("%n%s CalcFead details: %s",
                LocalDateTime.now().format(formatter), summaryMessage);
        query = String.format("INSERT INTO `auditchanges` (user,bank_id,id_dis,entity,entity_key,action) VALUES ('avdmadmin',10,0,'calcfead','%s','Update') ", summaryMessage);
        executeUpdateQuery(con, query);
        summaryMessage = String.format("%d Recv,%d Stock %d Attente,%d Refus",
                nbExpedieRows, nbStockRows, nbAttenteRows, nbRefusRows);
        System.out.printf("%n%s CalcFead details: %s",
                LocalDateTime.now().format(formatter), summaryMessage);
        query = String.format("INSERT INTO `auditchanges` (user,bank_id,id_dis,entity,entity_key,action) VALUES ('avdmadmin',10,0,'calcfead','%s','Update') ", summaryMessage);
        executeUpdateQuery(con, query);
    }
}
