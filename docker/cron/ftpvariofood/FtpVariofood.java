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
import java.io.IOException;
import org.apache.commons.net.ftp.*;
public class FtpVariofood {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private Connection con;
    
    private boolean success;

    public static void main(String[] args) {
        FtpVariofood myFtpVariofoodObj = new FtpVariofood();
        System.out.printf("%n%s FtpVariofood Started", LocalDateTime.now().format(formatter));

        // System.out.println("connection String: " + connectionString + " " + "user: " + user + "password: " + password );
        try {
            myFtpVariofoodObj.con = myFtpVariofoodObj.getDbConnection();
            myFtpVariofoodObj.runFtpVariofood();

        } catch (SQLException e) {
            System.out.printf("%n%s FtpVariofood a SQL Error Occurred", LocalDateTime.now().format(formatter));
            e.printStackTrace();
        } catch (Exception e) {
            System.out.printf("%n%s FtpVariofood a System Error Occurred", LocalDateTime.now().format(formatter));
            System.out.println(e);
        } finally {
            System.out.printf("%n%s FtpVariofood Ended%n", LocalDateTime.now().format(formatter));
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
            System.out.printf("%n%] FtpVariofood a SQL Error Occurred: %s\n", LocalDateTime.now().format(formatter), ex.getMessage());
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
                System.out.printf("%n%] FtpVariofood a SQL Error Occurred: %s\n", LocalDateTime.now().format(formatter), ex.getMessage());
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            System.out.printf("%n%] FtpVariofood a SQL Error Occurred: %s\n", LocalDateTime.now().format(formatter), ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void runFtpVariofood() throws Exception {
        LocalDateTime beginTime = LocalDateTime.now();
        System.out.printf("%n%] FtpVariofood Started \n", LocalDateTime.now().format(formatter));

        try {
            FTPClient ftpClient = new FTPClient();
            String ftpHost = System.getenv("FTP_HOST");
            String ftpUser = System.getenv("FTP_USER");
            String ftpPassword = System.getenv("FTP_PASSWORD");
            String ftpPort = "22";
            // ftpHost = "91.183.169.176";
            // ftpUser = "sftpfodmiprod";
            // ftpPassWord = "Uk7rxZdXCoNU";
            ftpClient.connect(ftpHost, ftpPort);
            if (ftpClient && ftpClient.login(ftpUser, ftpPassword)) {
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                String path ="/";
                for (FTPFile ftpFile : ftpclient.listFiles(path)) {
                    System.out.printf("%n%] FtpVariofood Processing File %s \n", LocalDateTime.now().format(formatter), ftpFile.getName());
                   String  query = String.format("INSERT INTO `auditchanges` (user,bank_id,id_dis,entity,entity_key,action) VALUES ('avdmadmin',10,0,'ftpVarioFood','%s','FtpDownLoad') ", ftpFile.getName());
                    executeUpdateQuery(con, query);
                }
            } else {
                System.out.printf("%n%] FtpVariofood a FTP Login failed.\n", LocalDateTime.now().format(formatter));
            }
            System.out.printf("%n%] FtpVariofood Ended \n", LocalDateTime.now().format(formatter));
        } catch (Exception ex) {
            System.out.printf("%n%] FtpVariofood a FTP Error Occurred: %s\n", LocalDateTime.now().format(formatter), ex.getMessage());
            ex.printStackTrace();
        }
    }
}
