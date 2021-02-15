//Generated with g9.

package foodbank.it.persistence.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="cpas")
public class Cpas implements Serializable {

 /**
	 * 
	 */
	private static final long serialVersionUID = 5506620177057042121L;


/** Primary key. */
 protected static final String PK = "cpasId";

 
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 @Column(name="cpas_ID", unique=true, nullable=false, precision=10)
 private int cpasId;
 @Column(name="cpas_zip", nullable=false, length=4)
 private String cpasZip;
 @Column(name="cpas_name", nullable=false, length=255)
 private String cpasName;
 @Column(name="cpas_mail", nullable=false, length=50)
 private String cpasMail;
 @Column(name="cpas_street", nullable=false, length=50)
 private String cpasStreet;
 @Column(name="cpas_tel", nullable=false, length=20)
 private String cpasTel;
 @Column(name="cpas_gsm", nullable=false, length=20)
 private String cpasGsm;
 @Column(name="cpas_contact_name", nullable=false, length=30)
 private String cpasContactName;
 @Column(name="cpas_contact_surname", nullable=false, length=50)
 private String cpasContactSurname;
 @Column(nullable=false, precision=3)
 private short civilite;
 @Column(nullable=false)
 private String rem;
 @Column(nullable=false, length=12)
 private String password;
 @Column(name="l_banque", nullable=false, precision=3)
 private short lBanque;
 @Column(precision=3)
 private short langue;

 /** Default constructor. */
 public Cpas() {
     super();
 }

 public Cpas(int cpasId, String cpasZip, String cpasName, String cpasMail, String cpasStreet, String cpasTel, String cpasGsm,
		String cpasContactName, String cpasContactSurname, short civilite, String rem, String password, short lBanque,
		short langue) {
	super();
	this.cpasId = cpasId;
	this.cpasZip = cpasZip;
	this.cpasName = cpasName;
	this.cpasMail = cpasMail;
	this.cpasStreet = cpasStreet;
	this.cpasTel = cpasTel;
	this.cpasGsm = cpasGsm;
	this.cpasContactName = cpasContactName;
	this.cpasContactSurname = cpasContactSurname;
	this.civilite = civilite;
	this.rem = rem;
	this.password = password;
	this.lBanque = lBanque;
	this.langue = langue;
}

/**
  * Access method for cpasId.
  *
  * @return the current value of cpasId
  */
 public int getCpasId() {
     return cpasId;
 }

 /**
  * Setter method for cpasId.
  *
  * @param aCpasId the new value for cpasId
  */
 public void setCpasId(int aCpasId) {
     cpasId = aCpasId;
 }

 /**
  * Access method for cpasZip.
  *
  * @return the current value of cpasZip
  */
 public String getCpasZip() {
     return cpasZip;
 }

 /**
  * Setter method for cpasZip.
  *
  * @param aCpasZip the new value for cpasZip
  */
 public void setCpasZip(String aCpasZip) {
     cpasZip = aCpasZip;
 }

 /**
  * Access method for cpasName.
  *
  * @return the current value of cpasName
  */
 public String getCpasName() {
     return cpasName;
 }

 /**
  * Setter method for cpasName.
  *
  * @param aCpasName the new value for cpasName
  */
 public void setCpasName(String aCpasName) {
     cpasName = aCpasName;
 }

 /**
  * Access method for cpasMail.
  *
  * @return the current value of cpasMail
  */
 public String getCpasMail() {
     return cpasMail;
 }

 /**
  * Setter method for cpasMail.
  *
  * @param aCpasMail the new value for cpasMail
  */
 public void setCpasMail(String aCpasMail) {
     cpasMail = aCpasMail;
 }

 /**
  * Access method for cpasStreet.
  *
  * @return the current value of cpasStreet
  */
 public String getCpasStreet() {
     return cpasStreet;
 }

 /**
  * Setter method for cpasStreet.
  *
  * @param aCpasStreet the new value for cpasStreet
  */
 public void setCpasStreet(String aCpasStreet) {
     cpasStreet = aCpasStreet;
 }

 /**
  * Access method for cpasTel.
  *
  * @return the current value of cpasTel
  */
 public String getCpasTel() {
     return cpasTel;
 }

 /**
  * Setter method for cpasTel.
  *
  * @param aCpasTel the new value for cpasTel
  */
 public void setCpasTel(String aCpasTel) {
     cpasTel = aCpasTel;
 }

 /**
  * Access method for cpasGsm.
  *
  * @return the current value of cpasGsm
  */
 public String getCpasGsm() {
     return cpasGsm;
 }

 /**
  * Setter method for cpasGsm.
  *
  * @param aCpasGsm the new value for cpasGsm
  */
 public void setCpasGsm(String aCpasGsm) {
     cpasGsm = aCpasGsm;
 }

 /**
  * Access method for cpasContactName.
  *
  * @return the current value of cpasContactName
  */
 public String getCpasContactName() {
     return cpasContactName;
 }

 /**
  * Setter method for cpasContactName.
  *
  * @param aCpasContactName the new value for cpasContactName
  */
 public void setCpasContactName(String aCpasContactName) {
     cpasContactName = aCpasContactName;
 }

 /**
  * Access method for cpasContactSurname.
  *
  * @return the current value of cpasContactSurname
  */
 public String getCpasContactSurname() {
     return cpasContactSurname;
 }

 /**
  * Setter method for cpasContactSurname.
  *
  * @param aCpasContactSurname the new value for cpasContactSurname
  */
 public void setCpasContactSurname(String aCpasContactSurname) {
     cpasContactSurname = aCpasContactSurname;
 }

 /**
  * Access method for civilite.
  *
  * @return the current value of civilite
  */
 public short getCivilite() {
     return civilite;
 }

 /**
  * Setter method for civilite.
  *
  * @param aCivilite the new value for civilite
  */
 public void setCivilite(short aCivilite) {
     civilite = aCivilite;
 }

 /**
  * Access method for rem.
  *
  * @return the current value of rem
  */
 public String getRem() {
     return rem;
 }

 /**
  * Setter method for rem.
  *
  * @param aRem the new value for rem
  */
 public void setRem(String aRem) {
     rem = aRem;
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
  * Access method for lBanque.
  *
  * @return the current value of lBanque
  */
 public short getLBanque() {
     return lBanque;
 }

 /**
  * Setter method for lBanque.
  *
  * @param aLBanque the new value for lBanque
  */
 public void setLBanque(short aLBanque) {
     lBanque = aLBanque;
 }

 /**
  * Access method for langue.
  *
  * @return the current value of langue
  */
 public short getLangue() {
     return langue;
 }

 /**
  * Setter method for langue.
  *
  * @param aLangue the new value for langue
  */
 public void setLangue(short aLangue) {
     langue = aLangue;
 }

 /**
  * Compares the key for this instance with another Cpas.
  *
  * @param other The object to compare to
  * @return True if other object is instance of class Cpas and the key objects are equal
  */
 private boolean equalKeys(Object other) {
     if (this==other) {
         return true;
     }
     if (!(other instanceof Cpas)) {
         return false;
     }
     Cpas that = (Cpas) other;
     if (this.getCpasId() != that.getCpasId()) {
         return false;
     }
     return true;
 }

 /**
  * Compares this instance with another Cpas.
  *
  * @param other The object to compare to
  * @return True if the objects are the same
  */
 @Override
 public boolean equals(Object other) {
     if (!(other instanceof Cpas)) return false;
     return this.equalKeys(other) && ((Cpas)other).equalKeys(this);
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
     i = getCpasId();
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
     StringBuffer sb = new StringBuffer("[Cpas |");
     sb.append(" cpasId=").append(getCpasId());
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
     ret.put("cpasId", Integer.valueOf(getCpasId()));
     return ret;
 }

}