import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class calcFead {
    public static void main(String[] args) {
        System.out.println("CalcFead Starting!");
        String host= System.getenv("MYSQL_HOST");
        String user =System.getenv("MYSQL_USER");
        String password =System.getenv("MYSQL_PASSWORD");
        String database =System.getenv("MYSQL_DATABASE");
        String annee = "2022";
        String connectionString =  String.format("jdbc:mysql://%s:3306/%s", host, database);
        System.out.println("connection String: " + connectionString + " " + "user: " + user + "password: " + password );
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(connectionString,user,password);
            Statement stmt=con.createStatement();
            String query= String.format("select annee,'CUMUL' as campagne,id_article,id_asso,concat(annee,'-01-01') as debut, concat(annee,'-12-31') as fin,sum(coalesce(init,0)) as init,sum(coalesce(qte,0)) as qte,sum(coalesce(expedie,0)) as expedie,sum(coalesce(envoye,0)) as envoye,sum(coalesce(cession,0)) as cession from campagne_fead where campagne<>'cumul' and annee=%s group by annee,id_article,id_asso" , annee);
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()) {
                System.out.println(rs.getString("annee") + "  " + rs.getString("id_article") + "  " + rs.getString("id_asso"));
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }finally {
            System.out.println("CalcFead ended!");
    }
}
}