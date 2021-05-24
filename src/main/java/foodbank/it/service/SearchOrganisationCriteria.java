package foodbank.it.service;

public class SearchOrganisationCriteria {
	private String societe;
	private String adresse;
	private String cp;
	private String localite;
	private Integer lienBanque;
	private Integer idDis;
	private Boolean isDepot;
	private Boolean isBirb;
	private String statut;
	
	
	public SearchOrganisationCriteria(String societe, String adresse, String cp,String localite, Integer lienBanque, Integer lienDis, Boolean isDepot,Boolean isBirb,String statut) {
		this.societe = societe;
		this.adresse = adresse;
		this.cp = cp;
		this.localite = localite;
		this.lienBanque = lienBanque;
		this.idDis = lienDis;
		this.isDepot = isDepot;
		this.isBirb = isBirb;
		this.statut = statut;
	}

	

	public String getSociete() {
		return societe;
	}



	public void setSociete(String societe) {
		this.societe = societe;
	}



	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public String getCp() {
		return cp;
	}



	public void setCp(String cp) {
		this.cp = cp;
	}



	public String getLocalite() {
		return localite;
	}



	public void setLocalite(String localite) {
		this.localite = localite;
	}



	public Boolean getIsDepot() {
		return isDepot;
	}



	public void setIsDepot(Boolean isDepot) {
		this.isDepot = isDepot;
	}



	public Boolean getIsBirb() {
		return isBirb;
	}



	public void setIsBirb(Boolean isBirb) {
		this.isBirb = isBirb;
	}



	public String getStatut() {
		return statut;
	}



	public void setStatut(String statut) {
		this.statut = statut;
	}



	public Integer getLienBanque() {
		return lienBanque;
	}

	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}

	public Integer getIdDis() {
		return idDis;
	}

	public void setIdDis(Integer idDis) {
		this.idDis = idDis;
	}

	
}
