package foodbank.it.persistence.model;

public class MovementBankCount {
    private String month;
    private String bankShortName;
    private String category;
    private Double quantity;

    public MovementBankCount(String month, String bankShortName, String category,Double quantity) {
        this.month = month;
        this.bankShortName = bankShortName;
        this.category = category;
        this.quantity =quantity;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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
