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
	private Boolean suspect;
    private String daten;
	private String duplicate;

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

	public String getDaten() {
		return daten;
	}

	public void setDaten(String daten) {
		this.daten = daten;
	}

	public Boolean getSuspect() {
		return suspect;
	}

	public void setSuspect(Boolean suspect) {
		this.suspect = suspect;
	}

	public String getDuplicate() {
		return duplicate;
	}

	public void setDuplicate(String duplicate) {
		this.duplicate = duplicate;
	}
}
