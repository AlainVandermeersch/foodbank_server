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

@Entity(name="type_emploi")
public class TypeEmploi implements Serializable {

    /** Primary key. */
    protected static final String PK = "jobNr";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="job_nr", unique=true, nullable=false, precision=10)
    private int jobNr;
    @Column(name="job_name_fr")
    private String jobNameFr;
    @Column(name="job_name_nl")
    private String jobNameNl;
    @Column(name="lien_banque", nullable=false, precision=10)
    private int lienBanque;
    @Column(precision=10)
    private int actif;
    @Column(name="job_name_en")
    private String jobNameEn;
    @Column(name="job_name_ge")
    private String jobNameGe;
    public TypeEmploi() {
        super();
    }
    public TypeEmploi(int jobNr, String jobNameFr, String jobNameNl, int lienBanque, int actif, String jobNameEn, String jobNameGe) {
        this.jobNr = jobNr;
        this.jobNameFr = jobNameFr;
        this.jobNameNl = jobNameNl;
        this.lienBanque = lienBanque;
        this.actif = actif;
        this.jobNameEn = jobNameEn;
        this.jobNameGe = jobNameGe;
    }

    /**
     * Access method for jobNr.
     *
     * @return the current value of jobNr
     */
    public int getJobNr() {
        return jobNr;
    }

    /**
     * Setter method for jobNr.
     *
     * @param aJobNr the new value for jobNr
     */
    public void setJobNr(int aJobNr) {
        jobNr = aJobNr;
    }

    /**
     * Access method for jobNameFr.
     *
     * @return the current value of jobNameFr
     */
    public String getJobNameFr() {
        return jobNameFr;
    }

    /**
     * Setter method for jobNameFr.
     *
     * @param aJobNameFr the new value for jobNameFr
     */
    public void setJobNameFr(String aJobNameFr) {
        jobNameFr = aJobNameFr;
    }

    /**
     * Access method for jobNameNl.
     *
     * @return the current value of jobNameNl
     */
    public String getJobNameNl() {
        return jobNameNl;
    }

    /**
     * Setter method for jobNameNl.
     *
     * @param aJobNameNl the new value for jobNameNl
     */
    public void setJobNameNl(String aJobNameNl) {
        jobNameNl = aJobNameNl;
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
     * Access method for jobNameEn.
     *
     * @return the current value of jobNameEn
     */
    public String getJobNameEn() {
        return jobNameEn;
    }

    /**
     * Setter method for jobNameEn.
     *
     * @param aJobNameEn the new value for jobNameEn
     */
    public void setJobNameEn(String aJobNameEn) {
        jobNameEn = aJobNameEn;
    }

    /**
     * Access method for jobNameGe.
     *
     * @return the current value of jobNameGe
     */
    public String getJobNameGe() {
        return jobNameGe;
    }

    /**
     * Setter method for jobNameGe.
     *
     * @param aJobNameGe the new value for jobNameGe
     */
    public void setJobNameGe(String aJobNameGe) {
        jobNameGe = aJobNameGe;
    }

    /**
     * Compares the key for this instance with another TypeEmploi.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class TypeEmploi and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof TypeEmploi)) {
            return false;
        }
        TypeEmploi that = (TypeEmploi) other;
        if (this.getJobNr() != that.getJobNr()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another TypeEmploi.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TypeEmploi)) return false;
        return this.equalKeys(other) && ((TypeEmploi)other).equalKeys(this);
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
        i = getJobNr();
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
        StringBuffer sb = new StringBuffer("[TypeEmploi |");
        sb.append(" jobNr=").append(getJobNr());
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
        ret.put("jobNr", Integer.valueOf(getJobNr()));
        return ret;
    }

}