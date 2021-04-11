
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

import org.hibernate.annotations.Formula;

@Entity(name="clients")
public class Client implements Serializable {

 /**
	 * 
	 */
private static final long serialVersionUID = -8518218294613491486L;


/**
	 * 
	 */



/** Primary key. */
 protected static final String PK = "idClient";

 
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 @Column(name="id_client", unique=true, nullable=false, precision=10)
 private int idClient;
 @Column(name="id_int", length=7)
 private String idInt;
 @Column(name="lien_dis", nullable=false, precision=10)
 private Integer lienDis;
  @Column(nullable=false, length=50)
 private String nom;
 @Column(nullable=false, length=50)
 private String prenom;
 @Column(length=50)
 private String nomconj;
 @Column(length=50)
 private String prenomconj;
 @Column(nullable=false, precision=5)
 private short civilite;
 private String daten;
 @Column(name="daten_conj", nullable=false)
 private String datenConj;
 @Column(nullable=false, precision=5)
 private short civiliteconj;
 private String adresse;
 private String cp;
 private String localite;
 @Column(length=20)
 private String pays;
 private String email;
 private String tel;
 private String gsm;
 @Column(nullable=false)
 private String connu;
 @Column(precision=10)
 private Integer genre;
 @Column(nullable=false, precision=3)
 private short actif;
 @Column(nullable=false, precision=3)
 private short birb;
 @Column(length=15)
 private String natnr;
 @Column(name="date_upd", nullable=false)
 private LocalDateTime dateUpd;
 @Column(nullable=false, length=2)
 private String regio;
 @Column(name="l_cpas", precision=5)
 private Short lCpas;
 @Column(name="dat_upd_birb")
 private String datUpdBirb;
 @Column(name="crit_birb", precision=3)
 private short critBirb;
 @Column(nullable=false, precision=3)
 private short coeff;
 @Column(length=50)
 private String nomsav;
 @Column(length=50)
 private String prenomsav;
 @Column(precision=10)
 private Integer genreconj;
 private Short lbanque; 
 @Formula("(select count(*) from dep d where d.lien_mast = id_client)")
 private Long nbDep;

 /** Default constructor. */
 public Client() {
     super();
 }
 

 public Client(int idClient,String idInt, Integer lienDis, short lbanque, String nom, String prenom, String nomconj, String prenomconj,
		short civilite, String daten, String datenConj, short civiliteconj, String adresse, String cp, String localite,
		String pays, String email, String tel, String gsm, String connu, int genre, short actif, short birb,
		String natnr, String regio, short lCpas, String datUpdBirb, short critBirb, short coeff,
		String nomsav, String prenomsav, int genreconj) {
	super();
	this.idClient = idClient;
	this.idInt = idInt;
	this.lienDis = lienDis;
	this.lbanque = lbanque;
	this.nom = nom;
	this.prenom = prenom;
	this.nomconj = nomconj;
	this.prenomconj = prenomconj;
	this.civilite = civilite;
	this.daten = daten;
	this.datenConj = datenConj;
	this.civiliteconj = civiliteconj;
	this.adresse = adresse;
	this.cp = cp;
	this.localite = localite;
	this.pays = pays;
	this.email = email;
	this.tel = tel;
	this.gsm = gsm;
	this.connu = connu;
	this.genre = genre;
	this.actif = actif;
	this.birb = birb;
	this.natnr = natnr;
	this.dateUpd = LocalDateTime.now(); // do not use dateUpd from DTO we need to update the time
	this.regio = regio;
	this.lCpas = lCpas;
	this.datUpdBirb = datUpdBirb;
	this.critBirb = critBirb;
	this.coeff = coeff;
	this.nomsav = nomsav;
	this.prenomsav = prenomsav;
	this.genreconj = genreconj;
}


public Long getNbDep() {
	return nbDep;
}





/**
  * Access method for idClient.
  *
  * @return the current value of idClient
  */
 public int getIdClient() {
     return idClient;
 }

 /**
  * Setter method for idClient.
  *
  * @param aIdClient the new value for idClient
  */
 public void setIdClient(int aIdClient) {
     idClient = aIdClient;
 }

 /**
  * Access method for idInt.
  *
  * @return the current value of idInt
  */
 public String getIdInt() {
     return idInt;
 }

 /**
  * Setter method for idInt.
  *
  * @param aIdInt the new value for idInt
  */
 public void setIdInt(String aIdInt) {
     idInt = aIdInt;
 }

 /**
  * Access method for lienDis.
  *
  * @return the current value of lienDis
  */
 public Integer getLienDis() {
     return lienDis;
 }

 /**
  * Setter method for lienDis.
  *
  * @param aLienDis the new value for lienDis
  */
 public void setLienDis(Integer aLienDis) {
     lienDis = aLienDis;
 }

 /**
  * Access method for lbanque.
  *
  * @return the current value of lbanque
  */
 public short getLbanque() {
     return lbanque;
 }

 /**
  * Setter method for lbanque.
  *
  * @param aLbanque the new value for lbanque
  */
 public void setLbanque(short aLbanque) {
     lbanque = aLbanque;
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
  * Access method for nomconj.
  *
  * @return the current value of nomconj
  */
 public String getNomconj() {
     return nomconj;
 }

 /**
  * Setter method for nomconj.
  *
  * @param aNomconj the new value for nomconj
  */
 public void setNomconj(String aNomconj) {
     nomconj = aNomconj;
 }

 /**
  * Access method for prenomconj.
  *
  * @return the current value of prenomconj
  */
 public String getPrenomconj() {
     return prenomconj;
 }

 /**
  * Setter method for prenomconj.
  *
  * @param aPrenomconj the new value for prenomconj
  */
 public void setPrenomconj(String aPrenomconj) {
     prenomconj = aPrenomconj;
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
  * Access method for daten.
  *
  * @return the current value of daten
  */
 public String getDaten() {
     return daten;
 }

 /**
  * Setter method for daten.
  *
  * @param aDaten the new value for daten
  */
 public void setDaten(String aDaten) {
     daten = aDaten;
 }

 /**
  * Access method for datenConj.
  *
  * @return the current value of datenConj
  */
 public String getDatenConj() {
     return datenConj;
 }

 /**
  * Setter method for datenConj.
  *
  * @param aDatenConj the new value for datenConj
  */
 public void setDatenConj(String aDatenConj) {
     datenConj = aDatenConj;
 }

 /**
  * Access method for civiliteconj.
  *
  * @return the current value of civiliteconj
  */
 public short getCiviliteconj() {
     return civiliteconj;
 }

 /**
  * Setter method for civiliteconj.
  *
  * @param aCiviliteconj the new value for civiliteconj
  */
 public void setCiviliteconj(short aCiviliteconj) {
     civiliteconj = aCiviliteconj;
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
 public String getCp() {
     return cp;
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
  * Access method for localite.
  *
  * @return the current value of localite
  */
 public String getLocalite() {
     return localite;
 }

 /**
  * Setter method for localite.
  *
  * @param aLocalite the new value for localite
  */
 public void setLocalite(String aLocalite) {
     localite = aLocalite;
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
  * Access method for connu.
  *
  * @return the current value of connu
  */
 public String getConnu() {
     return connu;
 }

 /**
  * Setter method for connu.
  *
  * @param aConnu the new value for connu
  */
 public void setConnu(String aConnu) {
     connu = aConnu;
 }

 /**
  * Access method for genre.
  *
  * @return the current value of genre
  */
 public int getGenre() {
	 if (genre == null ) return 0;
     return genre;
 }

 /**
  * Setter method for genre.
  *
  * @param aGenre the new value for genre
  */
 public void setGenre(int aGenre) {
     genre = aGenre;
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
  * Access method for birb.
  *
  * @return the current value of birb
  */
 public short getBirb() {
     return birb;
 }

 /**
  * Setter method for birb.
  *
  * @param aBirb the new value for birb
  */
 public void setBirb(short aBirb) {
     birb = aBirb;
 }

 /**
  * Access method for natnr.
  *
  * @return the current value of natnr
  */
 public String getNatnr() {
     return natnr;
 }

 /**
  * Setter method for natnr.
  *
  * @param aNatnr the new value for natnr
  */
 public void setNatnr(String aNatnr) {
     natnr = aNatnr;
 }

 /**
  * Access method for dateUpd.
  *
  * @return the current value of dateUpd
  */
 public LocalDateTime getDateUpd() {
     return dateUpd;
 }

 /**
  * Setter method for dateUpd.
  *
  * @param aDateUpd the new value for dateUpd
  */
 public void setDateUpd(LocalDateTime aDateUpd) {
     dateUpd = aDateUpd;
 }

 /**
  * Access method for regio.
  *
  * @return the current value of regio
  */
 public String getRegio() {
     return regio;
 }

 /**
  * Setter method for regio.
  *
  * @param aRegio the new value for regio
  */
 public void setRegio(String aRegio) {
     regio = aRegio;
 }

 /**
  * Access method for lCpas.
  *
  * @return the current value of lCpas
  */
 public short getLCpas() {
	 if (lCpas == null ) return 0;
     return lCpas;
 }

 /**
  * Setter method for lCpas.
  *
  * @param aLCpas the new value for lCpas
  */
 public void setLCpas(short aLCpas) {
     lCpas = aLCpas;
 }

 /**
  * Access method for datUpdBirb.
  *
  * @return the current value of datUpdBirb
  */
 public String getDatUpdBirb() {
     return datUpdBirb;
 }

 /**
  * Setter method for datUpdBirb.
  *
  * @param aDatUpdBirb the new value for datUpdBirb
  */
 public void setDatUpdBirb(String aDatUpdBirb) {
     datUpdBirb = aDatUpdBirb;
 }

 /**
  * Access method for critBirb.
  *
  * @return the current value of critBirb
  */
 public short getCritBirb() {
     return critBirb;
 }

 /**
  * Setter method for critBirb.
  *
  * @param aCritBirb the new value for critBirb
  */
 public void setCritBirb(short aCritBirb) {
     critBirb = aCritBirb;
 }

 /**
  * Access method for coeff.
  *
  * @return the current value of coeff
  */
 public short getCoeff() {
     return coeff;
 }

 /**
  * Setter method for coeff.
  *
  * @param aCoeff the new value for coeff
  */
 public void setCoeff(short aCoeff) {
     coeff = aCoeff;
 }

 /**
  * Access method for nomsav.
  *
  * @return the current value of nomsav
  */
 public String getNomsav() {
     return nomsav;
 }

 /**
  * Setter method for nomsav.
  *
  * @param aNomsav the new value for nomsav
  */
 public void setNomsav(String aNomsav) {
     nomsav = aNomsav;
 }

 /**
  * Access method for prenomsav.
  *
  * @return the current value of prenomsav
  */
 public String getPrenomsav() {
     return prenomsav;
 }

 /**
  * Setter method for prenomsav.
  *
  * @param aPrenomsav the new value for prenomsav
  */
 public void setPrenomsav(String aPrenomsav) {
     prenomsav = aPrenomsav;
 }

 /**
  * Access method for genreconj.
  *
  * @return the current value of genreconj
  */
 public int getGenreconj() {
	 if (genreconj == null ) return 0;
     return genreconj;
 }

 /**
  * Setter method for genreconj.
  *
  * @param aGenreconj the new value for genreconj
  */
 public void setGenreconj(int aGenreconj) {
     genreconj = aGenreconj;
 }

 /**
  * Compares the key for this instance with another Clients.
  *
  * @param other The object to compare to
  * @return True if other object is instance of class Clients and the key objects are equal
  */
 private boolean equalKeys(Object other) {
     if (this==other) {
         return true;
     }
     if (!(other instanceof Client)) {
         return false;
     }
     Client that = (Client) other;
     if (this.getIdClient() != that.getIdClient()) {
         return false;
     }
     return true;
 }

 /**
  * Compares this instance with another Clients.
  *
  * @param other The object to compare to
  * @return True if the objects are the same
  */
 @Override
 public boolean equals(Object other) {
     if (!(other instanceof Client)) return false;
     return this.equalKeys(other) && ((Client)other).equalKeys(this);
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
     i = getIdClient();
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
     StringBuffer sb = new StringBuffer("[Clients |");
     sb.append(" idClient=").append(getIdClient());
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
     ret.put("idClient", Integer.valueOf(getIdClient()));
     return ret;
 }

}
