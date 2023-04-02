package foodbank.it.service;

public class SearchCodePostalCriteria {
    private Integer zipCode;
    private Integer lienBanque;
    private String city;
    private String zipCodeCpas;
    private String cityCpas;

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getLienBanque() {
        return lienBanque;
    }

    public void setLienBanque(Integer lienBanque) {
        this.lienBanque = lienBanque;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCodeCpas() {
        return zipCodeCpas;
    }

    public void setZipCodeCpas(String zipCodeCpas) {
        this.zipCodeCpas = zipCodeCpas;
    }

    public String getCityCpas() {
        return cityCpas;
    }

    public void setCityCpas(String cityCpas) {
        this.cityCpas = cityCpas;
    }
}
