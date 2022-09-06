package foodbank.it.persistence.model;

//Generated with g9.

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="depots")
public class Depot implements Serializable {

 /**
	 * 
	 */
	private static final long serialVersionUID = -312582189985506840L;


/** Primary key. */
 protected static final String PK = "idDepot";

 
 @Id
 @Column(name="ID_DEPOT", unique=true, nullable=false, length=10)
 private String idDepot;
 @Column(name="NOM", length=45)
 private String nom;
 @Column(name="ADRESSE", length=45)
 private String adresse;
 @Column(name="ADRESSE_2", length=45)
 private String adresse2;
 @Column(name="CP", length=8)
 private String cp;
 @Column(name="VILLE", length=45)
 private String ville;
 @Column(name="ID_COMPANY", length=45)
 private String idCompany;
 @Column(name="TELEPHONE", length=20)
 private String telephone;
 @Column(name="CONTACT", length=45)
 private String contact;
 @Column(name="EMAIL", length=45)
 private String email;
 @Column(name="MEMO")
 private String memo;
 @Column(name="DEP_PRINC", precision=3)
 private short depPrinc;
 @Column(precision=3)
 private short actif;
 @Column(name="DEP_FEAD", precision=3)
 private short depFead;
 @Column(name="lien_banque")
 private Integer lienBanque;
 
 
 /** Default constructor. */
 public Depot() {
     super();
 }

 
 public Depot(String idDepot, String nom, String adresse, String adresse2, String cp, String ville, String idCompany,String telephone,
		String contact, String email, String memo, short depPrinc, short actif, short depFead, Integer lienBanque) {
	super();
	this.idDepot = idDepot;
	this.nom = nom;
	this.adresse = adresse;
	this.adresse2 = adresse2;
	this.cp = cp;
	this.ville = ville;
	this.idCompany = idCompany;
	this.telephone = telephone;
	this.contact = contact;
	this.email = email;
	this.memo = memo;
	this.depPrinc = depPrinc;
	this.actif = actif;
	this.depFead = depFead;
	this.lienBanque = lienBanque;
}


/**
  * Access method for idDepot.
  *
  * @return the current value of idDepot
  */
 public String getIdDepot() {
     return idDepot;
 }

 /**
  * Setter method for idDepot.
  *
  * @param aIdDepot the new value for idDepot
  */
 public void setIdDepot(String aIdDepot) {
     idDepot = aIdDepot;
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
  * Access method for adresse.
  *
  * @return the current value of adresse
  */
 public String getAdresse() {
     if (adresse == null) return "";
     else return adresse;
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
  * Access method for adresse2.
  *
  * @return the current value of adresse2
  */
 public String getAdresse2() {
     if (adresse2 == null) return "";
     else return adresse2;
 }

 /**
  * Setter method for adresse2.
  *
  * @param aAdresse2 the new value for adresse2
  */
 public void setAdresse2(String aAdresse2) {
     adresse2 = aAdresse2;
 }

 /**
  * Access method for cp.
  *
  * @return the current value of cp
  */
 public String getCp() {
     if (cp == null) return "";
     else return cp;
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
  * Access method for ville.
  *
  * @return the current value of ville
  */
 public String getVille() {

     if (ville == null) return "";
     else return ville;
 }

 /**
  * Setter method for ville.
  *
  * @param aVille the new value for ville
  */
 public void setVille(String aVille) {
     ville = aVille;
 }

 public String getIdCompany() {
	return idCompany;
}


public void setIdCompany(String idCompany) {
	this.idCompany = idCompany;
}


/**
  * Access method for telephone.
  *
  * @return the current value of telephone
  */
 public String getTelephone() {

     if (telephone == null) return "";
     else return telephone;
 }

 /**
  * Setter method for telephone.
  *
  * @param aTelephone the new value for telephone
  */
 public void setTelephone(String aTelephone) {
     telephone = aTelephone;
 }

 /**
  * Access method for contact.
  *
  * @return the current value of contact
  */
 public String getContact() {

     if (contact == null) return "";
     else return contact;
 }

 /**
  * Setter method for contact.
  *
  * @param aContact the new value for contact
  */
 public void setContact(String aContact) {
     contact = aContact;
 }

 /**
  * Access method for email.
  *
  * @return the current value of email
  */
 public String getEmail() {

     if (email == null) return "";
     else return email;
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
  * Access method for memo.
  *
  * @return the current value of memo
  */
 public String getMemo() {

     if (memo == null) return "";
     else return memo;
 }

 /**
  * Setter method for memo.
  *
  * @param aMemo the new value for memo
  */
 public void setMemo(String aMemo) {
     memo = aMemo;
 }

 /**
  * Access method for depPrinc.
  *
  * @return the current value of depPrinc
  */
 public short getDepPrinc() {
     return depPrinc;
 }

 /**
  * Setter method for depPrinc.
  *
  * @param aDepPrinc the new value for depPrinc
  */
 public void setDepPrinc(short aDepPrinc) {
     depPrinc = aDepPrinc;
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
  * Access method for depFead.
  *
  * @return the current value of depFead
  */
 public short getDepFead() {
     return depFead;
 }

 /**
  * Setter method for depFead.
  *
  * @param aDepFead the new value for depFead
  */
 public void setDepFead(short aDepFead) {
     depFead = aDepFead;
 }

 
 public Integer getLienBanque() {
	return lienBanque;
}


public void setLienBanque(Integer lienBanque) {
	this.lienBanque = lienBanque;
}


/**
  * Compares the key for this instance with another Depot.
  *
  * @param other The object to compare to
  * @return True if other object is instance of class Depot and the key objects are equal
  */
 private boolean equalKeys(Object other) {
     if (this==other) {
         return true;
     }
     if (!(other instanceof Depot)) {
         return false;
     }
     Depot that = (Depot) other;
     Object myIdDepot = this.getIdDepot();
     Object yourIdDepot = that.getIdDepot();
     if (myIdDepot==null ? yourIdDepot!=null : !myIdDepot.equals(yourIdDepot)) {
         return false;
     }
     return true;
 }

 /**
  * Compares this instance with another Depot.
  *
  * @param other The object to compare to
  * @return True if the objects are the same
  */
 @Override
 public boolean equals(Object other) {
     if (!(other instanceof Depot)) return false;
     return this.equalKeys(other) && ((Depot)other).equalKeys(this);
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
     if (getIdDepot() == null) {
         i = 0;
     } else {
         i = getIdDepot().hashCode();
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
     StringBuffer sb = new StringBuffer("[Depot |");
     sb.append(" idDepot=").append(getIdDepot());
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
     ret.put("idDepot", getIdDepot());
     return ret;
 }

}