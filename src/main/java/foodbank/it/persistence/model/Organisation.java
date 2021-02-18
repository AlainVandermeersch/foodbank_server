// Generated with g9.

package foodbank.it.persistence.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="organisations")
public class Organisation implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8424510676206873844L;


	/** Primary key. */
    protected static final String PK = "idDis";

    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_dis", unique=true, nullable=false, precision=10)
    private int idDis;
    @Column(name="ref_int", length=15)
    private String refInt;   
    @Column(name="lien_depot", nullable=false, precision=10)
    private int lienDepot;
    @Column(nullable=false, length=100)
    private String societe;
    @Column(length=100)
    private String adresse;
    @Column(length=2)
    private String statut;
    @Column(length=100)
    private String email;
    @Column(length=6)
    private String cp;
    @Column(length=50)
    private String localite;
    @Column(nullable=false, precision=5)
    private Short pays;
    @Column(length=50)
    private String tva;
    private String website;
    @Column(length=50)
    private String tel;
    @Column(length=50)
    private String gsm;
    @Column(precision=5)
    private Short daten;
    private String banque;
    @Column(precision=3)
    private Short region;
    private String iban;
    private String classique;
    private String bic;
    @Column(nullable=false, precision=3)
    private Short actif;
    @Column(precision=3)
    private Short civilite;
    @Column(length=50)
    private String nom;
    @Column(length=50)
    private String prenom;
    @Column(name="civiliteVP", precision=5)
    private Short civiliteVp;
    @Column(name="prenomVP", length=20)
    private String prenomVp;
    @Column(name="nomVP", length=30)
    private String nomVp;
    @Column(name="telVP", length=15)
    private String telVp;
    @Column(name="gsmVP", length=20)
    private String gsmVp;
    @Column(name="civiliteSEC", precision=5)
    private Short civiliteSec;
    @Column(name="prenomSEC", length=20)
    private String prenomSec;
    @Column(name="nomSEC", length=30)
    private String nomSec;
    @Column(name="telSEC", length=15)
    private String telSec;
    @Column(name="gsmSEC", length=20)
    private String gsmSec;
    @Column(name="civiliteTRES", precision=5)
    private Short civiliteTres;
    @Column(name="prenomTRES", length=20)
    private String prenomTres;
    @Column(name="nomTRES", length=30)
    private String nomTres;
    @Column(name="telTRES", length=15)
    private String telTres;
    @Column(name="gsmTRES", length=20)
    private String gsmTres;
    @Column(name="emailPRES", length=50)
    private String emailPres;
    @Column(name="emailVP", length=50)
    private String emailVp;
    @Column(name="emailSEC", length=50)
    private String emailSec;
    @Column(name="emailTRES", length=50)
    private String emailTres;
    @Column(name="telPRES", length=15)
    private String telPres;
    @Column(name="gsmPRES", length=20)
    private String gsmPres;
    private String disprog;
    @Column(length=20)
    private String afsca;
    @Column(length=3)
    private boolean webauthority;
    @Column(precision=3)
    private Short langue;
    private LocalDateTime lastvisit;
    @Column(precision=10)
    private Integer  nbrefix;
    @Column(name="cpasy_n", precision=3)
    private Short cpasyN;
    @Column(name="lien_cpas", precision=5)
    private Short lienCpas;
    @Column(name="depy_n", precision=3)
    private Short depyN;
    @Column(name="log_birb", precision=3)
    private Short logBirb;
    @Column(name="act_comp_1", precision=3)
    private Short actComp1;
    @Column(name="act_comp_2", precision=3)
    private Short actComp2;
    @Column(name="act_comp_3", precision=3)
    private Short actComp3;
    @Column(name="act_comp_4", precision=3)
    private Short actComp4;
    @Column(name="act_comp_5", precision=3)
    private Short actComp5;
    @Column(name="act_comp_6", precision=3)
    private Short actComp6;
    @Column(name="act_comp_7", length=100)
    private String actComp7;
    @Column(name="nr_tournee", precision=3)
    private Short nrTournee;
    @Column(nullable=false, precision=3)
    private Short susp;
    @Column(name="stop_susp")
    private String stopSusp;
    private String rem;
    @Column(precision=3)
    private Short msonac;
    @Column(name="classe_fbba1", precision=3)
    private Short classeFbba1;
    @Column(name="classe_fbba2", precision=3)
    private Short classeFbba2;
    @Column(name="classe_fbba3", precision=3)
    private Short classeFbba3;
    @Column(name="n_fam", precision=10)
    private Integer  nFam;
    @Column(name="n_pers", precision=10)
    private Integer  nPers;
    @Column(name="n_nour", precision=10)
    private Integer  nNour;
    @Column(name="n_bebe", precision=10)
    private Integer  nBebe;
    @Column(name="n_enf", precision=10)
    private Integer  nEnf;
    @Column(name="n_ado", precision=10)
    private Integer  nAdo;
    @Column(name="n_eq", precision=10)
    private BigDecimal nEq;
    @Column(name="n_sen", precision=10)
    private Integer  nSen;
    @Column(name="dep_princ", precision=3)
    private Short depPrinc;
    @Column(name="gest_ben", precision=3)
    private Short gestBen;
    @Column(name="tournee_jour", precision=3)
    private Short tourneeJour;
    @Column(name="tournee_sem", precision=3)
    private Short tourneeSem;
    @Column(precision=3)
    private Short coldis;
    @Column(name="lien_gd", precision=3)
    private Short lienGd;
    @Column(name="lien_gs", precision=3)
    private Short lienGs;
    @Column(name="mont_cot", precision=10)
    private BigDecimal montCot;
    @Column(precision=10)
    private Integer  antenne;
    @Column(length=15)
    private String afsca1;
    @Column(nullable=false, length=15)
    private String afsca2;
    @Column(length=15)
    private String afsca3;
    @Column(name="nr_fead", precision=10)
    private Integer  nrFead;
    @Column(name="tournee_mois", precision=3)
    private Short tourneeMois;
    @Column(name="distr_list_pdt", precision=3)
    private Short distrListPdt;
    @Column(name="distr_list_vp", precision=3)
    private Short distrListVp;
    @Column(name="distr_list_sec", precision=3)
    private Short distrListSec;
    @Column(name="distr_list_tres", precision=3)
    private Short distrListTres;
    @Column(length=100)
    private String adresse2;
    @Column(length=6)
    private String cp2;
    @Column(length=100)
    private String localite2;
    @Column(precision=3)
    private Short pays2;
    @Column(name="date_reg", length=10)
    private String dateReg;
    @Column(length=15)
    private String fax;
    @Column(name="fead_n", precision=3)
    private Short feadN;
    @Column(name="rem_livr")
    private String remLivr;
    @Column(name="cot_annuelle", precision=3)
    private Short cotAnnuelle;
    @Column(name="cot_months", precision=10)
    private Integer  cotMonths;
    @Column(name="cot_sup", precision=10)
    private Integer cotSup;
    @Column(name="cot_months_sup", precision=10)
    private Integer  cotMonthsSup;
    @Column(precision=10)
    private Integer  depotram;
    @Column(name="LUPD_USER_NAME", length=45)
    private String lupdUserName;
    @Column(name="LUPD_TS")
    private LocalDateTime lupdTs;
    @Column(name="lien_banque", nullable=false, insertable = false, updatable = false, precision=3)
    private Short lienBanque;
    
    public Short getLienBanque() {
		return lienBanque;
	}

	public void setLienBanque(Short lienBanque) {
		this.lienBanque = lienBanque;
	}

	@ManyToOne
    @JoinColumn(name="lien_banque")
    private Banque banqueObject;
    

	/** Default constructor. */
    public Organisation() {
        super();
    }

    public Organisation(int idDis,Short lienBanQue, String refInt, int lienDepot, String societe, String adresse, String statut, String email, String cp, String localite, Short pays, String tva, String website, String tel,
        String gsm, Short daten, String banque, Short region, String iban, String classique, String bic, Short actif, Short civilite, String nom, String prenom, Short civiliteVp, String prenomVp, String nomVp, String telVp, String gsmVp, Short civiliteSec,
        String prenomSec, String nomSec, String telSec, String gsmSec, Short civiliteTres, String prenomTres, String nomTres, String telTres, String gsmTres, String emailPres, String emailVp, String emailSec, String emailTres, String telPres, String gsmPres,
        String disprog, String afsca, boolean webauthority, Short langue, LocalDateTime lastvisit, Integer  nbrefix, Short cpasyN, Short lienCpas, Short depyN, Short logBirb, Short actComp1, Short actComp2, Short actComp3, Short actComp4,
        Short actComp5, Short actComp6, String actComp7, Short nrTournee, Short susp, String stopSusp, String rem, Short msonac, Short classeFbba1, Short classeFbba2, Short classeFbba3, Integer  nFam, Integer  nPers, Integer  nNour, Integer  nBebe, Integer  nEnf, Integer  nAdo,
        BigDecimal nEq, Integer  nSen, Short depPrinc, Short gestBen, Short tourneeJour, Short tourneeSem, Short coldis, Short lienGd, Short lienGs, BigDecimal montCot, Integer  antenne, String afsca1, String afsca2, String afsca3, Integer  nrFead, Short tourneeMois,
        Short distrListPdt, Short distrListVp, Short distrListSec, Short distrListTres, String adresse2, String cp2, String localite2, Short pays2, String dateReg, String fax, Short feadN, String remLivr, Short cotAnnuelle, Integer  cotMonths, Integer cotSup,
        Integer  cotMonthsSup, Integer  depotram, String lupdUserName, LocalDateTime lupdTs,Banque banqueObject) {
        super();
        this.idDis = idDis;
        this.lienBanque = lienBanque;
        this.refInt = refInt;
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
        this.banqueObject = banqueObject;
    }

    /**
     * Access method for idDis.
     *
     * @return the current value of idDis
     */
    public int getIdDis() {
        return idDis;
    }

    /**
     * Setter method for idDis.
     *
     * @param aIdDis the new value for idDis
     */
    public void setIdDis(int aIdDis) {
        idDis = aIdDis;
    }

    /**
     * Access method for refInt.
     *
     * @return the current value of refInt
     */
    public String getRefInt() {
        return refInt;
    }

    /**
     * Setter method for refInt.
     *
     * @param aRefInt the new value for refInt
     */
    public void setRefInt(String aRefInt) {
        refInt = aRefInt;
    }

   /**
     * Access method for lienDepot.
     *
     * @return the current value of lienDepot
     */
    public int getLienDepot() {
        return lienDepot;
    }

    /**
     * Setter method for lienDepot.
     *
     * @param aLienDepot the new value for lienDepot
     */
    public void setLienDepot(int aLienDepot) {
        lienDepot = aLienDepot;
    }

    /**
     * Access method for societe.
     *
     * @return the current value of societe
     */
    public String getSociete() {
        return societe;
    }

    /**
     * Setter method for societe.
     *
     * @param aSociete the new value for societe
     */
    public void setSociete(String aSociete) {
        societe = aSociete;
    }

    /**
     * Access method for adresse.
     *
     * @return the current value of adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Setter method for adresse.
     *
     * @param aAdresse the new value for adresse
     */
    public void setAdresse(String aAdresse) {
        adresse = aAdresse;
    }

    /**
     * Access method for statut.
     *
     * @return the current value of statut
     */
    public String getStatut() {
        return statut;
    }

    /**
     * Setter method for statut.
     *
     * @param aStatut the new value for statut
     */
    public void setStatut(String aStatut) {
        statut = aStatut;
    }

    /**
     * Access method for email.
     *
     * @return the current value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for email.
     *
     * @param aEmail the new value for email
     */
    public void setEmail(String aEmail) {
        email = aEmail;
    }


    /**
     * Access method for cp.
     *
     * @return the current value of cp
     */
    public String getCp() {
        return cp;
    }

    /**
     * Setter method for cp.
     *
     * @param aCp the new value for cp
     */
    public void setCp(String aCp) {
        cp = aCp;
    }

    /**
     * Access method for localite.
     *
     * @return the current value of localite
     */
    public String getLocalite() {
        return localite;
    }

    /**
     * Setter method for localite.
     *
     * @param aLocalite the new value for localite
     */
    public void setLocalite(String aLocalite) {
        localite = aLocalite;
    }

    /**
     * Access method for pays.
     *
     * @return the current value of pays
     */
    public Short getPays() {
        return pays;
    }

    /**
     * Setter method for pays.
     *
     * @param aPays the new value for pays
     */
    public void setPays(Short aPays) {
        pays = aPays;
    }

    /**
     * Access method for tva.
     *
     * @return the current value of tva
     */
    public String getTva() {
        return tva;
    }

    /**
     * Setter method for tva.
     *
     * @param aTva the new value for tva
     */
    public void setTva(String aTva) {
        tva = aTva;
    }

    /**
     * Access method for website.
     *
     * @return the current value of website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Setter method for website.
     *
     * @param aWebsite the new value for website
     */
    public void setWebsite(String aWebsite) {
        website = aWebsite;
    }

    /**
     * Access method for tel.
     *
     * @return the current value of tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * Setter method for tel.
     *
     * @param aTel the new value for tel
     */
    public void setTel(String aTel) {
        tel = aTel;
    }

    /**
     * Access method for gsm.
     *
     * @return the current value of gsm
     */
    public String getGsm() {
        return gsm;
    }

    /**
     * Setter method for gsm.
     *
     * @param aGsm the new value for gsm
     */
    public void setGsm(String aGsm) {
        gsm = aGsm;
    }

    /**
     * Access method for daten.
     *
     * @return the current value of daten
     */
    public Short getDaten() {
        return daten;
    }

    /**
     * Setter method for daten.
     *
     * @param aDaten the new value for daten
     */
    public void setDaten(Short aDaten) {
        daten = aDaten;
    }

    
    public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	/**
     * Access method for region.
     *
     * @return the current value of region
     */
    public Short getRegion() {
        return region;
    }

    /**
     * Setter method for region.
     *
     * @param aRegion the new value for region
     */
    public void setRegion(Short aRegion) {
        region = aRegion;
    }

    /**
     * Access method for iban.
     *
     * @return the current value of iban
     */
    public String getIban() {
        return iban;
    }

    /**
     * Setter method for iban.
     *
     * @param aIban the new value for iban
     */
    public void setIban(String aIban) {
        iban = aIban;
    }

    /**
     * Access method for classique.
     *
     * @return the current value of classique
     */
    public String getClassique() {
        return classique;
    }

    /**
     * Setter method for classique.
     *
     * @param aClassique the new value for classique
     */
    public void setClassique(String aClassique) {
        classique = aClassique;
    }

    /**
     * Access method for bic.
     *
     * @return the current value of bic
     */
    public String getBic() {
        return bic;
    }

    /**
     * Setter method for bic.
     *
     * @param aBic the new value for bic
     */
    public void setBic(String aBic) {
        bic = aBic;
    }

    /**
     * Access method for actif.
     *
     * @return the current value of actif
     */
    public Short getActif() {
        return actif;
    }

    /**
     * Setter method for actif.
     *
     * @param aActif the new value for actif
     */
    public void setActif(Short aActif) {
        actif = aActif;
    }

    /**
     * Access method for civilite.
     *
     * @return the current value of civilite
     */
    public Short getCivilite() {
        return civilite;
    }

    /**
     * Setter method for civilite.
     *
     * @param aCivilite the new value for civilite
     */
    public void setCivilite(Short aCivilite) {
        civilite = aCivilite;
    }

    /**
     * Access method for nom.
     *
     * @return the current value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter method for nom.
     *
     * @param aNom the new value for nom
     */
    public void setNom(String aNom) {
        nom = aNom;
    }

    /**
     * Access method for prenom.
     *
     * @return the current value of prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter method for prenom.
     *
     * @param aPrenom the new value for prenom
     */
    public void setPrenom(String aPrenom) {
        prenom = aPrenom;
    }

    /**
     * Access method for civiliteVp.
     *
     * @return the current value of civiliteVp
     */
    public Short getCiviliteVp() {
        return civiliteVp;
    }

    /**
     * Setter method for civiliteVp.
     *
     * @param aCiviliteVp the new value for civiliteVp
     */
    public void setCiviliteVp(Short aCiviliteVp) {
        civiliteVp = aCiviliteVp;
    }

    /**
     * Access method for prenomVp.
     *
     * @return the current value of prenomVp
     */
    public String getPrenomVp() {
        return prenomVp;
    }

    /**
     * Setter method for prenomVp.
     *
     * @param aPrenomVp the new value for prenomVp
     */
    public void setPrenomVp(String aPrenomVp) {
        prenomVp = aPrenomVp;
    }

    /**
     * Access method for nomVp.
     *
     * @return the current value of nomVp
     */
    public String getNomVp() {
        return nomVp;
    }

    /**
     * Setter method for nomVp.
     *
     * @param aNomVp the new value for nomVp
     */
    public void setNomVp(String aNomVp) {
        nomVp = aNomVp;
    }

    /**
     * Access method for telVp.
     *
     * @return the current value of telVp
     */
    public String getTelVp() {
        return telVp;
    }

    /**
     * Setter method for telVp.
     *
     * @param aTelVp the new value for telVp
     */
    public void setTelVp(String aTelVp) {
        telVp = aTelVp;
    }

    /**
     * Access method for gsmVp.
     *
     * @return the current value of gsmVp
     */
    public String getGsmVp() {
        return gsmVp;
    }

    /**
     * Setter method for gsmVp.
     *
     * @param aGsmVp the new value for gsmVp
     */
    public void setGsmVp(String aGsmVp) {
        gsmVp = aGsmVp;
    }

    /**
     * Access method for civiliteSec.
     *
     * @return the current value of civiliteSec
     */
    public Short getCiviliteSec() {
        return civiliteSec;
    }

    /**
     * Setter method for civiliteSec.
     *
     * @param aCiviliteSec the new value for civiliteSec
     */
    public void setCiviliteSec(Short aCiviliteSec) {
        civiliteSec = aCiviliteSec;
    }

    /**
     * Access method for prenomSec.
     *
     * @return the current value of prenomSec
     */
    public String getPrenomSec() {
        return prenomSec;
    }

    /**
     * Setter method for prenomSec.
     *
     * @param aPrenomSec the new value for prenomSec
     */
    public void setPrenomSec(String aPrenomSec) {
        prenomSec = aPrenomSec;
    }

    /**
     * Access method for nomSec.
     *
     * @return the current value of nomSec
     */
    public String getNomSec() {
        return nomSec;
    }

    /**
     * Setter method for nomSec.
     *
     * @param aNomSec the new value for nomSec
     */
    public void setNomSec(String aNomSec) {
        nomSec = aNomSec;
    }

    /**
     * Access method for telSec.
     *
     * @return the current value of telSec
     */
    public String getTelSec() {
        return telSec;
    }

    /**
     * Setter method for telSec.
     *
     * @param aTelSec the new value for telSec
     */
    public void setTelSec(String aTelSec) {
        telSec = aTelSec;
    }

    /**
     * Access method for gsmSec.
     *
     * @return the current value of gsmSec
     */
    public String getGsmSec() {
        return gsmSec;
    }

    /**
     * Setter method for gsmSec.
     *
     * @param aGsmSec the new value for gsmSec
     */
    public void setGsmSec(String aGsmSec) {
        gsmSec = aGsmSec;
    }

    /**
     * Access method for civiliteTres.
     *
     * @return the current value of civiliteTres
     */
    public Short getCiviliteTres() {
        return civiliteTres;
    }

    /**
     * Setter method for civiliteTres.
     *
     * @param aCiviliteTres the new value for civiliteTres
     */
    public void setCiviliteTres(Short aCiviliteTres) {
        civiliteTres = aCiviliteTres;
    }

    /**
     * Access method for prenomTres.
     *
     * @return the current value of prenomTres
     */
    public String getPrenomTres() {
        return prenomTres;
    }

    /**
     * Setter method for prenomTres.
     *
     * @param aPrenomTres the new value for prenomTres
     */
    public void setPrenomTres(String aPrenomTres) {
        prenomTres = aPrenomTres;
    }

    /**
     * Access method for nomTres.
     *
     * @return the current value of nomTres
     */
    public String getNomTres() {
        return nomTres;
    }

    /**
     * Setter method for nomTres.
     *
     * @param aNomTres the new value for nomTres
     */
    public void setNomTres(String aNomTres) {
        nomTres = aNomTres;
    }

    /**
     * Access method for telTres.
     *
     * @return the current value of telTres
     */
    public String getTelTres() {
        return telTres;
    }

    /**
     * Setter method for telTres.
     *
     * @param aTelTres the new value for telTres
     */
    public void setTelTres(String aTelTres) {
        telTres = aTelTres;
    }

    /**
     * Access method for gsmTres.
     *
     * @return the current value of gsmTres
     */
    public String getGsmTres() {
        return gsmTres;
    }

    /**
     * Setter method for gsmTres.
     *
     * @param aGsmTres the new value for gsmTres
     */
    public void setGsmTres(String aGsmTres) {
        gsmTres = aGsmTres;
    }

    /**
     * Access method for emailPres.
     *
     * @return the current value of emailPres
     */
    public String getEmailPres() {
        return emailPres;
    }

    /**
     * Setter method for emailPres.
     *
     * @param aEmailPres the new value for emailPres
     */
    public void setEmailPres(String aEmailPres) {
        emailPres = aEmailPres;
    }

    /**
     * Access method for emailVp.
     *
     * @return the current value of emailVp
     */
    public String getEmailVp() {
        return emailVp;
    }

    /**
     * Setter method for emailVp.
     *
     * @param aEmailVp the new value for emailVp
     */
    public void setEmailVp(String aEmailVp) {
        emailVp = aEmailVp;
    }

    /**
     * Access method for emailSec.
     *
     * @return the current value of emailSec
     */
    public String getEmailSec() {
        return emailSec;
    }

    /**
     * Setter method for emailSec.
     *
     * @param aEmailSec the new value for emailSec
     */
    public void setEmailSec(String aEmailSec) {
        emailSec = aEmailSec;
    }

    /**
     * Access method for emailTres.
     *
     * @return the current value of emailTres
     */
    public String getEmailTres() {
        return emailTres;
    }

    /**
     * Setter method for emailTres.
     *
     * @param aEmailTres the new value for emailTres
     */
    public void setEmailTres(String aEmailTres) {
        emailTres = aEmailTres;
    }

    /**
     * Access method for telPres.
     *
     * @return the current value of telPres
     */
    public String getTelPres() {
        return telPres;
    }

    /**
     * Setter method for telPres.
     *
     * @param aTelPres the new value for telPres
     */
    public void setTelPres(String aTelPres) {
        telPres = aTelPres;
    }

    /**
     * Access method for gsmPres.
     *
     * @return the current value of gsmPres
     */
    public String getGsmPres() {
        return gsmPres;
    }

    /**
     * Setter method for gsmPres.
     *
     * @param aGsmPres the new value for gsmPres
     */
    public void setGsmPres(String aGsmPres) {
        gsmPres = aGsmPres;
    }

    /**
     * Access method for disprog.
     *
     * @return the current value of disprog
     */
    public String getDisprog() {
        return disprog;
    }

    /**
     * Setter method for disprog.
     *
     * @param aDisprog the new value for disprog
     */
    public void setDisprog(String aDisprog) {
        disprog = aDisprog;
    }

    /**
     * Access method for afsca.
     *
     * @return the current value of afsca
     */
    public String getAfsca() {
        return afsca;
    }

    /**
     * Setter method for afsca.
     *
     * @param aAfsca the new value for afsca
     */
    public void setAfsca(String aAfsca) {
        afsca = aAfsca;
    }

    /**
     * Access method for webauthority.
     *
     * @return true if and only if webauthority is currently true
     */
    public boolean getWebauthority() {
        return webauthority;
    }

    /**
     * Setter method for webauthority.
     *
     * @param aWebauthority the new value for webauthority
     */
    public void setWebauthority(boolean aWebauthority) {
        webauthority = aWebauthority;
    }

    /**
     * Access method for langue.
     *
     * @return the current value of langue
     */
    public Short getLangue() {
        return langue;
    }

    /**
     * Setter method for langue.
     *
     * @param aLangue the new value for langue
     */
    public void setLangue(Short aLangue) {
        langue = aLangue;
    }

    /**
     * Access method for lastvisit.
     *
     * @return the current value of lastvisit
     */
    public LocalDateTime getLastvisit() {
        return lastvisit;
    }

    /**
     * Setter method for lastvisit.
     *
     * @param aLastvisit the new value for lastvisit
     */
    public void setLastvisit(LocalDateTime aLastvisit) {
        lastvisit = aLastvisit;
    }

    /**
     * Access method for nbrefix.
     *
     * @return the current value of nbrefix
     */
    public Integer  getNbrefix() {
        return nbrefix;
    }

    /**
     * Setter method for nbrefix.
     *
     * @param aNbrefix the new value for nbrefix
     */
    public void setNbrefix(Integer  aNbrefix) {
        nbrefix = aNbrefix;
    }

    /**
     * Access method for cpasyN.
     *
     * @return the current value of cpasyN
     */
    public Short getCpasyN() {
        return cpasyN;
    }

    /**
     * Setter method for cpasyN.
     *
     * @param aCpasyN the new value for cpasyN
     */
    public void setCpasyN(Short aCpasyN) {
        cpasyN = aCpasyN;
    }

    /**
     * Access method for lienCpas.
     *
     * @return the current value of lienCpas
     */
    public Short getLienCpas() {
        return lienCpas;
    }

    /**
     * Setter method for lienCpas.
     *
     * @param aLienCpas the new value for lienCpas
     */
    public void setLienCpas(Short aLienCpas) {
        lienCpas = aLienCpas;
    }

   
    /**
     * Access method for depyN.
     *
     * @return the current value of depyN
     */
    public Short getDepyN() {
        return depyN;
    }

    /**
     * Setter method for depyN.
     *
     * @param aDepyN the new value for depyN
     */
    public void setDepyN(Short aDepyN) {
        depyN = aDepyN;
    }

    /**
     * Access method for logBirb.
     *
     * @return the current value of logBirb
     */
    public Short getLogBirb() {
        return logBirb;
    }

    /**
     * Setter method for logBirb.
     *
     * @param aLogBirb the new value for logBirb
     */
    public void setLogBirb(Short aLogBirb) {
        logBirb = aLogBirb;
    }

    /**
     * Access method for actComp1.
     *
     * @return the current value of actComp1
     */
    public Short getActComp1() {
        return actComp1;
    }

    /**
     * Setter method for actComp1.
     *
     * @param aActComp1 the new value for actComp1
     */
    public void setActComp1(Short aActComp1) {
        actComp1 = aActComp1;
    }

    /**
     * Access method for actComp2.
     *
     * @return the current value of actComp2
     */
    public Short getActComp2() {
        return actComp2;
    }

    /**
     * Setter method for actComp2.
     *
     * @param aActComp2 the new value for actComp2
     */
    public void setActComp2(Short aActComp2) {
        actComp2 = aActComp2;
    }

    /**
     * Access method for actComp3.
     *
     * @return the current value of actComp3
     */
    public Short getActComp3() {
        return actComp3;
    }

    /**
     * Setter method for actComp3.
     *
     * @param aActComp3 the new value for actComp3
     */
    public void setActComp3(Short aActComp3) {
        actComp3 = aActComp3;
    }

    /**
     * Access method for actComp4.
     *
     * @return the current value of actComp4
     */
    public Short getActComp4() {
        return actComp4;
    }

    /**
     * Setter method for actComp4.
     *
     * @param aActComp4 the new value for actComp4
     */
    public void setActComp4(Short aActComp4) {
        actComp4 = aActComp4;
    }

    /**
     * Access method for actComp5.
     *
     * @return the current value of actComp5
     */
    public Short getActComp5() {
        return actComp5;
    }

    /**
     * Setter method for actComp5.
     *
     * @param aActComp5 the new value for actComp5
     */
    public void setActComp5(Short aActComp5) {
        actComp5 = aActComp5;
    }

    /**
     * Access method for actComp6.
     *
     * @return the current value of actComp6
     */
    public Short getActComp6() {
        return actComp6;
    }

    /**
     * Setter method for actComp6.
     *
     * @param aActComp6 the new value for actComp6
     */
    public void setActComp6(Short aActComp6) {
        actComp6 = aActComp6;
    }

    /**
     * Access method for actComp7.
     *
     * @return the current value of actComp7
     */
    public String getActComp7() {
        return actComp7;
    }

    /**
     * Setter method for actComp7.
     *
     * @param aActComp7 the new value for actComp7
     */
    public void setActComp7(String aActComp7) {
        actComp7 = aActComp7;
    }

    /**
     * Access method for nrTournee.
     *
     * @return the current value of nrTournee
     */
    public Short getNrTournee() {
        return nrTournee;
    }

    /**
     * Setter method for nrTournee.
     *
     * @param aNrTournee the new value for nrTournee
     */
    public void setNrTournee(Short aNrTournee) {
        nrTournee = aNrTournee;
    }

    /**
     * Access method for susp.
     *
     * @return the current value of susp
     */
    public Short getSusp() {
        return susp;
    }

    /**
     * Setter method for susp.
     *
     * @param aSusp the new value for susp
     */
    public void setSusp(Short aSusp) {
        susp = aSusp;
    }

    /**
     * Access method for stopSusp.
     *
     * @return the current value of stopSusp
     */
    public String getStopSusp() {
        return stopSusp;
    }

    /**
     * Setter method for stopSusp.
     *
     * @param aStopSusp the new value for stopSusp
     */
    public void setStopSusp(String aStopSusp) {
        stopSusp = aStopSusp;
    }

    /**
     * Access method for rem.
     *
     * @return the current value of rem
     */
    public String getRem() {
        return rem;
    }

    /**
     * Setter method for rem.
     *
     * @param aRem the new value for rem
     */
    public void setRem(String aRem) {
        rem = aRem;
    }

    /**
     * Access method for msonac.
     *
     * @return the current value of msonac
     */
    public Short getMsonac() {
        return msonac;
    }

    /**
     * Setter method for msonac.
     *
     * @param aMsonac the new value for msonac
     */
    public void setMsonac(Short aMsonac) {
        msonac = aMsonac;
    }

    /**
     * Access method for classeFbba1.
     *
     * @return the current value of classeFbba1
     */
    public Short getClasseFbba1() {
        return classeFbba1;
    }

    /**
     * Setter method for classeFbba1.
     *
     * @param aClasseFbba1 the new value for classeFbba1
     */
    public void setClasseFbba1(Short aClasseFbba1) {
        classeFbba1 = aClasseFbba1;
    }

    /**
     * Access method for classeFbba2.
     *
     * @return the current value of classeFbba2
     */
    public Short getClasseFbba2() {
        return classeFbba2;
    }

    /**
     * Setter method for classeFbba2.
     *
     * @param aClasseFbba2 the new value for classeFbba2
     */
    public void setClasseFbba2(Short aClasseFbba2) {
        classeFbba2 = aClasseFbba2;
    }

    /**
     * Access method for classeFbba3.
     *
     * @return the current value of classeFbba3
     */
    public Short getClasseFbba3() {
        return classeFbba3;
    }

    /**
     * Setter method for classeFbba3.
     *
     * @param aClasseFbba3 the new value for classeFbba3
     */
    public void setClasseFbba3(Short aClasseFbba3) {
        classeFbba3 = aClasseFbba3;
    }

    /**
     * Access method for nFam.
     *
     * @return the current value of nFam
     */
    public Integer  getNFam() {
        return nFam;
    }

    /**
     * Setter method for nFam.
     *
     * @param aNFam the new value for nFam
     */
    public void setNFam(Integer  aNFam) {
        nFam = aNFam;
    }

    /**
     * Access method for nPers.
     *
     * @return the current value of nPers
     */
    public Integer  getNPers() {
        return nPers;
    }

    /**
     * Setter method for nPers.
     *
     * @param aNPers the new value for nPers
     */
    public void setNPers(Integer  aNPers) {
        nPers = aNPers;
    }

    /**
     * Access method for nNour.
     *
     * @return the current value of nNour
     */
    public Integer  getNNour() {
        return nNour;
    }

    /**
     * Setter method for nNour.
     *
     * @param aNNour the new value for nNour
     */
    public void setNNour(Integer  aNNour) {
        nNour = aNNour;
    }

    /**
     * Access method for nBebe.
     *
     * @return the current value of nBebe
     */
    public Integer  getNBebe() {
        return nBebe;
    }

    /**
     * Setter method for nBebe.
     *
     * @param aNBebe the new value for nBebe
     */
    public void setNBebe(Integer  aNBebe) {
        nBebe = aNBebe;
    }

    /**
     * Access method for nEnf.
     *
     * @return the current value of nEnf
     */
    public Integer  getNEnf() {
        return nEnf;
    }

    /**
     * Setter method for nEnf.
     *
     * @param aNEnf the new value for nEnf
     */
    public void setNEnf(Integer  aNEnf) {
        nEnf = aNEnf;
    }

    /**
     * Access method for nAdo.
     *
     * @return the current value of nAdo
     */
    public Integer  getNAdo() {
        return nAdo;
    }

    /**
     * Setter method for nAdo.
     *
     * @param aNAdo the new value for nAdo
     */
    public void setNAdo(Integer  aNAdo) {
        nAdo = aNAdo;
    }

    /**
     * Access method for nEq.
     *
     * @return the current value of nEq
     */
    public BigDecimal getNEq() {
        return nEq;
    }

    /**
     * Setter method for nEq.
     *
     * @param aNEq the new value for nEq
     */
    public void setNEq(BigDecimal aNEq) {
        nEq = aNEq;
    }

    /**
     * Access method for nSen.
     *
     * @return the current value of nSen
     */
    public Integer  getNSen() {
        return nSen;
    }

    /**
     * Setter method for nSen.
     *
     * @param aNSen the new value for nSen
     */
    public void setNSen(Integer  aNSen) {
        nSen = aNSen;
    }

    /**
     * Access method for depPrinc.
     *
     * @return the current value of depPrinc
     */
    public Short getDepPrinc() {
        return depPrinc;
    }

    /**
     * Setter method for depPrinc.
     *
     * @param aDepPrinc the new value for depPrinc
     */
    public void setDepPrinc(Short aDepPrinc) {
        depPrinc = aDepPrinc;
    }

    /**
     * Access method for gestBen.
     *
     * @return the current value of gestBen
     */
    public Short getGestBen() {
        return gestBen;
    }

    /**
     * Setter method for gestBen.
     *
     * @param aGestBen the new value for gestBen
     */
    public void setGestBen(Short aGestBen) {
        gestBen = aGestBen;
    }

    /**
     * Access method for tourneeJour.
     *
     * @return the current value of tourneeJour
     */
    public Short getTourneeJour() {
        return tourneeJour;
    }

    /**
     * Setter method for tourneeJour.
     *
     * @param aTourneeJour the new value for tourneeJour
     */
    public void setTourneeJour(Short aTourneeJour) {
        tourneeJour = aTourneeJour;
    }

    /**
     * Access method for tourneeSem.
     *
     * @return the current value of tourneeSem
     */
    public Short getTourneeSem() {
        return tourneeSem;
    }

    /**
     * Setter method for tourneeSem.
     *
     * @param aTourneeSem the new value for tourneeSem
     */
    public void setTourneeSem(Short aTourneeSem) {
        tourneeSem = aTourneeSem;
    }

    /**
     * Access method for coldis.
     *
     * @return the current value of coldis
     */
    public Short getColdis() {
        return coldis;
    }

    /**
     * Setter method for coldis.
     *
     * @param aColdis the new value for coldis
     */
    public void setColdis(Short aColdis) {
        coldis = aColdis;
    }

    /**
     * Access method for lienGd.
     *
     * @return the current value of lienGd
     */
    public Short getLienGd() {
        return lienGd;
    }

    /**
     * Setter method for lienGd.
     *
     * @param aLienGd the new value for lienGd
     */
    public void setLienGd(Short aLienGd) {
        lienGd = aLienGd;
    }

    /**
     * Access method for lienGs.
     *
     * @return the current value of lienGs
     */
    public Short getLienGs() {
        return lienGs;
    }

    /**
     * Setter method for lienGs.
     *
     * @param aLienGs the new value for lienGs
     */
    public void setLienGs(Short aLienGs) {
        lienGs = aLienGs;
    }

    /**
     * Access method for montCot.
     *
     * @return the current value of montCot
     */
    public BigDecimal getMontCot() {
        return montCot;
    }

    /**
     * Setter method for montCot.
     *
     * @param aMontCot the new value for montCot
     */
    public void setMontCot(BigDecimal aMontCot) {
        montCot = aMontCot;
    }

    /**
     * Access method for antenne.
     *
     * @return the current value of antenne
     */
    public Integer  getAntenne() {
        return antenne;
    }

    /**
     * Setter method for antenne.
     *
     * @param aAntenne the new value for antenne
     */
    public void setAntenne(Integer  aAntenne) {
        antenne = aAntenne;
    }

    /**
     * Access method for afsca1.
     *
     * @return the current value of afsca1
     */
    public String getAfsca1() {
        return afsca1;
    }

    /**
     * Setter method for afsca1.
     *
     * @param aAfsca1 the new value for afsca1
     */
    public void setAfsca1(String aAfsca1) {
        afsca1 = aAfsca1;
    }

    /**
     * Access method for afsca2.
     *
     * @return the current value of afsca2
     */
    public String getAfsca2() {
        return afsca2;
    }

    /**
     * Setter method for afsca2.
     *
     * @param aAfsca2 the new value for afsca2
     */
    public void setAfsca2(String aAfsca2) {
        afsca2 = aAfsca2;
    }

    /**
     * Access method for afsca3.
     *
     * @return the current value of afsca3
     */
    public String getAfsca3() {
        return afsca3;
    }

    /**
     * Setter method for afsca3.
     *
     * @param aAfsca3 the new value for afsca3
     */
    public void setAfsca3(String aAfsca3) {
        afsca3 = aAfsca3;
    }

    /**
     * Access method for nrFead.
     *
     * @return the current value of nrFead
     */
    public Integer  getNrFead() {
        return nrFead;
    }

    /**
     * Setter method for nrFead.
     *
     * @param aNrFead the new value for nrFead
     */
    public void setNrFead(Integer  aNrFead) {
        nrFead = aNrFead;
    }

    /**
     * Access method for tourneeMois.
     *
     * @return the current value of tourneeMois
     */
    public Short getTourneeMois() {
        return tourneeMois;
    }

    /**
     * Setter method for tourneeMois.
     *
     * @param aTourneeMois the new value for tourneeMois
     */
    public void setTourneeMois(Short aTourneeMois) {
        tourneeMois = aTourneeMois;
    }

    /**
     * Access method for distrListPdt.
     *
     * @return the current value of distrListPdt
     */
    public Short getDistrListPdt() {
        return distrListPdt;
    }

    /**
     * Setter method for distrListPdt.
     *
     * @param aDistrListPdt the new value for distrListPdt
     */
    public void setDistrListPdt(Short aDistrListPdt) {
        distrListPdt = aDistrListPdt;
    }

    /**
     * Access method for distrListVp.
     *
     * @return the current value of distrListVp
     */
    public Short getDistrListVp() {
        return distrListVp;
    }

    /**
     * Setter method for distrListVp.
     *
     * @param aDistrListVp the new value for distrListVp
     */
    public void setDistrListVp(Short aDistrListVp) {
        distrListVp = aDistrListVp;
    }

    /**
     * Access method for distrListSec.
     *
     * @return the current value of distrListSec
     */
    public Short getDistrListSec() {
        return distrListSec;
    }

    /**
     * Setter method for distrListSec.
     *
     * @param aDistrListSec the new value for distrListSec
     */
    public void setDistrListSec(Short aDistrListSec) {
        distrListSec = aDistrListSec;
    }

    /**
     * Access method for distrListTres.
     *
     * @return the current value of distrListTres
     */
    public Short getDistrListTres() {
        return distrListTres;
    }

    /**
     * Setter method for distrListTres.
     *
     * @param aDistrListTres the new value for distrListTres
     */
    public void setDistrListTres(Short aDistrListTres) {
        distrListTres = aDistrListTres;
    }

    /**
     * Access method for adresse2.
     *
     * @return the current value of adresse2
     */
    public String getAdresse2() {
        return adresse2;
    }

    /**
     * Setter method for adresse2.
     *
     * @param aAdresse2 the new value for adresse2
     */
    public void setAdresse2(String aAdresse2) {
        adresse2 = aAdresse2;
    }

    /**
     * Access method for cp2.
     *
     * @return the current value of cp2
     */
    public String getCp2() {
        return cp2;
    }

    /**
     * Setter method for cp2.
     *
     * @param aCp2 the new value for cp2
     */
    public void setCp2(String aCp2) {
        cp2 = aCp2;
    }

    /**
     * Access method for localite2.
     *
     * @return the current value of localite2
     */
    public String getLocalite2() {
        return localite2;
    }

    /**
     * Setter method for localite2.
     *
     * @param aLocalite2 the new value for localite2
     */
    public void setLocalite2(String aLocalite2) {
        localite2 = aLocalite2;
    }

    /**
     * Access method for pays2.
     *
     * @return the current value of pays2
     */
    public Short getPays2() {
        return pays2;
    }

    /**
     * Setter method for pays2.
     *
     * @param aPays2 the new value for pays2
     */
    public void setPays2(Short aPays2) {
        pays2 = aPays2;
    }

    /**
     * Access method for dateReg.
     *
     * @return the current value of dateReg
     */
    public String getDateReg() {
        return dateReg;
    }

    /**
     * Setter method for dateReg.
     *
     * @param aDateReg the new value for dateReg
     */
    public void setDateReg(String aDateReg) {
        dateReg = aDateReg;
    }

    /**
     * Access method for fax.
     *
     * @return the current value of fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * Setter method for fax.
     *
     * @param aFax the new value for fax
     */
    public void setFax(String aFax) {
        fax = aFax;
    }

    /**
     * Access method for feadN.
     *
     * @return the current value of feadN
     */
    public Short getFeadN() {
        return feadN;
    }

    /**
     * Setter method for feadN.
     *
     * @param aFeadN the new value for feadN
     */
    public void setFeadN(Short aFeadN) {
        feadN = aFeadN;
    }

    /**
     * Access method for remLivr.
     *
     * @return the current value of remLivr
     */
    public String getRemLivr() {
        return remLivr;
    }

    /**
     * Setter method for remLivr.
     *
     * @param aRemLivr the new value for remLivr
     */
    public void setRemLivr(String aRemLivr) {
        remLivr = aRemLivr;
    }

    /**
     * Access method for cotAnnuelle.
     *
     * @return the current value of cotAnnuelle
     */
    public Short getCotAnnuelle() {
        return cotAnnuelle;
    }

    /**
     * Setter method for cotAnnuelle.
     *
     * @param aCotAnnuelle the new value for cotAnnuelle
     */
    public void setCotAnnuelle(Short aCotAnnuelle) {
        cotAnnuelle = aCotAnnuelle;
    }

    /**
     * Access method for cotMonths.
     *
     * @return the current value of cotMonths
     */
    public Integer  getCotMonths() {
        return cotMonths;
    }

    /**
     * Setter method for cotMonths.
     *
     * @param aCotMonths the new value for cotMonths
     */
    public void setCotMonths(Integer  aCotMonths) {
        cotMonths = aCotMonths;
    }

    /**
     * Access method for cotSup.
     *
     * @return the current value of cotSup
     */
    public Integer getCotSup() {
        return cotSup;
    }

    /**
     * Setter method for cotSup.
     *
     * @param aCotSup the new value for cotSup
     */
    public void setCotSup(Integer aCotSup) {
        cotSup = aCotSup;
    }

    /**
     * Access method for cotMonthsSup.
     *
     * @return the current value of cotMonthsSup
     */
    public Integer  getCotMonthsSup() {
        return cotMonthsSup;
    }

    /**
     * Setter method for cotMonthsSup.
     *
     * @param aCotMonthsSup the new value for cotMonthsSup
     */
    public void setCotMonthsSup(Integer  aCotMonthsSup) {
        cotMonthsSup = aCotMonthsSup;
    }

    /**
     * Access method for depotram.
     *
     * @return the current value of depotram
     */
    public Integer  getDepotram() {
        return depotram;
    }

    /**
     * Setter method for depotram.
     *
     * @param aDepotram the new value for depotram
     */
    public void setDepotram(Integer  aDepotram) {
        depotram = aDepotram;
    }

    /**
     * Access method for lupdUserName.
     *
     * @return the current value of lupdUserName
     */
    public String getLupdUserName() {
        return lupdUserName;
    }

    /**
     * Setter method for lupdUserName.
     *
     * @param aLupdUserName the new value for lupdUserName
     */
    public void setLupdUserName(String aLupdUserName) {
        lupdUserName = aLupdUserName;
    }

    /**
     * Access method for lupdTs.
     *
     * @return the current value of lupdTs
     */
    public LocalDateTime getLupdTs() {
        return lupdTs;
    }

    /**
     * Setter method for lupdTs.
     *
     * @param aLupdTs the new value for lupdTs
     */
    public void setLupdTs(LocalDateTime aLupdTs) {
        lupdTs = aLupdTs;
    }
    public Banque getBanqueObject() {
		return banqueObject;
	}

	public void setBanqueObject(Banque banqueObject) {
		this.banqueObject = banqueObject;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + actComp1;
		result = prime * result + actComp2;
		result = prime * result + actComp3;
		result = prime * result + actComp4;
		result = prime * result + actComp5;
		result = prime * result + actComp6;
		result = prime * result + ((actComp7 == null) ? 0 : actComp7.hashCode());
		result = prime * result + actif;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((adresse2 == null) ? 0 : adresse2.hashCode());
		result = prime * result + ((afsca == null) ? 0 : afsca.hashCode());
		result = prime * result + ((afsca1 == null) ? 0 : afsca1.hashCode());
		result = prime * result + ((afsca2 == null) ? 0 : afsca2.hashCode());
		result = prime * result + ((afsca3 == null) ? 0 : afsca3.hashCode());
		result = prime * result + antenne;
		result = prime * result + ((bic == null) ? 0 : bic.hashCode());
		result = prime * result + civilite;
		result = prime * result + civiliteSec;
		result = prime * result + civiliteTres;
		result = prime * result + civiliteVp;
		result = prime * result + classeFbba1;
		result = prime * result + classeFbba2;
		result = prime * result + classeFbba3;
		result = prime * result + ((classique == null) ? 0 : classique.hashCode());
		result = prime * result + coldis;
		result = prime * result + cotAnnuelle;
		result = prime * result + cotMonths;
		result = prime * result + cotMonthsSup;
		result = prime * result + cotSup;
		result = prime * result + ((cp == null) ? 0 : cp.hashCode());
		result = prime * result + ((cp2 == null) ? 0 : cp2.hashCode());
		result = prime * result + cpasyN;
		result = prime * result + ((dateReg == null) ? 0 : dateReg.hashCode());
		result = prime * result + daten;
		result = prime * result + depPrinc;
		result = prime * result + depotram;
		result = prime * result + depyN;
		result = prime * result + ((disprog == null) ? 0 : disprog.hashCode());
		result = prime * result + distrListPdt;
		result = prime * result + distrListSec;
		result = prime * result + distrListTres;
		result = prime * result + distrListVp;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((emailPres == null) ? 0 : emailPres.hashCode());
		result = prime * result + ((emailSec == null) ? 0 : emailSec.hashCode());
		result = prime * result + ((emailTres == null) ? 0 : emailTres.hashCode());
		result = prime * result + ((emailVp == null) ? 0 : emailVp.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + feadN;
		result = prime * result + gestBen;
		result = prime * result + ((gsm == null) ? 0 : gsm.hashCode());
		result = prime * result + ((gsmPres == null) ? 0 : gsmPres.hashCode());
		result = prime * result + ((gsmSec == null) ? 0 : gsmSec.hashCode());
		result = prime * result + ((gsmTres == null) ? 0 : gsmTres.hashCode());
		result = prime * result + ((gsmVp == null) ? 0 : gsmVp.hashCode());
		result = prime * result + ((iban == null) ? 0 : iban.hashCode());
		result = prime * result + idDis;
		result = prime * result + langue;
		result = prime * result + ((lastvisit == null) ? 0 : lastvisit.hashCode());
		result = prime * result + lienCpas;
		result = prime * result + lienDepot;
		result = prime * result + lienGd;
		result = prime * result + lienGs;
		result = prime * result + ((localite == null) ? 0 : localite.hashCode());
		result = prime * result + ((localite2 == null) ? 0 : localite2.hashCode());
		result = prime * result + logBirb;
		result = prime * result + ((lupdTs == null) ? 0 : lupdTs.hashCode());
		result = prime * result + ((lupdUserName == null) ? 0 : lupdUserName.hashCode());
		result = prime * result + ((montCot == null) ? 0 : montCot.hashCode());
		result = prime * result + msonac;
		result = prime * result + nAdo;
		result = prime * result + nBebe;
		result = prime * result + nEnf;
		result = prime * result + ((nEq == null) ? 0 : nEq.hashCode());
		result = prime * result + nFam;
		result = prime * result + nNour;
		result = prime * result + nPers;
		result = prime * result + nSen;
		result = prime * result + nbrefix;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((nomSec == null) ? 0 : nomSec.hashCode());
		result = prime * result + ((nomTres == null) ? 0 : nomTres.hashCode());
		result = prime * result + ((nomVp == null) ? 0 : nomVp.hashCode());
		result = prime * result + nrFead;
		result = prime * result + nrTournee;
		result = prime * result + pays;
		result = prime * result + pays2;
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((prenomSec == null) ? 0 : prenomSec.hashCode());
		result = prime * result + ((prenomTres == null) ? 0 : prenomTres.hashCode());
		result = prime * result + ((prenomVp == null) ? 0 : prenomVp.hashCode());
		result = prime * result + ((refInt == null) ? 0 : refInt.hashCode());
		result = prime * result + region;
		result = prime * result + ((rem == null) ? 0 : rem.hashCode());
		result = prime * result + ((remLivr == null) ? 0 : remLivr.hashCode());
		result = prime * result + ((societe == null) ? 0 : societe.hashCode());
		result = prime * result + ((statut == null) ? 0 : statut.hashCode());
		result = prime * result + ((stopSusp == null) ? 0 : stopSusp.hashCode());
		result = prime * result + susp;
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		result = prime * result + ((telPres == null) ? 0 : telPres.hashCode());
		result = prime * result + ((telSec == null) ? 0 : telSec.hashCode());
		result = prime * result + ((telTres == null) ? 0 : telTres.hashCode());
		result = prime * result + ((telVp == null) ? 0 : telVp.hashCode());
		result = prime * result + tourneeJour;
		result = prime * result + tourneeMois;
		result = prime * result + tourneeSem;
		result = prime * result + ((tva == null) ? 0 : tva.hashCode());
		result = prime * result + (webauthority ? 1231 : 1237);
		result = prime * result + ((website == null) ? 0 : website.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Organisation other = (Organisation) obj;
		if (actComp1 != other.actComp1)
			return false;
		if (actComp2 != other.actComp2)
			return false;
		if (actComp3 != other.actComp3)
			return false;
		if (actComp4 != other.actComp4)
			return false;
		if (actComp5 != other.actComp5)
			return false;
		if (actComp6 != other.actComp6)
			return false;
		if (actComp7 == null) {
			if (other.actComp7 != null)
				return false;
		} else if (!actComp7.equals(other.actComp7))
			return false;
		if (actif != other.actif)
			return false;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (adresse2 == null) {
			if (other.adresse2 != null)
				return false;
		} else if (!adresse2.equals(other.adresse2))
			return false;
		if (afsca == null) {
			if (other.afsca != null)
				return false;
		} else if (!afsca.equals(other.afsca))
			return false;
		if (afsca1 == null) {
			if (other.afsca1 != null)
				return false;
		} else if (!afsca1.equals(other.afsca1))
			return false;
		if (afsca2 == null) {
			if (other.afsca2 != null)
				return false;
		} else if (!afsca2.equals(other.afsca2))
			return false;
		if (afsca3 == null) {
			if (other.afsca3 != null)
				return false;
		} else if (!afsca3.equals(other.afsca3))
			return false;
		if (antenne != other.antenne)
			return false;
		if (bic == null) {
			if (other.bic != null)
				return false;
		} else if (!bic.equals(other.bic))
			return false;
		if (civilite != other.civilite)
			return false;
		if (civiliteSec != other.civiliteSec)
			return false;
		if (civiliteTres != other.civiliteTres)
			return false;
		if (civiliteVp != other.civiliteVp)
			return false;
		if (classeFbba1 != other.classeFbba1)
			return false;
		if (classeFbba2 != other.classeFbba2)
			return false;
		if (classeFbba3 != other.classeFbba3)
			return false;
		if (classique == null) {
			if (other.classique != null)
				return false;
		} else if (!classique.equals(other.classique))
			return false;
		if (coldis != other.coldis)
			return false;
		if (cotAnnuelle != other.cotAnnuelle)
			return false;
		if (cotMonths != other.cotMonths)
			return false;
		if (cotMonthsSup != other.cotMonthsSup)
			return false;
		if (cotSup != other.cotSup)
			return false;
		if (cp == null) {
			if (other.cp != null)
				return false;
		} else if (!cp.equals(other.cp))
			return false;
		if (cp2 == null) {
			if (other.cp2 != null)
				return false;
		} else if (!cp2.equals(other.cp2))
			return false;
		if (cpasyN != other.cpasyN)
			return false;
		if (dateReg == null) {
			if (other.dateReg != null)
				return false;
		} else if (!dateReg.equals(other.dateReg))
			return false;
		if (daten != other.daten)
			return false;
		if (depPrinc != other.depPrinc)
			return false;
		if (depotram != other.depotram)
			return false;
		if (depyN != other.depyN)
			return false;
		if (disprog == null) {
			if (other.disprog != null)
				return false;
		} else if (!disprog.equals(other.disprog))
			return false;
		if (distrListPdt != other.distrListPdt)
			return false;
		if (distrListSec != other.distrListSec)
			return false;
		if (distrListTres != other.distrListTres)
			return false;
		if (distrListVp != other.distrListVp)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emailPres == null) {
			if (other.emailPres != null)
				return false;
		} else if (!emailPres.equals(other.emailPres))
			return false;
		if (emailSec == null) {
			if (other.emailSec != null)
				return false;
		} else if (!emailSec.equals(other.emailSec))
			return false;
		if (emailTres == null) {
			if (other.emailTres != null)
				return false;
		} else if (!emailTres.equals(other.emailTres))
			return false;
		if (emailVp == null) {
			if (other.emailVp != null)
				return false;
		} else if (!emailVp.equals(other.emailVp))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (feadN != other.feadN)
			return false;
		if (gestBen != other.gestBen)
			return false;
		if (gsm == null) {
			if (other.gsm != null)
				return false;
		} else if (!gsm.equals(other.gsm))
			return false;
		if (gsmPres == null) {
			if (other.gsmPres != null)
				return false;
		} else if (!gsmPres.equals(other.gsmPres))
			return false;
		if (gsmSec == null) {
			if (other.gsmSec != null)
				return false;
		} else if (!gsmSec.equals(other.gsmSec))
			return false;
		if (gsmTres == null) {
			if (other.gsmTres != null)
				return false;
		} else if (!gsmTres.equals(other.gsmTres))
			return false;
		if (gsmVp == null) {
			if (other.gsmVp != null)
				return false;
		} else if (!gsmVp.equals(other.gsmVp))
			return false;
		if (iban == null) {
			if (other.iban != null)
				return false;
		} else if (!iban.equals(other.iban))
			return false;
		if (idDis != other.idDis)
			return false;
		if (langue != other.langue)
			return false;
		if (lastvisit == null) {
			if (other.lastvisit != null)
				return false;
		} else if (!lastvisit.equals(other.lastvisit))
			return false;
		if (lienCpas != other.lienCpas)
			return false;
		if (lienDepot != other.lienDepot)
			return false;
		if (lienGd != other.lienGd)
			return false;
		if (lienGs != other.lienGs)
			return false;
		if (localite == null) {
			if (other.localite != null)
				return false;
		} else if (!localite.equals(other.localite))
			return false;
		if (localite2 == null) {
			if (other.localite2 != null)
				return false;
		} else if (!localite2.equals(other.localite2))
			return false;
		if (logBirb != other.logBirb)
			return false;
		if (lupdTs == null) {
			if (other.lupdTs != null)
				return false;
		} else if (!lupdTs.equals(other.lupdTs))
			return false;
		if (lupdUserName == null) {
			if (other.lupdUserName != null)
				return false;
		} else if (!lupdUserName.equals(other.lupdUserName))
			return false;
		if (montCot == null) {
			if (other.montCot != null)
				return false;
		} else if (!montCot.equals(other.montCot))
			return false;
		if (msonac != other.msonac)
			return false;
		if (nAdo != other.nAdo)
			return false;
		if (nBebe != other.nBebe)
			return false;
		if (nEnf != other.nEnf)
			return false;
		if (nEq == null) {
			if (other.nEq != null)
				return false;
		} else if (!nEq.equals(other.nEq))
			return false;
		if (nFam != other.nFam)
			return false;
		if (nNour != other.nNour)
			return false;
		if (nPers != other.nPers)
			return false;
		if (nSen != other.nSen)
			return false;
		if (nbrefix != other.nbrefix)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (nomSec == null) {
			if (other.nomSec != null)
				return false;
		} else if (!nomSec.equals(other.nomSec))
			return false;
		if (nomTres == null) {
			if (other.nomTres != null)
				return false;
		} else if (!nomTres.equals(other.nomTres))
			return false;
		if (nomVp == null) {
			if (other.nomVp != null)
				return false;
		} else if (!nomVp.equals(other.nomVp))
			return false;
		if (nrFead != other.nrFead)
			return false;
		if (nrTournee != other.nrTournee)
			return false;
		if (pays != other.pays)
			return false;
		if (pays2 != other.pays2)
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (prenomSec == null) {
			if (other.prenomSec != null)
				return false;
		} else if (!prenomSec.equals(other.prenomSec))
			return false;
		if (prenomTres == null) {
			if (other.prenomTres != null)
				return false;
		} else if (!prenomTres.equals(other.prenomTres))
			return false;
		if (prenomVp == null) {
			if (other.prenomVp != null)
				return false;
		} else if (!prenomVp.equals(other.prenomVp))
			return false;
		if (refInt == null) {
			if (other.refInt != null)
				return false;
		} else if (!refInt.equals(other.refInt))
			return false;
		if (region != other.region)
			return false;
		if (rem == null) {
			if (other.rem != null)
				return false;
		} else if (!rem.equals(other.rem))
			return false;
		if (remLivr == null) {
			if (other.remLivr != null)
				return false;
		} else if (!remLivr.equals(other.remLivr))
			return false;
		if (societe == null) {
			if (other.societe != null)
				return false;
		} else if (!societe.equals(other.societe))
			return false;
		if (statut == null) {
			if (other.statut != null)
				return false;
		} else if (!statut.equals(other.statut))
			return false;
		if (stopSusp == null) {
			if (other.stopSusp != null)
				return false;
		} else if (!stopSusp.equals(other.stopSusp))
			return false;
		if (susp != other.susp)
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		if (telPres == null) {
			if (other.telPres != null)
				return false;
		} else if (!telPres.equals(other.telPres))
			return false;
		if (telSec == null) {
			if (other.telSec != null)
				return false;
		} else if (!telSec.equals(other.telSec))
			return false;
		if (telTres == null) {
			if (other.telTres != null)
				return false;
		} else if (!telTres.equals(other.telTres))
			return false;
		if (telVp == null) {
			if (other.telVp != null)
				return false;
		} else if (!telVp.equals(other.telVp))
			return false;
		if (tourneeJour != other.tourneeJour)
			return false;
		if (tourneeMois != other.tourneeMois)
			return false;
		if (tourneeSem != other.tourneeSem)
			return false;
		if (tva == null) {
			if (other.tva != null)
				return false;
		} else if (!tva.equals(other.tva))
			return false;
		if (webauthority != other.webauthority)
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Organisation [idDis=" + idDis + ", refInt=" + refInt + ",  lienDepot=" + lienDepot + ", societe=" + societe + ", adresse=" + adresse + ", statut="
				+ statut + ", email=" + email + ", cp=" + cp + ", localite=" + localite
				+ ", pays=" + pays + ", tva=" + tva + ", website=" + website + ", tel=" + tel + ", gsm=" + gsm
				+ ", daten=" + daten + ", region=" + region + ", iban=" + iban + ", classique="
				+ classique + ", bic=" + bic + ", actif=" + actif + ", civilite=" + civilite + ", nom=" + nom
				+ ", prenom=" + prenom + ", civiliteVp=" + civiliteVp + ", prenomVp=" + prenomVp + ", nomVp=" + nomVp
				+ ", telVp=" + telVp + ", gsmVp=" + gsmVp + ", civiliteSec=" + civiliteSec + ", prenomSec=" + prenomSec
				+ ", nomSec=" + nomSec + ", telSec=" + telSec + ", gsmSec=" + gsmSec + ", civiliteTres=" + civiliteTres
				+ ", prenomTres=" + prenomTres + ", nomTres=" + nomTres + ", telTres=" + telTres + ", gsmTres="
				+ gsmTres + ", emailPres=" + emailPres + ", emailVp=" + emailVp + ", emailSec=" + emailSec
				+ ", emailTres=" + emailTres + ", telPres=" + telPres + ", gsmPres=" + gsmPres + ", disprog=" + disprog
				+ ", afsca=" + afsca + ", webauthority=" + webauthority + ", langue=" + langue + ", lastvisit="
				+ lastvisit + ", nbrefix=" + nbrefix + ", cpasyN=" + cpasyN + ", lienCpas=" + lienCpas + ", depyN=" + depyN + ", logBirb=" + logBirb + ", actComp1=" + actComp1 + ", actComp2="
				+ actComp2 + ", actComp3=" + actComp3 + ", actComp4=" + actComp4 + ", actComp5=" + actComp5
				+ ", actComp6=" + actComp6 + ", actComp7=" + actComp7 + ", nrTournee=" + nrTournee + ", susp=" + susp
				+ ", stopSusp=" + stopSusp + ", rem=" + rem + ", msonac=" + msonac + ", classeFbba1=" + classeFbba1
				+ ", classeFbba2=" + classeFbba2 + ", classeFbba3=" + classeFbba3 + ", nFam=" + nFam + ", nPers="
				+ nPers + ", nNour=" + nNour + ", nBebe=" + nBebe + ", nEnf=" + nEnf + ", nAdo=" + nAdo + ", nEq=" + nEq
				+ ", nSen=" + nSen + ", depPrinc=" + depPrinc + ", gestBen=" + gestBen + ", tourneeJour=" + tourneeJour
				+ ", tourneeSem=" + tourneeSem + ", coldis=" + coldis + ", lienGd=" + lienGd + ", lienGs=" + lienGs
				+ ", montCot=" + montCot + ", antenne=" + antenne + ", afsca1=" + afsca1 + ", afsca2=" + afsca2
				+ ", afsca3=" + afsca3 + ", nrFead=" + nrFead + ", tourneeMois=" + tourneeMois + ", distrListPdt="
				+ distrListPdt + ", distrListVp=" + distrListVp + ", distrListSec=" + distrListSec + ", distrListTres="
				+ distrListTres + ", adresse2=" + adresse2 + ", cp2=" + cp2 + ", localite2=" + localite2 + ", pays2="
				+ pays2 + ", dateReg=" + dateReg + ", fax=" + fax + ", feadN=" + feadN + ", remLivr=" + remLivr
				+ ", cotAnnuelle=" + cotAnnuelle + ", cotMonths=" + cotMonths + ", cotSup=" + cotSup + ", cotMonthsSup="
				+ cotMonthsSup + ", depotram=" + depotram + ", lupdUserName=" + lupdUserName + ", lupdTs=" + lupdTs
				+ "]";
	}

  

   

   
}
