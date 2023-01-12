package foodbank.it.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrganisationDto {
	 
	    private int idDis;
	     
	    private String refInt;
	    private String birbCode;
	     
	    private int lienDepot;
	     
	    private String societe;
	    
	    private String adresse;
	    
	    private String statut;
	     
	    private String email;
	     
	    private String cp;
	     
	    private String localite;
	    
	    private Short pays;
	   
	    private String tva;
	    private String website;
	    
	    private String tel;
	   
	    private String gsm;

	    private boolean agreed; 
	    private String banque;
	   
	    private Short region;
	    private String iban;
	    private String classique;
	    private String bic;
	  
	    private boolean actif;
	    
	    private Short civilite;
	  
	    private String nom;
	   
	    private String prenom;
	   
	    private Short civiliteVp;
	    
	    private String prenomVp;
	    
	    private String nomVp;
	    
	    private String telVp;
	    
	    private String gsmVp;
	   
	    private Short civiliteSec;
	   
	    private String prenomSec;
	    
	    private String nomSec;
	   
	    private String telSec;
	   
	    private String gsmSec;
	    
	    private Short civiliteTres;
	    
	    private String prenomTres;
	   
	    private String nomTres;
	    
	    private String telTres;
	   
	    private String gsmTres;
	  
	    private String emailPres;
	    
	    private String emailVp;
	    
	    private String emailSec;
	   
	    private String emailTres;
	    
	    private String telPres;
	    
	    private String gsmPres;
	    
	    private String disprog;
	   
	    private String afsca;
	    
	    private boolean webauthority;
	    
	    private Short langue;
	    private String lastvisit;
	   
	    private Integer  nbrefix;
	    
	    private boolean cpasyN;
	   
	    private Short lienCpas;
	    
	    private boolean birbyN;
	      
	    private boolean depyN;

	    private Short logBirb;
	   
	    private Short actComp1;
	    
	    private Short actComp2;
	    
	    private Short actComp3;
	   
	    private Short actComp4;
	   
	    private Short actComp5;
	   
	    private Short actComp6;
	   
	    private String actComp7;
	    
	    private Short nrTournee;
	   
	    private boolean susp;
	    
	    private String stopSusp;
	    
	    private String rem;
	    
	    private boolean msonac;
	    
	    private Short classeFbba1;
	   
	    private Short classeFbba2;
	    
	    private Short classeFbba3;
	   
	    private Integer  nFam;
	   
	    private Integer  nPers;
	   
	    private Integer  nNour;
	    
	    private Integer  nBebe;
	   
	    private Integer  nEnf;
	   
	    private Integer  nAdo;
	    
	    private Integer  n1824;
	   
	    private BigDecimal nEq;
	    
	    private Integer  nSen;
	   
	    private boolean depPrinc;
	   
	    private boolean gestBen;
	   
	    private Short tourneeJour;
	   
	    private Short tourneeSem;
	    
	    private Short coldis;
	    
	    private Short lienGd;
	    
	    private Short lienGs;
	    
	    private BigDecimal montCot;
	   
	    private Integer  antenne;
	    
	    private String afsca1;
	   
	    private String afsca2;
	    
	    private String afsca3;
	   
	    private Integer  nrFead;
	   
	    private Short tourneeMois;
	   
	    private boolean distrListPdt;
	  
	    private boolean distrListVp;
	    
	    private boolean distrListSec;
	   
	    private boolean distrListTres;
	    
	    private String adresse2;
	    
	    private String cp2;
	   
	    private String localite2;
	    
	    private Short pays2;
	  
	    private String dateReg;
	  
	    private String fax;
	   
	    private boolean feadN;
	    
	    private String remLivr;
	    
	    private boolean cotAnnuelle;
	   
	    private Integer  cotMonths;
	    
	    private boolean cotSup;
	   
	    private Integer  cotMonthsSup;
	    
	    private Integer  depotram;
	    
	    private String lupdUserName;
	   
	    private String lupdTs;
	    
	    private Short lienBanque; 
	    
	    private String nomDepot; // calculated field
		private String nomDepotRamasse; // calculated field
	   
	    private String bankShortName;

		private String antenneOrgName; // generated when creating dto in controller

		private long nbLogins;

		private String anomalies; // generated when creating dto in controller
	    private Long  totalRecords;

		private Long totalFamilies;
		private Long totalPersons;


	    


		public int getIdDis() {
			return idDis;
		}

		public void setIdDis(int idDis) {
			this.idDis = idDis;
		}

		public String getRefInt() {
			return refInt;
		}

		public void setRefInt(String refInt) {
			this.refInt = refInt;
		}

		public String getBirbCode() {
			return birbCode;
		}

		public void setBirbCode(String birbCode) {
			this.birbCode = birbCode;
		}

		public int getLienDepot() {
			return lienDepot;
		}

		public void setLienDepot(int lienDepot) {
			this.lienDepot = lienDepot;
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

		public String getStatut() {
			return statut;
		}

		public void setStatut(String statut) {
			this.statut = statut;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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

		public Short getPays() {
			return pays;
		}

		public void setPays(Short pays) {
			this.pays = pays;
		}

		public String getTva() {
			return tva;
		}

		public void setTva(String tva) {
			this.tva = tva;
		}

		public String getWebsite() {
			return website;
		}

		public void setWebsite(String website) {
			this.website = website;
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

		public boolean getAgreed() {
			return agreed;
		}

		public void setAgreed(boolean agreed) {
			this.agreed = agreed;
		}

	    public String getBanque() {
			return banque;
		}

		public void setBanque(String banque) {
			this.banque = banque;
		}

		public Short getRegion() {
			return region;
		}

		public void setRegion(Short region) {
			this.region = region;
		}

		public String getIban() {
			return iban;
		}

		public void setIban(String iban) {
			this.iban = iban;
		}

		public String getClassique() {
			return classique;
		}

		public void setClassique(String classique) {
			this.classique = classique;
		}

		public String getBic() {
			return bic;
		}

		public void setBic(String bic) {
			this.bic = bic;
		}

		public boolean getActif() {
			return actif;
		}

		public void setActif(boolean actif) {
			this.actif = actif;
		}

		public Short getCivilite() {
			return civilite;
		}

		public void setCivilite(Short civilite) {
			this.civilite = civilite;
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

		public Short getCiviliteVp() {
			return civiliteVp;
		}

		public void setCiviliteVp(Short civiliteVp) {
			this.civiliteVp = civiliteVp;
		}

		public String getPrenomVp() {
			return prenomVp;
		}

		public void setPrenomVp(String prenomVp) {
			this.prenomVp = prenomVp;
		}

		public String getNomVp() {
			return nomVp;
		}

		public void setNomVp(String nomVp) {
			this.nomVp = nomVp;
		}

		public String getTelVp() {
			return telVp;
		}

		public void setTelVp(String telVp) {
			this.telVp = telVp;
		}

		public String getGsmVp() {
			return gsmVp;
		}

		public void setGsmVp(String gsmVp) {
			this.gsmVp = gsmVp;
		}

		public Short getCiviliteSec() {
			return civiliteSec;
		}

		public void setCiviliteSec(Short civiliteSec) {
			this.civiliteSec = civiliteSec;
		}

		public String getPrenomSec() {
			return prenomSec;
		}

		public void setPrenomSec(String prenomSec) {
			this.prenomSec = prenomSec;
		}

		public String getNomSec() {
			return nomSec;
		}

		public void setNomSec(String nomSec) {
			this.nomSec = nomSec;
		}

		public String getTelSec() {
			return telSec;
		}

		public void setTelSec(String telSec) {
			this.telSec = telSec;
		}

		public String getGsmSec() {
			return gsmSec;
		}

		public void setGsmSec(String gsmSec) {
			this.gsmSec = gsmSec;
		}

		public Short getCiviliteTres() {
			return civiliteTres;
		}

		public void setCiviliteTres(Short civiliteTres) {
			this.civiliteTres = civiliteTres;
		}

		public String getPrenomTres() {
			return prenomTres;
		}

		public void setPrenomTres(String prenomTres) {
			this.prenomTres = prenomTres;
		}

		public String getNomTres() {
			return nomTres;
		}

		public void setNomTres(String nomTres) {
			this.nomTres = nomTres;
		}

		public String getTelTres() {
			return telTres;
		}

		public void setTelTres(String telTres) {
			this.telTres = telTres;
		}

		public String getGsmTres() {
			return gsmTres;
		}

		public void setGsmTres(String gsmTres) {
			this.gsmTres = gsmTres;
		}

		public String getEmailPres() {
			return emailPres;
		}

		public void setEmailPres(String emailPres) {
			this.emailPres = emailPres;
		}

		public String getEmailVp() {
			return emailVp;
		}

		public void setEmailVp(String emailVp) {
			this.emailVp = emailVp;
		}

		public String getEmailSec() {
			return emailSec;
		}

		public void setEmailSec(String emailSec) {
			this.emailSec = emailSec;
		}

		public String getEmailTres() {
			return emailTres;
		}

		public void setEmailTres(String emailTres) {
			this.emailTres = emailTres;
		}

		public String getTelPres() {
			return telPres;
		}

		public void setTelPres(String telPres) {
			this.telPres = telPres;
		}

		public String getGsmPres() {
			return gsmPres;
		}

		public void setGsmPres(String gsmPres) {
			this.gsmPres = gsmPres;
		}

		public String getDisprog() {
			return disprog;
		}

		public void setDisprog(String disprog) {
			this.disprog = disprog;
		}

		public String getAfsca() {
			return afsca;
		}

		public void setAfsca(String afsca) {
			this.afsca = afsca;
		}

		public boolean isWebauthority() {
			return webauthority;
		}

		public void setWebauthority(boolean webauthority) {
			this.webauthority = webauthority;
		}

		public Short getLangue() {
			return langue;
		}

		public void setLangue(Short langue) {
			this.langue = langue;
		}

		public String getLastvisit() {
			return lastvisit;
		}

		public void setLastvisit(String lastvisit) {
			this.lastvisit = lastvisit;
		}

		public Integer  getNbrefix() {
			return nbrefix;
		}

		public void setNbrefix(Integer  nbrefix) {
			this.nbrefix = nbrefix;
		}

		public boolean getCpasyN() {
			return cpasyN;
		}

		public void setCpasyN(boolean cpasyN) {
			this.cpasyN = cpasyN;
		}

		public Short getLienCpas() {
			return lienCpas;
		}

		public void setLienCpas(Short lienCpas) {
			this.lienCpas = lienCpas;
		}

		
		public boolean isBirbyN() {
			return birbyN;
		}

		public void setBirbyN(boolean birbyN) {
			this.birbyN = birbyN;
		}

		public boolean getDepyN() {
			return depyN;
		}

		public void setDepyN(boolean depyN) {
			this.depyN = depyN;
		}

		public Short getLogBirb() {
			return logBirb;
		}

		public void setLogBirb(Short logBirb) {
			this.logBirb = logBirb;
		}

		public Short getActComp1() {
			return actComp1;
		}

		public void setActComp1(Short actComp1) {
			this.actComp1 = actComp1;
		}

		public Short getActComp2() {
			return actComp2;
		}

		public void setActComp2(Short actComp2) {
			this.actComp2 = actComp2;
		}

		public Short getActComp3() {
			return actComp3;
		}

		public void setActComp3(Short actComp3) {
			this.actComp3 = actComp3;
		}

		public Short getActComp4() {
			return actComp4;
		}

		public void setActComp4(Short actComp4) {
			this.actComp4 = actComp4;
		}

		public Short getActComp5() {
			return actComp5;
		}

		public void setActComp5(Short actComp5) {
			this.actComp5 = actComp5;
		}

		public Short getActComp6() {
			return actComp6;
		}

		public void setActComp6(Short actComp6) {
			this.actComp6 = actComp6;
		}

		public String getActComp7() {
			return actComp7;
		}

		public void setActComp7(String actComp7) {
			this.actComp7 = actComp7;
		}

		public Short getNrTournee() {
			return nrTournee;
		}

		public void setNrTournee(Short nrTournee) {
			this.nrTournee = nrTournee;
		}

		public boolean getSusp() {
			return susp;
		}

		public void setSusp(boolean susp) {
			this.susp = susp;
		}

		public String getStopSusp() {
			return stopSusp;
		}

		public void setStopSusp(String stopSusp) {
			this.stopSusp = stopSusp;
		}

		public String getRem() {
			return rem;
		}

		public void setRem(String rem) {
			this.rem = rem;
		}

		public boolean getMsonac() {
			return msonac;
		}

		public void setMsonac(boolean msonac) {
			this.msonac = msonac;
		}

		public Short getClasseFbba1() {
			return classeFbba1;
		}

		public void setClasseFbba1(Short classeFbba1) {
			this.classeFbba1 = classeFbba1;
		}

		public Short getClasseFbba2() {
			return classeFbba2;
		}

		public void setClasseFbba2(Short classeFbba2) {
			this.classeFbba2 = classeFbba2;
		}

		public Short getClasseFbba3() {
			return classeFbba3;
		}

		public void setClasseFbba3(Short classeFbba3) {
			this.classeFbba3 = classeFbba3;
		}

		public Integer  getnFam() {
			return nFam;
		}

		public void setnFam(Integer  nFam) {
			this.nFam = nFam;
		}

		public Integer  getnPers() {
			return nPers;
		}

		public void setnPers(Integer  nPers) {
			this.nPers = nPers;
		}

		public Integer  getnNour() {
			return nNour;
		}

		public void setnNour(Integer  nNour) {
			this.nNour = nNour;
		}

		public Integer  getnBebe() {
			return nBebe;
		}

		public void setnBebe(Integer  nBebe) {
			this.nBebe = nBebe;
		}

		public Integer  getnEnf() {
			return nEnf;
		}

		public void setnEnf(Integer  nEnf) {
			this.nEnf = nEnf;
		}

		public Integer  getnAdo() {
			return nAdo;
		}

		public void setnAdo(Integer  nAdo) {
			this.nAdo = nAdo;
		}

		public Integer getN1824() {
			return n1824;
		}

		public void setN1824(Integer n1824) {
			this.n1824 = n1824;
		}

		public BigDecimal getnEq() {
			return nEq;
		}

		public void setnEq(BigDecimal nEq) {
			this.nEq = nEq;
		}

		public Integer  getnSen() {
			return nSen;
		}

		public void setnSen(Integer  nSen) {
			this.nSen = nSen;
		}

		public boolean getDepPrinc() {
			return depPrinc;
		}

		public void setDepPrinc(boolean depPrinc) {
			this.depPrinc = depPrinc;
		}

		public boolean getGestBen() {
			return gestBen;
		}

		public void setGestBen(boolean gestBen) {
			this.gestBen = gestBen;
		}

		public Short getTourneeJour() {
			return tourneeJour;
		}

		public void setTourneeJour(Short tourneeJour) {
			this.tourneeJour = tourneeJour;
		}

		public Short getTourneeSem() {
			return tourneeSem;
		}

		public void setTourneeSem(Short tourneeSem) {
			this.tourneeSem = tourneeSem;
		}

		public Short getColdis() {
			return coldis;
		}

		public void setColdis(Short coldis) {
			this.coldis = coldis;
		}

		public Short getLienGd() {
			return lienGd;
		}

		public void setLienGd(Short lienGd) {
			this.lienGd = lienGd;
		}

		public Short getLienGs() {
			return lienGs;
		}

		public void setLienGs(Short lienGs) {
			this.lienGs = lienGs;
		}

		public BigDecimal getMontCot() {
			return montCot;
		}

		public void setMontCot(BigDecimal montCot) {
			this.montCot = montCot;
		}

		public Integer  getAntenne() {
			return antenne;
		}

		public void setAntenne(Integer  antenne) {
			this.antenne = antenne;
		}

		public String getAfsca1() {
			return afsca1;
		}

		public void setAfsca1(String afsca1) {
			this.afsca1 = afsca1;
		}

		public String getAfsca2() {
			return afsca2;
		}

		public void setAfsca2(String afsca2) {
			this.afsca2 = afsca2;
		}

		public String getAfsca3() {
			return afsca3;
		}

		public void setAfsca3(String afsca3) {
			this.afsca3 = afsca3;
		}

		public Integer  getNrFead() {
			return nrFead;
		}

		public void setNrFead(Integer  nrFead) {
			this.nrFead = nrFead;
		}

		public Short getTourneeMois() {
			return tourneeMois;
		}

		public void setTourneeMois(Short tourneeMois) {
			this.tourneeMois = tourneeMois;
		}

		public boolean getDistrListPdt() {
			return distrListPdt;
		}

		public void setDistrListPdt(boolean distrListPdt) {
			this.distrListPdt = distrListPdt;
		}

		public boolean getDistrListVp() {
			return distrListVp;
		}

		public void setDistrListVp(boolean distrListVp) {
			this.distrListVp = distrListVp;
		}

		public boolean getDistrListSec() {
			return distrListSec;
		}

		public void setDistrListSec(boolean distrListSec) {
			this.distrListSec = distrListSec;
		}

		public boolean getDistrListTres() {
			return distrListTres;
		}

		public void setDistrListTres(boolean distrListTres) {
			this.distrListTres = distrListTres;
		}

		public String getAdresse2() {
			return adresse2;
		}

		public void setAdresse2(String adresse2) {
			this.adresse2 = adresse2;
		}

		public String getCp2() {
			return cp2;
		}

		public void setCp2(String cp2) {
			this.cp2 = cp2;
		}

		public String getLocalite2() {
			return localite2;
		}

		public void setLocalite2(String localite2) {
			this.localite2 = localite2;
		}

		public Short getPays2() {
			return pays2;
		}

		public void setPays2(Short pays2) {
			this.pays2 = pays2;
		}

		public String getDateReg() {
			return dateReg;
		}

		public void setDateReg(String dateReg) {
			this.dateReg = dateReg;
		}

		public String getFax() {
			return fax;
		}

		public void setFax(String fax) {
			this.fax = fax;
		}
	
		public boolean isFeadN() {
			return feadN;
		}

		public void setFeadN(boolean feadN) {
			this.feadN = feadN;
		}

		public String getRemLivr() {
			return remLivr;
		}

		public void setRemLivr(String remLivr) {
			this.remLivr = remLivr;
		}

		public boolean isCotAnnuelle() {
			return cotAnnuelle;
		}

		public void setCotAnnuelle(boolean cotAnnuelle) {
			this.cotAnnuelle = cotAnnuelle;
		}

		public Integer  getCotMonths() {
			return cotMonths;
		}

		public void setCotMonths(Integer  cotMonths) {
			this.cotMonths = cotMonths;
		}

		public boolean isCotSup() {
			return cotSup;
		}

		public void setCotSup(boolean cotSup) {
			this.cotSup = cotSup;
		}

		public Integer  getCotMonthsSup() {
			return cotMonthsSup;
		}

		public void setCotMonthsSup(Integer  cotMonthsSup) {
			this.cotMonthsSup = cotMonthsSup;
		}

		public Integer  getDepotram() {
			return depotram;
		}

		public void setDepotram(Integer  depotram) {
			this.depotram = depotram;
		}

		public String getLupdUserName() {
			return lupdUserName;
		}

		public void setLupdUserName(String lupdUserName) {
			this.lupdUserName = lupdUserName;
		}

		public String getLupdTs() {
			return lupdTs;
		}

		public void setLupdTs(String lupdTs) {
			this.lupdTs = lupdTs;
		}

		public Short getLienBanque() {
			return lienBanque;
		}

		public void setLienBanque(Short lienBanque) {
			this.lienBanque = lienBanque;
		}
	
		public String getNomDepot() {
			return nomDepot;
		}

		public void setNomDepot(String nomDepot) {
			this.nomDepot = nomDepot;
		}

	public String getNomDepotRamasse() {
		return nomDepotRamasse;
	}

	public void setNomDepotRamasse(String nomDepotRamasse) {
		this.nomDepotRamasse = nomDepotRamasse;
	}

	public String getBankShortName() {
			return bankShortName;
		}

		public void setBankShortName(String bankShortName) {
			this.bankShortName = bankShortName;
		}

		public String getAntenneOrgName() {
			return antenneOrgName;
		}

		public void setAntenneOrgName(String antenneOrgName) {
			this.antenneOrgName = antenneOrgName;
		}

		public long getNbLogins() {
			return nbLogins;
		}

		public void setNbLogins(long nbLogins) {
			this.nbLogins = nbLogins;
		}

	public String getAnomalies() {
		return anomalies;
	}

	public void setAnomalies(String anomalies) {
		this.anomalies = anomalies;
	}

	public Long getTotalRecords() {
			return totalRecords;
		}

	public void setTotalRecords(Long totalRecords) {
			this.totalRecords = totalRecords;
		}

	public Long getTotalFamilies() {
		return totalFamilies;
	}

	public void setTotalFamilies(Long totalFamilies) {
		this.totalFamilies = totalFamilies;
	}

	public Long getTotalPersons() {
		return totalPersons;
	}

	public void setTotalPersons(Long totalPersons) {
		this.totalPersons = totalPersons;
	}
}
