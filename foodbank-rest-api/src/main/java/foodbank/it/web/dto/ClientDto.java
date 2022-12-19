package foodbank.it.web.dto;
public class ClientDto {
	 private int idClient;
	
	 private String idInt;
	
	 private Integer lienDis;
	 
	 private String nom;
	
	 private String prenom;
	
	 private String nomconj;
	
	 private String prenomconj;
	
	 private short civilite;
	 private String daten;
	
	 private String datenConj;
	
	 private short civiliteconj;
	 private String adresse;
	 private String cp;
	 private String localite;
	 
	 private String pays;
	 private String email;
	 private String tel;
	 private String gsm;
	
	 private String connu;
	
	 private Integer genre;
	// Alain actif means archived in the MySQLDB - its counterintuitive actif = 1 means archived - actif  = 0 means "current, not archived"
	// So in the DTO we expose the property as archived
	 private boolean actif;
	 
	 private short birb;
	
	 private String natnr;
	
	 private String dateUpd;
	
	 private String regio;
	 
	 private Short lCpas;
	
	 private String datUpdBirb;
	  
	 private short critBirb;
	 
	 private short coeff;
	  
	 private String nomsav;
	  
	 private String prenomsav;
	 
	 private Integer genreconj;
	
	 private short lbanque;
	 
	 private Long nbDep;
	 
	 private String societe; // calculated field from organisation object
	 
	 private Long  totalRecords;
	 
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getIdInt() {
		return idInt;
	}
	public void setIdInt(String idInt) {
		this.idInt = idInt;
	}
	public Integer getLienDis() {
		return lienDis;
	}
	public void setLienDis(Integer lienDis) {
		this.lienDis = lienDis;
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
	public String getNomconj() {
		return nomconj;
	}
	public void setNomconj(String nomconj) {
		this.nomconj = nomconj;
	}
	public String getPrenomconj() {
		return prenomconj;
	}
	public void setPrenomconj(String prenomconj) {
		this.prenomconj = prenomconj;
	}
	public short getCivilite() {
		return civilite;
	}
	public void setCivilite(short civilite) {
		this.civilite = civilite;
	}
	public String getDaten() {
		return daten;
	}
	public void setDaten(String daten) {
		this.daten = daten;
	}
	public String getDatenConj() {
		return datenConj;
	}
	public void setDatenConj(String datenConj) {
		this.datenConj = datenConj;
	}
	public short getCiviliteconj() {
		return civiliteconj;
	}
	public void setCiviliteconj(short civiliteconj) {
		this.civiliteconj = civiliteconj;
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
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
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
	public String getConnu() {
		return connu;
	}
	public void setConnu(String connu) {
		this.connu = connu;
	}
	public int getGenre() {
		return genre;
	}
	public void setGenre(int genre) {
		this.genre = genre;
	}
	public boolean getActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	public short getBirb() {
		return birb;
	}
	public void setBirb(short birb) {
		this.birb = birb;
	}
	public String getNatnr() {
		return natnr;
	}
	public void setNatnr(String natnr) {
		this.natnr = natnr;
	}
	public String getDateUpd() {
		return dateUpd;
	}
	public void setDateUpd(String dateUpd) {
		this.dateUpd = dateUpd;
	}
	public String getRegio() {
		return regio;
	}
	public void setRegio(String regio) {
		this.regio = regio;
	}
	public short getLCpas() {
		return lCpas;
	}
	public void setLCpas(short lCpas) {
		this.lCpas = lCpas;
	}
	public String getDatUpdBirb() {
		return datUpdBirb;
	}
	public void setDatUpdBirb(String datUpdBirb) {
		this.datUpdBirb = datUpdBirb;
	}
	public short getCritBirb() {
		return critBirb;
	}
	public void setCritBirb(short critBirb) {
		this.critBirb = critBirb;
	}
	public short getCoeff() {
		return coeff;
	}
	public void setCoeff(short coeff) {
		this.coeff = coeff;
	}
	public String getNomsav() {
		return nomsav;
	}
	public void setNomsav(String nomsav) {
		this.nomsav = nomsav;
	}
	public String getPrenomsav() {
		return prenomsav;
	}
	public void setPrenomsav(String prenomsav) {
		this.prenomsav = prenomsav;
	}
	public int getGenreconj() {
		return genreconj;
	}
	public void setGenreconj(int genreconj) {
		this.genreconj = genreconj;
	}
	public short getLbanque() {
		return lbanque;
	}
	public void setLbanque(short lbanque) {
		this.lbanque = lbanque;
	}
	
	
	public Long getNbDep() {
		return nbDep;
	}

	public void setNbDep(Long nbDep) {
		this.nbDep = nbDep;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}
	
	public Long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	
	 
	 
}
