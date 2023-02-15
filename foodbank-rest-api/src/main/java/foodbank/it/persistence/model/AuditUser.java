package foodbank.it.persistence.model;

public class AuditUser {
    private String idUser;
    private String userName;
    private String idCompany;
    private Integer idOrg;
    private String societe;
    private String email;
    private String rights;
    private String application;
    private Long loginCount;

    public AuditUser(String idUser,String application, String userName,String idCompany,
                     int idOrg,String societe, String email, String rights,
                     Long loginCount) {
        this.idUser = idUser;
        this.application = application;
        this.userName = userName;
        this.idCompany = idCompany;
        this.idOrg = idOrg;
        this.societe = societe;
        this.email = email;
        this.rights = rights;
        this.loginCount = loginCount;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }

    public Integer getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(Integer idOrg) {
        this.idOrg = idOrg;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Long getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Long loginCount) {
        this.loginCount = loginCount;
    }
}
