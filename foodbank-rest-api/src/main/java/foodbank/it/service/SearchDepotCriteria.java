package foodbank.it.service;

public class SearchDepotCriteria {
	
	private String nom;
	private Integer lienBanque;
	public SearchDepotCriteria(String nom, Integer lienBanque) {
		super();
		this.nom = nom;
		this.lienBanque = lienBanque;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Integer getLienBanque() {
		return lienBanque;
	}
	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}
	

}
