//Generated with g9.

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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;



@Entity
@Table(name="bat", indexes={@Index(name="batName", columnList="nom,prenom")})
public class Membre implements Serializable {

 /**
	 * 
	 */
	private static final long serialVersionUID = 8583967731561881562L;


/** Primary key. */
 protected static final String PK = "batId";

 
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 @Column(name="bat_ID", unique=true, nullable=false, precision=10)
 private Integer batId;
 @Column(name="lien_dis", precision=10)
 private Integer lienDis;
 @Column(nullable=false, length=50)
 private String nom;
 @Column(nullable=false, length=40)
 private String prenom;
 @Column(length=50)
 private String address;
 @Column(length=50)
 private String city;
 @Column(length=10)
 private String zip;
 @Column(length=20)
 private String tel;
 @Column(length=20)
 private String gsm;
 @Column(length=50)
 private String batmail;
 @Column(length=30)
 private String veh;
 @Column(name="veh_typ", length=30)
 private String vehTyp;
 @Column(name="veh_imm", length=30)
 private String vehImm;
 @Column(precision=10)
 private Integer fonction;
 @Column(precision=3)
 private Short ca;
 @Column(precision=3)
 private Short ag;
 @Column(precision=3)
 private Short cg;
 @Column(precision=3)
 private Short civilite;
 @Column(precision=3)
 private Short pays;
 @Column(nullable=false, precision=3)
 private Short actif;
 @Column(nullable=false, precision=3)
 private Short authority;
 private String datmand;
 private String rem;
 @Column(name="last_visit")
 private LocalDateTime lastVisit;
 @Column(nullable=false, precision=3)
 private Short ben;
 @Column(name="code_acces", precision=5)
 private Short codeAcces;
 @Column(name="nr_code_acces", precision=5)
 private Short nrCodeAcces;
 @Column(precision=5)
 private Short langue;
 private String datedeb;
 @Column(name="date_fin")
 private String dateFin;
 @Column(precision=3)
 private Short deleted;
 @Column(name="typ_emploi", precision=3)
 private Short typEmploi;
 @Column(name="date_naissance")
 private String dateNaissance;
 @Column(length=20)
 private String nnat;
 @Column(name="date_contrat")
 private String dateContrat;
 @Column(name="l_dep", precision=10)
 private Integer lDep;
 @Column(name="lien_banque", precision=10)
 private Short lienBanque; 
 @Formula("(select d.societe from organisations d where d.id_dis = lien_dis)")
 private String societe;
 @Formula("(select d.lien_depot from organisations d where d.id_dis = lien_dis)")
 private String lienDepot;


 /** Default constructor. */
 public Membre() {
     super();
 }

 public Membre(Integer batId, Integer lienDis, String nom, String prenom, String address, String city,
		String zip, String tel, String gsm, String batmail, String veh, String vehTyp, String vehImm, Integer fonction,
		Short ca, Short ag, Short cg, Short civilite, Short pays, Short actif, Short authority, String datmand,
		String rem, Short ben, Short codeAcces, Short nrCodeAcces, Short langue,
		String datedeb, String dateFin, Short deleted, Short typEmploi, String dateNaissance, String nnat,
		String dateContrat, Integer lDep, Short lienBanque) {
	super();	
	this.batId = batId;
	this.lienDis = lienDis;
	this.nom = nom;
	this.prenom = prenom;
	this.address = address;
	this.city = city;
	this.zip = zip;
	this.tel = tel;
	this.gsm = gsm;
	this.batmail = batmail;
	this.veh = veh;
	this.vehTyp = vehTyp;
	this.vehImm = vehImm;
	this.fonction = fonction;
	this.ca = ca;
	this.ag = ag;
	this.cg = cg;
	this.civilite = civilite;
	this.pays = pays;
	this.actif = actif;
	this.authority = authority;
	this.datmand = datmand;
	this.rem = rem;
	this.ben = ben;
	this.codeAcces = codeAcces;
	this.nrCodeAcces = nrCodeAcces;
	this.langue = langue;
	this.datedeb = datedeb;
	this.dateFin = dateFin;
	this.deleted = deleted;
	this.typEmploi = typEmploi;
	this.dateNaissance = dateNaissance;
	this.nnat = nnat;
	this.dateContrat = dateContrat;
	this.lDep = lDep;
	this.lastVisit = LocalDateTime.now(); // do not use lastVisit from DTO we need to update the time
	this.lienBanque = lienBanque;
}



/**
  * Access method for batId.
  *
  * @return the current value of batId
  */
 public Integer getBatId() {
     return batId;
 }

 /**
  * Setter method for batId.
  *
  * @param aBatId the new value for batId
  */
 public void setBatId(Integer aBatId) {
     batId = aBatId;
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
  * Access method for address.
  *
  * @return the current value of address
  */
 public String getAddress() {
     return address;
 }

 /**
  * Setter method for address.
  *
  * @param aAddress the new value for address
  */
 public void setAddress(String aAddress) {
     address = aAddress;
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
  * Access method for zip.
  *
  * @return the current value of zip
  */
 public String getZip() {
     return zip;
 }

 /**
  * Setter method for zip.
  *
  * @param aZip the new value for zip
  */
 public void setZip(String aZip) {
     zip = aZip;
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
  * Access method for batmail.
  *
  * @return the current value of batmail
  */
 public String getBatmail() {
     return batmail;
 }

 /**
  * Setter method for batmail.
  *
  * @param aBatmail the new value for batmail
  */
 public void setBatmail(String aBatmail) {
     batmail = aBatmail;
 }

 /**
  * Access method for veh.
  *
  * @return the current value of veh
  */
 public String getVeh() {
     return veh;
 }

 /**
  * Setter method for veh.
  *
  * @param aVeh the new value for veh
  */
 public void setVeh(String aVeh) {
     veh = aVeh;
 }

 /**
  * Access method for vehTyp.
  *
  * @return the current value of vehTyp
  */
 public String getVehTyp() {
     return vehTyp;
 }

 /**
  * Setter method for vehTyp.
  *
  * @param aVehTyp the new value for vehTyp
  */
 public void setVehTyp(String aVehTyp) {
     vehTyp = aVehTyp;
 }

 /**
  * Access method for vehImm.
  *
  * @return the current value of vehImm
  */
 public String getVehImm() {
     return vehImm;
 }

 /**
  * Setter method for vehImm.
  *
  * @param aVehImm the new value for vehImm
  */
 public void setVehImm(String aVehImm) {
     vehImm = aVehImm;
 }

 /**
  * Access method for fonction.
  *
  * @return the current value of fonction
  */
 public Integer getFonction() {
     return fonction;
 }

 /**
  * Setter method for fonction.
  *
  * @param aFonction the new value for fonction
  */
 public void setFonction(Integer aFonction) {
     fonction = aFonction;
 }

 /**
  * Access method for ca.
  *
  * @return the current value of ca
  */
 public Short getCa() {
     return ca;
 }

 /**
  * Setter method for ca.
  *
  * @param aCa the new value for ca
  */
 public void setCa(Short aCa) {
     ca = aCa;
 }

 /**
  * Access method for ag.
  *
  * @return the current value of ag
  */
 public Short getAg() {
     return ag;
 }

 /**
  * Setter method for ag.
  *
  * @param aAg the new value for ag
  */
 public void setAg(Short aAg) {
     ag = aAg;
 }

 /**
  * Access method for cg.
  *
  * @return the current value of cg
  */
 public Short getCg() {
     return cg;
 }

 /**
  * Setter method for cg.
  *
  * @param aCg the new value for cg
  */
 public void setCg(Short aCg) {
     cg = aCg;
 }

 /**
  * Access method for civilite.
  *
  * @return the current value of civilite
  */
 public Short getCivilite() {
     return civilite;
 }

 /**
  * Setter method for civilite.
  *
  * @param aCivilite the new value for civilite
  */
 public void setCivilite(Short aCivilite) {
     civilite = aCivilite;
 }

 /**
  * Access method for pays.
  *
  * @return the current value of pays
  */
 public Short getPays() {
     return pays;
 }

 /**
  * Setter method for pays.
  *
  * @param aPays the new value for pays
  */
 public void setPays(Short aPays) {
     pays = aPays;
 }

 /**
  * Access method for actif.
  *
  * @return the current value of actif
  */
 public Short getActif() {
     return actif;
 }

 /**
  * Setter method for actif.
  *
  * @param aActif the new value for actif
  */
 public void setActif(Short aActif) {
     actif = aActif;
 }

 /**
  * Access method for authority.
  *
  * @return the current value of authority
  */
 public Short getAuthority() {
     return authority;
 }

 /**
  * Setter method for authority.
  *
  * @param aAuthority the new value for authority
  */
 public void setAuthority(Short aAuthority) {
     authority = aAuthority;
 }

 /**
  * Access method for datmand.
  *
  * @return the current value of datmand
  */
 public String getDatmand() {
     return datmand;
 }

 /**
  * Setter method for datmand.
  *
  * @param aDatmand the new value for datmand
  */
 public void setDatmand(String aDatmand) {
     datmand = aDatmand;
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
  * Access method for ben.
  *
  * @return the current value of ben
  */
 public Short getBen() {
     return ben;
 }

 /**
  * Setter method for ben.
  *
  * @param aBen the new value for ben
  */
 public void setBen(Short aBen) {
     ben = aBen;
 }

 /**
  * Access method for codeAcces.
  *
  * @return the current value of codeAcces
  */
 public Short getCodeAcces() {
     return codeAcces;
 }

 /**
  * Setter method for codeAcces.
  *
  * @param aCodeAcces the new value for codeAcces
  */
 public void setCodeAcces(Short aCodeAcces) {
     codeAcces = aCodeAcces;
 }

 /**
  * Access method for nrCodeAcces.
  *
  * @return the current value of nrCodeAcces
  */
 public Short getNrCodeAcces() {
     return nrCodeAcces;
 }

 /**
  * Setter method for nrCodeAcces.
  *
  * @param aNrCodeAcces the new value for nrCodeAcces
  */
 public void setNrCodeAcces(Short aNrCodeAcces) {
     nrCodeAcces = aNrCodeAcces;
 }

 /**
  * Access method for langue.
  *
  * @return the current value of langue
  */
 public Short getLangue() {
     return langue;
 }

 /**
  * Setter method for langue.
  *
  * @param aLangue the new value for langue
  */
 public void setLangue(Short aLangue) {
     langue = aLangue;
 }

 /**
  * Access method for datedeb.
  *
  * @return the current value of datedeb
  */
 public String getDatedeb() {
     return datedeb;
 }

 /**
  * Setter method for datedeb.
  *
  * @param aDatedeb the new value for datedeb
  */
 public void setDatedeb(String aDatedeb) {
     datedeb = aDatedeb;
 }

 /**
  * Access method for dateFin.
  *
  * @return the current value of dateFin
  */
 public String getDateFin() {
     return dateFin;
 }

 /**
  * Setter method for dateFin.
  *
  * @param aDateFin the new value for dateFin
  */
 public void setDateFin(String aDateFin) {
     dateFin = aDateFin;
 }

 /**
  * Access method for deleted.
  *
  * @return the current value of deleted
  */
 public Short getDeleted() {
     return deleted;
 }

 /**
  * Setter method for deleted.
  *
  * @param aDeleted the new value for deleted
  */
 public void setDeleted(Short aDeleted) {
     deleted = aDeleted;
 }

 /**
  * Access method for typEmploi.
  *
  * @return the current value of typEmploi
  */
 public Short getTypEmploi() {
     return typEmploi;
 }

 /**
  * Setter method for typEmploi.
  *
  * @param aTypEmploi the new value for typEmploi
  */
 public void setTypEmploi(Short aTypEmploi) {
     typEmploi = aTypEmploi;
 }

 /**
  * Access method for dateNaissance.
  *
  * @return the current value of dateNaissance
  */
 public String getDateNaissance() {
     return dateNaissance;
 }

 /**
  * Setter method for dateNaissance.
  *
  * @param aDateNaissance the new value for dateNaissance
  */
 public void setDateNaissance(String aDateNaissance) {
     dateNaissance = aDateNaissance;
 }

 /**
  * Access method for nnat.
  *
  * @return the current value of nnat
  */
 public String getNnat() {
     return nnat;
 }

 /**
  * Setter method for nnat.
  *
  * @param aNnat the new value for nnat
  */
 public void setNnat(String aNnat) {
     nnat = aNnat;
 }

 /**
  * Access method for dateContrat.
  *
  * @return the current value of dateContrat
  */
 public String getDateContrat() {
     return dateContrat;
 }

 /**
  * Setter method for dateContrat.
  *
  * @param aDateContrat the new value for dateContrat
  */
 public void setDateContrat(String aDateContrat) {
     dateContrat = aDateContrat;
 }

 /**
  * Access method for lDep.
  *
  * @return the current value of lDep
  */
 public Integer getLDep() {
     return lDep;
 }

 /**
  * Setter method for lDep.
  *
  * @param aLDep the new value for lDep
  */
 public void setLDep(Integer aLDep) {
     lDep = aLDep;
 }
 
 public LocalDateTime getLastVisit() {
	return lastVisit;
}

public void setLastVisit(LocalDateTime lastVisit) {
	this.lastVisit = lastVisit;
}

 public Short getLienBanque() {
	return lienBanque;
}

public void setLienBanque(Short lienBanque) {
	this.lienBanque = lienBanque;
}



public Integer getLienDis() {
	return lienDis;
}
public void setLienDis(Integer lienDis) {
	this.lienDis = lienDis;
}


public String getSociete() {
	return societe;
}

public String getLienDepot() {
	return lienDepot;
}

/**
  * Compares the key for this instance with another Membre.
  *
  * @param other The object to compare to
  * @return True if other object is instance of class Membre and the key objects are equal
  */
 private boolean equalKeys(Object other) {
     if (this==other) {
         return true;
     }
     if (!(other instanceof Membre)) {
         return false;
     }
     Membre that = (Membre) other;
     if (this.getBatId() != that.getBatId()) {
         return false;
     }
     return true;
 }

 /**
  * Compares this instance with another Membre.
  *
  * @param other The object to compare to
  * @return True if the objects are the same
  */
 @Override
 public boolean equals(Object other) {
     if (!(other instanceof Membre)) return false;
     return this.equalKeys(other) && ((Membre)other).equalKeys(this);
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
     i = getBatId();
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
     StringBuffer sb = new StringBuffer("[Membre |");
     sb.append(" batId=").append(getBatId());
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
     ret.put("batId", Integer.valueOf(getBatId()));
     return ret;
 }

}
