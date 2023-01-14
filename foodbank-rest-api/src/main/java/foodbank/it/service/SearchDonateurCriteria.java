package foodbank.it.service;

public class SearchDonateurCriteria {
	private String nom;
	private String prenom;
	private String adresse;
	private Integer cp;
	private String city;
	private Integer lienBanque;
	public SearchDonateurCriteria(String nom, String prenom, String adresse, Integer cp, String city,
			Integer lienBanque) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.cp = cp;
		this.city = city;
		this.lienBanque = lienBanque;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public Integer getCp() {
		return cp;
	}
	public void setCp(Integer cp) {
		this.cp = cp;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getLienBanque() {
		return lienBanque;
	}
	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}
	

}
