package foodbank.it.service;

public class SearchOrganisationSummariesCriteria {
	private String societe;
	private Integer lienBanque;
	private Integer lienDepot;
	private Boolean isDepot;
	private Boolean agreed;
	private Boolean actif;
	private Boolean cotType;
	private Integer regId;
	
	public SearchOrganisationSummariesCriteria(String societe, Integer lienBanque, Integer lienDepot, Boolean isDepot, Boolean agreed, Boolean actif, Boolean cotType,Integer regId) {
		super();
		this.societe = societe;
		this.lienBanque = lienBanque;	
		this.lienDepot = lienDepot;
		this.isDepot = isDepot;
		this.agreed = agreed;
		this.actif = actif;
		this.cotType = cotType;
		this.regId = regId;
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
	public Boolean getAgreed() {
		return agreed;
	}
	public void setAgreed(Boolean agreed) {
		this.agreed = agreed;
	}
	public Boolean getActif() {
		return actif;
	}
	public void setActif(Boolean actif) {
		this.actif = actif;
	}
	public Boolean getCotType() {
		return cotType;
	}
	public void setCotType(Boolean cotType) {
		this.cotType = cotType;
	}
	public Integer getRegId() {
		return regId;
	}
	public void setRegId(Integer regId) {
		this.regId = regId;
	}
	
	

}
