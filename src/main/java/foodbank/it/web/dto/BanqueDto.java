package foodbank.it.web.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class BanqueDto {
    private int bankId;
   
    private String bankShortName;
    
    private String bankName;
   
    private String nrEntr;
   
    private String bankMail;
   
    private short actif;
   
    private int comGest;
   
    private LocalDateTime lastvisit;
    
    private short nomPres;
   
    private short nomVp;
   
    private short nomSec;
   
    private short nomTres;
    
    private short nomIt;
    
    private short nomLog;
    
    private short nomRh;
    
    private short nomSh;
    
    private short nomPp;
   
    private short nomAsso;
   
    private short nomAppro;
    
    private short nomPubrel;
    
    private int nomCeo;
   
    private int nomFead;
   
    private String adresse;
   
    private String cp;
   
    private String localite;
   
    private String bankTel;
    
    private String bankGsm;
   
    private String adresseDepotPrinc;
   
    private String cpDepotPrinc;
    
    private String cityDepotPrinc;
   
    private String depPrincTel;
    
    private String ssAdresse;
    
    private String ssCp;
    
    private String ssCity;
   
    private String ssTel;
    
    private int regio;
    
    private String website;
   
    private String bank;
    
   
    
    protected BanqueDto() {
        
    }

    public BanqueDto(int bankId, String bankShortName, String bankName, String nrEntr, String bankMail, short actif, int comGest, LocalDateTime lastvisit, short nomPres, short nomVp, short nomSec, short nomTres, short nomIt, short nomLog, short nomRh,
        short nomSh, short nomPp, short nomAsso, short nomAppro, short nomPubrel, int nomCeo, int nomFead, String adresse, String cp, String localite, String bankTel, String bankGsm, String adresseDepotPrinc, String cpDepotPrinc, String cityDepotPrinc,
        String depPrincTel, String ssAdresse, String ssCp, String ssCity, String ssTel, int regio, String website, String bank) {
        super();
        this.bankId = bankId;
        this.bankShortName = bankShortName;
        this.bankName = bankName;
        this.nrEntr = nrEntr;
        this.bankMail = bankMail;
        this.actif = actif;
        this.comGest = comGest;
        this.lastvisit = lastvisit;
        this.nomPres = nomPres;
        this.nomVp = nomVp;
        this.nomSec = nomSec;
        this.nomTres = nomTres;
        this.nomIt = nomIt;
        this.nomLog = nomLog;
        this.nomRh = nomRh;
        this.nomSh = nomSh;
        this.nomPp = nomPp;
        this.nomAsso = nomAsso;
        this.nomAppro = nomAppro;
        this.nomPubrel = nomPubrel;
        this.nomCeo = nomCeo;
        this.nomFead = nomFead;
        this.adresse = adresse;
        this.cp = cp;
        this.localite = localite;
        this.bankTel = bankTel;
        this.bankGsm = bankGsm;
        this.adresseDepotPrinc = adresseDepotPrinc;
        this.cpDepotPrinc = cpDepotPrinc;
        this.cityDepotPrinc = cityDepotPrinc;
        this.depPrincTel = depPrincTel;
        this.ssAdresse = ssAdresse;
        this.ssCp = ssCp;
        this.ssCity = ssCity;
        this.ssTel = ssTel;
        this.regio = regio;
        this.website = website;
        this.bank = bank;
      
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getNrEntr() {
        return nrEntr;
    }

    public void setNrEntr(String nrEntr) {
        this.nrEntr = nrEntr;
    }

    public String getBankMail() {
        return bankMail;
    }

    public void setBankMail(String bankMail) {
        this.bankMail = bankMail;
    }

    public short getActif() {
        return actif;
    }

    public void setActif(short actif) {
        this.actif = actif;
    }

    public int getComGest() {
        return comGest;
    }

    public void setComGest(int comGest) {
        this.comGest = comGest;
    }

    public LocalDateTime getLastvisit() {
        return lastvisit;
    }

    public void setLastvisit(LocalDateTime lastvisit) {
        this.lastvisit = lastvisit;
    }

    public short getNomPres() {
        return nomPres;
    }

    public void setNomPres(short nomPres) {
        this.nomPres = nomPres;
    }

    public short getNomVp() {
        return nomVp;
    }

    public void setNomVp(short nomVp) {
        this.nomVp = nomVp;
    }

    public short getNomSec() {
        return nomSec;
    }

    public void setNomSec(short nomSec) {
        this.nomSec = nomSec;
    }

    public short getNomTres() {
        return nomTres;
    }

    public void setNomTres(short nomTres) {
        this.nomTres = nomTres;
    }

    public short getNomIt() {
        return nomIt;
    }

    public void setNomIt(short nomIt) {
        this.nomIt = nomIt;
    }

    public short getNomLog() {
        return nomLog;
    }

    public void setNomLog(short nomLog) {
        this.nomLog = nomLog;
    }

    public short getNomRh() {
        return nomRh;
    }

    public void setNomRh(short nomRh) {
        this.nomRh = nomRh;
    }

    public short getNomSh() {
        return nomSh;
    }

    public void setNomSh(short nomSh) {
        this.nomSh = nomSh;
    }

    public short getNomPp() {
        return nomPp;
    }

    public void setNomPp(short nomPp) {
        this.nomPp = nomPp;
    }

    public short getNomAsso() {
        return nomAsso;
    }

    public void setNomAsso(short nomAsso) {
        this.nomAsso = nomAsso;
    }

    public short getNomAppro() {
        return nomAppro;
    }

    public void setNomAppro(short nomAppro) {
        this.nomAppro = nomAppro;
    }

    public short getNomPubrel() {
        return nomPubrel;
    }

    public void setNomPubrel(short nomPubrel) {
        this.nomPubrel = nomPubrel;
    }

    public int getNomCeo() {
        return nomCeo;
    }

    public void setNomCeo(int nomCeo) {
        this.nomCeo = nomCeo;
    }

    public int getNomFead() {
        return nomFead;
    }

    public void setNomFead(int nomFead) {
        this.nomFead = nomFead;
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

    public String getBankTel() {
        return bankTel;
    }

    public void setBankTel(String bankTel) {
        this.bankTel = bankTel;
    }

    public String getBankGsm() {
        return bankGsm;
    }

    public void setBankGsm(String bankGsm) {
        this.bankGsm = bankGsm;
    }

    public String getAdresseDepotPrinc() {
        return adresseDepotPrinc;
    }

    public void setAdresseDepotPrinc(String adresseDepotPrinc) {
        this.adresseDepotPrinc = adresseDepotPrinc;
    }

    public String getCpDepotPrinc() {
        return cpDepotPrinc;
    }

    public void setCpDepotPrinc(String cpDepotPrinc) {
        this.cpDepotPrinc = cpDepotPrinc;
    }

    public String getCityDepotPrinc() {
        return cityDepotPrinc;
    }

    public void setCityDepotPrinc(String cityDepotPrinc) {
        this.cityDepotPrinc = cityDepotPrinc;
    }

    public String getDepPrincTel() {
        return depPrincTel;
    }

    public void setDepPrincTel(String depPrincTel) {
        this.depPrincTel = depPrincTel;
    }

    public String getSsAdresse() {
        return ssAdresse;
    }

    public void setSsAdresse(String ssAdresse) {
        this.ssAdresse = ssAdresse;
    }

    public String getSsCp() {
        return ssCp;
    }

    public void setSsCp(String ssCp) {
        this.ssCp = ssCp;
    }

    public String getSsCity() {
        return ssCity;
    }

    public void setSsCity(String ssCity) {
        this.ssCity = ssCity;
    }

    public String getSsTel() {
        return ssTel;
    }

    public void setSsTel(String ssTel) {
        this.ssTel = ssTel;
    }

    public int getRegio() {
        return regio;
    }

    public void setRegio(int regio) {
        this.regio = regio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

   
    
}
