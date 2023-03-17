package foodbank.it.web.dto;

public class CodePostalDto {
    private int zipCode;

    private String city;

    private int lCpas;

    private Short zipCodeCpas;

    private String cityCpas;

    private String mailCpas;

    private String remCpas;
    private long totalRecords;

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getlCpas() {
        return lCpas;
    }

    public void setlCpas(int lCpas) {
        this.lCpas = lCpas;
    }

    public Short getZipCodeCpas() {
        return zipCodeCpas;
    }

    public void setZipCodeCpas(Short zipCodeCpas) {
        this.zipCodeCpas = zipCodeCpas;
    }

    public String getCityCpas() {
        return cityCpas;
    }

    public void setCityCpas(String cityCpas) {
        this.cityCpas = cityCpas;
    }

    public String getMailCpas() {
        return mailCpas;
    }

    public void setMailCpas(String mailCpas) {
        this.mailCpas = mailCpas;
    }

    public String getRemCpas() {
        return remCpas;
    }

    public void setRemCpas(String remCpas) {
        this.remCpas = remCpas;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }
}
