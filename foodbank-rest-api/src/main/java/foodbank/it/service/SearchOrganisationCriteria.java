package foodbank.it.service;

public class SearchOrganisationCriteria {
	private String societe;
	private String adresse;
	private String nomDepot;
	private Integer lienBanque;
	private Integer lienDepot;
	private Boolean isDepot;
	private Boolean isBirb;
	private Boolean isWeb;
	private String statut;
	
	
	public SearchOrganisationCriteria(String societe, String adresse, String nomDepot, Integer lienBanque, Integer lienDepot, Boolean isDepot,Boolean isBirb,Boolean isWeb, String statut) {
		this.societe = societe;
		this.adresse = adresse;
		this.nomDepot = nomDepot;		
		this.lienBanque = lienBanque;
		this.lienDepot = lienDepot;
		this.isDepot = isDepot;
		this.isBirb = isBirb;
		this.isWeb = isWeb;
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



	


	public String getNomDepot() {
		return nomDepot;
	}



	public void setNomDepot(String nomDepot) {
		this.nomDepot = nomDepot;
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



	public Boolean getIsWeb() {
		return isWeb;
	}



	public void setIsWeb(Boolean isWeb) {
		this.isWeb = isWeb;
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

	public Integer getlienDepot() {
		return lienDepot;
	}

	public void setlienDepot(Integer lienDepot) {
		this.lienDepot = lienDepot;
	}

	
}
