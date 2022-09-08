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
	private Boolean feadN;
	private String bankShortName;
	
	public SearchOrganisationSummariesCriteria(String societe, Integer lienBanque, Integer lienDepot, Boolean isDepot, Boolean agreed, 
			Boolean actif, Boolean cotType,Integer regId,Boolean feadN, String bankShortName) {
		super();
		this.societe = societe;
		this.lienBanque = lienBanque;
		this.bankShortName = bankShortName;
		this.lienDepot = lienDepot;
		this.isDepot = isDepot;
		this.agreed = agreed;
		this.actif = actif;
		this.cotType = cotType;
		this.regId = regId;
		this.feadN = feadN;
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
	public Boolean getFeadN() {
		return feadN;
	}
	public void setFeadN(Boolean feadN) {
		this.feadN = feadN;
	}
	public String getBankShortName() {
		return bankShortName;
	}
	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}
	
	
	
	

}
