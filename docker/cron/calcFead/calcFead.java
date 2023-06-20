import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class calcFead {
     private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static void main(String[] args) {
        System.out.printf("%n%s CalcFead Started", LocalDateTime.now().format(formatter));
        String host = System.getenv("MYSQL_HOST");
        String user = System.getenv("MYSQL_USER");
        String password = System.getenv("MYSQL_PASSWORD");
        String database = System.getenv("MYSQL_DATABASE");
        String connectionString =  String.format("jdbc:mysql://%s:3306/%s", host, database);
        // System.out.println("connection String: " + connectionString + " " + "user: " + user + "password: " + password );
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(connectionString,user,password);
            CalcFeadOneYear(con,"2022");
            con.close();
        }catch(SQLException e){
            System.out.printf("%n%s CalcFead a SQL Error Occurred", LocalDateTime.now().format(formatter));
                    e.printStackTrace();
        }
        catch(Exception e){
            System.out.printf("%n%s CalcFead a System Error Occurred", LocalDateTime.now().format(formatter));
            System.out.println(e);
        }finally {
            System.out.printf("%n%s CalcFead Ended", LocalDateTime.now().format(formatter));
        }
    }


 private static void CalcFeadOneYear(Connection con, String annee) throws Exception {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_UPDATABLE);
            String query= String.format("delete from campagne_fead where campagne='CUMUL' and annee = '%s'", annee );
            int numrows = stmt.executeUpdate(query);
            System.out.printf("%n%s CalcFead Deleted %d CUMUL rows from  campagne_fead table for year %s .",
                    LocalDateTime.now().format(formatter),numrows, annee );

            query= String.format("update campagne_fead set envoye=0,cession=0,qte=init where annee = '%s'", annee );
            numrows = stmt.executeUpdate(query);
            System.out.printf("%n%s CalcFead Reinitialized %d rows from  campagne_fead table for year %s .",
                    LocalDateTime.now().format(formatter),numrows, annee );

            majCessions(con,annee);

            query= String.format("update campagne_fead set qte=init+cession where annee= '%s' and campagne<>'cumul'" ,annee);
            numrows = stmt.executeUpdate(query);
            System.out.printf("%n%s CalcFead Updated %d rows with qte = qte=init+cession for non-cumul from  campagne_fead table for year %s .",
                    LocalDateTime.now().format(formatter),numrows, annee );

            majEnvoye(con,annee);

            query= String.format("update campagne_fead set qte=init+cession  where annee= '%s'",annee);
            numrows = stmt.executeUpdate(query);
            System.out.printf("%n%s CalcFead Updated %d rows with qte = qte=init+cession for year and cumul from  campagne_fead table for year %s .",
                    LocalDateTime.now().format(formatter),numrows, annee );

            query= String.format("select annee,'CUMUL' as campagne,id_article,id_asso,sum(coalesce(init,0)) as init,sum(coalesce(qte,0)) as qte,sum(coalesce(expedie,0)) as expedie,sum(coalesce(envoye,0)) as envoye,sum(coalesce(cession,0)) as cession from campagne_fead where campagne<>'cumul' and annee='%s' group by annee,id_article,id_asso" , annee);
            ResultSet rs=stmt.executeQuery(query);
            numrows = updateCampagneFeadWithResultSet(stmt,rs,"INITCUMUL",annee);
            System.out.printf("%n%s CalcFead Reloaded %d CUMUL rows from  campagne_fead table for year %s .",
                    LocalDateTime.now().format(formatter),numrows, annee );
            //Maj réceptions par les assos
            query = "select m.id_article,o.birbcode as id_asso,round(sum(coalesce(quantite*1000/POIDS_UNITE,0)),0) as expedie from mvtasso m join organisations o on (o.id_dis=m.id_asso) " +
             " join articles a on (a.id_article=m.id_article) ";
            query +=  String.format(" where a.annee_fead= '%s'  and coalesce(o.birbcode,'')<>''  group by id_article,id_asso ",annee);
                      rs=stmt.executeQuery(query);
            numrows = updateCampagneFeadWithResultSet(stmt,rs,"EXPEDIE",annee);
            System.out.printf("%n%s CalcFead Updated %d EXPEDIE rows from  mvtasso table for year %s .",
                    LocalDateTime.now().format(formatter),numrows, annee );


            //Maj stock assos

            query= "select m.id_article,o.birbcode as id_asso,round(sum(coalesce(quantite*1000/POIDS_UNITE,0)),0) as STOCK " +
                    " from stoasso m join organisations o on (o.id_dis=m.id_asso) " +
                    " join articles a on (a.id_article=m.id_article) " ;
            query +=  String.format(" where a.annee_fead= '%s'  and coalesce(o.birbcode,'')<>''  group by id_article,id_asso ",annee);
            rs=stmt.executeQuery(query);
            numrows = updateCampagneFeadWithResultSet(stmt,rs,"STOCK",annee);
            System.out.printf("%n%s CalcFead Updated %d STOCK rows from  stoasso table for year %s .",
                    LocalDateTime.now().format(formatter),numrows, annee );

            //Maj en attente par les assos
            query= "select m.id_article,o.birbcode as id_asso" +
                    ",round(sum(coalesce(quantite*1000/POIDS_UNITE,0)),0) as ATTENTE " +
                    " from stoasso_prev m join organisations o on (o.id_dis=m.id_asso) " +
                    " join articles a on (a.id_article=m.id_article) " ;
            query +=  String.format(" where a.annee_fead= '%s' and status= 0 and coalesce(o.birbcode,'')<>''  group by id_article,id_asso ",annee);
            rs=stmt.executeQuery(query);
            numrows = updateCampagneFeadWithResultSet(stmt,rs,"ATTENTE",annee);
            System.out.printf("%n%s CalcFead Updated %d attente rows from  stoasso_prev table for year %s .",
                    LocalDateTime.now().format(formatter),numrows, annee );

            //Maj refus par les assos
            query= "select m.id_article,o.birbcode as id_asso" +
                    ",round(sum(coalesce(quantite*1000/POIDS_UNITE,0)),0) as refus " +
                    " from stoasso_prev m join organisations o on (o.id_dis=m.id_asso) " +
                    " join articles a on (a.id_article=m.id_article) ";
            query +=  String.format(" where a.annee_fead= '%s' and status=2  and coalesce(o.birbcode,'')<>''  group by id_article,id_asso ",  annee );
            rs=stmt.executeQuery(query);
            numrows = updateCampagneFeadWithResultSet(stmt,rs,"REFUS",annee);
            System.out.printf("%n%s CalcFead Updated %d refus rows from  stoasso_prev table for year %s .",
                    LocalDateTime.now().format(formatter),numrows, annee );



    }

    private static int updateCampagneFeadWithResultSet(Statement stmt,ResultSet rs,String item,String annee) throws Exception {
            int numrows = 0;
            String query = null;
            while (rs.next()) {
                switch (item) {
                    case "INITCUMUL":
                        query = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,init,qte,expedie,envoye,cession) values(annee,'CUMUL',%s,'%s','%s','%s','%s',%d,%d,%d,%d)",
                                rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                                rs.getInt("init"), rs.getInt("qte"), rs.getInt("expedie"), rs.getInt("cession"));
                        query += String.format(" on duplicate key update init = %d, qte = %d, expedie= %d, envoye = %d, cession = %d",
                                rs.getInt("init"), rs.getInt("qte"), rs.getInt("expedie"), rs.getInt("cession"));
                        break;

                    case "EXPEDIE":
                        query = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,expedie) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                                rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                               rs.getInt("expedie"), rs.getInt("cession"));
                        query += String.format(" on duplicate key update expedie= %d",
                               rs.getInt("expedie"));
                        break;
                    case "STOCK":
                        query = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,stock) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                                rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                                rs.getInt("stock"));
                        query += String.format(" on duplicate key update stock = %d",
                                rs.getInt("Stock"));
                        break;
                    case "ATTENTE":
                        query = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,attente) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                                rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                                rs.getInt("attente"));
                        query += String.format(" on duplicate key update attente = %d",
                                rs.getInt("attente"));
                        break;
                    case "REFUS":
                        query = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,refus) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                                rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                                rs.getInt("refus"));
                        query += String.format(" on duplicate key update refus = %d",
                                rs.getInt("refus"));
                        break;
                    case "ENVOYE":
                        query = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,envoye) values(annee,annee,'%s','%s','%s','%s',%d)",
                                rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                                rs.getInt("envoye"));
                        query += String.format(" on duplicate key update envoye = %d",
                                rs.getInt("envoye"));
                        break;
                    case "CESSION":
                        query = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,cession) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                                rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                                rs.getInt("cession"));
                        query += String.format(" on duplicate key update cession = %d",
                                rs.getInt("cession"));
                        break;
                    case "CESSIONQTE":
                        query = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,cession,qte) values(annee,'CUMUL','%s','%s','%s','%s',%d,%d)",
                                rs.getString("id_article"), rs.getString("id_asso"), annee + "-01-01", annee + "-12-31",
                                rs.getInt("cession"),rs.getInt("qte"));
                        query += String.format(" on duplicate key update cession = %d, qte = %d",
                                rs.getInt("cession"), rs.getInt("qte"));
                        break;
                    case "TOURNEE":
                        query = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,tournee) values(annee,%s,'%s','%s','%s','%s',%d)",
                                rs.getString("campagne"), rs.getString("id_article"), rs.getString("id_asso"),  rs.getString("debut"),  rs.getString("fin"),
                                rs.getInt("tournee"));
                        query += String.format(" on duplicate key update tournee = %d",
                                rs.getInt("tournee"));
                        System.out.printf("%n%s CalcFead  tournee query '%s'",
                                LocalDateTime.now().format(formatter),query);
                        break;
                    default:
                        System.out.printf("%n%s CalcFead  unknown update option '%s'",
                                LocalDateTime.now().format(formatter), item);
                }               
                numrows += stmt.executeUpdate(query);
            }
            return numrows;
   }
        //Mise à jour des envoyés par les banques
        private static void majEnvoye(Connection con,String annee) throws Exception {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_UPDATABLE);
            String query ="select m.id_article,m.id_asso,a.fead_pds_unit,o.birbcode,sum(coalesce(m.quantite * -1,0)) as poids,round(sum(coalesce(m.quantite * -1,0)) *1000/a.fead_pds_unit ,0) as nbunit,o.lien_depot "+
                    " from mouvements m "+
                    " join articles a on (a.id_article=m.id_article) "+
                    " join organisations o on (o.id_dis=m.id_asso) "+
                    " left join depots d on (d.id_depot=m.id_asso) ";
            query+= String.format(" where a.annee_fead=%s and d.id_depot is null  group by o.birbcode,a.id_article order by o.birbcode,a.id_article ",annee);
            System.out.println(query);
            ResultSet rs=stmt.executeQuery(query);
           int qte = 0;
            String article="";
            String asso="";
            ResultSet rs1;
            int dispo =0;
            Float fqte;

            while (rs.next()) {
               qte=rs.getInt("nbunit");
               article=rs.getString("id_article");
               asso=rs.getString("birbcode");

                query = String.format("select * from campagne_fead where annee=%s and campagne=%s and id_asso=%s and id_article=%s  order by debut",annee,annee,asso,article);
                rs1=stmt.executeQuery(query);
                while(rs1.next() && qte>0) {
                    dispo=rs1.getInt("qte");
                    // Alain getRow() returns row number of current row
                   // Alain old statement if(dispo>=qte || getRowCount(rs1)==rs1.getRow()) {
                    if(dispo>=qte) {
                        rs1.updateInt("envoye", qte);
                        rs1.updateRow();
                        qte=0;
                    }
                    else {
                        rs1.updateInt("envoye", dispo);
                        rs1.updateRow();
                        qte-=dispo;
                    }
                }
                rs1.beforeFirst();
                int numrows = updateCampagneFeadWithResultSet(stmt,rs1,"ENVOYE",annee);
                System.out.printf("%n%s CalcFead Updated %d envoye rows from  mouvements table for year %s .",
                        LocalDateTime.now().format(formatter),numrows, annee );
            }
        }



       private static void majCessions(Connection con,String annee) throws Exception {
            //réinitialisation qte=init
           Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_UPDATABLE);
            if(annee.startsWith("20")) {
                //creation des assos manquantes (destination)
                String query ="select a.annee_fead as annee,a.annee_fead as campagne,c.id_article,o.birbcode as id_asso,'' as debut ,'' as fin "+
                        ",0 as qte,0 as expedie,0 as stock,0 as attente,0 as refus,1 as tournee,0 as init,0 as envoye "+
                        " from cession_fead c "+
                        " join organisations o on (o.id_dis=c.asso_destination) "+
                        " join articles a on (a.id_article=c.id_article) "+
                        " left join campagne_fead f on ( f.id_asso=o.birbcode and f.id_article=c.id_article and f.campagne<>'cumul') ";
                query += String.format(" where c.annee=%s and f.annee is null and o.birbcode>0" ,annee);
                ResultSet rs=stmt.executeQuery(query);
                while(rs.next()) {
                    addCampagneRow(con,annee, rs.getString("id_article"), rs.getString("id_asso"),false);
                }

                //creation des assos manquantes (origine)
                query = "select a.annee_fead as annee,a.annee_fead as campagne,c.id_article,o.birbcode as id_asso, concat(a.annee_fead,'-01-01') as debut ,concat(a.annee_fead,'-12-31') as fin "+
                        ",0 as qte,0 as expedie,0 as stock,0 as attente,0 as refus,1 as tournee,0 as init,0 as envoye "+
                        " from cession_fead c "+
                        " join organisations o on (o.id_dis=c.asso_origin) "+
                        " join articles a on (a.id_article=c.id_article) "+
                        " left join campagne_fead f on ( f.id_asso=o.birbcode and f.id_article=c.id_article and f.campagne<>'CUMUL') ";
                query += String.format(" where c.annee=%s and f.annee is null and o.birbcode>0" ,annee);
                rs=stmt.executeQuery(query);
                while(rs.next()) {
                   addCampagneRow(con,annee, rs.getString("id_article"), rs.getString("id_asso"),false);
                }

            }



            String query = "select asso_origin,o1.birbcode as asso1,asso_destination,o2.birbcode as asso2,a.id_article,  coalesce(quantite,0) as qte "+
                    " from cession_fead f  join organisations o1 on (o1.id_dis=f.asso_origin)  join organisations o2 on (o2.id_dis=f.asso_destination) "+
                    " join articles a on (a.id_article=f.id_article) ";
            query+= String.format(" where annee=%s order by id_cession ",annee);
            ResultSet rs=stmt.executeQuery(query);
            int qte=0;
            String article="";
            String origin="";
            String destination="";

            while(rs.next()) {
                article=rs.getString("id_article");
                qte=rs.getInt("qte");
                origin=rs.getString("asso1");
                destination=rs.getString("asso2");

                majUneCessionOrigin(con, annee, origin, destination, article, qte);
            }

        }
        private static void majUneCessionOrigin(Connection con, String annee,String origin,String destination,String article,int qte) throws Exception {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_UPDATABLE);
            int ucart= 0;
            String query=String.format("select fead_ucart from articles where id_article=%s", article).trim();
            ResultSet rs=stmt.executeQuery(query);
            if(rs.next())
            {
                ucart =rs.getInt("fead_ucart");
            }
            if((qte!=0) && (ucart != 0)) {

                int intQte=qte * ucart;

                String exped="0";
                query=String.format("select * from campagne_fead where annee=%s and id_article=%s and id_asso=%s and campagne<>'CUMUL' and coalesce(init,0)-coalesce(envoye,0)+coalesce(cession,0)>0 ",annee , article,origin);
                ResultSet rs1=stmt.executeQuery(query);
                while(rs1.next() && intQte>0) {
                  int  intInit = rs1.getInt("init");
                  int  intCession = rs1.getInt("cession");
                  int  intEnvoye = rs1.getInt("envoye");
                  int  intDispo = intInit + intCession - intEnvoye;

                  if(intDispo > 0) {
                        if(intDispo-intQte>=0f) {
                            intDispo -= intQte;
                            intCession += intQte;
                            rs1.updateInt("cession", intCession);
                            rs1.updateRow();
                            majUneCessionsDestination(con,annee, rs1.getString("campagne"),article,destination, rs1.getString("debut"),rs1.getString("fin"),String.valueOf(intQte));
                            intQte=0;
                        }
                        else {
                            majUneCessionsDestination(con,annee, rs1.getString("campagne"),article,destination, rs1.getString("debut"),rs1.getString("fin"),String.valueOf(intDispo));
                            // intQte -= intDispo; not needed cfr original source
                            intCession -=intDispo;
                            rs1.updateInt("cession",intCession);
                            rs1.updateRow();
                        }
                    }
                }
                rs1.beforeFirst();
                int numrows = updateCampagneFeadWithResultSet(stmt,rs1,"CESSION",annee);
                System.out.printf("%n%s CalcFead Updated %d cession values in rows for article %s for year %s .",
                        LocalDateTime.now().format(formatter),numrows, article,annee );
            }

        }

        private static void majUneCessionsDestination(Connection con,String annee, String campagne ,String article,String asso, String debut,String fin, String qte) throws Exception {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_UPDATABLE);
            String query = String.format("select * from campagne_fead where annee=%s and campagne=%s and id_article=%s and id_asso=%s and debut='%s' and fin='%s'", annee, campagne ,article,asso, debut,fin);
            ResultSet rs=stmt.executeQuery(query);
            if(!rs.next()) {
                int tournee = 1;
                addResultSetRow(rs,annee,campagne,article,asso,debut,fin,tournee);
            }
            int intCession= rs.getInt("cession");
            int intQte= Integer.parseInt(qte);
            rs.updateInt("cession", intCession + intQte);
            rs.updateInt("qte", rs.getInt("init") + intCession);
            rs.updateRow();
            rs.beforeFirst();
            int numrows = updateCampagneFeadWithResultSet(stmt,rs,"CESSIONQTE",annee);
            System.out.printf("%n%s CalcFead Updated %d cessionqte values in rows for article %s for year %s .",
                    LocalDateTime.now().format(formatter),numrows, article,annee );
        }

        /**
         * Ajout d'une ligne dans campagne_fead si la ligne de cumul est inconnue
         */
        private static void addCampagneRow(Connection con,String annee, String article,String asso,boolean genCumulRow) throws Exception {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_UPDATABLE);
            //génération des lignes

            ResultSet rs=stmt.executeQuery("select * from campagne_fead where false");
            String query = String.format("select distinct debut,fin,tournee from campagne_fead where id_article=%s and campagne<>'cumul' and tournee<>0",article);
            ResultSet rs2=stmt.executeQuery(query);
            while(rs2.next()) {
               addResultSetRow(rs,annee,annee,article,asso,rs2.getString("debut"),rs2.getString("fin"),rs2.getInt("tournee"));               
            }
            rs.beforeFirst();
            int numrows = updateCampagneFeadWithResultSet(stmt,rs,"TOURNEE",annee);
            System.out.printf("%n%s CalcFead Updated %d tournee values in rows for article %s for year %s .",
                    LocalDateTime.now().format(formatter),numrows, article,annee );
            if(genCumulRow) {
                rs=stmt.executeQuery("select * from campagne_fead where false");
                query = String.format("select debut,fin from campagne_fead where id_article=%s and campagne='CUMUL' AND annee=%s limit 1",article,annee);
                rs2=stmt.executeQuery(query);
                if(rs2.next()) {
                    int tournee = 0;
                    addResultSetRow(rs,annee,"CUMUL",article,asso,rs2.getString("debut"),rs2.getString("fin"),tournee);
                    rs.beforeFirst();
                    numrows = updateCampagneFeadWithResultSet(stmt,rs,"TOURNEE",annee);
                    System.out.printf("%n%s CalcFead Updated %d tournee values in CUMUL rows for article %s for year %s .",
                            LocalDateTime.now().format(formatter),numrows, article,annee );
                }
            }
        }
        private static void addResultSetRow(ResultSet rs,String annee, String campagne,String article,String asso,String debut, String fin, int tournee) throws Exception {
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
            // rs.moveToCurrentRow(); Alain todo - check if logic requires this
        }

}
