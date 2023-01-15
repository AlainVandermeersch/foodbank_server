package foodbank.it.persistence.model;

public class MovementMonthlyCount {
    private String key; // can be month or date
    private String bankShortName;
    private String category;
    private Double quantity;

    public MovementMonthlyCount(String key, String bankShortName, String category, Double quantity) {
        this.key = key;
        this.bankShortName = bankShortName;
        this.category = category;
        this.quantity =quantity;
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
}
