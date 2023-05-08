package foodbank.it.persistence.model;

import java.time.LocalDate;

public class MovementDailyCountByBankDepot {
    private LocalDate key; // can be month or date
    private String bankShortName;
    private Integer idOrg; // lienDepot: idOrg of the depot

    private String orgname; // orgName: name of the depot
    private String category;
    private Double quantity;
    private Long nfamilies;
    private Long npersons;
    // special constructor for multiselect grouped by idOrg
    public MovementDailyCountByBankDepot(LocalDate key, String bankShortName,String category, Integer idOrg,String orgname, Double quantity,Long nfamilies, Long npersons) {
        this.key = key;
        this.bankShortName = bankShortName;
        this.idOrg = idOrg;
        this.category = category;
        this.quantity =quantity;
        this.orgname = orgname;
        this.nfamilies = nfamilies;
        this.npersons = npersons;
    }
    // special constructor for multiselect grouped by depot
    public MovementDailyCountByBankDepot(LocalDate key, String bankShortName,String category, Integer lienDepot, Double quantity,Long nfamilies, Long npersons) {
        this.key = key;
        this.bankShortName = bankShortName;
        this.idOrg = lienDepot;
        this.category = category;
        this.quantity =quantity;
        this.orgname = "Unknown";
        this.nfamilies = nfamilies;
        this.npersons = npersons;
    }
    public String getKey() {
        return key.toString();
    }

    public void setKey(LocalDate key) {
        this.key = key;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }

    public Integer getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(Integer idOrg) {
        this.idOrg = idOrg;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getnfamilies() {
        return nfamilies;
    }
    public void setnfamilies(Long nfamilies) {
        this.nfamilies = nfamilies;
    }
    public Long getnpersons() {
        return npersons;
    }
    public void setnpersons(Long npersons) {
        this.npersons = npersons;
    }
}

