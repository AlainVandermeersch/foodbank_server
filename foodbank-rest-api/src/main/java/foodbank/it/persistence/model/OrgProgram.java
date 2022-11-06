// Generated with g9.

package foodbank.it.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity(name="assoprogr")
public class OrgProgram implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5190554725935411964L;

	/** Primary key. */
    protected static final String PK = "lienDis";
  
    @Id
    @Column(name="lien_dis", unique=true, nullable=false, precision=10)
    private int lienDis;
    @Column(name="lien_banque", nullable=false, precision=10)
    private int lienBanque;
    @Column(name="lien_depot", precision=10)
    private int lienDepot;
    @Column(precision=10)
    private int luam;
    @Column(precision=10)
    private int lupm;
    @Column(precision=10)
    private int tuam;
    @Column(precision=10)
    private int tupm;
    @Column(precision=10)
    private int weam;
    @Column(precision=10)
    private int wepm;
    @Column(precision=10)
    private int tham;
    @Column(precision=10)
    private int thpm;
    @Column(precision=10)
    private int fram;
    @Column(precision=10)
    private int frpm;
    @Column(precision=10)
    private int saam;
    @Column(precision=10)
    private int sapm;
    @Column(precision=10)
    private int sunam;
    @Column(precision=10)
    private int sunpm;
    private String reluam;
    private String relupm;
    private String retuam;
    private String retupm;
    private String reweam;
    private String rewepm;
    private String retham;
    private String rethpm;
    private String refram;
    private String refrpm;
    private String resaam;
    private String resapm;
    private String resunam;
    private String resunpm;
    @Column(precision=10)
    private Integer porc;
    @Column(name="leg_frais", precision=10)
    private Integer legFrais;
    @Column(precision=10)
    private Integer congel;
    @Column(name="congel_cap")
    private String congelCap;
    @Column(precision=10)
    private Integer auditor;
    @Column(name="date_audit", length=255)
    private String dateAudit;
    @Column(name="last_audit", precision=19)
    private Long lastAudit;

    /** Default constructor. */
    public OrgProgram() {
        super();
    }

    public OrgProgram(int lienDis, int lienBanque, int lienDepot, int luam, int lupm, int tuam, int tupm, int weam,
			int wepm, int tham, int thpm, int fram, int frpm, int saam, int sapm, int sunam, int sunpm, String reluam,
			String relupm, String retuam, String retupm, String reweam, String rewepm, String retham, String rethpm,
			String refram, String refrpm, String resaam, String resapm, String resunam, String resunpm, Integer porc,
			Integer legFrais, Integer congel, String congelCap, Integer auditor, String dateAudit, Long lastAudit) {
		super();
		this.lienDis = lienDis;
		this.lienBanque = lienBanque;
		this.lienDepot = lienDepot;
		this.luam = luam;
		this.lupm = lupm;
		this.tuam = tuam;
		this.tupm = tupm;
		this.weam = weam;
		this.wepm = wepm;
		this.tham = tham;
		this.thpm = thpm;
		this.fram = fram;
		this.frpm = frpm;
		this.saam = saam;
		this.sapm = sapm;
		this.sunam = sunam;
		this.sunpm = sunpm;
		this.reluam = reluam;
		this.relupm = relupm;
		this.retuam = retuam;
		this.retupm = retupm;
		this.reweam = reweam;
		this.rewepm = rewepm;
		this.retham = retham;
		this.rethpm = rethpm;
		this.refram = refram;
		this.refrpm = refrpm;
		this.resaam = resaam;
		this.resapm = resapm;
		this.resunam = resunam;
		this.resunpm = resunpm;
		this.porc = porc;
		this.legFrais = legFrais;
		this.congel = congel;
		this.congelCap = congelCap;
		this.auditor = auditor;
		this.dateAudit = dateAudit;
		this.lastAudit = lastAudit;
	}
    public OrgProgram(int lienDis, int lienBanque, int lienDepot) {
		super();
		this.lienDis = lienDis;
		this.lienBanque = lienBanque;
		this.lienDepot = lienDepot;
		this.luam = 0;
		this.lupm = 0;
		this.tuam = 0;
		this.tupm = 0;
		this.weam = 0;
		this.wepm = 0;
		this.tham = 0;
		this.thpm = 0;
		this.fram = 0;
		this.frpm = 0;
		this.saam = 0;
		this.sapm = 0;
		this.sunam = 0;
		this.sunpm = 0;
		this.reluam = "";
		this.relupm = "";
		this.retuam = "";
		this.retupm = "";
		this.reweam = "";
		this.rewepm = "";
		this.retham = "";
		this.rethpm = "";
		this.refram = "";
		this.refrpm = "";
		this.resaam = "";
		this.resapm = "";
		this.resunam = "";
		this.resunpm = "";
		this.porc = 0;
		this.legFrais = 0;
		this.congel = 0;
		this.congelCap = "";
		this.auditor = 0;
		this.dateAudit = "";
		this.lastAudit = (long) 0;
	}


	/**
     * Access method for lienDis.
     *
     * @return the current value of lienDis
     */
    public int getLienDis() {
        return lienDis;
    }

    /**
     * Setter method for lienDis.
     *
     * @param aLienDis the new value for lienDis
     */
    public void setLienDis(int aLienDis) {
        lienDis = aLienDis;
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
     * Access method for lienDepot.
     *
     * @return the current value of lienDepot
     */
    public int getLienDepot() {
        return lienDepot;
    }

    /**
     * Setter method for lienDepot.
     *
     * @param aLienDepot the new value for lienDepot
     */
    public void setLienDepot(int aLienDepot) {
        lienDepot = aLienDepot;
    }

    /**
     * Access method for luam.
     *
     * @return the current value of luam
     */
    public int getLuam() {
        return luam;
    }

    /**
     * Setter method for luam.
     *
     * @param aLuam the new value for luam
     */
    public void setLuam(int aLuam) {
        luam = aLuam;
    }

    /**
     * Access method for lupm.
     *
     * @return the current value of lupm
     */
    public int getLupm() {
        return lupm;
    }

    /**
     * Setter method for lupm.
     *
     * @param aLupm the new value for lupm
     */
    public void setLupm(int aLupm) {
        lupm = aLupm;
    }

    /**
     * Access method for tuam.
     *
     * @return the current value of tuam
     */
    public int getTuam() {
        return tuam;
    }

    /**
     * Setter method for tuam.
     *
     * @param aTuam the new value for tuam
     */
    public void setTuam(int aTuam) {
        tuam = aTuam;
    }

    /**
     * Access method for tupm.
     *
     * @return the current value of tupm
     */
    public int getTupm() {
        return tupm;
    }

    /**
     * Setter method for tupm.
     *
     * @param aTupm the new value for tupm
     */
    public void setTupm(int aTupm) {
        tupm = aTupm;
    }

    /**
     * Access method for weam.
     *
     * @return the current value of weam
     */
    public int getWeam() {
        return weam;
    }

    /**
     * Setter method for weam.
     *
     * @param aWeam the new value for weam
     */
    public void setWeam(int aWeam) {
        weam = aWeam;
    }

    /**
     * Access method for wepm.
     *
     * @return the current value of wepm
     */
    public int getWepm() {
        return wepm;
    }

    /**
     * Setter method for wepm.
     *
     * @param aWepm the new value for wepm
     */
    public void setWepm(int aWepm) {
        wepm = aWepm;
    }

    /**
     * Access method for tham.
     *
     * @return the current value of tham
     */
    public int getTham() {
        return tham;
    }

    /**
     * Setter method for tham.
     *
     * @param aTham the new value for tham
     */
    public void setTham(int aTham) {
        tham = aTham;
    }

    /**
     * Access method for thpm.
     *
     * @return the current value of thpm
     */
    public int getThpm() {
        return thpm;
    }

    /**
     * Setter method for thpm.
     *
     * @param aThpm the new value for thpm
     */
    public void setThpm(int aThpm) {
        thpm = aThpm;
    }

    /**
     * Access method for fram.
     *
     * @return the current value of fram
     */
    public int getFram() {
        return fram;
    }

    /**
     * Setter method for fram.
     *
     * @param aFram the new value for fram
     */
    public void setFram(int aFram) {
        fram = aFram;
    }

    /**
     * Access method for frpm.
     *
     * @return the current value of frpm
     */
    public int getFrpm() {
        return frpm;
    }

    /**
     * Setter method for frpm.
     *
     * @param aFrpm the new value for frpm
     */
    public void setFrpm(int aFrpm) {
        frpm = aFrpm;
    }

    /**
     * Access method for saam.
     *
     * @return the current value of saam
     */
    public int getSaam() {
        return saam;
    }

    /**
     * Setter method for saam.
     *
     * @param aSaam the new value for saam
     */
    public void setSaam(int aSaam) {
        saam = aSaam;
    }

    /**
     * Access method for sapm.
     *
     * @return the current value of sapm
     */
    public int getSapm() {
        return sapm;
    }

    /**
     * Setter method for sapm.
     *
     * @param aSapm the new value for sapm
     */
    public void setSapm(int aSapm) {
        sapm = aSapm;
    }

    /**
     * Access method for sunam.
     *
     * @return the current value of sunam
     */
    public int getSunam() {
        return sunam;
    }

    /**
     * Setter method for sunam.
     *
     * @param aSunam the new value for sunam
     */
    public void setSunam(int aSunam) {
        sunam = aSunam;
    }

    /**
     * Access method for sunpm.
     *
     * @return the current value of sunpm
     */
    public int getSunpm() {
        return sunpm;
    }

    /**
     * Setter method for sunpm.
     *
     * @param aSunpm the new value for sunpm
     */
    public void setSunpm(int aSunpm) {
        sunpm = aSunpm;
    }

    /**
     * Access method for reluam.
     *
     * @return the current value of reluam
     */
    public String getReluam() {
        return reluam;
    }

    /**
     * Setter method for reluam.
     *
     * @param aReluam the new value for reluam
     */
    public void setReluam(String aReluam) {
        reluam = aReluam;
    }

    /**
     * Access method for relupm.
     *
     * @return the current value of relupm
     */
    public String getRelupm() {
        return relupm;
    }

    /**
     * Setter method for relupm.
     *
     * @param aRelupm the new value for relupm
     */
    public void setRelupm(String aRelupm) {
        relupm = aRelupm;
    }

    /**
     * Access method for retuam.
     *
     * @return the current value of retuam
     */
    public String getRetuam() {
        return retuam;
    }

    /**
     * Setter method for retuam.
     *
     * @param aRetuam the new value for retuam
     */
    public void setRetuam(String aRetuam) {
        retuam = aRetuam;
    }

    /**
     * Access method for retupm.
     *
     * @return the current value of retupm
     */
    public String getRetupm() {
        return retupm;
    }

    /**
     * Setter method for retupm.
     *
     * @param aRetupm the new value for retupm
     */
    public void setRetupm(String aRetupm) {
        retupm = aRetupm;
    }

    /**
     * Access method for reweam.
     *
     * @return the current value of reweam
     */
    public String getReweam() {
        return reweam;
    }

    /**
     * Setter method for reweam.
     *
     * @param aReweam the new value for reweam
     */
    public void setReweam(String aReweam) {
        reweam = aReweam;
    }

    /**
     * Access method for rewepm.
     *
     * @return the current value of rewepm
     */
    public String getRewepm() {
        return rewepm;
    }

    /**
     * Setter method for rewepm.
     *
     * @param aRewepm the new value for rewepm
     */
    public void setRewepm(String aRewepm) {
        rewepm = aRewepm;
    }

    /**
     * Access method for retham.
     *
     * @return the current value of retham
     */
    public String getRetham() {
        return retham;
    }

    /**
     * Setter method for retham.
     *
     * @param aRetham the new value for retham
     */
    public void setRetham(String aRetham) {
        retham = aRetham;
    }

    /**
     * Access method for rethpm.
     *
     * @return the current value of rethpm
     */
    public String getRethpm() {
        return rethpm;
    }

    /**
     * Setter method for rethpm.
     *
     * @param aRethpm the new value for rethpm
     */
    public void setRethpm(String aRethpm) {
        rethpm = aRethpm;
    }

    /**
     * Access method for refram.
     *
     * @return the current value of refram
     */
    public String getRefram() {
        return refram;
    }

    /**
     * Setter method for refram.
     *
     * @param aRefram the new value for refram
     */
    public void setRefram(String aRefram) {
        refram = aRefram;
    }

    /**
     * Access method for refrpm.
     *
     * @return the current value of refrpm
     */
    public String getRefrpm() {
        return refrpm;
    }

    /**
     * Setter method for refrpm.
     *
     * @param aRefrpm the new value for refrpm
     */
    public void setRefrpm(String aRefrpm) {
        refrpm = aRefrpm;
    }

    /**
     * Access method for resaam.
     *
     * @return the current value of resaam
     */
    public String getResaam() {
        return resaam;
    }

    /**
     * Setter method for resaam.
     *
     * @param aResaam the new value for resaam
     */
    public void setResaam(String aResaam) {
        resaam = aResaam;
    }

    /**
     * Access method for resapm.
     *
     * @return the current value of resapm
     */
    public String getResapm() {
        return resapm;
    }

    /**
     * Setter method for resapm.
     *
     * @param aResapm the new value for resapm
     */
    public void setResapm(String aResapm) {
        resapm = aResapm;
    }

    /**
     * Access method for resunam.
     *
     * @return the current value of resunam
     */
    public String getResunam() {
        return resunam;
    }

    /**
     * Setter method for resunam.
     *
     * @param aResunam the new value for resunam
     */
    public void setResunam(String aResunam) {
        resunam = aResunam;
    }

    /**
     * Access method for resunpm.
     *
     * @return the current value of resunpm
     */
    public String getResunpm() {
        return resunpm;
    }

    /**
     * Setter method for resunpm.
     *
     * @param aResunpm the new value for resunpm
     */
    public void setResunpm(String aResunpm) {
        resunpm = aResunpm;
    }

    /**
     * Access method for porc.
     *
     * @return the current value of porc
     */
    public Integer getPorc() {
        return porc;
    }

    /**
     * Setter method for porc.
     *
     * @param aPorc the new value for porc
     */
    public void setPorc(Integer aPorc) {
        porc = aPorc;
    }

    /**
     * Access method for legFrais.
     *
     * @return the current value of legFrais
     */
    public Integer getLegFrais() {
        return legFrais;
    }

    /**
     * Setter method for legFrais.
     *
     * @param aLegFrais the new value for legFrais
     */
    public void setLegFrais(Integer aLegFrais) {
        legFrais = aLegFrais;
    }

    /**
     * Access method for congel.
     *
     * @return the current value of congel
     */
    public Integer getCongel() {
        return congel;
    }

    /**
     * Setter method for congel.
     *
     * @param aCongel the new value for congel
     */
    public void setCongel(Integer aCongel) {
        congel = aCongel;
    }

    /**
     * Access method for congelCap.
     *
     * @return the current value of congelCap
     */
    public String getCongelCap() {
        return congelCap;
    }

    /**
     * Setter method for congelCap.
     *
     * @param aCongelCap the new value for congelCap
     */
    public void setCongelCap(String aCongelCap) {
        congelCap = aCongelCap;
    }

    /**
     * Access method for auditor.
     *
     * @return the current value of auditor
     */
    public Integer getAuditor() {
        return auditor;
    }

    /**
     * Setter method for auditor.
     *
     * @param aAuditor the new value for auditor
     */
    public void setAuditor(Integer aAuditor) {
        auditor = aAuditor;
    }

    /**
     * Access method for dateAudit.
     *
     * @return the current value of dateAudit
     */
    public String getDateAudit() {
        return dateAudit;
    }

    /**
     * Setter method for dateAudit.
     *
     * @param aDateAudit the new value for dateAudit
     */
    public void setDateAudit(String aDateAudit) {
        dateAudit = aDateAudit;
    }

    /**
     * Access method for lastAudit.
     *
     * @return the current value of lastAudit
     */
    public Long getLastAudit() {
        return lastAudit;
    }

    /**
     * Setter method for lastAudit.
     *
     * @param aLastAudit the new value for lastAudit
     */
    public void setLastAudit(Long aLastAudit) {
        lastAudit = aLastAudit;
    }

    /**
     * Compares the key for this instance with another Assoprogr.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Assoprogr and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof OrgProgram)) {
            return false;
        }
        OrgProgram that = (OrgProgram) other;
        if (this.getLienDis() != that.getLienDis()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Assoprogr.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OrgProgram)) return false;
        return this.equalKeys(other) && ((OrgProgram)other).equalKeys(this);
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
        i = getLienDis();
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
        StringBuffer sb = new StringBuffer("[Assoprogr |");
        sb.append(" lienDis=").append(getLienDis());
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
        ret.put("lienDis", Integer.valueOf(getLienDis()));
        return ret;
    }

}
