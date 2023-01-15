package foodbank.it.service;

public class SearchMovementCriteria {
    String scope;
    private String idCompany;
    private Integer lienDis;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }

    public Integer getLienDis() {
        return lienDis;
    }

    public void setLienDis(Integer lienDis) {
        this.lienDis = lienDis;
    }
}
