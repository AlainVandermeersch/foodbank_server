package foodbank.it.web.dto;


public class TUserDto {
    private String idUser;
    
    private String userName;
    
    private String idCompany;
    
    private int idOrg;
     
    private String idLanguage;
     
    private int lienBat;
    
    private int actif;
     
    private String rights;
    
    private String password;
     
    private String depot;
     
    private int droit1;
     
    private String email;
    
    private int gestBen;
    
    private int gestInv;
     
    private int gestFead;
     
    private int gestAsso;
     
    private int gestCpas;
     
    private int gestMemb;
     
    private int gestDon;
     
    private int lienBanque;
     
    private int lienCpas;
     
    

    /** Default constructor. */
    protected TUserDto() {
      
    }
    public TUserDto(String idUser, String userName, String idCompany, int idOrg, String idLanguage, int lienBat, int actif, String rights, String password, String depot, int droit1, String email, int gestBen, int gestInv, int gestFead, int gestAsso,
        int gestCpas, int gestMemb, int gestDon, int lienBanque, int lienCpas) {
        super();
        this.idUser = idUser;
        this.userName = userName;
        this.idCompany = idCompany;
        this.idOrg = idOrg;
        this.idLanguage = idLanguage;
        this.lienBat = lienBat;
        this.actif = actif;
        this.rights = rights;
        this.password = password;
        this.depot = depot;
        this.droit1 = droit1;
        this.email = email;
        this.gestBen = gestBen;
        this.gestInv = gestInv;
        this.gestFead = gestFead;
        this.gestAsso = gestAsso;
        this.gestCpas = gestCpas;
        this.gestMemb = gestMemb;
        this.gestDon = gestDon;
        this.lienBanque = lienBanque;
        this.lienCpas = lienCpas;
        
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }

    public int getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(int idOrg) {
        this.idOrg = idOrg;
    }

    public String getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(String idLanguage) {
        this.idLanguage = idLanguage;
    }

    public int getLienBat() {
        return lienBat;
    }

    public void setLienBat(int lienBat) {
        this.lienBat = lienBat;
    }

    public int getActif() {
        return actif;
    }

    public void setActif(int actif) {
        this.actif = actif;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public int getDroit1() {
        return droit1;
    }

    public void setDroit1(int droit1) {
        this.droit1 = droit1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGestBen() {
        return gestBen;
    }

    public void setGestBen(int gestBen) {
        this.gestBen = gestBen;
    }

    public int getGestInv() {
        return gestInv;
    }

    public void setGestInv(int gestInv) {
        this.gestInv = gestInv;
    }

    public int getGestFead() {
        return gestFead;
    }

    public void setGestFead(int gestFead) {
        this.gestFead = gestFead;
    }

    public int getGestAsso() {
        return gestAsso;
    }

    public void setGestAsso(int gestAsso) {
        this.gestAsso = gestAsso;
    }

    public int getGestCpas() {
        return gestCpas;
    }

    public void setGestCpas(int gestCpas) {
        this.gestCpas = gestCpas;
    }

    public int getGestMemb() {
        return gestMemb;
    }

    public void setGestMemb(int gestMemb) {
        this.gestMemb = gestMemb;
    }

    public int getGestDon() {
        return gestDon;
    }

    public void setGestDon(int gestDon) {
        this.gestDon = gestDon;
    }

    public int getLienBanque() {
        return lienBanque;
    }

    public void setLienBanque(int lienBanque) {
        this.lienBanque = lienBanque;
    }

    public int getLienCpas() {
        return lienCpas;
    }

    public void setLienCpas(int lienCpas) {
        this.lienCpas = lienCpas;
    }
   

   
    

}
