package foodbank.it.service;

public class SearchCodePostalCriteria {
    private Integer zipCode;
    private String city;
    private Integer zipCodeCpas;
    private String cityCpas;

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipCodeCpas() {
        return zipCodeCpas;
    }

    public void setZipCodeCpas(Integer zipCodeCpas) {
        this.zipCodeCpas = zipCodeCpas;
    }

    public String getCityCpas() {
        return cityCpas;
    }

    public void setCityCpas(String cityCpas) {
        this.cityCpas = cityCpas;
    }
}