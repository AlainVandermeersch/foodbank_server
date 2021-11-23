package foodbank.it.service;

public class SearchOrganisationSummariesCriteria {
	private String societe;
	private Integer lienBanque;
	private Integer lienDepot;
	private Boolean isDepot;
	
	public SearchOrganisationSummariesCriteria(String societe, Integer lienBanque, Integer lienDepot, Boolean isDepot) {
		super();
		this.societe = societe;
		this.lienBanque = lienBanque;	
		this.lienDepot = lienDepot;
		this.isDepot = isDepot;
	}
	public String getSociete() {
		return societe;
	}
	public void setSociete(String societe) {
		this.societe = societe;
	}
	public Integer getLienBanque() {
		return lienBanque;
	}
	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}
	public Integer getLienDepot() {
		return lienDepot;
	}
	public void setLienDepot(Integer lienDepotInteger) {
		this.lienDepot = lienDepotInteger;
	}
	public Boolean getIsDepot() {
		return isDepot;
	}
	public void setIsDepot(Boolean isDepot) {
		this.isDepot = isDepot;
	}
	
	

}
