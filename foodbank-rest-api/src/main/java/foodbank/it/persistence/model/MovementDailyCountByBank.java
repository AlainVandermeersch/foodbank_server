package foodbank.it.persistence.model;

import java.time.LocalDate;

public class MovementDailyCountByBank {
    private LocalDate key; // can be month or date
    private String bankShortName;
    private String category;
    private Double quantity;

    public MovementDailyCountByBank(LocalDate key, String bankShortName, String category, Double quantity) {
        this.key = key;
        this.bankShortName = bankShortName;
        this.category = category;
        this.quantity =quantity;
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

