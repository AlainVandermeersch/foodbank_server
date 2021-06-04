package foodbank.it.web.dto;

public class DepotDto {
	private String idDepot;
	 
	 private String nom;
	 
	 private String adresse;
	 
	 private String adresse2;
	 
	 private String cp;
	
	 private String ville;
	 
	 private String telephone;
	
	 private String contact;
	 
	 private String email;
	 
	 private String memo;
	
	 private boolean depPrinc;
	
	 private boolean actif;
	
	 private boolean depFead;
	 
	 private Integer lienBanque;
	 
	
	 
	public DepotDto() {
		super();
		
	}

	public DepotDto(String idDepot, String nom, String adresse, String adresse2, String cp, String ville,
			String telephone, String contact, String email, String memo, boolean depPrinc, boolean actif, boolean depFead,
			Integer lienBanque) {
		super();
		this.idDepot = idDepot;
		this.nom = nom;
		this.adresse = adresse;
		this.adresse2 = adresse2;
		this.cp = cp;
		this.ville = ville;
		this.telephone = telephone;
		this.contact = contact;
		this.email = email;
		this.memo = memo;
		this.depPrinc = depPrinc;
		this.actif = actif ;
		this.depFead = depFead;
		this.lienBanque = lienBanque;	
		
	}

	public String getIdDepot() {
		return idDepot;
	}

	public void setIdDepot(String idDepot) {
		this.idDepot = idDepot;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public boolean getDepPrinc() {
		return depPrinc;
	}

	public void setDepPrinc(boolean  depPrinc) {
		this.depPrinc = depPrinc;
	}

	public boolean getActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public boolean getDepFead() {
		return depFead;
	}

	public void setDepFead(boolean depFead) {
		this.depFead = depFead;
	}

	public Integer getLienBanque() {
		return lienBanque;
	}

	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}

	

	
	
	 
}

