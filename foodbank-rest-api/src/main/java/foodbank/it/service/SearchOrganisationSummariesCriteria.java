package foodbank.it.service;

public class SearchOrganisationSummariesCriteria {
	private String societe;
	private Integer lienBanque;
	private Integer idDis;
	public SearchOrganisationSummariesCriteria(String societe, Integer lienBanque) {
		super();
		this.societe = societe;
		this.lienBanque = lienBanque;		
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
	
	

}
