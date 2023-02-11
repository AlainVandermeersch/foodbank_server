package foodbank.it.service;

public class SearchMembreCriteria {
	private String nom;
	private Boolean actif;
	private String address;
	private String zip;
	private String city;
	private Integer lienBanque;
	private Integer lienDis;
	private Integer lienDepot;
	private String bankShortName;
	private Integer fonction;
	private String batmail;

	private String telgsm;
	private String hasAnomalies;
	private Boolean classicBanks;
	


	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public Boolean getActif() {
		return actif;
	}



	public void setActif(Boolean actif) {
		this.actif = actif;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getZip() {
		return zip;
	}



	public void setZip(String zip) {
		this.zip = zip;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getBatmail() {
		return batmail;
	}



	public void setBatmail(String batmail) {
		this.batmail = batmail;
	}



	public Integer getLienBanque() {
		return lienBanque;
	}

	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}

	public Integer getLienDis() {
		return lienDis;
	}

	public void setLienDis(Integer lienDis) {
		this.lienDis = lienDis;
	}

	public Integer getLienDepot() {
		return lienDepot;
	}

	public void setLienDepot(Integer lienDepot) {
		this.lienDepot = lienDepot;
	}



	public String getBankShortName() {
		return bankShortName;
	}



	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}

	public String getTelgsm() {	return telgsm;	}

	public void setTelgsm(String telgsm) { 	this.telgsm = telgsm; }

	public Integer getFonction() {
		return fonction;
	}

	public void setFonction(Integer fonction) {
		this.fonction = fonction;
	}

	public String getHasAnomalies() {
		return hasAnomalies;
	}

	public void setHasAnomalies(String hasAnomalies) {
		this.hasAnomalies = hasAnomalies;
	}

	public Boolean getClassicBanks() {
		return classicBanks;
	}

	public void setClassicBanks(Boolean classicBanks) {
		this.classicBanks = classicBanks;
	}
}
