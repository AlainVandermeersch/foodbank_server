package foodbank.it.service;

public class SearchDonCriteria {
	private String donateurNom;
	private String donateurPrenom;	
	private Integer lienBanque;
	public SearchDonCriteria(String donateurNom, String donateurPrenom, Integer lienBanque) {
		super();
		this.donateurNom = donateurNom;
		this.donateurPrenom = donateurPrenom;
		
		this.lienBanque = lienBanque;
	}
	public String getDonateurNom() {
		return donateurNom;
	}
	public void setDonateurNom(String donateurNom) {
		this.donateurNom = donateurNom;
	}
	public String getDonateurPrenom() {
		return donateurPrenom;
	}
	public void setDonateurPrenom(String donateurPrenom) {
		this.donateurPrenom = donateurPrenom;
	}
	public Integer getLienBanque() {
		return lienBanque;
	}
	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}
	
}
