package foodbank.it.web.dto;

public class OrgPersoDto {
	 private int orgPersId; 
	 private int civilite;
	 private int lienAsso;
	 private String nom; 
	 private String prenom;
	 private String email;
	 private String tel;
	 private String gsm;
	 private String fonction;
	 private boolean deleted;
	 private boolean distr;
	 
	public OrgPersoDto() {
		super();	
	}

	public OrgPersoDto(int orgPersId, int civilite, int lienAsso, String nom, String prenom, String email, String tel,
			String gsm, String fonction, boolean deleted, boolean distr) {
		super();
		this.orgPersId = orgPersId;
		this.civilite = civilite;
		this.lienAsso = lienAsso;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.gsm = gsm;
		this.fonction = fonction;
		this.deleted = deleted;
		this.distr = distr;
	}

	public int getOrgPersId() {
		return orgPersId;
	}

	public void setOrgPersId(int orgPersId) {
		this.orgPersId = orgPersId;
	}

	public int getCivilite() {
		return civilite;
	}

	public void setCivilite(int civilite) {
		this.civilite = civilite;
	}

	public int getLienAsso() {
		return lienAsso;
	}

	public void setLienAsso(int lienAsso) {
		this.lienAsso = lienAsso;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getGsm() {
		return gsm;
	}

	public void setGsm(String gsm) {
		this.gsm = gsm;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isDistr() {
		return distr;
	}

	public void setDistr(boolean distr) {
		this.distr = distr;
	} 
	
}

