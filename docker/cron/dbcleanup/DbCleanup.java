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

public class DbCleanup {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private Connection con;
    private boolean success;

    public static void main(String[] args) {
        DbCleanup myDbCleanupObj = new DbCleanup();
        System.out.printf("%n%s DbCleanup Started", LocalDateTime.now().format(formatter));

        // System.out.println("connection String: " + connectionString + " " + "user: " + user + "password: " + password );
        try {
            myDbCleanupObj.con = myDbCleanupObj.getDbConnection();
            myDbCleanupObj.DbCleanup();
        } catch (SQLException e) {
            System.out.printf("%n%s DbCleanup a SQL Error Occurred", LocalDateTime.now().format(formatter));
            e.printStackTrace();
        } catch (Exception e) {
            System.out.printf("%n%s DbCleanup a System Error Occurred", LocalDateTime.now().format(formatter));
            System.out.println(e);
        } finally {
            System.out.printf("%n%s DbCleanup Ended%n", LocalDateTime.now().format(formatter));
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
            System.out.printf("%n%] DbCleanup a SQL Error Occurred: %s\n", LocalDateTime.now().format(formatter), ex.getMessage());
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
                System.out.printf("%n%] DbCleanup a SQL Error Occurred: %s\n", LocalDateTime.now().format(formatter), ex.getMessage());
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            System.out.printf("%n%] DbCleanup a SQL Error Occurred: %s\n", LocalDateTime.now().format(formatter), ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void DbCleanup() throws Exception {
        LocalDateTime beginTime = LocalDateTime.now();
        this.success = true;
        int nbDemandeRows = 0;
        int nbPreparationRows = 0;
        int nbStockPrevisionRows = 0;
        String query = "delete from demandes_lignes where demande_id in (select demande_id from demandes where STATUS = 1 and DATEDIFF(CURRENT_DATE, DATE) > 180)";
        int numrows = executeUpdateQuery(con, query);
        System.out.printf("%n%s DbCleanup Deleted %d rows from  demandes_lignes table with date request older than 180 days",
                LocalDateTime.now().format(formatter), numrows);
        query = "delete from demandes where STATUS = 1 and DATEDIFF(CURRENT_DATE, DATE) > 180";
        numrows = executeUpdateQuery(con, query);
        System.out.printf("%n%s DbCleanup Deleted %d rows from  demandes table with date request older than 180 days",
                LocalDateTime.now().format(formatter), numrows);
        nbDemandeRows += numrows;

        query = "delete from preparation_lignes where preparation_id in (select preparation_id from preparations where STATUS = 1 and DATEDIFF(CURRENT_DATE, DATE_PREPA) > 60)";
        numrows = executeUpdateQuery(con, query);
        System.out.printf("%n%s DbCleanup Deleted %d rows from  preparation_lignes table with date_prepa older than 60 days",
                LocalDateTime.now().format(formatter), numrows);
        query = "delete from preparations where STATUS = 1 and DATEDIFF(CURRENT_DATE, DATE_PREPA) > 60";
        numrows = executeUpdateQuery(con, query);
        System.out.printf("%n%s DbCleanup Deleted %d rows from  preparations table with date_prepa older than 60 days",
                LocalDateTime.now().format(formatter), numrows);
        nbPreparationRows += numrows;

        query = "delete from stock_prev where STATUS = 1 ";
        numrows = executeUpdateQuery(con, query);
        System.out.printf("%n%s DbCleanup Deleted %d rows from  stock_prev table",
                LocalDateTime.now().format(formatter), numrows);
        nbStockPrevisionRows += numrows;

        LocalDateTime endTime = LocalDateTime.now();
        int elapsedMinutes = (int) ChronoUnit.MINUTES.between(beginTime, endTime);
        String ok = "OK";
        if (!success) {
            ok = "Failed";
        }
        String summaryMessage = String.format("%s in %d minutes", ok, elapsedMinutes);
        System.out.printf("%n%s DbCleanup summary: %s",
                LocalDateTime.now().format(formatter), summaryMessage);
        query = String.format("INSERT INTO `auditchanges` (user,bank_id,id_dis,entity,entity_key,action) VALUES ('avdmadmin',10,0,'DbCleanup','%s','Update') ", summaryMessage);
        executeUpdateQuery(con, query);
        summaryMessage = String.format("Deletes: %d Demandes,%d Preparations,%d Previsions",
        nbPreparationRows, nbPreparationRows, nbStockPrevisionRows);
        System.out.printf("%n%s DbCleanup details: %s",
                LocalDateTime.now().format(formatter), summaryMessage);
        query = String.format("INSERT INTO `auditchanges` (user,bank_id,id_dis,entity,entity_key,action) VALUES ('avdmadmin',10,0,'DbCleanup','%s','Update') ", summaryMessage);
        executeUpdateQuery(con, query);

    }
}
