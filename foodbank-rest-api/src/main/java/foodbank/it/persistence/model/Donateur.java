// Generated with g9.

package foodbank.it.persistence.model;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity(name="donateurs")
public class Donateur implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -18147674499380802L;
	/** Primary key. */
    protected static final String PK = "donateurId";   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="donateur_id", unique=true, nullable=false, precision=10)
    private int donateurId;
    @Column(name="lien_banque", nullable=false, precision=3)
    private short lienBanque;
    @Column(nullable=false, length=50)
    private String nom;
    @Column(nullable=false, length=30)
    private String prenom;
    @Column(nullable=false, length=100)
    private String adresse;
    @Column(nullable=false, precision=5)
    private int cp;
    @Column(nullable=false, length=50)
    private String city;
    @Column(nullable=false, length=50)
    private String pays;
    @Column(nullable=false, length=40)
    private String titre;
    @Formula("(select sum(d.amount) from dons d where d.donateur_id = donateur_id)")
    private Long totalDons;
   



	public int getDonateurId() {
		return donateurId;
	}


	public void setDonateurId(int donateurId) {
		this.donateurId = donateurId;
	}


	/**
     * Access method for lienBanque.
     *
     * @return the current value of lienBanque
     */
    public short getLienBanque() {
        return lienBanque;
    }

    /**
     * Setter method for lienBanque.
     *
     * @param aLienBanque the new value for lienBanque
     */
    public void setLienBanque(short aLienBanque) {
        lienBanque = aLienBanque;
    }

    /**
     * Access method for nom.
     *
     * @return the current value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter method for nom.
     *
     * @param aNom the new value for nom
     */
    public void setNom(String aNom) {
        nom = aNom;
    }

    /**
     * Access method for prenom.
     *
     * @return the current value of prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter method for prenom.
     *
     * @param aPrenom the new value for prenom
     */
    public void setPrenom(String aPrenom) {
        prenom = aPrenom;
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
    public int getCp() {
        return cp;
    }

    /**
     * Setter method for cp.
     *
     * @param aCp the new value for cp
     */
    public void setCp(int aCp) {
        cp = aCp;
    }

    /**
     * Access method for city.
     *
     * @return the current value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter method for city.
     *
     * @param aCity the new value for city
     */
    public void setCity(String aCity) {
        city = aCity;
    }

    /**
     * Access method for pays.
     *
     * @return the current value of pays
     */
    public String getPays() {
        return pays;
    }

    /**
     * Setter method for pays.
     *
     * @param aPays the new value for pays
     */
    public void setPays(String aPays) {
        pays = aPays;
    }

    /**
     * Access method for titre.
     *
     * @return the current value of titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Setter method for titre.
     *
     * @param aTitre the new value for titre
     */
    public void setTitre(String aTitre) {
        titre = aTitre;
    }

    public Long getTotalDons() {
        return totalDons;
    }

    /**
     * Compares the key for this instance with another DonationBat.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class DonationBat and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Donateur)) {
            return false;
        }
        Donateur that = (Donateur) other;
        if (this.getDonateurId() != that.getDonateurId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another DonationBat.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Donateur)) return false;
        return this.equalKeys(other) && ((Donateur)other).equalKeys(this);
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
        i = getDonateurId();
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
        StringBuffer sb = new StringBuffer("[DonationBat |");
        sb.append(" donId=").append(getDonateurId());
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
        ret.put("donId", Integer.valueOf(getDonateurId()));
        return ret;
    }

}
