package foodbank.it.web.dto;

public class DonateurDto {
	
    
    private String titre;  
   
    private String nom;
    
    private String prenom;
   
    private String adresse;
    
    private short cp;
   
    private String city;
   
    private String pays;
    
    private int donateurId;
	   
    private short lienBanque;
    
    private Long  totalRecords;
	public DonateurDto(int donateurId, short lienBanque, String nom, String prenom, String adresse, short cp, String city,
			String pays, String titre, Long totalRecords) {
		super();		
		this.titre = titre;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.cp = cp;
		this.city = city;
		this.pays = pays;
		this.donateurId = donateurId;
		this.lienBanque = lienBanque;
		this.totalRecords = totalRecords;
	}
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
	public short getCp() {
		return cp;
	}
	public void setCp(short cp) {
		this.cp = cp;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public Long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
    
    

}
