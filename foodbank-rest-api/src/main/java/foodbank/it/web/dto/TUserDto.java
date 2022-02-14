package foodbank.it.web.dto;


public class TUserDto {
    private String idUser;
    
    private String userName;
    
    private String idCompany;
    
    private Integer idOrg;
     
    private String idLanguage;
     
    private int lienBat;
    
    private boolean actif;
     
    private String rights;
    
    private String password;
     
    private String depot;
     
    private boolean droit1;
     
    private String email;
    
    private boolean gestBen;
    
    private boolean gestInv;
     
    private boolean gestFead;
     
    private boolean gestAsso;
     
    private boolean gestCpas;
     
    private boolean gestMemb;
     
    private boolean gestDon;
     
    private Short lienBanque;
     
    private int lienCpas;
    
    private String societe; // derived field from organisation object
    private String membreNom; // derived fields from membre  object
    private String membrePrenom;
    private String membreEmail;
    private Short membreLangue;
    private long nbLogins;
    
    private Long  totalRecords;
     
    

    /** Default constructor. */
    protected TUserDto() {
      
    }
    public TUserDto(String idUser, String userName, String idCompany, Integer idOrg, String idLanguage, int lienBat, boolean actif, String rights, String password, String depot, 
    		boolean droit1, String email, boolean gestBen, boolean gestInv, boolean gestFead, boolean gestAsso,
        boolean gestCpas, boolean gestMemb, boolean gestDon, Short lienBanque, int lienCpas,String societe,String membreNom, String membrePrenom, String membreEmail, Short membreLangue, 
        long nbLogins,Long  totalRecords) {
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
        this.societe = societe;
        this.membreNom = membreNom;
        this.membrePrenom = membrePrenom;
        this.membreEmail = membreEmail;
        this.membreLangue = membreLangue;  
        this.nbLogins = nbLogins;
        this.totalRecords = totalRecords;
        
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

    public Integer getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(Integer idOrg) {
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

    public boolean getActif() {
        return actif;
    }

    public void setActif(boolean actif) {
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

    public boolean getDroit1() {
        return droit1;
    }

    public void setDroit1(boolean droit1) {
        this.droit1 = droit1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getGestBen() {
        return gestBen;
    }

    public void setGestBen(boolean gestBen) {
        this.gestBen = gestBen;
    }

    public boolean getGestInv() {
        return gestInv;
    }

    public void setGestInv(boolean gestInv) {
        this.gestInv = gestInv;
    }

    public boolean getGestFead() {
        return gestFead;
    }

    public void setGestFead(boolean gestFead) {
        this.gestFead = gestFead;
    }

    public boolean getGestAsso() {
        return gestAsso;
    }

    public void setGestAsso(boolean gestAsso) {
        this.gestAsso = gestAsso;
    }

    public boolean getGestCpas() {
        return gestCpas;
    }

    public void setGestCpas(boolean gestCpas) {
        this.gestCpas = gestCpas;
    }

    public boolean getGestMemb() {
        return gestMemb;
    }

    public void setGestMemb(boolean gestMemb) {
        this.gestMemb = gestMemb;
    }

    public boolean getGestDon() {
        return gestDon;
    }

    public void setGestDon(boolean gestDon) {
        this.gestDon = gestDon;
    }

    public Short getLienBanque() {
		return lienBanque;
	}
	public void setLienBanque(Short lienBanque) {
		this.lienBanque = lienBanque;
	}
	public int getLienCpas() {
        return lienCpas;
    }

    public void setLienCpas(int lienCpas) {
        this.lienCpas = lienCpas;
    }
    
   
    public String getSociete() {
		return societe;
	}
    
	public String getMembreNom() {
		return membreNom;
	}
	public String getMembrePrenom() {
		return membrePrenom;
	}
	
	public String getMembreEmail() {
		return membreEmail;
	}
	public Short getMembreLangue() {
		return membreLangue;
	}
	
	public long getNbLogins() {
		return nbLogins;
	}
	public void setNbLogins(long nbLogins) {
		this.nbLogins = nbLogins;
	}
	public Long  getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long  totalRecords) {
		this.totalRecords = totalRecords;
	}
   
    

}
