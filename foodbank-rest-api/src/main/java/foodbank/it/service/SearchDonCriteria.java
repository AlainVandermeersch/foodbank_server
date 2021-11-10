package foodbank.it.service;

public class SearchDonCriteria {
	private String donateurNom;
	private Integer lienBanque;
	private Integer donYear;
	public SearchDonCriteria(String donateurNom, Integer donYear,Integer lienBanque) {
		super();
		this.donateurNom = donateurNom;			
		this.lienBanque = lienBanque;
		this.donYear = donYear;
	}
	public String getDonateurNom() {
		return donateurNom;
	}
	public void setDonateurNom(String donateurNom) {
		this.donateurNom = donateurNom;
	}
	public Integer getDonYear() {
		return donYear;
	}
	public void setDonYear(Integer donYear) {
		this.donYear = donYear;
	}
	public Integer getLienBanque() {
		return lienBanque;
	}
	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}
	
}
