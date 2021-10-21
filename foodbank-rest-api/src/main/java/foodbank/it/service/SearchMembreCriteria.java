package foodbank.it.service;

public class SearchMembreCriteria {
	private String nom;
	private String prenom;
	private String address;
	private String zip;
	private String city;
	private Integer lienBanque;
	private Integer lienDis;
	private Integer lienDepot;
	
	
	public SearchMembreCriteria(String nom, String prenom, String address, String zip, String city, Integer lienBanque, Integer lienDis,Integer lienDepot) {
		this.nom = nom;
		this.prenom = prenom;
		this.address = address;
		this.zip = zip;
		this.city = city;
		this.lienBanque = lienBanque;
		this.lienDis = lienDis;
		this.lienDepot = lienDepot;
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



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getZip() {
		return zip;
	}



	public void setZip(String zip) {
		this.zip = zip;
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

	public Integer getLienDis() {
		return lienDis;
	}

	public void setLienDis(Integer lienDis) {
		this.lienDis = lienDis;
	}

	public Integer getLienDepot() {
		return lienDepot;
	}

	public void setLienDepot(Integer lienDepot) {
		this.lienDepot = lienDepot;
	}

	
}
