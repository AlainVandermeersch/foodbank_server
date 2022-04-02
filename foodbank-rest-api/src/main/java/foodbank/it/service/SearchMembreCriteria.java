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
	private String fonctionName;
	private String fonctionNameNl;
	private String batmail;
	private String hasAnomalies;
	private Boolean classicBanks;
	
	public SearchMembreCriteria(String nom, Boolean actif, String address, String zip, String city, String batmail,
			Integer lienBanque, Integer lienDis,Integer lienDepot,String bankShortName,String fonctionName,String fonctionNameNl,
								String hasAnomalies, Boolean classicBanks) {
		this.nom = nom;		
		this.actif = actif;
		this.address = address;
		this.zip = zip;
		this.city = city;
		this.batmail = batmail;
		this.lienBanque = lienBanque;
		this.lienDis = lienDis;
		this.lienDepot = lienDepot;
		this.bankShortName = bankShortName;
		this.fonctionName = fonctionName;
		this.fonctionNameNl = fonctionNameNl;
		this.hasAnomalies = hasAnomalies;
		this.classicBanks = classicBanks;
	}

	

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

	public String getFonctionName() {
		return fonctionName;
	}

	public void setFonctionName(String fonctionName) {
		this.fonctionName = fonctionName;
	}

	public String getFonctionNameNl() {
		return fonctionNameNl;
	}

	public void setFonctionNameNl(String fonctionNameNl) {
		this.fonctionNameNl = fonctionNameNl;
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
