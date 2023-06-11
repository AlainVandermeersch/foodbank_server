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

            String query= String.format("delete from campagne_fead where campagne='CUMUL' and annee = '%s'", annee );
            int numrows = stmt.executeUpdate(query);
            System.out.printf("Deleted %d CUMUL rows from  campagne_fead table for year %s .",numrows, annee );

            query= String.format("update campagne_fead set envoye=0,cession=0,qte=init where annee = '%s'", annee );
            numrows = stmt.executeUpdate(query);
            System.out.printf("Reinitialized %d rows from  campagne_fead table for year %s .",numrows, annee );

            majCessions(stmt,annee);

            query= String.format("update campagne_fead set qte=init+cession where annee= '%s' and campagne<>'cumul'" ,annee);
            numrows = stmt.executeUpdate(query);
            System.out.printf("Updated %d rows with qte = qte=init+cession for non-cumul from  campagne_fead table for year %s .",numrows, annee );

            majEnvoye(stmt,annee);

            query= String.format("update campagne_fead set qte=init+cession  where annee= '%s'",annee);
            numrows = stmt.executeUpdate(query);
            System.out.printf("Updated %d rows with qte = qte=init+cession for year and cumul from  campagne_fead table for year %s .",numrows, annee );

            //Maj réceptions par les assos
            query= String.format("select annee,id_article,id_asso,sum(coalesce(init,0)) as init,sum(coalesce(qte,0)) as qte,sum(coalesce(expedie,0)) as expedie,sum(coalesce(envoye,0)) as envoye,sum(coalesce(cession,0)) as cession from campagne_fead where campagne<>'cumul' and annee='%s' group by annee,id_article,id_asso" , annee);
            ResultSet rs=stmt.executeQuery(query);
            numrows = updateCampagneFeadWithResultSet(stmt,rs,'EXPEDIE');
            System.out.printf("Updated %d EXPEDIE rows from  stoasso table for year %s .",numrows, annee );

            System.out.printf("Reloaded %d CUMUL rows from  campagne_fead table for year %s .",numrows, annee );
            //Maj stock assos
            query= "select m.id_article,o.birbcode as id_asso,round(sum(coalesce(quantite*1000/POIDS_UNITE,0)),0) as STOCK " +
                    " from stoasso m join organisations o on (o.id_dis=m.id_asso) " +
                    " join articles a on (a.id_article=m.id_article) " ;
            query +=  String.format(" where a.annee_fead= '%s'  and coalesce(o.birbcode,'')<>''  group by id_article,id_asso ",annee);
            ResultSet rs=stmt.executeQuery(query);
            numrows = updateCampagneFeadWithResultSet(stmt,rs,'STOCK');
            System.out.printf("Updated %d STOCK rows from  stoasso table for year %s .",numrows, annee );

            //Maj en attente par les assos
            query= "select m.id_article,o.birbcode as id_asso" +
                    ",round(sum(coalesce(quantite*1000/POIDS_UNITE,0)),0) as ATTENTE " +
                    " from stoasso_prev m join organisations o on (o.id_dis=m.id_asso) " +
                    " join articles a on (a.id_article=m.id_article) " ;
            query +=  String.format(" where a.annee_fead= '%s'  and coalesce(o.birbcode,'')<>''  group by id_article,id_asso ",annee);
            ResultSet rs=stmt.executeQuery(query);
            numrows = updateCampagneFeadWithResultSet(stmt,rs,'ATTENTE');
            System.out.printf("Retrieved %d attente rows from  stoasso_prev table for year %s .",numrows, annee );

            //Maj refus par les assos
            query= "select m.id_article,o.birbcode as id_asso" +
                    ",round(sum(coalesce(quantite*1000/POIDS_UNITE,0)),0) as refus " +
                    " from stoasso_prev m join organisations o on (o.id_dis=m.id_asso) " +
                    " join articles a on (a.id_article=m.id_article) ";
            query +=  String.format(" where a.annee_fead= '%s' and status=2  and coalesce(o.birbcode,'')<>''  group by id_article,id_asso ",  annee );
            ResultSet rs=stmt.executeQuery(query);
            numrows = updateCampagneFeadWithResultSet(stmt,rs,'REFUS');
            System.out.printf("Retrieved %d refus rows from  stoasso_prev table for year %s .",numrows, annee );


            con.close();
        }catch(Exception e){
            System.out.println(e);
        }finally {
            System.out.println("CalcFead ended!");
    }

    int updateCampagneFeadWithResultSet(Statement stmt,ResultSet rs,String item) {
            int numrows = 0;
            String query = null;
            while (rs.next()) {
                switch (item) {
                    case 'EXPEDIE':
                        query = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,init,qte,expedie,envoye,cession) values(annee,'CUMUL','%s','%s','%s','%s',%d,%d,%d,%d)",
                                rs.getString("id_article"), rs.getString("id_asso"), annee + '-01-01', annee + '-12-31',
                                rs.getInt("init"), rs.getInt("qte"), rs.getInt("expedie"), rs.getInt("cession"));
                        query += String.format(" on duplicate key update init = %d, qte = %d, expedie= %d, envoye = %d, cession = %d",
                                rs.getInt("init"), rs.getInt("qte"), rs.getInt("expedie"), rs.getInt("cession"));
                        break;
                    case 'STOCK':
                        query = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,stock) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                                rs.getString("id_article"), rs.getString("id_asso"), annee + '-01-01', annee + '-12-31',
                                rs.getInt("stock"));
                        query += String.format(" on duplicate key update stock = %d",
                                rs.getInt("Stock"));
                        break;
                    case 'ATTENTE':
                        query = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,attente) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                                rs.getString("id_article"), rs.getString("id_asso"), annee + '-01-01', annee + '-12-31',
                                rs.getInt("attente"));
                        query += String.format(" on duplicate key update attente = %d",
                                rs.getInt("attente"));
                        break;
                    case 'REFUS':
                        query = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,refus) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                                rs.getString("id_article"), rs.getString("id_asso"), annee + '-01-01', annee + '-12-31',
                                rs.getInt("refus"));
                        query += String.format(" on duplicate key update refus = %d",
                                rs.getInt("refus"));
                        break;
                    case 'ENVOYE':
                        query = String.format("insert into campagne_fead(annee,campagne,id_article,id_asso,debut,fin,envoye) values(annee,'CUMUL','%s','%s','%s','%s',%d)",
                                rs.getString("id_article"), rs.getString("id_asso"), annee + '-01-01', annee + '-12-31',
                                rs.getInt("envoye"));
                        query += String.format(" on duplicate key update envoye = %d",
                                rs.getInt("envoye"));
                        break;
                    default:
                        System.out.printf("CalcFEAD unknown update option '%s'", item);
                }
                System.out.println(query);
                numrows += stmt.executeUpdate(query);
            }
            return numrows
   }
        //Mise à jour des envoyés par les banques
        public void majEnvoye(Statement stmt,String annee) {
            String query ="select m.id_article,m.id_asso,a.fead_pds_unit,o.birbcode,sum(coalesce(m.quantite * -1,0)) as poids,round(sum(coalesce(m.quantite * -1,0)) *1000/a.fead_pds_unit ,0) as nbunit,o.lien_depot "+
                    " from mouvements m "+
                    " join articles a on (a.id_article=m.id_article) "+
                    " join organisations o on (o.id_dis=m.id_asso) "+
                    " left join depots d on (d.id_depot=m.id_asso) ";
            query+= String.format(" where a.annee_fead="%s" and d.id_depot is null  group by o.birbcode,a.id_article order by o.birbcode,a.id_article ",annee);
            System.out.println(query);
            ResultSet rs=stmt.executeQuery(query);
            String qte="0";
            String article="";
            String asso="";
            ResultSet rs1;
            Float dispo;
            Float fqte;

            while (rs.next()) {
                qte=rs.getString("nbunit");
                article=rs.getString("id_article");
                asso=rs.getString("birbcode");
                fqte=Float.parseFloat(qte);
                query = String.format("select * from campagne_fead where annee=%s and campagne=%s and id_asso=%s and id_article=%s  order by debut",annee,annee,asso,article);
                ResultSet rs1=stmt.executeQuery(query);
                while(rs1.next() && fqte>0) {
                    dispo=Float.parseFloat(rs1.getString("qte"));
                    if(dispo>=fqte || rs1.getRowCount()==rs1.getCurrentRow()) {
                        rs1.setString("envoye", String.valueOf(fqte));
                        fqte=0f;
                    }
                    else {
                        rs1.setString("envoye", rs1.getString("qte"));
                        fqte-=dispo;
                    }
                }
                rs1.moveTo(0);
                numrows = updateCampagneFeadWithResultSet(stmt,rs,'ENVOYE');
            }
        }



        public void majCessions(Statement stmt,String annee) {
            //réinitialisation qte=init

            if(annee.startsWith("20")) {
                //creation des assos manquantes (destination)
                String query ="select a.annee_fead as annee,a.annee_fead as campagne,c.id_article,o.birbcode as id_asso,'' as debut ,'' as fin "+
                        ",0 as qte,0 as expedie,0 as stock,0 as attente,0 as refus,1 as tournee,0 as init,0 as envoye "+
                        " from cession_fead c "+
                        " join organisations o on (o.id_dis=c.asso_destination) "+
                        " join articles a on (a.id_article=c.id_article) "+
                        " left join campagne_fead f on ( f.id_asso=o.birbcode and f.id_article=c.id_article and f.campagne<>'cumul') ";
                query += String.format(" where c.annee=%s and f.annee is null and o.birbcode>0" ,annee);
                System.out.println(query);
                ResultSet rs=stmt.executeQuery(query);
                if(rs.getRowCount()>0) {
                    while(rs.next()) {
                        addCampagneRow(annee, rs.getString("id_article"), rs.getString("id_asso"),false);
                    }
                }

                //creation des assos manquantes (origine)
                String query = "select a.annee_fead as annee,a.annee_fead as campagne,c.id_article,o.birbcode as id_asso, concat(a.annee_fead,'-01-01') as debut ,concat(a.annee_fead,'-12-31') as fin "+
                        ",0 as qte,0 as expedie,0 as stock,0 as attente,0 as refus,1 as tournee,0 as init,0 as envoye "+
                        " from cession_fead c "+
                        " join organisations o on (o.id_dis=c.asso_origin) "+
                        " join articles a on (a.id_article=c.id_article) "+
                        " left join campagne_fead f on ( f.id_asso=o.birbcode and f.id_article=c.id_article and f.campagne<>'CUMUL') ";
                query += String.format(" where c.annee=%s and f.annee is null and o.birbcode>0" ,annee);
                System.out.println(query);
                rs=stmt.executeQuery(query);
                if(rs.getRowCount()>0) {
                    while(rs.next()) {
                        addCampagneRow(annee, rs.getString("id_article"), rs.getString("id_asso"),false);
                    }
                }
            }



            query = "select asso_origin,o1.birbcode as asso1,asso_destination,o2.birbcode as asso2,a.id_article,  coalesce(quantite,0) as qte "+
                    " from cession_fead f  join organisations o1 on (o1.id_dis=f.asso_origin)  join organisations o2 on (o2.id_dis=f.asso_destination) "+
                    " join articles a on (a.id_article=f.id_article) ";
            query+= String.format(" where annee=? order by id_cession ",annee);
            System.out.println(query);
            rs=stmt.executeQuery(query);
            String qte="";
            String article="";
            String origin="";
            String destination="";

            while(rs.next()) {
                article=rs.getString("id_article");
                qte=rs.getString("qte");
                origin=rs.getString("asso1");
                destination=rs.getString("asso2");
                System.out.println("cession origin="+ origin+" destination="+destination+" article="+article+" qte= "+qte);
                majUneCessionOrigin(stmt, annee, origin, destination, article, qte.trim());
            }
            System.out.println("fin maj cessions");
        }
        public void majUneCessionOrigin(Statement stmt, String annee,String origin,String destination,String article,String qte) {
            String ucart=Common.getValueOf("select fead_ucart from articles where id_article=?", article).trim();
            DatabaseUpdate db=new DatabaseUpdate();
            if(!qte.equals("0") && !qte.equals("") && !ucart.equals("")) {
                qte=Common.multiply(qte,ucart);
                Float fqte=Float.parseFloat(qte);
                String dispo;
                String exped="0";
                ResultSet rs1=Common.queryPS("select * from campagne_fead where annee=? and id_article=? and id_asso=? and campagne<>'CUMUL' and coalesce(init,0)-coalesce(envoye,0)+coalesce(cession,0)>0 ", new String[] {annee , article,origin});
                while(rs1.next() && fqte>0) {
                    dispo=Common.add(rs1.getString("init"),rs1.getString("cession"));
                    dispo=Common.substract(dispo,rs1.getString("envoye"));
                    if(!dispo.equals("") && !dispo.equals("0")) {
                        if(Float.parseFloat(dispo)-fqte>=0f) {

                            dispo=Common.substract(dispo, String.valueOf(fqte));
                            String cession=rs1.getString("cession");
                            cession=Common.add(cession, String.valueOf(fqte*-1));
                            rs1.setString("cession", cession);
                            majUneCessionsDestination(stmt,annee, rs1.getString("campagne"),article,destination, rs1.getString("debut"),rs1.getString("fin"),String.valueOf(fqte));
                            fqte=0f;
                        }
                        else {
                            majUneCessionsDestination(stmt,annee, rs1.getString("campagne"),article,destination, rs1.getString("debut"),rs1.getString("fin"),dispo);
                            fqte=fqte-Float.parseFloat(dispo);
                            rs1.setString("cession", Common.multiply(Common.substract(rs1.getString("cession"), dispo),"1"));
                        }
                    }
                }
                rs1.moveTo(0);
                db.update("campagne_fead", rs1);
            }

        }

        public void majUneCessionsDestination(Statement stmt,String annee, String campagne ,String article,String asso, String debut,String fin, String qte) {
            DatabaseUpdate db=new DatabaseUpdate();
            ResultSet rs=Common.queryPS("select * from campagne_fead where annee=? and campagne=? and id_article=? and id_asso=? and debut=? and fin=?", new String[] {annee, campagne ,article,asso, debut,fin});
            if(rs.getRowCount()==0) {
                rs.addRow(annee+" ;"+campagne+" ;"+article+" ;"+asso+" ;"+debut+" ;"+fin+" ;0; ;0;0;0;0;1;0;0;0");
            }
            rs.setString("cession", Common.add(rs.getString("cession"),qte));
            rs.setString("qte", Common.add(rs.getString("init"),rs.getString("cession")));
            rs.moveTo(0);
            db.update("campagne_fead", rs);
        }
        /**
         * Ajout d'une ligne dans campagne_fead si la ligne de cumul est inconnue
         */
        public void addCampagneRow(Statement stmt,String year, String article,String asso,boolean genCumulRow) {
            //génération des lignes
            DatabaseUpdate db= new DatabaseUpdate();
            ResultSet rs=Common.queryPS("select * from campagne_fead where false");
            ResultSet rs2=Common.queryPS("select distinct debut,fin,tournee from campagne_fead where id_article=? and campagne<>'cumul' and tournee<>0",article);
            while(rs2.next()) {
                rs.addRow(year+" ;"+year+" ;"+article+" ;"+asso+" ;"+rs2.getString("debut")+" ;"+rs2.getString("fin")+" ;0; ;0;0;0;0;" + rs2.getString("tournee") + " ;0;0;0;0");
            }
            rs.moveTo(0);
            db.update("campagne_fead",rs);
            if(genCumulRow) {
                rs=Common.queryPS("select * from campagne_fead where false");
                rs2=Common.queryPS("select debut,fin from campagne_fead where id_article=? and campagne='CUMUL' AND annee=? limit 1",new String[] {article,year});
                if(rs2.next()) {
                    rs.addRow(year+" ;CUMUL;"+article+" ;"+asso+" ;"+rs2.getString("debut")+" ;"+rs2.getString("fin")+" ;0; ;0;0;0;0;0;0;0;0");
                    rs.moveTo(0);
                    db.update("campagne_fead",rs);
                }
            }
        }
}
