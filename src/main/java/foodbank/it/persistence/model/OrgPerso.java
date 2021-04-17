package foodbank.it.persistence.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="org_perso")
public class OrgPerso implements Serializable {

 /**
	 * 
	 */
	private static final long serialVersionUID = 6096056401525544503L;

/** Primary key. */
 protected static final String PK = "orgPersId";

 /**
  * The optimistic lock. Available via standard bean get/set operations.
  */
 

 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 @Column(name="org_pers_ID", unique=true, nullable=false, precision=10)
 private int orgPersId;
 @Column(precision=10)
 private int civilite;
 @Column(name="lien_asso", precision=10)
 private int lienAsso;
 @Column(length=50)
 private String nom;
 @Column(length=50)
 private String prenom;
 @Column(length=100)
 private String email;
 @Column(length=20)
 private String tel;
 @Column(length=20)
 private String gsm;
 @Column(length=100)
 private String fonction;
 @Column(precision=10)
 private int deleted;
 @Column(precision=10)
 private int distr;

 /** Default constructor. */
 public OrgPerso() {
     super();
 }

 public OrgPerso(int orgPersId, int civilite, int lienAsso, String nom, String prenom, String email, String tel,
		String gsm, String fonction, int deleted, int distr) {
	super();
	this.orgPersId = orgPersId;
	this.civilite = civilite;
	this.lienAsso = lienAsso;
	this.nom = nom;
	this.prenom = prenom;
	this.email = email;
	this.tel = tel;
	this.gsm = gsm;
	this.fonction = fonction;
	this.deleted = deleted;
	this.distr = distr;
}

/**
  * Access method for orgPersId.
  *
  * @return the current value of orgPersId
  */
 public int getOrgPersId() {
     return orgPersId;
 }

 /**
  * Setter method for orgPersId.
  *
  * @param aOrgPersId the new value for orgPersId
  */
 public void setOrgPersId(int aOrgPersId) {
     orgPersId = aOrgPersId;
 }

 /**
  * Access method for civilite.
  *
  * @return the current value of civilite
  */
 public int getCivilite() {
     return civilite;
 }

 /**
  * Setter method for civilite.
  *
  * @param aCivilite the new value for civilite
  */
 public void setCivilite(int aCivilite) {
     civilite = aCivilite;
 }

 /**
  * Access method for lienAsso.
  *
  * @return the current value of lienAsso
  */
 public int getLienAsso() {
     return lienAsso;
 }

 /**
  * Setter method for lienAsso.
  *
  * @param aLienAsso the new value for lienAsso
  */
 public void setLienAsso(int aLienAsso) {
     lienAsso = aLienAsso;
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
  * Access method for tel.
  *
  * @return the current value of tel
  */
 public String getTel() {
     return tel;
 }

 /**
  * Setter method for tel.
  *
  * @param aTel the new value for tel
  */
 public void setTel(String aTel) {
     tel = aTel;
 }

 /**
  * Access method for gsm.
  *
  * @return the current value of gsm
  */
 public String getGsm() {
     return gsm;
 }

 /**
  * Setter method for gsm.
  *
  * @param aGsm the new value for gsm
  */
 public void setGsm(String aGsm) {
     gsm = aGsm;
 }

 /**
  * Access method for fonction.
  *
  * @return the current value of fonction
  */
 public String getFonction() {
     return fonction;
 }

 /**
  * Setter method for fonction.
  *
  * @param aFonction the new value for fonction
  */
 public void setFonction(String aFonction) {
     fonction = aFonction;
 }

 /**
  * Access method for deleted.
  *
  * @return the current value of deleted
  */
 public int getDeleted() {
     return deleted;
 }

 /**
  * Setter method for deleted.
  *
  * @param aDeleted the new value for deleted
  */
 public void setDeleted(int aDeleted) {
     deleted = aDeleted;
 }

 /**
  * Access method for distr.
  *
  * @return the current value of distr
  */
 public int getDistr() {
     return distr;
 }

 /**
  * Setter method for distr.
  *
  * @param aDistr the new value for distr
  */
 public void setDistr(int aDistr) {
     distr = aDistr;
 }

 /**
  * Compares the key for this instance with another OrgPerso.
  *
  * @param other The object to compare to
  * @return True if other object is instance of class OrgPerso and the key objects are equal
  */
 private boolean equalKeys(Object other) {
     if (this==other) {
         return true;
     }
     if (!(other instanceof OrgPerso)) {
         return false;
     }
     OrgPerso that = (OrgPerso) other;
     if (this.getOrgPersId() != that.getOrgPersId()) {
         return false;
     }
     return true;
 }

 /**
  * Compares this instance with another OrgPerso.
  *
  * @param other The object to compare to
  * @return True if the objects are the same
  */
 @Override
 public boolean equals(Object other) {
     if (!(other instanceof OrgPerso)) return false;
     return this.equalKeys(other) && ((OrgPerso)other).equalKeys(this);
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
     i = getOrgPersId();
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
     StringBuffer sb = new StringBuffer("[OrgPerso |");
     sb.append(" orgPersId=").append(getOrgPersId());
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
     ret.put("orgPersId", Integer.valueOf(getOrgPersId()));
     return ret;
 }

}
