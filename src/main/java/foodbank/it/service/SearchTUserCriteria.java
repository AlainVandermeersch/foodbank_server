package foodbank.it.service;

public class SearchTUserCriteria {
	
	private String idUser;
	private String userName;
	private String idLanguage;
	private String email;
	private String rights;
	private Integer lienBanque;
	private Integer idOrg;
	
	public SearchTUserCriteria(String idUser, String userName,String idLanguage,String email,String rights, Integer lienBanque, Integer idOrg) {
		this.idUser = idUser;
		this.userName = userName;
		this.idLanguage = idLanguage;
		this.email = email;
		this.rights = rights;
		this.lienBanque = lienBanque;
		this.idOrg = idOrg;
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
	
}