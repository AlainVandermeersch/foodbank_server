package foodbank.it.persistence.model;

public class MovementMonthlyCountbyBank {
    private String key; // can be month or date
    private String bankShortName;
    private String category;
    private Double quantity;
    private Long nfamilies;
    private Long npersons;

    public MovementMonthlyCountbyBank(String key, String bankShortName, String category, Double quantity,Long nfamilies, Long npersons) {
        this.key = key;
        this.bankShortName = bankShortName;
        this.category = category;
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
