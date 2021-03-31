package foodbank.it.web.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BanqueDto {
    private int bankId;
   
    private String bankShortName;
    
    private String bankName;
   
    private String nrEntr;
   
    private String bankMail;
   
    private short actif;
   
    private int comGest;
   
    private String lastvisit;
    
    private short idMemberPres;
   
    private short idMemberVp;
   
    private short idMemberSec;
   
    private short idMemberTres;
    
    private short idMemberIt;
    
    private short idMemberLog;
    
    private short idMemberRh;
    
    private short idMemberSh;
        
    private short idMemberPp;
   
    private short idMemberAsso;
   
    private short idMemberAppro;
    
    private short idMemberPubrel;
    
    private int idMemberCeo;
   
    private int idMemberFead;
    
    private Short idMemberQual;
   
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

    public BanqueDto(int bankId, String bankShortName, String bankName, String nrEntr, String bankMail, short actif, int comGest, LocalDateTime lastvisit, short idMemberPres, short idMemberVp, short idMemberSec, short idMemberTres, short idMemberIt, short idMemberLog, short idMemberRh,
        short idMemberSh, short idMemberPp, short idMemberAsso, short idMemberAppro, short idMemberPubrel, int idMemberCeo, int idMemberFead, Short idMemberQual, String adresse, String cp, String localite, String bankTel, String bankGsm, String adresseDepotPrinc, String cpDepotPrinc, String cityDepotPrinc,
        String depPrincTel, String ssAdresse, String ssCp, String ssCity, String ssTel, int regio, String website, String bank) {
        super();
        this.bankId = bankId;
        this.bankShortName = bankShortName;
        this.bankName = bankName;
        this.nrEntr = nrEntr;
        this.bankMail = bankMail;
        this.actif = actif;
        this.comGest = comGest;
        if (lastvisit == null) {
			this.lastvisit = "";
		}
		else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			this.lastvisit = lastvisit.format(formatter);
		}
        this.idMemberPres = idMemberPres;
        this.idMemberVp = idMemberVp;
        this.idMemberSec = idMemberSec;
        this.idMemberTres = idMemberTres;
        this.idMemberIt = idMemberIt;
        this.idMemberLog = idMemberLog;
        this.idMemberRh = idMemberRh;
        this.idMemberSh = idMemberSh;
        this.idMemberPp = idMemberPp;
        this.idMemberAsso = idMemberAsso;
        this.idMemberAppro = idMemberAppro;
        this.idMemberPubrel = idMemberPubrel;
        this.idMemberCeo = idMemberCeo;
        this.idMemberFead = idMemberFead;
        this.idMemberQual = idMemberQual;
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

    public String getLastvisit() {
        return lastvisit;
    }

    public void setLastvisit(String lastvisit) {
        this.lastvisit = lastvisit;
    }

   
    public short getIdMemberPres() {
		return idMemberPres;
	}

	public void setIdMemberPres(short idMemberPres) {
		this.idMemberPres = idMemberPres;
	}

	public short getIdMemberVp() {
		return idMemberVp;
	}

	public void setIdMemberVp(short idMemberVp) {
		this.idMemberVp = idMemberVp;
	}

	public short getIdMemberSec() {
		return idMemberSec;
	}

	public void setIdMemberSec(short idMemberSec) {
		this.idMemberSec = idMemberSec;
	}

	public short getIdMemberTres() {
		return idMemberTres;
	}

	public void setIdMemberTres(short idMemberTres) {
		this.idMemberTres = idMemberTres;
	}

	public short getIdMemberIt() {
		return idMemberIt;
	}

	public void setIdMemberIt(short idMemberIt) {
		this.idMemberIt = idMemberIt;
	}

	public short getIdMemberLog() {
		return idMemberLog;
	}

	public void setIdMemberLog(short idMemberLog) {
		this.idMemberLog = idMemberLog;
	}

	public short getIdMemberRh() {
		return idMemberRh;
	}

	public void setIdMemberRh(short idMemberRh) {
		this.idMemberRh = idMemberRh;
	}

	public short getIdMemberSh() {
		return idMemberSh;
	}

	public void setIdMemberSh(short idMemberSh) {
		this.idMemberSh = idMemberSh;
	}

	public short getIdMemberPp() {
		return idMemberPp;
	}

	public void setIdMemberPp(short idMemberPp) {
		this.idMemberPp = idMemberPp;
	}

	public short getIdMemberAsso() {
		return idMemberAsso;
	}

	public void setIdMemberAsso(short idMemberAsso) {
		this.idMemberAsso = idMemberAsso;
	}

	public short getIdMemberAppro() {
		return idMemberAppro;
	}

	public void setIdMemberAppro(short idMemberAppro) {
		this.idMemberAppro = idMemberAppro;
	}

	public short getIdMemberPubrel() {
		return idMemberPubrel;
	}

	public void setIdMemberPubrel(short idMemberPubrel) {
		this.idMemberPubrel = idMemberPubrel;
	}

	public int getIdMemberCeo() {
		return idMemberCeo;
	}

	public void setIdMemberCeo(int idMemberCeo) {
		this.idMemberCeo = idMemberCeo;
	}

	public int getIdMemberFead() {
		return idMemberFead;
	}

	public void setIdMemberFead(int idMemberFead) {
		this.idMemberFead = idMemberFead;
	}
	
	

	public Short getIdMemberQual() {
		return idMemberQual;
	}

	public void setIdMemberQual(Short idMemberQual) {
		this.idMemberQual = idMemberQual;
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
