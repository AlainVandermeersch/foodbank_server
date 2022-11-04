package foodbank.it.persistence.model;

public class UserCount {
    private String idCompany;
    private Long count;
    public UserCount(String idCompany, Long count) {
        super();
        this.idCompany = idCompany;
        this.count = count;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
