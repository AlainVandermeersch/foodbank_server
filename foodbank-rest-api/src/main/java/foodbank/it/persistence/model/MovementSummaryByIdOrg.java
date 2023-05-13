package foodbank.it.persistence.model;

public class MovementSummaryByIdOrg {
    private String key; // can be month or date
    private String bankShortName;
    private Integer lienDepot;
    private Integer idOrg;
    private String orgname;
    private String category;
    private Double quantity;
    private Long nfamilies;
    private Long npersons;


    public MovementSummaryByIdOrg(String key, String bankShortName,String category, Integer lienDepot,Integer idOrg,String orgname, Double quantity,Long nfamilies, Long npersons) {
        this.key = key;
        this.bankShortName = bankShortName;
        this.category = category;
        this.lienDepot = lienDepot;
        this.idOrg = idOrg;
        this.orgname = orgname;
        this.quantity =quantity;
        this.nfamilies = nfamilies;
        this.npersons = npersons;
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

    public Integer getLienDepot() {
        return lienDepot;
    }

    public void setLienDepot(Integer lienDepot) {
        this.lienDepot = lienDepot;
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

    public Long getNfamilies() {
        return nfamilies;
    }

    public void setNfamilies(Long nfamilies) {
        this.nfamilies = nfamilies;
    }

    public Long getNpersons() {
        return npersons;
    }

    public void setNpersons(Long npersons) {
        this.npersons = npersons;
    }
}
