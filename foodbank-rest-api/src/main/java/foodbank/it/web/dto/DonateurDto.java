package foodbank.it.web.dto;

public class DonateurDto {
	
    
    private String titre;  
   
    private String nom;
    
    private String prenom;
   
    private String adresse;
    
    private int cp;
   
    private String city;
   
    private int pays;
    
    private int donateurId;
	   
    private short lienBanque;
	private long totalDons;
    private Long  totalRecords;

	public int getDonateurId() {
		return donateurId;
	}
	public void setDonateurId(int donId) {
		this.donateurId = donId;
	}
	public short getLienBanque() {
		return lienBanque;
	}
	public void setLienBanque(short lienBanque) {
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
	public int getCp() {
		return cp;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPays() {
		return pays;
	}
	public void setPays(int pays) {
		this.pays = pays;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public long getTotalDons() {
		return totalDons;
	}
	public void setTotalDons(long totalDons) {
		this.totalDons = totalDons;
	}
	
	public Long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
    
    

}
