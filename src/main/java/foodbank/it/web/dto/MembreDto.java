package foodbank.it.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;

public class MembreDto {
	 private int batId;
	 
	 private int lienDis;
	
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
	 
	 private int fonction;
	 
	 private short ca;
	 
	 private short ag;
	 
	 private short cg;
	 
	 private short civilite;
	 
	 private short pays;
	
	 private short actif;
	 
	 private short authority;
	 private LocalDate datmand;
	 private String rem;
	
	 private LocalDateTime lastVisit;
	
	 private short ben;
	 
	 private short codeAcces;
	 
	 private short nrCodeAcces;
	
	 private short langue;
	 private String datedeb;
	 
	 private String dateFin;
	
	 private short deleted;
	 
	 private short typEmploi;
	
	 private String dateNaissance;
	
	 private String nnat;
	 
	 private String dateContrat;
	
	 private int lDep;
	 private String bankName;
	 private String bankShortName;
	 
	public MembreDto() {
		super();
		
	}

	public MembreDto(int batId, int lienDis, String nom, String prenom, String address, String city, String zip,
			String tel, String gsm, String batmail, String veh, String vehTyp, String vehImm, int fonction, short ca,
			short ag, short cg, short civilite, short pays, short actif, short authority, LocalDate datmand, String rem,
			LocalDateTime lastVisit, short ben, short codeAcces, short nrCodeAcces, short langue, String datedeb,
			String dateFin, short deleted, short typEmploi, String dateNaissance, String nnat, String dateContrat,
			int lDep, String bankName, String bankShortName) {
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
		this.lastVisit = lastVisit;
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
		this.bankName = bankName;
		this.bankShortName = bankShortName;
	}

	public int getBatId() {
		return batId;
	}

	public void setBatId(int batId) {
		this.batId = batId;
	}

	public int getLienDis() {
		return lienDis;
	}

	public void setLienDis(int lienDis) {
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

	public int getFonction() {
		return fonction;
	}

	public void setFonction(int fonction) {
		this.fonction = fonction;
	}

	public short getCa() {
		return ca;
	}

	public void setCa(short ca) {
		this.ca = ca;
	}

	public short getAg() {
		return ag;
	}

	public void setAg(short ag) {
		this.ag = ag;
	}

	public short getCg() {
		return cg;
	}

	public void setCg(short cg) {
		this.cg = cg;
	}

	public short getCivilite() {
		return civilite;
	}

	public void setCivilite(short civilite) {
		this.civilite = civilite;
	}

	public short getPays() {
		return pays;
	}

	public void setPays(short pays) {
		this.pays = pays;
	}

	public short getActif() {
		return actif;
	}

	public void setActif(short actif) {
		this.actif = actif;
	}

	public short getAuthority() {
		return authority;
	}

	public void setAuthority(short authority) {
		this.authority = authority;
	}

	public LocalDate getDatmand() {
		return datmand;
	}

	public void setDatmand(LocalDate datmand) {
		this.datmand = datmand;
	}

	public String getRem() {
		return rem;
	}

	public void setRem(String rem) {
		this.rem = rem;
	}

	public LocalDateTime getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(LocalDateTime lastVisit) {
		this.lastVisit = lastVisit;
	}

	public short getBen() {
		return ben;
	}

	public void setBen(short ben) {
		this.ben = ben;
	}

	public short getCodeAcces() {
		return codeAcces;
	}

	public void setCodeAcces(short codeAcces) {
		this.codeAcces = codeAcces;
	}

	public short getNrCodeAcces() {
		return nrCodeAcces;
	}

	public void setNrCodeAcces(short nrCodeAcces) {
		this.nrCodeAcces = nrCodeAcces;
	}

	public short getLangue() {
		return langue;
	}

	public void setLangue(short langue) {
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

	public short getDeleted() {
		return deleted;
	}

	public void setDeleted(short deleted) {
		this.deleted = deleted;
	}

	public short getTypEmploi() {
		return typEmploi;
	}

	public void setTypEmploi(short typEmploi) {
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

	public int getLDep() {
		return lDep;
	}

	public void setLDep(int lDep) {
		this.lDep = lDep;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankShortName() {
		return bankShortName;
	}

	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}

	
	 
}
