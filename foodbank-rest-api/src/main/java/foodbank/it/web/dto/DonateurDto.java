package foodbank.it.web.dto;

public class DonateurDto {
	private int donateurId;
	   
    private short lienBanque;
   
    private String nom;
    
    private String prenom;
   
    private String adresse;
    
    private short cp;
   
    private String city;
   
    private String pays;
    
    private String titre;   
    
    private Long  totalRecords;
	public DonateurDto(int donateurId, short lienBanque, String nom, String prenom, String adresse, short cp, String city,
			String pays, String titre, Long totalRecords) {
		super();
		this.donateurId = donateurId;
		this.lienBanque = lienBanque;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.cp = cp;
		this.city = city;
		this.pays = pays;
		this.titre = titre;
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
