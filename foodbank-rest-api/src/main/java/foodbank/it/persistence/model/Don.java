
package foodbank.it.persistence.model;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity(name="dons")
public class Don implements Serializable {

 /**
	 * 
	 */
private static final long serialVersionUID = -893007371697272121L;
/** Primary key. */
 protected static final String PK = "idDon";

 


 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 @Column(name="id_don", unique=true, nullable=false, precision=10)
 private int idDon;
 @Column(nullable=false, precision=10)
 private int amount;
 @Column(name="lien_banque", nullable=false, precision=3)
 private int lienBanque;
 @Column(name="donateur_id", nullable=false, precision=10)
 private int donateurId;
 @Column(name="date_entered", nullable=false)
 private LocalDateTime dateEntered;
 @Column(nullable=false, precision=3)
 private short appended;
 @Column(nullable=false, precision=3)
 private short checked;
 private LocalDate date;
 @Formula("(select e.nom from donateurs e where e.donateur_id = donateur_id)")
 private String donateurNom;
 @Formula("(select e.prenom from donateurs e where e.donateur_id = donateur_id)")
 private String donateurPrenom;
 @Transient
 private long totalAmount;


/**
  * Access method for idDon.
  *
  * @return the current value of idDon
  */
 public int getIdDon() {
     return idDon;
 }

 /**
  * Setter method for idDon.
  *
  * @param aIdDon the new value for idDon
  */
 public void setIdDon(int aIdDon) {
     idDon = aIdDon;
 }

 /**
  * Access method for amount.
  *
  * @return the current value of amount
  */
 public int getAmount() {
     return amount;
 }

 /**
  * Setter method for amount.
  *
  * @param aAmount the new value for amount
  */
 public void setAmount(int aAmount) {
     amount = aAmount;
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
  * Access method for donateurId.
  *
  * @return the current value of donateurId
  */
 public int getDonateurId() {
     return donateurId;
 }

 /**
  * Setter method for donateurId.
  *
  * @param aDonateurId the new value for donateurId
  */
 public void setDonateurId(int aDonateurId) {
     donateurId = aDonateurId;
 }

 /**
  * Access method for dateEntered.
  *
  * @return the current value of dateEntered
  */
 public LocalDateTime getDateEntered() {
     return dateEntered;
 }

 /**
  * Setter method for dateEntered.
  *
  * @param aDateEntered the new value for dateEntered
  */
 public void setDateEntered(LocalDateTime aDateEntered) {
     dateEntered = aDateEntered;
 }

 
/**
  * Access method for appended.
  *
  * @return the current value of appended
  */
 public short getAppended() {
     return appended;
 }

 /**
  * Setter method for appended.
  *
  * @param aAppended the new value for appended
  */
 public void setAppended(short aAppended) {
     appended = aAppended;
 }

 /**
  * Access method for checked.
  *
  * @return the current value of checked
  */
 public short getChecked() {
     return checked;
 }

 /**
  * Setter method for checked.
  *
  * @param aChecked the new value for checked
  */
 public void setChecked(short aChecked) {
     checked = aChecked;
 }

 /**
  * Access method for date.
  *
  * @return the current value of date
  */
 public LocalDate getDate() {
     return date;
 }

 /**
  * Setter method for date.
  *
  * @param aDate the new value for date
  */
 public void setDate(LocalDate aDate) {
     date = aDate;
 }

 public String getDonateurNom() {
	return donateurNom;
}

public String getDonateurPrenom() {
	return donateurPrenom;
}

public void setDonateurNom(String donateurNom) {
	this.donateurNom = donateurNom;
}

public void setDonateurPrenom(String donateurPrenom) {
	this.donateurPrenom = donateurPrenom;
}

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
  * Compares the key for this instance with another Don.
  *
  * @param other The object to compare to
  * @return True if other object is instance of class Don and the key objects are equal
  */
 private boolean equalKeys(Object other) {
     if (this==other) {
         return true;
     }
     if (!(other instanceof Don)) {
         return false;
     }
     Don that = (Don) other;
     if (this.getIdDon() != that.getIdDon()) {
         return false;
     }
     return true;
 }

 /**
  * Compares this instance with another Don.
  *
  * @param other The object to compare to
  * @return True if the objects are the same
  */
 @Override
 public boolean equals(Object other) {
     if (!(other instanceof Don)) return false;
     return this.equalKeys(other) && ((Don)other).equalKeys(this);
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
     i = getIdDon();
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
     StringBuffer sb = new StringBuffer("[Don |");
     sb.append(" idDon=").append(getIdDon());
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
     ret.put("idDon", Integer.valueOf(getIdDon()));
     return ret;
 }

}
