package foodbank.it.service;

public class SearchDepotCriteria {
	
	private String nom;
	private Boolean actif;
	private String idCompany;
	public SearchDepotCriteria(String nom, Boolean actif,String idCompany) {
		super();
		this.nom = nom;
		this.actif = actif;
		this.idCompany = idCompany;
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
	public String getIdCompany() {
		return idCompany;
	}
	public void setIdCompany(String idCompany) {
		this.idCompany = idCompany;
	}
	
	

}
