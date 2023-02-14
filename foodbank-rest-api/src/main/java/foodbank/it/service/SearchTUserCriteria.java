package foodbank.it.service;

public class SearchTUserCriteria {
	
	private String idUser;
	private String userName;
	private Boolean actif;
	private String  idLanguage;
	private String email;
	private String rights;
	private Boolean droit1;
	private Boolean gestMemb;
	private Boolean gestBen;
	private Boolean gestFead;
	private Boolean gestDon;
	private Integer lienBanque;
	private Integer idOrg;
	private Integer lienDepot;
	private String idCompany;
	private Boolean hasLogins;
	private String hasAnomalies;
	private Boolean classicBanks;
	private Integer lienBat;
	public SearchTUserCriteria(String idUser, String userName,Boolean actif, String  idLanguage,String email,String rights, 
			Boolean droit1, Boolean gestMemb, Boolean gestBen, Boolean gestFead, Boolean gestDon,
			Integer lienBanque, Integer idOrg,Integer lienDepot, String idCompany,Boolean hasLogins,String hasAnomalies, Boolean classicBanks,Integer lienBat) {
		this.idUser = idUser;
		this.userName = userName;		
		this.actif = actif;
		this.idLanguage = idLanguage;
		this.email = email;
		this.rights = rights;
		this.droit1 = droit1;
		this.gestMemb = gestMemb;
		this.gestBen = gestBen;
		this.gestFead = gestFead;
		this.gestDon = gestDon;
		this.lienBanque = lienBanque;
		this.idOrg = idOrg;
		this.lienDepot = lienDepot;
		this.idCompany = idCompany;
		this.hasLogins = hasLogins;
		this.hasAnomalies = hasAnomalies;
		this.classicBanks = classicBanks;
		this.lienBat = lienBat;
	}

	

	public String getIdUser() {
		return idUser;
	}



	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}


	

	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public Boolean getActif() {
		return actif;
	}



	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	public String getIdLanguage() {
		return idLanguage;
	}
	public void setIdLanguage(String idLanguage) {
		this.idLanguage = idLanguage;
	}

	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getRights() {
		return rights;
	}



	public void setRights(String rights) {
		this.rights = rights;
	}

	


	public Boolean getDroit1() {
		return droit1;
	}



	public void setDroit1(Boolean droit1) {
		this.droit1 = droit1;
	}



	public Boolean getGestMemb() {
		return gestMemb;
	}



	public void setGestMemb(Boolean gestMemb) {
		this.gestMemb = gestMemb;
	}



	public Boolean getGestBen() {
		return gestBen;
	}



	public void setGestBen(Boolean gestBen) {
		this.gestBen = gestBen;
	}



	public Boolean getGestFead() {
		return gestFead;
	}



	public void setGestFead(Boolean gestFead) {
		this.gestFead = gestFead;
	}



	public Boolean getGestDon() {
		return gestDon;
	}



	public void setGestDon(Boolean gestDon) {
		this.gestDon = gestDon;
	}



	public Integer getLienBanque() {
		return lienBanque;
	}

	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}

	public Integer getIdOrg() {
		return idOrg;
	}

	public void setIdOrg(Integer idOrg) {
		this.idOrg = idOrg;
	}
	public Integer getLienDepot() {
		return lienDepot;
	}

	public void setLienDepot(Integer lienDepot) {
		this.lienDepot = lienDepot;
	}

	public String getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(String idCompany) {
		this.idCompany = idCompany;
	}

	public Boolean getHasLogins() {
		return hasLogins;
	}

	public void setHasLogins(Boolean hasLogins) {
		this.hasLogins = hasLogins;
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

	public Integer getLienBat() {
		return lienBat;
	}

	public void setLienBat(Integer lienBat) {
		this.lienBat = lienBat;
	}
}