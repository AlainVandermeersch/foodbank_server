package foodbank.it.service;

public class SearchMovementCriteria {

    private String idCompany;
    private Integer lienDis;
    private String lowRange;
    private String highRange;
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

    public String getLowRange() {
        return lowRange;
    }

    public void setLowRange(String lowRange) {
        this.lowRange = lowRange;
    }

    public String getHighRange() {
        return highRange;
    }

    public void setHighRange(String highRange) {
        this.highRange = highRange;
    }
}
