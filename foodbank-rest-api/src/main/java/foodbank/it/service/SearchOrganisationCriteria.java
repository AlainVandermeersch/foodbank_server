package foodbank.it.service;

public class SearchOrganisationCriteria {
	private Integer idDis;
	private Integer regId;
	private Integer classeFBBA;
	private String societe;
	private String adresse;
	private String nomDepot;
	private Integer lienBanque;
	private Integer lienDepot;
	private Boolean isDepot;
	private Boolean isBirb;
	private Boolean isAgreed;
	private Boolean actif;
	private String refInt;
	private Boolean cotAnnuelle;
	private Boolean cotSup;
	private String statut;
	private Boolean gestBen;
	
	
	public SearchOrganisationCriteria(Integer idDis,Integer regId, Integer classeFBBA, String societe, String adresse, Boolean isAgreed, Boolean actif, String nomDepot, 
			Integer lienBanque, Integer lienDepot, Boolean isDepot,Boolean isBirb, String refInt, Boolean cotAnnuelle,Boolean cotSup,String statut,Boolean gestBen) {
		this.idDis = idDis;
		this.regId = regId;
		this.classeFBBA = classeFBBA;
		this.societe = societe;
		this.adresse = adresse;
		this.isAgreed = isAgreed;
		this.nomDepot = nomDepot;		
		this.lienBanque = lienBanque;
		this.lienDepot = lienDepot;
		this.isDepot = isDepot;
		this.isBirb = isBirb;
		this.actif = actif;
		this.refInt = refInt;
		this.cotAnnuelle = cotAnnuelle;
		this.cotSup = cotSup;
		this.statut = statut;
		this.gestBen = gestBen;
	}

	

	public Integer getIdDis() {
		return idDis;
	}



	public void setIdDis(Integer idDis) {
		this.idDis = idDis;
	}



	public Integer getRegId() {
		return regId;
	}



	public void setRegId(Integer regId) {
		this.regId = regId;
	}



	public Integer getClasseFBBA() {
		return classeFBBA;
	}



	public void setClasseFBBA(Integer classeFBBA) {
		this.classeFBBA = classeFBBA;
	}



	public String getSociete() {
		return societe;
	}



	public void setSociete(String societe) {
		this.societe = societe;
	}



	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public Boolean getIsAgreed() {
		return isAgreed;
	}



	public void setIsAgreed(Boolean isAgreed) {
		this.isAgreed = isAgreed;
	}



	public Boolean getActif() {
		return actif;
	}



	public void setActif(Boolean actif) {
		this.actif = actif;
	}



	public String getNomDepot() {
		return nomDepot;
	}



	public void setNomDepot(String nomDepot) {
		this.nomDepot = nomDepot;
	}



	public Boolean getIsDepot() {
		return isDepot;
	}



	public void setIsDepot(Boolean isDepot) {
		this.isDepot = isDepot;
	}



	public Boolean getIsBirb() {
		return isBirb;
	}



	public void setIsBirb(Boolean isBirb) {
		this.isBirb = isBirb;
	}


	public String getRefInt() {
		return refInt;
	}



	public void setRefInt(String refInt) {
		this.refInt = refInt;
	}



	public Boolean getCotAnnuelle() {
		return cotAnnuelle;
	}



	public void setCotAnnuelle(Boolean cotAnnuelle) {
		this.cotAnnuelle = cotAnnuelle;
	}



	public Boolean getCotSup() {
		return cotSup;
	}



	public void setCotSup(Boolean cotSup) {
		this.cotSup = cotSup;
	}



	public String getStatut() {
		return statut;
	}



	public void setStatut(String statut) {
		this.statut = statut;
	}



	public Integer getLienBanque() {
		return lienBanque;
	}

	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}

	public Integer getlienDepot() {
		return lienDepot;
	}

	public void setlienDepot(Integer lienDepot) {
		this.lienDepot = lienDepot;
	}



	public Boolean getGestBen() {
		return gestBen;
	}



	public void setGestBen(Boolean gestBen) {
		this.gestBen = gestBen;
	}

	
}
