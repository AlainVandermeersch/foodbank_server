package foodbank.it.service;

public class SearchDepotCriteria {
	
	private String nom;
	private Boolean actif;
	private Integer lienBanque;
	public SearchDepotCriteria(String nom, Boolean actif,Integer lienBanque) {
		super();
		this.nom = nom;
		this.actif = actif;
		this.lienBanque = lienBanque;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Boolean getActif() {
		return actif;
	}
	public void setActif(Boolean actif) {
		this.actif = actif;
	}
	public Integer getLienBanque() {
		return lienBanque;
	}
	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}
	

}
