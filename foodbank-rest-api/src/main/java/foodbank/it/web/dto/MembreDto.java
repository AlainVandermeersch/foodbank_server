package foodbank.it.web.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MembreDto {
	 private Integer batId;
	 
	 private Integer lienDis;
	
	 private String nom;
	
	 private String prenom;
	
	 private String address;
	
	 private String city;
	
	 private String zip;
	 
	 private String tel;
	 
	 private String gsm;
	 
	 private String batmail;
	 
	 private String veh;
	 
	 private String vehTyp;
	 
	 private String vehImm;
	 
	 private Integer fonction;
	 
	 private Short ca;
	 
	 private Short ag;
	 
	 private Short cg;
	 
	 private Short civilite;
	 
	 private Short pays;
	
	 private boolean actif;
	 
	 private Short authority;
	 private String datmand;
	 private String rem;
	 private String lastVisit;
	
	 private Short ben;
	 
	 private Short codeAcces;
	 
	 private Short nrCodeAcces;
	
	 private Short langue;
	 private String datedeb;
	 
	 private String dateFin;
	
	 private Short deleted;
	 
	 private Short typEmploi;
	
	 private String dateNaissance;
	
	 private String nnat;
	 
	 private String dateContrat;
	
	 private Integer lDep;
	 private Short lienBanque;
	 
	 private String societe; // calculated field from organisation object
	 
	 private String bankShortName;
	 private long nbUsers;
	 
	 private Long  totalRecords;
	 
	public MembreDto() {
		super();
		
	}

	public MembreDto(Integer batId, Integer lienDis, String nom, String prenom, String address, String city, String zip,
			String tel, String gsm, String batmail, String veh, String vehTyp, String vehImm, Integer fonction, Short ca,
			Short ag, Short cg, Short civilite, Short pays, boolean actif, Short authority, String datmand, String rem,
			Short ben, Short codeAcces, Short nrCodeAcces, Short langue, String datedeb,
			String dateFin, Short deleted, Short typEmploi, String dateNaissance, String nnat, String dateContrat,
			Integer lDep,LocalDateTime lastVisit, Short lienBanque,String societe,String bankShortName,long nbUsers, Long  totalRecords) {
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
		if (lastVisit == null) {
			this.lastVisit = "";
		}
		else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			this.lastVisit = lastVisit.format(formatter);
		}
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
		this.lienBanque = lienBanque;
		this.societe = societe;
		this.bankShortName = bankShortName;
		this.nbUsers = nbUsers;
		this.totalRecords = totalRecords;
	}

	public Integer getBatId() {
		return batId;
	}

	public void setBatId(Integer batId) {
		this.batId = batId;
	}

	public Integer getLienDis() {
		return lienDis;
	}

	public void setLienDis(Integer lienDis) {
		this.lienDis = lienDis;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getGsm() {
		return gsm;
	}

	public void setGsm(String gsm) {
		this.gsm = gsm;
	}

	public String getBatmail() {
		return batmail;
	}

	public void setBatmail(String batmail) {
		this.batmail = batmail;
	}

	public String getVeh() {
		return veh;
	}

	public void setVeh(String veh) {
		this.veh = veh;
	}

	public String getVehTyp() {
		return vehTyp;
	}

	public void setVehTyp(String vehTyp) {
		this.vehTyp = vehTyp;
	}

	public String getVehImm() {
		return vehImm;
	}

	public void setVehImm(String vehImm) {
		this.vehImm = vehImm;
	}

	public Integer getFonction() {
		return fonction;
	}

	public void setFonction(Integer fonction) {
		this.fonction = fonction;
	}

	public Short getCa() {
		return ca;
	}

	public void setCa(Short ca) {
		this.ca = ca;
	}

	public Short getAg() {
		return ag;
	}

	public void setAg(Short ag) {
		this.ag = ag;
	}

	public Short getCg() {
		return cg;
	}

	public void setCg(Short cg) {
		this.cg = cg;
	}

	public Short getCivilite() {
		return civilite;
	}

	public void setCivilite(Short civilite) {
		this.civilite = civilite;
	}

	public Short getPays() {
		return pays;
	}

	public void setPays(Short pays) {
		this.pays = pays;
	}

	public boolean getActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public Short getAuthority() {
		return authority;
	}

	public void setAuthority(Short authority) {
		this.authority = authority;
	}

	public String getDatmand() {
		return datmand;
	}

	public void setDatmand(String datmand) {
		this.datmand = datmand;
	}

	public String getRem() {
		return rem;
	}

	public void setRem(String rem) {
		this.rem = rem;
	}

	public String getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}

	public Short getBen() {
		return ben;
	}

	public void setBen(Short ben) {
		this.ben = ben;
	}

	public Short getCodeAcces() {
		return codeAcces;
	}

	public void setCodeAcces(Short codeAcces) {
		this.codeAcces = codeAcces;
	}

	public Short getNrCodeAcces() {
		return nrCodeAcces;
	}

	public void setNrCodeAcces(Short nrCodeAcces) {
		this.nrCodeAcces = nrCodeAcces;
	}

	public Short getLangue() {
		return langue;
	}

	public void setLangue(Short langue) {
		this.langue = langue;
	}

	public String getDatedeb() {
		return datedeb;
	}

	public void setDatedeb(String datedeb) {
		this.datedeb = datedeb;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public Short getDeleted() {
		return deleted;
	}

	public void setDeleted(Short deleted) {
		this.deleted = deleted;
	}

	public Short getTypEmploi() {
		return typEmploi;
	}

	public void setTypEmploi(Short typEmploi) {
		this.typEmploi = typEmploi;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getNnat() {
		return nnat;
	}

	public void setNnat(String nnat) {
		this.nnat = nnat;
	}

	public String getDateContrat() {
		return dateContrat;
	}

	public void setDateContrat(String dateContrat) {
		this.dateContrat = dateContrat;
	}

	public Integer getLDep() {
		return lDep;
	}

	public void setLDep(Integer lDep) {
		this.lDep = lDep;
	}
	
	public Short getLienBanque() {
		return lienBanque;
	}

	public void setLienBanque(Short lienBanque) {
		this.lienBanque = lienBanque;
	}	
	

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	
	public String getBankShortName() {
		return bankShortName;
	}

	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}
	
	
	public long getNbUsers() {
		return nbUsers;
	}

	public void setNbUsers(long nbUsers) {
		this.nbUsers = nbUsers;
	}

	public Long  getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long  totalRecords) {
		this.totalRecords = totalRecords;
	}

	
	 
}
