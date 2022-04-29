package foodbank.it.service;

public class SearchAuditChangeCriteria {

    private String user;
    private String bankShortName;
    private Integer idDis;
    private String societe;
    private String userName;
    private String entity;
    private String entity_key;
    private String action;
    private String fromDate;
    private String toDate;

    public SearchAuditChangeCriteria(String user, String bankShortName, Integer idDis, String societe, String userName,
                                     String entity,String entity_key, String action, String fromDate, String toDate ) {
        super();
        this.user = user;
        this.bankShortName = bankShortName;
        this.idDis = idDis;
        this.societe = societe;
        this.userName = userName;
        this.entity = entity;
        this.entity_key = entity_key;
        this.action = action;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public Integer getIdDis() {
        return idDis;
    }
    public void setIdDis(Integer idDis) {
        this.idDis = idDis;
    }
    public String getSociete() {
        return societe;
    }
    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getBankShortName() {
        return bankShortName;
    }
    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getEntity_key() {
        return entity_key;
    }

    public void setEntity_key(String entity_key) {
        this.entity_key = entity_key;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFromDate() {
        return fromDate;
    }
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
    public String getToDate() {
        return toDate;
    }
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
