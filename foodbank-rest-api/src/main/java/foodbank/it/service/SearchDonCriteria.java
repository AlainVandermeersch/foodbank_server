package foodbank.it.service;

public class SearchDonCriteria {
	private String donateurNom;
	private Integer lienBanque;
	public SearchDonCriteria(String donateurNom, Integer lienBanque) {
		super();
		this.donateurNom = donateurNom;			
		this.lienBanque = lienBanque;
	}
	public String getDonateurNom() {
		return donateurNom;
	}
	public void setDonateurNom(String donateurNom) {
		this.donateurNom = donateurNom;
	}
	
	public Integer getLienBanque() {
		return lienBanque;
	}
	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}
	
}
