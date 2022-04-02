package foodbank.it.web.dto;

public class TypeEmploiDto {
    private int jobNr;
    private String jobNameFr;
    private String jobNameNl;
    private int lienBanque;
    private boolean actif;
    private String jobNameEn;
    private String jobNameGe;
    private String bankShortName;

    public TypeEmploiDto(int jobNr, String jobNameFr, String jobNameNl, int lienBanque, boolean actif,
                         String jobNameEn, String jobNameGe, String bankShortName) {
        this.jobNr = jobNr;
        this.jobNameFr = jobNameFr;
        this.jobNameNl = jobNameNl;
        this.lienBanque = lienBanque;
        this.actif = actif;
        this.jobNameEn = jobNameEn;
        this.jobNameGe = jobNameGe;
        this.bankShortName = bankShortName;
    }

    public int getJobNr() {
        return jobNr;
    }

    public void setJobNr(int jobNr) {
        this.jobNr = jobNr;
    }

    public String getJobNameFr() {
        return jobNameFr;
    }

    public void setJobNameFr(String jobNameFr) {
        this.jobNameFr = jobNameFr;
    }

    public String getJobNameNl() {
        return jobNameNl;
    }

    public void setJobNameNl(String jobNameNl) {
        this.jobNameNl = jobNameNl;
    }

    public int getLienBanque() {
        return lienBanque;
    }

    public void setLienBanque(int lienBanque) {
        this.lienBanque = lienBanque;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getJobNameEn() {
        return jobNameEn;
    }

    public void setJobNameEn(String jobNameEn) {
        this.jobNameEn = jobNameEn;
    }

    public String getJobNameGe() {
        return jobNameGe;
    }

    public void setJobNameGe(String jobNameGe) {
        this.jobNameGe = jobNameGe;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }
}
