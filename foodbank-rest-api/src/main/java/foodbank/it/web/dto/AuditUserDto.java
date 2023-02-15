package foodbank.it.web.dto;

public class AuditUserDto {
    private String idUser;

    private String userName;

    private String idCompany;
    private Integer idOrg;
    private String societe;
    private String email;
    private String rights;
    private Long loginCountPHP;
    private Long loginCountFBIT;
    private Long totalRecords;

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

    public Long getLoginCountPHP() {
        return loginCountPHP;
    }

    public void setLoginCountPHP(Long loginCountPHP) {
        this.loginCountPHP = loginCountPHP;
    }

    public Long getLoginCountFBIT() {
        return loginCountFBIT;
    }

    public void setLoginCountFBIT(Long loginCountFBIT) {
        this.loginCountFBIT = loginCountFBIT;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }
}
