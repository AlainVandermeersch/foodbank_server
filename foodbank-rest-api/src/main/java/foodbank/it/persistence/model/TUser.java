// Generated with g9.

package foodbank.it.persistence.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Formula;

@Entity(name="t_user")
public class TUser implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5478532363583734690L;

	/** Primary key. */
    protected static final String PK = "idUser";
    
    @Id
    @Column(name="ID_USER", unique=true, nullable=false, length=60)
    private String idUser;
    @Column(name="USER_NAME", length=45)
    private String userName;
    @Column(name="ID_COMPANY", length=10)
    private String idCompany;
    @Column(name="ID_ORG", precision=10)
    private Integer idOrg;
    @Column(name="ID_LANGUAGE", length=2)
    private String idLanguage;
    @Column(name="lien_bat", nullable=false, precision=10)
    private int lienBat;
    @Column(name="ACTIF", nullable=false, precision=10)
    private int actif;
    @Column(name="RIGHTS", length=10)
    private String rights;
    @Column(name="PASSWORD", length=150)
    private String password;
    @Column(name="DEPOT", length=5)
    private String depot;
    @Column(name="DROIT1", precision=10)
    private int droit1;
    @Column(name="Email", length=50)
    private String email;
    @Column(name="Gest_Ben", precision=10)
    private int gestBen;
    @Column(name="Gest_Inv", precision=10)
    private int gestInv;
    @Column(name="Gest_Fead", precision=10)
    private int gestFead;
    @Column(name="Gest_Asso", precision=10)
    private int gestAsso;
    @Column(name="Gest_CPAS", precision=10)
    private int gestCpas;
    @Column(name="Gest_Memb", precision=10)
    private int gestMemb;
    @Column(name="Gest_Don", precision=10)
    private int gestDon;
    @Column(name="lien_banque", precision=10)
    private int lienBanque;
    @Column(name="lien_cpas", precision=10)
    private int lienCpas;
    @Formula("(select d.societe from organisations d where d.id_dis = ID_ORG)")
    private String societe;
    @Formula("(select d.lien_depot from organisations d where d.id_dis = ID_ORG)")
    private String lienDepot;
    @Formula("(select e.nom from bat e where e.bat_ID = lien_bat)")
    private String membreNom;
    @Formula("(select e.prenom from bat e where e.bat_ID = lien_bat)")
    private String membrePrenom;
    @Formula("(select e.batmail from bat e where e.bat_ID = lien_bat)")
    private String membreEmail;
    @Formula("(select e.langue from bat e where e.bat_ID = lien_bat)")
    private Short membreLangue;
    
	/** Default constructor. */
    protected TUser() {
        super();
    }

    public TUser(String idUser, String userName, String idCompany, Integer idOrg, String idLanguage, int lienBat, int actif, String rights, String password, String depot, int droit1, String email, int gestBen, int gestInv, int gestFead,
        int gestAsso, int gestCpas, int gestMemb, int gestDon, int lienBanque, int lienCpas) {
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

    /**
     * Access method for idUser.
     *
     * @return the current value of idUser
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * Setter method for idUser.
     *
     * @param aIdUser the new value for idUser
     */
    public void setIdUser(String aIdUser) {
        idUser = aIdUser;
    }

    /**
     * Access method for userName.
     *
     * @return the current value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method for userName.
     *
     * @param aUserName the new value for userName
     */
    public void setUserName(String aUserName) {
        userName = aUserName;
    }

    /**
     * Access method for idCompany.
     *
     * @return the current value of idCompany
     */
    public String getIdCompany() {
        return idCompany;
    }

    /**
     * Setter method for idCompany.
     *
     * @param aIdCompany the new value for idCompany
     */
    public void setIdCompany(String aIdCompany) {
        idCompany = aIdCompany;
    }
    
    

    public String getMembreNom() {
		return membreNom;
	}

	public void setMembreNom(String membreNom) {
		this.membreNom = membreNom;
	}

	public String getMembrePrenom() {
		return membrePrenom;
	}

	public void setMembrePrenom(String membrePrenom) {
		this.membrePrenom = membrePrenom;
	}

	public String getMembreEmail() {
		return membreEmail;
	}

	public void setMembreEmail(String membreEmail) {
		this.membreEmail = membreEmail;
	}

	public Short getMembreLangue() {
		return membreLangue;
	}

	public void setMembreLangue(Short membreLangue) {
		this.membreLangue = membreLangue;
	}

	/**
     * Access method for idOrg.
     *
     * @return the current value of idOrg
     */
    public Integer getIdOrg() {
        return idOrg;
    }

    /**
     * Setter method for idOrg.
     *
     * @param aIdOrg the new value for idOrg
     */
    public void setIdOrg(Integer aIdOrg) {
        idOrg = aIdOrg;
    }

    /**
     * Access method for idLanguage.
     *
     * @return the current value of idLanguage
     */
    public String getIdLanguage() {
        return idLanguage;
    }

    /**
     * Setter method for idLanguage.
     *
     * @param aIdLanguage the new value for idLanguage
     */
    public void setIdLanguage(String aIdLanguage) {
        idLanguage = aIdLanguage;
    }

    /**
     * Access method for lienBat.
     *
     * @return the current value of lienBat
     */
    public int getLienBat() {
        return lienBat;
    }

    /**
     * Setter method for lienBat.
     *
     * @param aLienBat the new value for lienBat
     */
    public void setLienBat(int aLienBat) {
        lienBat = aLienBat;
    }

    /**
     * Access method for actif.
     *
     * @return the current value of actif
     */
    public int getActif() {
        return actif;
    }

    /**
     * Setter method for actif.
     *
     * @param aActif the new value for actif
     */
    public void setActif(int aActif) {
        actif = aActif;
    }

    /**
     * Access method for rights.
     *
     * @return the current value of rights
     */
    public String getRights() {
        return rights;
    }

    /**
     * Setter method for rights.
     *
     * @param aRights the new value for rights
     */
    public void setRights(String aRights) {
        rights = aRights;
    }

    /**
     * Access method for password.
     *
     * @return the current value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for password.
     *
     * @param aPassword the new value for password
     */
    public void setPassword(String aPassword) {
        password = aPassword;
    }

    /**
     * Access method for depot.
     *
     * @return the current value of depot
     */
    public String getDepot() {
        return depot;
    }

    /**
     * Setter method for depot.
     *
     * @param aDepot the new value for depot
     */
    public void setDepot(String aDepot) {
        depot = aDepot;
    }

    /**
     * Access method for droit1.
     *
     * @return the current value of droit1
     */
    public int getDroit1() {
        return droit1;
    }

    /**
     * Setter method for droit1.
     *
     * @param aDroit1 the new value for droit1
     */
    public void setDroit1(int aDroit1) {
        droit1 = aDroit1;
    }

    /**
     * Access method for email.
     *
     * @return the current value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for email.
     *
     * @param aEmail the new value for email
     */
    public void setEmail(String aEmail) {
        email = aEmail;
    }

    /**
     * Access method for gestBen.
     *
     * @return the current value of gestBen
     */
    public int getGestBen() {
        return gestBen;
    }

    /**
     * Setter method for gestBen.
     *
     * @param aGestBen the new value for gestBen
     */
    public void setGestBen(int aGestBen) {
        gestBen = aGestBen;
    }

    /**
     * Access method for gestInv.
     *
     * @return the current value of gestInv
     */
    public int getGestInv() {
        return gestInv;
    }

    /**
     * Setter method for gestInv.
     *
     * @param aGestInv the new value for gestInv
     */
    public void setGestInv(int aGestInv) {
        gestInv = aGestInv;
    }

    /**
     * Access method for gestFead.
     *
     * @return the current value of gestFead
     */
    public int getGestFead() {
        return gestFead;
    }

    /**
     * Setter method for gestFead.
     *
     * @param aGestFead the new value for gestFead
     */
    public void setGestFead(int aGestFead) {
        gestFead = aGestFead;
    }

    /**
     * Access method for gestAsso.
     *
     * @return the current value of gestAsso
     */
    public int getGestAsso() {
        return gestAsso;
    }

    /**
     * Setter method for gestAsso.
     *
     * @param aGestAsso the new value for gestAsso
     */
    public void setGestAsso(int aGestAsso) {
        gestAsso = aGestAsso;
    }

    /**
     * Access method for gestCpas.
     *
     * @return the current value of gestCpas
     */
    public int getGestCpas() {
        return gestCpas;
    }

    /**
     * Setter method for gestCpas.
     *
     * @param aGestCpas the new value for gestCpas
     */
    public void setGestCpas(int aGestCpas) {
        gestCpas = aGestCpas;
    }

    /**
     * Access method for gestMemb.
     *
     * @return the current value of gestMemb
     */
    public int getGestMemb() {
        return gestMemb;
    }

    /**
     * Setter method for gestMemb.
     *
     * @param aGestMemb the new value for gestMemb
     */
    public void setGestMemb(int aGestMemb) {
        gestMemb = aGestMemb;
    }

    /**
     * Access method for gestDon.
     *
     * @return the current value of gestDon
     */
    public int getGestDon() {
        return gestDon;
    }

    /**
     * Setter method for gestDon.
     *
     * @param aGestDon the new value for gestDon
     */
    public void setGestDon(int aGestDon) {
        gestDon = aGestDon;
    }

    /**
     * Access method for lienBanque.
     *
     * @return the current value of lienBanque
     */
    public int getLienBanque() {
        return lienBanque;
    }

    /**
     * Setter method for lienBanque.
     *
     * @param aLienBanque the new value for lienBanque
     */
    public void setLienBanque(int aLienBanque) {
        lienBanque = aLienBanque;
    }

    /**
     * Access method for lienCpas.
     *
     * @return the current value of lienCpas
     */
    public int getLienCpas() {
        return lienCpas;
    }

    /**
     * Setter method for lienCpas.
     *
     * @param aLienCpas the new value for lienCpas
     */
    public void setLienCpas(int aLienCpas) {
        lienCpas = aLienCpas;
    }

  
   

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public String getLienDepot() {
		return lienDepot;
	}

	public void setLienDepot(String lienDepot) {
		this.lienDepot = lienDepot;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getPk() {
		return PK;
	}

	/**
     * Compares the key for this instance with another TUser.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class TUser and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof TUser)) {
            return false;
        }
        TUser that = (TUser) other;
        Object myIdUser = this.getIdUser();
        Object yourIdUser = that.getIdUser();
        if (myIdUser==null ? yourIdUser!=null : !myIdUser.equals(yourIdUser)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another TUser.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TUser)) return false;
        return this.equalKeys(other) && ((TUser)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        if (getIdUser() == null) {
            i = 0;
        } else {
            i = getIdUser().hashCode();
        }
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[TUser |");
        sb.append(" idUser=").append(getIdUser());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("idUser", getIdUser());
        return ret;
    }

}
