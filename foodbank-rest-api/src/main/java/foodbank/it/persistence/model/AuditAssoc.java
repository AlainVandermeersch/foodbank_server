// Generated with g9.

package foodbank.it.persistence.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Formula;

@Entity(name="audit_assoc")
public class AuditAssoc implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3583973615325469868L;



	/** Primary key. */
    protected static final String PK = "auditId";

  

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="audit_id", unique=true, nullable=false, precision=19)
    private long auditId;
    @Column(name="lien_dis", precision=10)
    private int lienDis;
    @Column(name="lien_dep", precision=10)
    private int lienDep;
    @Column(name="auditor_nr", precision=10)
    private int auditorNr;
    @Column(name="demunis_Y_N_rem", nullable=false)
    private String demunisYNRem;
    @Column(name="hyg_check", nullable=false)
    private String hygCheck;
    @Column(name="serv_usage", nullable=false)
    private String servUsage;
    @Column(name="Prob_Sug", nullable=false)
    private String probSug;
    @Column(name="audit_date")
    private String auditDate;
    @Column(name="benef_check", nullable=false, length=1)
    private String benefCheck;
    @Formula("(select d.societe from organisations d where d.id_dis = lien_dis)")
    private String societe;
    @Formula("(select d.societe from organisations d where d.id_dis = lien_dep)")
    private String depotName;
    @Formula("(select d.lien_banque from organisations d where d.id_dis = lien_dis)")
    private Short lienBanque; 
    @Formula("(select concat(d.nom,' ',d.prenom) from bat d where d.bat_ID = auditor_nr)")
    private String auditorName; 

    /** Default constructor. */
    public AuditAssoc() {
        super();
    }

    public AuditAssoc(long auditId, int lienDis, int lienDep, int auditorNr, String demunisYNRem, String hygCheck,
			String servUsage, String probSug, String auditDate, String benefCheck) {
		super();
		this.auditId = auditId;
		this.lienDis = lienDis;
		this.lienDep = lienDep;
		this.auditorNr = auditorNr;
		this.demunisYNRem = demunisYNRem;
		this.hygCheck = hygCheck;
		this.servUsage = servUsage;
		this.probSug = probSug;
		this.auditDate = auditDate;
		this.benefCheck = benefCheck;
	}

	/**
     * Access method for auditId.
     *
     * @return the current value of auditId
     */
    public long getAuditId() {
        return auditId;
    }

    /**
     * Setter method for auditId.
     *
     * @param aAuditId the new value for auditId
     */
    public void setAuditId(long aAuditId) {
        auditId = aAuditId;
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
     * Access method for lienDep.
     *
     * @return the current value of lienDep
     */
    public int getLienDep() {
        return lienDep;
    }

    /**
     * Setter method for lienDep.
     *
     * @param aLienDep the new value for lienDep
     */
    public void setLienDep(int aLienDep) {
        lienDep = aLienDep;
    }

    /**
     * Access method for auditorNr.
     *
     * @return the current value of auditorNr
     */
    public int getAuditorNr() {
        return auditorNr;
    }

    /**
     * Setter method for auditorNr.
     *
     * @param aAuditorNr the new value for auditorNr
     */
    public void setAuditorNr(int aAuditorNr) {
        auditorNr = aAuditorNr;
    }

    /**
     * Access method for demunisYNRem.
     *
     * @return the current value of demunisYNRem
     */
    public String getDemunisYNRem() {
        return demunisYNRem;
    }

    /**
     * Setter method for demunisYNRem.
     *
     * @param aDemunisYNRem the new value for demunisYNRem
     */
    public void setDemunisYNRem(String aDemunisYNRem) {
        demunisYNRem = aDemunisYNRem;
    }

    /**
     * Access method for hygCheck.
     *
     * @return the current value of hygCheck
     */
    public String getHygCheck() {
        return hygCheck;
    }

    /**
     * Setter method for hygCheck.
     *
     * @param aHygCheck the new value for hygCheck
     */
    public void setHygCheck(String aHygCheck) {
        hygCheck = aHygCheck;
    }

    /**
     * Access method for servUsage.
     *
     * @return the current value of servUsage
     */
    public String getServUsage() {
        return servUsage;
    }

    /**
     * Setter method for servUsage.
     *
     * @param aServUsage the new value for servUsage
     */
    public void setServUsage(String aServUsage) {
        servUsage = aServUsage;
    }

    /**
     * Access method for probSug.
     *
     * @return the current value of probSug
     */
    public String getProbSug() {
        return probSug;
    }

    /**
     * Setter method for probSug.
     *
     * @param aProbSug the new value for probSug
     */
    public void setProbSug(String aProbSug) {
        probSug = aProbSug;
    }

    /**
     * Access method for auditDate.
     *
     * @return the current value of auditDate
     */
    public String getAuditDate() {
        return auditDate;
    }

    /**
     * Setter method for auditDate.
     *
     * @param aAuditDate the new value for auditDate
     */
    public void setAuditDate(String aAuditDate) {
        auditDate = aAuditDate;
    }

    /**
     * Access method for benefCheck.
     *
     * @return the current value of benefCheck
     */
    public String getBenefCheck() {
        return benefCheck;
    }

    /**
     * Setter method for benefCheck.
     *
     * @param aBenefCheck the new value for benefCheck
     */
    public void setBenefCheck(String aBenefCheck) {
        benefCheck = aBenefCheck;
    }
    
    

    public String getSociete() {
		return societe;
	}

	public String getDepotName() {
		return depotName;
	}

	public Short getLienBanque() {
		return lienBanque;
	}
	

	public String getAuditorName() {
		return auditorName;
	}

	/**
     * Compares the key for this instance with another AuditAssoc.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class AuditAssoc and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof AuditAssoc)) {
            return false;
        }
        AuditAssoc that = (AuditAssoc) other;
        if (this.getAuditId() != that.getAuditId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another AuditAssoc.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AuditAssoc)) return false;
        return this.equalKeys(other) && ((AuditAssoc)other).equalKeys(this);
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
        i = (int)(getAuditId() ^ (getAuditId()>>>32));
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
        StringBuffer sb = new StringBuffer("[AuditAssoc |");
        sb.append(" auditId=").append(getAuditId());
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
        ret.put("auditId", Long.valueOf(getAuditId()));
        return ret;
    }

}
