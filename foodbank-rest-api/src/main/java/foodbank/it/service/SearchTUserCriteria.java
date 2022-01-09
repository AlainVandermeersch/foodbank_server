package foodbank.it.service;

public class SearchTUserCriteria {
	
	private String idUser;
	private String membreNom;
	private Boolean actif;
	private Integer  membreLangue;
	private String membreEmail;
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
	
	public SearchTUserCriteria(String idUser, String membreNom,Boolean actif, Integer membreLangue,String membreEmail,String rights, 
			Boolean droit1, Boolean gestMemb, Boolean gestBen, Boolean gestFead, Boolean gestDon,
			Integer lienBanque, Integer idOrg,Integer lienDepot, String idCompany) {
		this.idUser = idUser;
		this.membreNom = membreNom;		
		this.actif = actif;
		this.membreLangue = membreLangue;
		this.membreEmail = membreEmail;
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
		
	}

	

	public String getIdUser() {
		return idUser;
	}



	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}


	public String getMembreNom() {
		return membreNom;
	}



	public void setMembreNom(String membreNom) {
		this.membreNom = membreNom;
	}

	public Boolean getActif() {
		return actif;
	}



	public void setActif(Boolean actif) {
		this.actif = actif;
	}



	public Integer getMembreLangue() {
		return membreLangue;
	}



	public void setMembreLangue(Integer membreLangue) {
		this.membreLangue = membreLangue;
	}



	public String getMembreEmail() {
		return membreEmail;
	}



	public void setMembreEmail(String membreEmail) {
		this.membreEmail = membreEmail;
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
	
}