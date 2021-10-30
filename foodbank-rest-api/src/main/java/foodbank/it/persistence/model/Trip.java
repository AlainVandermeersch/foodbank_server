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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity(name="trip")
public class Trip implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1918164740180591613L;


	/** Primary key. */
    protected static final String PK = "tripId";

    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="trip_ID", unique=true, nullable=false, precision=10)
    private int tripId;
    @Column(name="trip_date", nullable=false)
    private String tripDate;
    @Column(name="trip_depart", nullable=false, length=30)
    private String tripDepart;
    @Column(name="trip_arrivee", nullable=false, length=30)
    private String tripArrivee;
    @Column(name="trip_km", nullable=false, length=10)
    private String tripKm;
    @Column(nullable=false, insertable=false,updatable=false)
    private int batnr;
    @Column(name="date_enreg", nullable=false)
    private LocalDateTime dateEnreg;
    @Column(precision=3)
    private short actif;
    @ManyToOne
    @JoinColumn(name="batnr")
    @NotFound(action = NotFoundAction.IGNORE)
    private Membre membreObject;
    
    /** Default constructor. */
    public Trip() {
        super();
    }

    
    public Trip(int tripId, String tripDate, String tripDepart, String tripArrivee, String tripKm,
			 short actif, Membre membreObject) {
		super();
		this.tripId = tripId;
		this.tripDate = tripDate;
		this.tripDepart = tripDepart;
		this.tripArrivee = tripArrivee;
		this.tripKm = tripKm;
		this.dateEnreg = LocalDateTime.now();
		this.actif = actif;
		this.membreObject = membreObject;
	}


	public Membre getMembreObject() {
		return membreObject;
	}

	public void setMembreObject(Membre membreObject) {
		this.membreObject = membreObject;
	}

  
    /**
     * Access method for tripId.
     *
     * @return the current value of tripId
     */
    public int getTripId() {
        return tripId;
    }

    /**
     * Setter method for tripId.
     *
     * @param aTripId the new value for tripId
     */
    public void setTripId(int aTripId) {
        tripId = aTripId;
    }

    /**
     * Access method for tripDate.
     *
     * @return the current value of tripDate
     */
    public String getTripDate() {
        return tripDate;
    }

    /**
     * Setter method for tripDate.
     *
     * @param aTripDate the new value for tripDate
     */
    public void setTripDate(String aTripDate) {
        tripDate = aTripDate;
    }

    /**
     * Access method for tripDepart.
     *
     * @return the current value of tripDepart
     */
    public String getTripDepart() {
        return tripDepart;
    }

    /**
     * Setter method for tripDepart.
     *
     * @param aTripDepart the new value for tripDepart
     */
    public void setTripDepart(String aTripDepart) {
        tripDepart = aTripDepart;
    }

    /**
     * Access method for tripArrivee.
     *
     * @return the current value of tripArrivee
     */
    public String getTripArrivee() {
        return tripArrivee;
    }

    /**
     * Setter method for tripArrivee.
     *
     * @param aTripArrivee the new value for tripArrivee
     */
    public void setTripArrivee(String aTripArrivee) {
        tripArrivee = aTripArrivee;
    }

    /**
     * Access method for tripKm.
     *
     * @return the current value of tripKm
     */
    public String getTripKm() {
        return tripKm;
    }

    /**
     * Setter method for tripKm.
     *
     * @param aTripKm the new value for tripKm
     */
    public void setTripKm(String aTripKm) {
        tripKm = aTripKm;
    }

    /**
     * Access method for batnr.
     *
     * @return the current value of batnr
     */
    public int getBatnr() {
        return batnr;
    }

    /**
     * Setter method for batnr.
     *
     * @param aBatnr the new value for batnr
     */
    public void setBatnr(int aBatnr) {
        batnr = aBatnr;
    }

    /**
     * Access method for dateEnreg.
     *
     * @return the current value of dateEnreg
     */
    public LocalDateTime getDateEnreg() {
        return dateEnreg;
    }

    /**
     * Setter method for dateEnreg.
     *
     * @param aDateEnreg the new value for dateEnreg
     */
    public void setDateEnreg(LocalDateTime aDateEnreg) {
        dateEnreg = aDateEnreg;
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
     * Compares the key for this instance with another Trip.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Trip and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Trip)) {
            return false;
        }
        Trip that = (Trip) other;
        if (this.getTripId() != that.getTripId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Trip.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Trip)) return false;
        return this.equalKeys(other) && ((Trip)other).equalKeys(this);
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
        i = getTripId();
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
        StringBuffer sb = new StringBuffer("[Trip |");
        sb.append(" tripId=").append(getTripId());
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
        ret.put("tripId", Integer.valueOf(getTripId()));
        return ret;
    }

}
