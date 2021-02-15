// Generated with g9.

package foodbank.it.persistence.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="banques")
public class Banque implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -585268270165829786L;


	/** Primary key. */
    protected static final String PK = "bankId";

   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="bank_id", unique=true, nullable=false, precision=3)
    private int bankId;
    @Column(name="bank_short_name", nullable=false, length=10)
    private String bankShortName;
    @Column(name="bank_name", nullable=false, length=100)
    private String bankName;
    @Column(name="nr_entr", nullable=false, length=20)
    private String nrEntr;
    @Column(name="bank_mail", nullable=false, length=50)
    private String bankMail;
    @Column(nullable=false, precision=3)
    private short actif;
    @Column(name="com_gest", nullable=false, precision=10)
    private int comGest;
    @Column(nullable=false)
    private LocalDateTime lastvisit;
    @Column(name="nomPRES", nullable=false, precision=5)
    private short nomPres;
    @Column(name="nomVP", nullable=false, precision=5)
    private short nomVp;
    @Column(name="nomSEC", nullable=false, precision=5)
    private short nomSec;
    @Column(name="nomTRES", nullable=false, precision=5)
    private short nomTres;
    @Column(name="nomIT", nullable=false, precision=5)
    private short nomIt;
    @Column(name="nomLOG", nullable=false, precision=5)
    private short nomLog;
    @Column(name="nomRH", nullable=false, precision=5)
    private short nomRh;
    @Column(name="nomSH", nullable=false, precision=5)
    private short nomSh;
    @Column(name="nomPP", nullable=false, precision=5)
    private short nomPp;
    @Column(name="nomASSO", nullable=false, precision=5)
    private short nomAsso;
    @Column(name="nomAPPRO", nullable=false, precision=5)
    private short nomAppro;
    @Column(name="nomPUBREL", nullable=false, precision=5)
    private short nomPubrel;
    @Column(name="nomCEO", nullable=false, precision=10)
    private int nomCeo;
    @Column(name="nomFEAD", nullable=false, precision=10)
    private int nomFead;
    @Column(nullable=false, length=50)
    private String adresse;
    @Column(nullable=false, length=10)
    private String cp;
    @Column(nullable=false, length=30)
    private String localite;
    @Column(name="bank_tel", nullable=false, length=20)
    private String bankTel;
    @Column(name="bank_gsm", nullable=false, length=20)
    private String bankGsm;
    @Column(name="adresse_depot_princ", nullable=false, length=100)
    private String adresseDepotPrinc;
    @Column(name="cp_depot_princ", nullable=false, length=10)
    private String cpDepotPrinc;
    @Column(name="city_depot_princ", nullable=false, length=50)
    private String cityDepotPrinc;
    @Column(name="dep_princ_tel", nullable=false, length=20)
    private String depPrincTel;
    @Column(name="ss_adresse", nullable=false, length=100)
    private String ssAdresse;
    @Column(name="ss_cp", nullable=false, length=10)
    private String ssCp;
    @Column(name="ss_city", nullable=false, length=50)
    private String ssCity;
    @Column(name="ss_tel", nullable=false, length=20)
    private String ssTel;
    @Column(nullable=false, precision=10)
    private int regio;
    @Column(nullable=false, length=50)
    private String website;
    @Column(length=20)
    private String bank;
    

    /** Default constructor. */
    public Banque() {
        super();
    }

    public Banque(int bankId, String bankShortName, String bankName, String nrEntr, String bankMail, short actif, int comGest, LocalDateTime lastvisit, short nomPres, short nomVp, short nomSec, short nomTres, short nomIt, short nomLog, short nomRh,
        short nomSh, short nomPp, short nomAsso, short nomAppro, short nomPubrel, int nomCeo, int nomFead, String adresse, String cp, String localite, String bankTel, String bankGsm, String adresseDepotPrinc, String cpDepotPrinc, String cityDepotPrinc,
        String depPrincTel, String ssAdresse, String ssCp, String ssCity, String ssTel, int regio, String website, String bank) {
              
        this.bankId = bankId;
    	this.bankShortName = bankShortName;
        this.bankName = bankName;
        this.nrEntr = nrEntr;
        this.bankMail = bankMail;
        this.actif = actif;
        this.comGest = comGest;
        this.lastvisit = lastvisit;
        this.nomPres = nomPres;
        this.nomVp = nomVp;
        this.nomSec = nomSec;
        this.nomTres = nomTres;
        this.nomIt = nomIt;
        this.nomLog = nomLog;
        this.nomRh = nomRh;
        this.nomSh = nomSh;
        this.nomPp = nomPp;
        this.nomAsso = nomAsso;
        this.nomAppro = nomAppro;
        this.nomPubrel = nomPubrel;
        this.nomCeo = nomCeo;
        this.nomFead = nomFead;
        this.adresse = adresse;
        this.cp = cp;
        this.localite = localite;
        this.bankTel = bankTel;
        this.bankGsm = bankGsm;
        this.adresseDepotPrinc = adresseDepotPrinc;
        this.cpDepotPrinc = cpDepotPrinc;
        this.cityDepotPrinc = cityDepotPrinc;
        this.depPrincTel = depPrincTel;
        this.ssAdresse = ssAdresse;
        this.ssCp = ssCp;
        this.ssCity = ssCity;
        this.ssTel = ssTel;
        this.regio = regio;
        this.website = website;
        this.bank = bank;
       
    }

    /**
     * Access method for bankId.
     *
     * @return the current value of bankId
     */
    public int getBankId() {
        return bankId;
    }

    /**
     * Setter method for bankId.
     *
     * @param aBankId the new value for bankId
     */
    public void setBankId(int aBankId) {
        bankId = aBankId;
    }

    /**
     * Access method for bankShortName.
     *
     * @return the current value of bankShortName
     */
    public String getBankShortName() {
        return bankShortName;
    }

    /**
     * Setter method for bankShortName.
     *
     * @param aBankShortName the new value for bankShortName
     */
    public void setBankShortName(String aBankShortName) {
        bankShortName = aBankShortName;
    }

    /**
     * Access method for bankName.
     *
     * @return the current value of bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Setter method for bankName.
     *
     * @param aBankName the new value for bankName
     */
    public void setBankName(String aBankName) {
        bankName = aBankName;
    }

    /**
     * Access method for nrEntr.
     *
     * @return the current value of nrEntr
     */
    public String getNrEntr() {
        return nrEntr;
    }

    /**
     * Setter method for nrEntr.
     *
     * @param aNrEntr the new value for nrEntr
     */
    public void setNrEntr(String aNrEntr) {
        nrEntr = aNrEntr;
    }

    /**
     * Access method for bankMail.
     *
     * @return the current value of bankMail
     */
    public String getBankMail() {
        return bankMail;
    }

    /**
     * Setter method for bankMail.
     *
     * @param aBankMail the new value for bankMail
     */
    public void setBankMail(String aBankMail) {
        bankMail = aBankMail;
    }

    /**
     * Access method for actif.
     *
     * @return the current value of actif
     */
    public short getActif() {
        return actif;
    }

    /**
     * Setter method for actif.
     *
     * @param aActif the new value for actif
     */
    public void setActif(short aActif) {
        actif = aActif;
    }

    /**
     * Access method for comGest.
     *
     * @return the current value of comGest
     */
    public int getComGest() {
        return comGest;
    }

    /**
     * Setter method for comGest.
     *
     * @param aComGest the new value for comGest
     */
    public void setComGest(int aComGest) {
        comGest = aComGest;
    }

    /**
     * Access method for lastvisit.
     *
     * @return the current value of lastvisit
     */
    public LocalDateTime getLastvisit() {
        return lastvisit;
    }

    /**
     * Setter method for lastvisit.
     *
     * @param aLastvisit the new value for lastvisit
     */
    public void setLastvisit(LocalDateTime aLastvisit) {
        lastvisit = aLastvisit;
    }

    /**
     * Access method for nomPres.
     *
     * @return the current value of nomPres
     */
    public short getNomPres() {
        return nomPres;
    }

    /**
     * Setter method for nomPres.
     *
     * @param aNomPres the new value for nomPres
     */
    public void setNomPres(short aNomPres) {
        nomPres = aNomPres;
    }

    /**
     * Access method for nomVp.
     *
     * @return the current value of nomVp
     */
    public short getNomVp() {
        return nomVp;
    }

    /**
     * Setter method for nomVp.
     *
     * @param aNomVp the new value for nomVp
     */
    public void setNomVp(short aNomVp) {
        nomVp = aNomVp;
    }

    /**
     * Access method for nomSec.
     *
     * @return the current value of nomSec
     */
    public short getNomSec() {
        return nomSec;
    }

    /**
     * Setter method for nomSec.
     *
     * @param aNomSec the new value for nomSec
     */
    public void setNomSec(short aNomSec) {
        nomSec = aNomSec;
    }

    /**
     * Access method for nomTres.
     *
     * @return the current value of nomTres
     */
    public short getNomTres() {
        return nomTres;
    }

    /**
     * Setter method for nomTres.
     *
     * @param aNomTres the new value for nomTres
     */
    public void setNomTres(short aNomTres) {
        nomTres = aNomTres;
    }

    /**
     * Access method for nomIt.
     *
     * @return the current value of nomIt
     */
    public short getNomIt() {
        return nomIt;
    }

    /**
     * Setter method for nomIt.
     *
     * @param aNomIt the new value for nomIt
     */
    public void setNomIt(short aNomIt) {
        nomIt = aNomIt;
    }

    /**
     * Access method for nomLog.
     *
     * @return the current value of nomLog
     */
    public short getNomLog() {
        return nomLog;
    }

    /**
     * Setter method for nomLog.
     *
     * @param aNomLog the new value for nomLog
     */
    public void setNomLog(short aNomLog) {
        nomLog = aNomLog;
    }

    /**
     * Access method for nomRh.
     *
     * @return the current value of nomRh
     */
    public short getNomRh() {
        return nomRh;
    }

    /**
     * Setter method for nomRh.
     *
     * @param aNomRh the new value for nomRh
     */
    public void setNomRh(short aNomRh) {
        nomRh = aNomRh;
    }

    /**
     * Access method for nomSh.
     *
     * @return the current value of nomSh
     */
    public short getNomSh() {
        return nomSh;
    }

    /**
     * Setter method for nomSh.
     *
     * @param aNomSh the new value for nomSh
     */
    public void setNomSh(short aNomSh) {
        nomSh = aNomSh;
    }

    /**
     * Access method for nomPp.
     *
     * @return the current value of nomPp
     */
    public short getNomPp() {
        return nomPp;
    }

    /**
     * Setter method for nomPp.
     *
     * @param aNomPp the new value for nomPp
     */
    public void setNomPp(short aNomPp) {
        nomPp = aNomPp;
    }

    /**
     * Access method for nomAsso.
     *
     * @return the current value of nomAsso
     */
    public short getNomAsso() {
        return nomAsso;
    }

    /**
     * Setter method for nomAsso.
     *
     * @param aNomAsso the new value for nomAsso
     */
    public void setNomAsso(short aNomAsso) {
        nomAsso = aNomAsso;
    }

    /**
     * Access method for nomAppro.
     *
     * @return the current value of nomAppro
     */
    public short getNomAppro() {
        return nomAppro;
    }

    /**
     * Setter method for nomAppro.
     *
     * @param aNomAppro the new value for nomAppro
     */
    public void setNomAppro(short aNomAppro) {
        nomAppro = aNomAppro;
    }

    /**
     * Access method for nomPubrel.
     *
     * @return the current value of nomPubrel
     */
    public short getNomPubrel() {
        return nomPubrel;
    }

    /**
     * Setter method for nomPubrel.
     *
     * @param aNomPubrel the new value for nomPubrel
     */
    public void setNomPubrel(short aNomPubrel) {
        nomPubrel = aNomPubrel;
    }

    /**
     * Access method for nomCeo.
     *
     * @return the current value of nomCeo
     */
    public int getNomCeo() {
        return nomCeo;
    }

    /**
     * Setter method for nomCeo.
     *
     * @param aNomCeo the new value for nomCeo
     */
    public void setNomCeo(int aNomCeo) {
        nomCeo = aNomCeo;
    }

    /**
     * Access method for nomFead.
     *
     * @return the current value of nomFead
     */
    public int getNomFead() {
        return nomFead;
    }

    /**
     * Setter method for nomFead.
     *
     * @param aNomFead the new value for nomFead
     */
    public void setNomFead(int aNomFead) {
        nomFead = aNomFead;
    }

    /**
     * Access method for adresse.
     *
     * @return the current value of adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Setter method for adresse.
     *
     * @param aAdresse the new value for adresse
     */
    public void setAdresse(String aAdresse) {
        adresse = aAdresse;
    }

    /**
     * Access method for cp.
     *
     * @return the current value of cp
     */
    public String getCp() {
        return cp;
    }

    /**
     * Setter method for cp.
     *
     * @param aCp the new value for cp
     */
    public void setCp(String aCp) {
        cp = aCp;
    }

    /**
     * Access method for localite.
     *
     * @return the current value of localite
     */
    public String getLocalite() {
        return localite;
    }

    /**
     * Setter method for localite.
     *
     * @param aLocalite the new value for localite
     */
    public void setLocalite(String aLocalite) {
        localite = aLocalite;
    }

    /**
     * Access method for bankTel.
     *
     * @return the current value of bankTel
     */
    public String getBankTel() {
        return bankTel;
    }

    /**
     * Setter method for bankTel.
     *
     * @param aBankTel the new value for bankTel
     */
    public void setBankTel(String aBankTel) {
        bankTel = aBankTel;
    }

    /**
     * Access method for bankGsm.
     *
     * @return the current value of bankGsm
     */
    public String getBankGsm() {
        return bankGsm;
    }

    /**
     * Setter method for bankGsm.
     *
     * @param aBankGsm the new value for bankGsm
     */
    public void setBankGsm(String aBankGsm) {
        bankGsm = aBankGsm;
    }

    /**
     * Access method for adresseDepotPrinc.
     *
     * @return the current value of adresseDepotPrinc
     */
    public String getAdresseDepotPrinc() {
        return adresseDepotPrinc;
    }

    /**
     * Setter method for adresseDepotPrinc.
     *
     * @param aAdresseDepotPrinc the new value for adresseDepotPrinc
     */
    public void setAdresseDepotPrinc(String aAdresseDepotPrinc) {
        adresseDepotPrinc = aAdresseDepotPrinc;
    }

    /**
     * Access method for cpDepotPrinc.
     *
     * @return the current value of cpDepotPrinc
     */
    public String getCpDepotPrinc() {
        return cpDepotPrinc;
    }

    /**
     * Setter method for cpDepotPrinc.
     *
     * @param aCpDepotPrinc the new value for cpDepotPrinc
     */
    public void setCpDepotPrinc(String aCpDepotPrinc) {
        cpDepotPrinc = aCpDepotPrinc;
    }

    /**
     * Access method for cityDepotPrinc.
     *
     * @return the current value of cityDepotPrinc
     */
    public String getCityDepotPrinc() {
        return cityDepotPrinc;
    }

    /**
     * Setter method for cityDepotPrinc.
     *
     * @param aCityDepotPrinc the new value for cityDepotPrinc
     */
    public void setCityDepotPrinc(String aCityDepotPrinc) {
        cityDepotPrinc = aCityDepotPrinc;
    }

    /**
     * Access method for depPrincTel.
     *
     * @return the current value of depPrincTel
     */
    public String getDepPrincTel() {
        return depPrincTel;
    }

    /**
     * Setter method for depPrincTel.
     *
     * @param aDepPrincTel the new value for depPrincTel
     */
    public void setDepPrincTel(String aDepPrincTel) {
        depPrincTel = aDepPrincTel;
    }

    /**
     * Access method for ssAdresse.
     *
     * @return the current value of ssAdresse
     */
    public String getSsAdresse() {
        return ssAdresse;
    }

    /**
     * Setter method for ssAdresse.
     *
     * @param aSsAdresse the new value for ssAdresse
     */
    public void setSsAdresse(String aSsAdresse) {
        ssAdresse = aSsAdresse;
    }

    /**
     * Access method for ssCp.
     *
     * @return the current value of ssCp
     */
    public String getSsCp() {
        return ssCp;
    }

    /**
     * Setter method for ssCp.
     *
     * @param aSsCp the new value for ssCp
     */
    public void setSsCp(String aSsCp) {
        ssCp = aSsCp;
    }

    /**
     * Access method for ssCity.
     *
     * @return the current value of ssCity
     */
    public String getSsCity() {
        return ssCity;
    }

    /**
     * Setter method for ssCity.
     *
     * @param aSsCity the new value for ssCity
     */
    public void setSsCity(String aSsCity) {
        ssCity = aSsCity;
    }

    /**
     * Access method for ssTel.
     *
     * @return the current value of ssTel
     */
    public String getSsTel() {
        return ssTel;
    }

    /**
     * Setter method for ssTel.
     *
     * @param aSsTel the new value for ssTel
     */
    public void setSsTel(String aSsTel) {
        ssTel = aSsTel;
    }

    /**
     * Access method for regio.
     *
     * @return the current value of regio
     */
    public int getRegio() {
        return regio;
    }

    /**
     * Setter method for regio.
     *
     * @param aRegio the new value for regio
     */
    public void setRegio(int aRegio) {
        regio = aRegio;
    }

    /**
     * Access method for website.
     *
     * @return the current value of website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Setter method for website.
     *
     * @param aWebsite the new value for website
     */
    public void setWebsite(String aWebsite) {
        website = aWebsite;
    }

    /**
     * Access method for bank.
     *
     * @return the current value of bank
     */
    public String getBank() {
        return bank;
    }

    /**
     * Setter method for bank.
     *
     * @param aBank the new value for bank
     */
    public void setBank(String aBank) {
        bank = aBank;
    }

  
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Banque)) {
            return false;
        }
        Banque that = (Banque) other;
        if (this.getBankId() != that.getBankId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Banque.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Banque)) return false;
        return this.equalKeys(other) && ((Banque)other).equalKeys(this);
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
        i = getBankId();
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
        StringBuffer sb = new StringBuffer("[Banque |");
        sb.append(" bankId=").append(getBankId());
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
        ret.put("bankId", Integer.valueOf(getBankId()));
        return ret;
    }

}
