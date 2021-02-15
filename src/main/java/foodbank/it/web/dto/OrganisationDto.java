package foodbank.it.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
	    
	    private short pays;
	   
	    private String tva;
	    private String website;
	    
	    private String tel;
	   
	    private String gsm;

	    private short daten; 
	    private String banque;
	   
	    private short region;
	    private String iban;
	    private String classique;
	    private String bic;
	  
	    private short actif;
	    
	    private short civilite;
	  
	    private String nom;
	   
	    private String prenom;
	   
	    private short civiliteVp;
	    
	    private String prenomVp;
	    
	    private String nomVp;
	    
	    private String telVp;
	    
	    private String gsmVp;
	   
	    private short civiliteSec;
	   
	    private String prenomSec;
	    
	    private String nomSec;
	   
	    private String telSec;
	   
	    private String gsmSec;
	    
	    private short civiliteTres;
	    
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
	    
	    private short langue;
	    private LocalDateTime lastvisit;
	   
	    private int nbrefix;
	    
	    private short cpasyN;
	   
	    private short lienCpas;
	    
	    private short birbyN;
	    
	    private short depyN;
	  
	    private short logBirb;
	   
	    private short actComp1;
	    
	    private short actComp2;
	    
	    private short actComp3;
	   
	    private short actComp4;
	   
	    private short actComp5;
	   
	    private short actComp6;
	   
	    private String actComp7;
	    
	    private short nrTournee;
	   
	    private short susp;
	    
	    private String stopSusp;
	    
	    private String rem;
	    
	    private short msonac;
	    
	    private short classeFbba1;
	   
	    private short classeFbba2;
	    
	    private short classeFbba3;
	   
	    private int nFam;
	   
	    private int nPers;
	   
	    private int nNour;
	    
	    private int nBebe;
	   
	    private int nEnf;
	   
	    private int nAdo;
	   
	    private BigDecimal nEq;
	    
	    private int nSen;
	   
	    private short depPrinc;
	   
	    private short gestBen;
	   
	    private short tourneeJour;
	   
	    private short tourneeSem;
	    
	    private short coldis;
	    
	    private short lienGd;
	    
	    private short lienGs;
	    
	    private BigDecimal montCot;
	   
	    private int antenne;
	    
	    private String afsca1;
	   
	    private String afsca2;
	    
	    private String afsca3;
	   
	    private int nrFead;
	   
	    private short tourneeMois;
	   
	    private short distrListPdt;
	  
	    private short distrListVp;
	    
	    private short distrListSec;
	   
	    private short distrListTres;
	    
	    private String adresse2;
	    
	    private String cp2;
	   
	    private String localite2;
	    
	    private short pays2;
	  
	    private String dateReg;
	  
	    private String fax;
	   
	    private short feadN;
	    
	    private String remLivr;
	    
	    private short cotAnnuelle;
	   
	    private int cotMonths;
	    
	    private int cotSup;
	   
	    private int cotMonthsSup;
	    
	    private int depotram;
	    
	    private String lupdUserName;
	   
	    private LocalDateTime lupdTs;
	    
	    private short lienBanque;
	    
	    public short getLienBanque() {
			return lienBanque;
		}

		public void setLienBanque(short lienBanque) {
			this.lienBanque = lienBanque;
		}

		private String bankName;
	    private String bankShortName;
	    public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getBankShortName() {
			return bankShortName;
		}

		public void setBankShortName(String bankShortName) {
			this.bankShortName = bankShortName;
		}

		
	    
	    protected OrganisationDto() {
	    	
	    }

		public OrganisationDto(int idDis, short lienBanque, String refInt, String birbCode, int lienDepot,
				String societe, String adresse, String statut, String email, String cp, String localite,
				short pays, String tva, String website, String tel, String gsm, short daten, String banque,
				short region, String iban, String classique, String bic, short actif, short civilite, String nom,
				String prenom, short civiliteVp, String prenomVp, String nomVp, String telVp, String gsmVp,
				short civiliteSec, String prenomSec, String nomSec, String telSec, String gsmSec, short civiliteTres,
				String prenomTres, String nomTres, String telTres, String gsmTres, String emailPres, String emailVp,
				String emailSec, String emailTres, String telPres, String gsmPres, String disprog, String afsca,
				boolean webauthority, short langue, LocalDateTime lastvisit, int nbrefix, short cpasyN, short lienCpas,
				short birbyN, short depyN, short logBirb, short actComp1, short actComp2, short actComp3,
				short actComp4, short actComp5, short actComp6, String actComp7, short nrTournee, short susp,
				String stopSusp, String rem, short msonac, short classeFbba1, short classeFbba2, short classeFbba3,
				int nFam, int nPers, int nNour, int nBebe, int nEnf, int nAdo, BigDecimal nEq, int nSen, short depPrinc,
				short gestBen, short tourneeJour, short tourneeSem, short coldis, short lienGd, short lienGs,
				BigDecimal montCot, int antenne, String afsca1, String afsca2, String afsca3, int nrFead,
				short tourneeMois, short distrListPdt, short distrListVp, short distrListSec, short distrListTres,
				String adresse2, String cp2, String localite2, short pays2, String dateReg, String fax, short feadN,
				String remLivr, short cotAnnuelle, int cotMonths, int cotSup, int cotMonthsSup, int depotram,
				String lupdUserName, LocalDateTime lupdTs, String bankShortName,String bankName ) {
			super();
			this.idDis = idDis;
			this.lienBanque = lienBanque;
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
			this.lastvisit = lastvisit;
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
			this.msonac = msonac;
			this.classeFbba1 = classeFbba1;
			this.classeFbba2 = classeFbba2;
			this.classeFbba3 = classeFbba3;
			this.nFam = nFam;
			this.nPers = nPers;
			this.nNour = nNour;
			this.nBebe = nBebe;
			this.nEnf = nEnf;
			this.nAdo = nAdo;
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
			this.lupdTs = lupdTs;
			this.bankShortName = bankShortName;
			this.bankName = bankName;
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

		public short getPays() {
			return pays;
		}

		public void setPays(short pays) {
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

		public short getDaten() {
			return daten;
		}

		public void setDaten(short daten) {
			this.daten = daten;
		}

	    public String getBanque() {
			return banque;
		}

		public void setBanque(String banque) {
			this.banque = banque;
		}

		public short getRegion() {
			return region;
		}

		public void setRegion(short region) {
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

		public short getActif() {
			return actif;
		}

		public void setActif(short actif) {
			this.actif = actif;
		}

		public short getCivilite() {
			return civilite;
		}

		public void setCivilite(short civilite) {
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

		public short getCiviliteVp() {
			return civiliteVp;
		}

		public void setCiviliteVp(short civiliteVp) {
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

		public short getCiviliteSec() {
			return civiliteSec;
		}

		public void setCiviliteSec(short civiliteSec) {
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

		public short getCiviliteTres() {
			return civiliteTres;
		}

		public void setCiviliteTres(short civiliteTres) {
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

		public short getLangue() {
			return langue;
		}

		public void setLangue(short langue) {
			this.langue = langue;
		}

		public LocalDateTime getLastvisit() {
			return lastvisit;
		}

		public void setLastvisit(LocalDateTime lastvisit) {
			this.lastvisit = lastvisit;
		}

		public int getNbrefix() {
			return nbrefix;
		}

		public void setNbrefix(int nbrefix) {
			this.nbrefix = nbrefix;
		}

		public short getCpasyN() {
			return cpasyN;
		}

		public void setCpasyN(short cpasyN) {
			this.cpasyN = cpasyN;
		}

		public short getLienCpas() {
			return lienCpas;
		}

		public void setLienCpas(short lienCpas) {
			this.lienCpas = lienCpas;
		}

		public short getBirbyN() {
			return birbyN;
		}

		public void setBirbyN(short birbyN) {
			this.birbyN = birbyN;
		}

		public short getDepyN() {
			return depyN;
		}

		public void setDepyN(short depyN) {
			this.depyN = depyN;
		}

		public short getLogBirb() {
			return logBirb;
		}

		public void setLogBirb(short logBirb) {
			this.logBirb = logBirb;
		}

		public short getActComp1() {
			return actComp1;
		}

		public void setActComp1(short actComp1) {
			this.actComp1 = actComp1;
		}

		public short getActComp2() {
			return actComp2;
		}

		public void setActComp2(short actComp2) {
			this.actComp2 = actComp2;
		}

		public short getActComp3() {
			return actComp3;
		}

		public void setActComp3(short actComp3) {
			this.actComp3 = actComp3;
		}

		public short getActComp4() {
			return actComp4;
		}

		public void setActComp4(short actComp4) {
			this.actComp4 = actComp4;
		}

		public short getActComp5() {
			return actComp5;
		}

		public void setActComp5(short actComp5) {
			this.actComp5 = actComp5;
		}

		public short getActComp6() {
			return actComp6;
		}

		public void setActComp6(short actComp6) {
			this.actComp6 = actComp6;
		}

		public String getActComp7() {
			return actComp7;
		}

		public void setActComp7(String actComp7) {
			this.actComp7 = actComp7;
		}

		public short getNrTournee() {
			return nrTournee;
		}

		public void setNrTournee(short nrTournee) {
			this.nrTournee = nrTournee;
		}

		public short getSusp() {
			return susp;
		}

		public void setSusp(short susp) {
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

		public short getMsonac() {
			return msonac;
		}

		public void setMsonac(short msonac) {
			this.msonac = msonac;
		}

		public short getClasseFbba1() {
			return classeFbba1;
		}

		public void setClasseFbba1(short classeFbba1) {
			this.classeFbba1 = classeFbba1;
		}

		public short getClasseFbba2() {
			return classeFbba2;
		}

		public void setClasseFbba2(short classeFbba2) {
			this.classeFbba2 = classeFbba2;
		}

		public short getClasseFbba3() {
			return classeFbba3;
		}

		public void setClasseFbba3(short classeFbba3) {
			this.classeFbba3 = classeFbba3;
		}

		public int getnFam() {
			return nFam;
		}

		public void setnFam(int nFam) {
			this.nFam = nFam;
		}

		public int getnPers() {
			return nPers;
		}

		public void setnPers(int nPers) {
			this.nPers = nPers;
		}

		public int getnNour() {
			return nNour;
		}

		public void setnNour(int nNour) {
			this.nNour = nNour;
		}

		public int getnBebe() {
			return nBebe;
		}

		public void setnBebe(int nBebe) {
			this.nBebe = nBebe;
		}

		public int getnEnf() {
			return nEnf;
		}

		public void setnEnf(int nEnf) {
			this.nEnf = nEnf;
		}

		public int getnAdo() {
			return nAdo;
		}

		public void setnAdo(int nAdo) {
			this.nAdo = nAdo;
		}

		public BigDecimal getnEq() {
			return nEq;
		}

		public void setnEq(BigDecimal nEq) {
			this.nEq = nEq;
		}

		public int getnSen() {
			return nSen;
		}

		public void setnSen(int nSen) {
			this.nSen = nSen;
		}

		public short getDepPrinc() {
			return depPrinc;
		}

		public void setDepPrinc(short depPrinc) {
			this.depPrinc = depPrinc;
		}

		public short getGestBen() {
			return gestBen;
		}

		public void setGestBen(short gestBen) {
			this.gestBen = gestBen;
		}

		public short getTourneeJour() {
			return tourneeJour;
		}

		public void setTourneeJour(short tourneeJour) {
			this.tourneeJour = tourneeJour;
		}

		public short getTourneeSem() {
			return tourneeSem;
		}

		public void setTourneeSem(short tourneeSem) {
			this.tourneeSem = tourneeSem;
		}

		public short getColdis() {
			return coldis;
		}

		public void setColdis(short coldis) {
			this.coldis = coldis;
		}

		public short getLienGd() {
			return lienGd;
		}

		public void setLienGd(short lienGd) {
			this.lienGd = lienGd;
		}

		public short getLienGs() {
			return lienGs;
		}

		public void setLienGs(short lienGs) {
			this.lienGs = lienGs;
		}

		public BigDecimal getMontCot() {
			return montCot;
		}

		public void setMontCot(BigDecimal montCot) {
			this.montCot = montCot;
		}

		public int getAntenne() {
			return antenne;
		}

		public void setAntenne(int antenne) {
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

		public int getNrFead() {
			return nrFead;
		}

		public void setNrFead(int nrFead) {
			this.nrFead = nrFead;
		}

		public short getTourneeMois() {
			return tourneeMois;
		}

		public void setTourneeMois(short tourneeMois) {
			this.tourneeMois = tourneeMois;
		}

		public short getDistrListPdt() {
			return distrListPdt;
		}

		public void setDistrListPdt(short distrListPdt) {
			this.distrListPdt = distrListPdt;
		}

		public short getDistrListVp() {
			return distrListVp;
		}

		public void setDistrListVp(short distrListVp) {
			this.distrListVp = distrListVp;
		}

		public short getDistrListSec() {
			return distrListSec;
		}

		public void setDistrListSec(short distrListSec) {
			this.distrListSec = distrListSec;
		}

		public short getDistrListTres() {
			return distrListTres;
		}

		public void setDistrListTres(short distrListTres) {
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

		public short getPays2() {
			return pays2;
		}

		public void setPays2(short pays2) {
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

		public short getFeadN() {
			return feadN;
		}

		public void setFeadN(short feadN) {
			this.feadN = feadN;
		}

		public String getRemLivr() {
			return remLivr;
		}

		public void setRemLivr(String remLivr) {
			this.remLivr = remLivr;
		}

		public short getCotAnnuelle() {
			return cotAnnuelle;
		}

		public void setCotAnnuelle(short cotAnnuelle) {
			this.cotAnnuelle = cotAnnuelle;
		}

		public int getCotMonths() {
			return cotMonths;
		}

		public void setCotMonths(int cotMonths) {
			this.cotMonths = cotMonths;
		}

		public int getCotSup() {
			return cotSup;
		}

		public void setCotSup(int cotSup) {
			this.cotSup = cotSup;
		}

		public int getCotMonthsSup() {
			return cotMonthsSup;
		}

		public void setCotMonthsSup(int cotMonthsSup) {
			this.cotMonthsSup = cotMonthsSup;
		}

		public int getDepotram() {
			return depotram;
		}

		public void setDepotram(int depotram) {
			this.depotram = depotram;
		}

		public String getLupdUserName() {
			return lupdUserName;
		}

		public void setLupdUserName(String lupdUserName) {
			this.lupdUserName = lupdUserName;
		}

		public LocalDateTime getLupdTs() {
			return lupdTs;
		}

		public void setLupdTs(LocalDateTime lupdTs) {
			this.lupdTs = lupdTs;
		}
	    


}
