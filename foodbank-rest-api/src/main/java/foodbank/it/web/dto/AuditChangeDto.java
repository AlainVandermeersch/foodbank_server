package foodbank.it.web.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditChangeDto {
    private int auditId;
    private String user;
    private String dateIn;
    private int bankId;
    private int idDis;
    private String entity;
    private String entity_key;
    private String action;
    private String societe;
    private String bankShortName;
    private String userName;
        private Long  totalRecords;

    public AuditChangeDto(int auditId, String user, LocalDateTime dateIn, int bankId, int idDis,
                          String entity, String entity_key, String action, String societe, String bankShortName, String userName, Long totalRecords) {
        super();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        this.auditId = auditId;
        this.user = user;
        if (dateIn == null) {
            this.dateIn = "";
        }
        else {
            this.dateIn = dateIn.plusHours(1).format(formatter);
        }
        this.bankId = bankId;
        this.idDis = idDis;
        this.entity = entity;
        this.entity_key = entity_key;
        this.action = action;
        this.societe = societe;
        this.bankShortName = bankShortName;
        this.userName = userName;
        this.totalRecords = totalRecords;
    }

    public int getAuditId() {
        return auditId;
    }

    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public int getIdDis() {
        return idDis;
    }

    public void setIdDis(int idDis) {
        this.idDis = idDis;
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

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }
}
