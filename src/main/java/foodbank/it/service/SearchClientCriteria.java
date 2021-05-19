package foodbank.it.service;

public class SearchClientCriteria {

	private String nom;
	private String prenom;
	private String adresse;
	private String cp;
	private String localite;
	private Integer lienBanque;
	private Integer lienDis;
	private Integer actif;
    
	public SearchClientCriteria(String nom, String prenom, String adresse, String cp,String localite, Integer lienBanque, Integer lienDis, Integer actif) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.cp = cp;
		this.localite = localite;
		this.lienBanque = lienBanque;
		this.lienDis = lienDis;
		this.actif = actif;
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



	public String getCp() {
		return cp;
	}



	public void setCp(String cp) {
		this.cp = cp;
	}



	public String getLocalite() {
		return localite;
	}


	public void setLocalite(String localite) {
		this.localite = localite;
	}

	public Integer getLienBanque() {
		return lienBanque;
	}

	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}	

	public Integer getLienDis() {
		return lienDis;
	}

	public void setLienDis(Integer lienDis) {
		this.lienDis = lienDis;
	}

	public Integer getActif() {
		return actif;
	}

	public void setActif(Integer actif) {
		this.actif = actif;
	}
	
}
