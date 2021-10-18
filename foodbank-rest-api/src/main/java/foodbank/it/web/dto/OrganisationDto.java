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

	    private Short daten; 
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
	   
	    private Short feadN;
	    
	    private String remLivr;
	    
	    private Short cotAnnuelle;
	   
	    private Integer  cotMonths;
	    
	    private Integer cotSup;
	   
	    private Integer  cotMonthsSup;
	    
	    private Integer  depotram;
	    
	    private String lupdUserName;
	   
	    private String lupdTs;
	    
	    private Short lienBanque; 
	    
	    private String nomDepot; // calculated field
	    
	    // OrgProgram entities
	    
private boolean luam;
	    
	    private boolean lupm;
	    
	    private boolean tuam;
	    
	    private boolean tupm;
	    
	    private boolean weam;
	    
	    private boolean wepm;
	    
	    private boolean tham;
	    
	    private boolean thpm;
	    
	    private boolean fram;
	    
	    private boolean frpm;
	    
	    private boolean saam;
	    
	    private boolean sapm;
	    
	    private boolean sunam;
	    
	    private boolean sunpm;
	    private String reluam;
	    private String relupm;
	    private String retuam;
	    private String retupm;
	    private String reweam;
	    private String rewepm;
	    private String retham;
	    private String rethpm;
	    private String refram;
	    private String refrpm;
	    private String resaam;
	    private String resapm;
	    private String resunam;
	    private String resunpm;
	    
	    private boolean porc;
	    private boolean legFrais;
	    
	    private boolean congel;
	    private String congelCap;
	    
	    private Integer auditor;
	    private String dateAudit;
	    private Long lastAudit;
	    private Long  totalRecords;
		
	    
	    protected OrganisationDto() {
	    	
	    }

		public OrganisationDto(int idDis, String refInt, String birbCode, int lienDepot,
				String societe, String adresse, String statut, String email, String cp, String localite,
				Short pays, String tva, String website, String tel, String gsm, Short daten, String banque,
				Short region, String iban, String classique, String bic, boolean actif, Short civilite, String nom,
				String prenom, Short civiliteVp, String prenomVp, String nomVp, String telVp, String gsmVp,
				Short civiliteSec, String prenomSec, String nomSec, String telSec, String gsmSec, Short civiliteTres,
				String prenomTres, String nomTres, String telTres, String gsmTres, String emailPres, String emailVp,
				String emailSec, String emailTres, String telPres, String gsmPres, String disprog, String afsca,
				boolean webauthority, Short langue, LocalDateTime lastvisit, Integer  nbrefix, boolean cpasyN, Short lienCpas,
				boolean birbyN,boolean depyN, Short logBirb, Short actComp1, Short actComp2, Short actComp3,
				Short actComp4, Short actComp5, Short actComp6, String actComp7, Short nrTournee, boolean susp,
				String stopSusp, String rem, boolean isMsonac, Short classeFbba1, Short classeFbba2, Short classeFbba3,
				Integer  nFam, Integer  nPers, Integer  nNour, Integer  nBebe, Integer  nEnf, Integer nAdo, Integer n1824,
				BigDecimal nEq, Integer  nSen, boolean depPrinc,
				boolean gestBen, Short tourneeJour, Short tourneeSem, Short coldis, Short lienGd, Short lienGs,
				BigDecimal montCot, Integer  antenne, String afsca1, String afsca2, String afsca3, Integer  nrFead,
				Short tourneeMois, boolean distrListPdt, boolean distrListVp, boolean distrListSec, boolean distrListTres,
				String adresse2, String cp2, String localite2, Short pays2, String dateReg, String fax, Short feadN,
				String remLivr, Short cotAnnuelle, Integer  cotMonths, Integer cotSup, Integer  cotMonthsSup, Integer  depotram,
				String lupdUserName, LocalDateTime lupdTs,Short lienBanque, String nomDepot,
				// orgprogram fields
				boolean luam, boolean lupm, boolean tuam, boolean tupm,
				boolean weam, boolean wepm, boolean tham, boolean thpm, boolean fram, boolean frpm, boolean saam, boolean sapm, boolean sunam, boolean sunpm,
				String reluam, String relupm, String retuam, String retupm, String reweam, String rewepm, String retham,
				String rethpm, String refram, String refrpm, String resaam, String resapm, String resunam,
				String resunpm, boolean porc, boolean legFrais, boolean congel, String congelCap, Integer auditor, String dateAudit,
				Long lastAudit, Long  totalRecords
				) {
			super();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			this.idDis = idDis;
			this.refInt = refInt;
			this.birbCode = birbCode;
			this.lienDepot = lienDepot;
			this.societe = societe;
			this.adresse = adresse;
			this.statut = statut;
			this.email = email;
			this.cp = cp;
			this.localite = localite;
			this.pays = pays;
			this.tva = tva;
			this.website = website;
			this.tel = tel;
			this.gsm = gsm;
			this.daten = daten;
			this.banque = banque;
			this.region = region;
			this.iban = iban;
			this.classique = classique;
			this.bic = bic;
			this.actif = actif;
			this.civilite = civilite;
			this.nom = nom;
			this.prenom = prenom;
			this.civiliteVp = civiliteVp;
			this.prenomVp = prenomVp;
			this.nomVp = nomVp;
			this.telVp = telVp;
			this.gsmVp = gsmVp;
			this.civiliteSec = civiliteSec;
			this.prenomSec = prenomSec;
			this.nomSec = nomSec;
			this.telSec = telSec;
			this.gsmSec = gsmSec;
			this.civiliteTres = civiliteTres;
			this.prenomTres = prenomTres;
			this.nomTres = nomTres;
			this.telTres = telTres;
			this.gsmTres = gsmTres;
			this.emailPres = emailPres;
			this.emailVp = emailVp;
			this.emailSec = emailSec;
			this.emailTres = emailTres;
			this.telPres = telPres;
			this.gsmPres = gsmPres;
			this.disprog = disprog;
			this.afsca = afsca;
			this.webauthority = webauthority;
			this.langue = langue;
			if (lastvisit == null) {
				this.lastvisit = "";
			}
			else {				
				this.lastvisit = lastvisit.format(formatter);
			}
			this.nbrefix = nbrefix;
			this.cpasyN = cpasyN;
			this.lienCpas = lienCpas;
			this.birbyN = birbyN;
			this.depyN = depyN;
			this.logBirb = logBirb;
			this.actComp1 = actComp1;
			this.actComp2 = actComp2;
			this.actComp3 = actComp3;
			this.actComp4 = actComp4;
			this.actComp5 = actComp5;
			this.actComp6 = actComp6;
			this.actComp7 = actComp7;
			this.nrTournee = nrTournee;
			this.susp = susp;
			this.stopSusp = stopSusp;
			this.rem = rem;
			this.msonac = isMsonac;
			this.classeFbba1 = classeFbba1;
			this.classeFbba2 = classeFbba2;
			this.classeFbba3 = classeFbba3;
			this.nFam = nFam;
			this.nPers = nPers;
			this.nNour = nNour;
			this.nBebe = nBebe;
			this.nEnf = nEnf;
			this.nAdo = nAdo;
			this.n1824 = n1824;
			this.nEq = nEq;
			this.nSen = nSen;
			this.depPrinc = depPrinc;
			this.gestBen = gestBen;
			this.tourneeJour = tourneeJour;
			this.tourneeSem = tourneeSem;
			this.coldis = coldis;
			this.lienGd = lienGd;
			this.lienGs = lienGs;
			this.montCot = montCot;
			this.antenne = antenne;
			this.afsca1 = afsca1;
			this.afsca2 = afsca2;
			this.afsca3 = afsca3;
			this.nrFead = nrFead;
			this.tourneeMois = tourneeMois;
			this.distrListPdt = distrListPdt;
			this.distrListVp = distrListVp;
			this.distrListSec = distrListSec;
			this.distrListTres = distrListTres;
			this.adresse2 = adresse2;
			this.cp2 = cp2;
			this.localite2 = localite2;
			this.pays2 = pays2;
			this.dateReg = dateReg;
			this.fax = fax;
			this.feadN = feadN;
			this.remLivr = remLivr;
			this.cotAnnuelle = cotAnnuelle;
			this.cotMonths = cotMonths;
			this.cotSup = cotSup;
			this.cotMonthsSup = cotMonthsSup;
			this.depotram = depotram;
			this.lupdUserName = lupdUserName;
			if (lupdTs == null) {
				this.lupdTs = "";
			}
			else {				
				this.lupdTs = lupdTs.format(formatter);
			}
			this.lienBanque = lienBanque;
			this.nomDepot = nomDepot;
			// OrgProgram Fields
			this.luam = luam;
			this.lupm = lupm;
			this.tuam = tuam;
			this.tupm = tupm;
			this.weam = weam;
			this.wepm = wepm;
			this.tham = tham;
			this.thpm = thpm;
			this.fram = fram;
			this.frpm = frpm;
			this.saam = saam;
			this.sapm = sapm;
			this.sunam = sunam;
			this.sunpm = sunpm;
			this.reluam = reluam;
			this.relupm = relupm;
			this.retuam = retuam;
			this.retupm = retupm;
			this.reweam = reweam;
			this.rewepm = rewepm;
			this.retham = retham;
			this.rethpm = rethpm;
			this.refram = refram;
			this.refrpm = refrpm;
			this.resaam = resaam;
			this.resapm = resapm;
			this.resunam = resunam;
			this.resunpm = resunpm;
			this.porc = porc;
			this.legFrais = legFrais;
			this.congel = congel;
			this.congelCap = congelCap;
			this.auditor = auditor;
			this.dateAudit = dateAudit;
			this.lastAudit = lastAudit;
			this.totalRecords = totalRecords;
			
		}

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

		public Short getDaten() {
			return daten;
		}

		public void setDaten(Short daten) {
			this.daten = daten;
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

		public Short getFeadN() {
			return feadN;
		}

		public void setFeadN(Short feadN) {
			this.feadN = feadN;
		}

		public String getRemLivr() {
			return remLivr;
		}

		public void setRemLivr(String remLivr) {
			this.remLivr = remLivr;
		}

		public Short getCotAnnuelle() {
			return cotAnnuelle;
		}

		public void setCotAnnuelle(Short cotAnnuelle) {
			this.cotAnnuelle = cotAnnuelle;
		}

		public Integer  getCotMonths() {
			return cotMonths;
		}

		public void setCotMonths(Integer  cotMonths) {
			this.cotMonths = cotMonths;
		}

		public Integer getCotSup() {
			return cotSup;
		}

		public void setCotSup(Integer cotSup) {
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
	    // OrgProgram fields getters and setters
		public boolean getLuam() {
			return luam;
		}
		public void setLuam(boolean luam) {
			this.luam = luam;
		}
		public boolean getLupm() {
			return lupm;
		}
		public void setLupm(boolean lupm) {
			this.lupm = lupm;
		}
		public boolean getTuam() {
			return tuam;
		}
		public void setTuam(boolean tuam) {
			this.tuam = tuam;
		}
		public boolean getTupm() {
			return tupm;
		}
		public void setTupm(boolean tupm) {
			this.tupm = tupm;
		}
		public boolean getWeam() {
			return weam;
		}
		public void setWeam(boolean weam) {
			this.weam = weam;
		}
		public boolean getWepm() {
			return wepm;
		}
		public void setWepm(boolean wepm) {
			this.wepm = wepm;
		}
		public boolean getTham() {
			return tham;
		}
		public void setTham(boolean tham) {
			this.tham = tham;
		}
		public boolean getThpm() {
			return thpm;
		}
		public void setThpm(boolean thpm) {
			this.thpm = thpm;
		}
		public boolean getFram() {
			return fram;
		}
		public void setFram(boolean fram) {
			this.fram = fram;
		}
		public boolean getFrpm() {
			return frpm;
		}
		public void setFrpm(boolean frpm) {
			this.frpm = frpm;
		}
		public boolean getSaam() {
			return saam;
		}
		public void setSaam(boolean saam) {
			this.saam = saam;
		}
		public boolean getSapm() {
			return sapm;
		}
		public void setSapm(boolean sapm) {
			this.sapm = sapm;
		}
		public boolean getSunam() {
			return sunam;
		}
		public void setSunam(boolean sunam) {
			this.sunam = sunam;
		}
		public boolean getSunpm() {
			return sunpm;
		}
		public void setSunpm(boolean sunpm) {
			this.sunpm = sunpm;
		}
		public String getReluam() {
			return reluam;
		}
		public void setReluam(String reluam) {
			this.reluam = reluam;
		}
		public String getRelupm() {
			return relupm;
		}
		public void setRelupm(String relupm) {
			this.relupm = relupm;
		}
		public String getRetuam() {
			return retuam;
		}
		public void setRetuam(String retuam) {
			this.retuam = retuam;
		}
		public String getRetupm() {
			return retupm;
		}
		public void setRetupm(String retupm) {
			this.retupm = retupm;
		}
		public String getReweam() {
			return reweam;
		}
		public void setReweam(String reweam) {
			this.reweam = reweam;
		}
		public String getRewepm() {
			return rewepm;
		}
		public void setRewepm(String rewepm) {
			this.rewepm = rewepm;
		}
		public String getRetham() {
			return retham;
		}
		public void setRetham(String retham) {
			this.retham = retham;
		}
		public String getRethpm() {
			return rethpm;
		}
		public void setRethpm(String rethpm) {
			this.rethpm = rethpm;
		}
		public String getRefram() {
			return refram;
		}
		public void setRefram(String refram) {
			this.refram = refram;
		}
		public String getRefrpm() {
			return refrpm;
		}
		public void setRefrpm(String refrpm) {
			this.refrpm = refrpm;
		}
		public String getResaam() {
			return resaam;
		}
		public void setResaam(String resaam) {
			this.resaam = resaam;
		}
		public String getResapm() {
			return resapm;
		}
		public void setResapm(String resapm) {
			this.resapm = resapm;
		}
		public String getResunam() {
			return resunam;
		}
		public void setResunam(String resunam) {
			this.resunam = resunam;
		}
		public String getResunpm() {
			return resunpm;
		}
		public void setResunpm(String resunpm) {
			this.resunpm = resunpm;
		}
		public boolean getPorc() {
			return porc;
		}
		public void setPorc(boolean porc) {
			this.porc = porc;
		}
		public boolean getLegFrais() {
			return legFrais;
		}
		public void setLegFrais(boolean legFrais) {
			this.legFrais = legFrais;
		}
		public boolean getCongel() {
			return congel;
		}
		public void setCongel(boolean congel) {
			this.congel = congel;
		}
		public String getCongelCap() {
			return congelCap;
		}
		public void setCongelCap(String congelCap) {
			this.congelCap = congelCap;
		}
		public Integer getAuditor() {
			return auditor;
		}
		public void setAuditor(Integer auditor) {
			this.auditor = auditor;
		}
		public String getDateAudit() {
			return dateAudit;
		}
		public void setDateAudit(String dateAudit) {
			this.dateAudit = dateAudit;
		}
		public Long getLastAudit() {
			return lastAudit;
		}
		public void setLastAudit(Long lastAudit) {
			this.lastAudit = lastAudit;
		}
		

		public String getNomDepot() {
			return nomDepot;
		}

		public void setNomDepot(String nomDepot) {
			this.nomDepot = nomDepot;
		}

		public Long getTotalRecords() {
			return totalRecords;
		}

		public void setTotalRecords(Long totalRecords) {
			this.totalRecords = totalRecords;
		}


}
