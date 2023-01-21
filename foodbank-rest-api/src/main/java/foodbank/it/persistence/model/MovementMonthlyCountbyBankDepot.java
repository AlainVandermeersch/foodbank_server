package foodbank.it.persistence.model;

public class MovementMonthlyCountbyBankDepot {
    private String key; // can be month or date
    private String bankShortName;

    private Integer idOrg; // lienDepot: idOrg of the depot
    private String orgname; // orgName: name of the depot
    private String category;
    private Double quantity;

    public MovementMonthlyCountbyBankDepot(String key, String bankShortName, Integer lienDepot,String category, Double quantity) {
        this.key = key;
        this.bankShortName = bankShortName;
        this.idOrg = lienDepot;
        this.category = category;
        this.quantity =quantity;
        this.orgname = "Unknown";
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
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
}
