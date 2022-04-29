package foodbank.it.persistence.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Formula;
@Entity(name="auditchanges")
public class AuditChange implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "change_id", unique = true, nullable = false, precision = 10)
    private int auditId;
    @Column(nullable = false, length = 50)
    private String user;
    @Column(name = "date_in")
    private LocalDateTime dateIn;
    @Column(name = "bank_id", nullable = false, precision = 3)
    private int bankId;
    @Column(name = "id_dis", nullable = false, precision = 10)
    private int idDis;
    private String entity;
    private String entity_key;
    private String action;
    @Formula("(select d.societe from organisations d where d.id_dis = id_dis)")
    private String societe;
    @Formula("(select d.ID_COMPANY from t_user d where d.ID_USER = user)")
    private String bankShortName;
    @Formula("(select d.USER_NAME from t_user d where d.ID_USER = user)")
    private String userName;

    public AuditChange() {
        super();
    }

    public AuditChange(int auditId, String user, int bankId, int idDis, String entity, String entity_key, String action) {
        this.auditId = auditId;
        this.user = user;
        this.dateIn = LocalDateTime.now();
        this.bankId = bankId;
        this.idDis = idDis;
        this.entity = entity;
        this.entity_key = entity_key;
        this.action = action;
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

    public LocalDateTime getDateIn() {
        return dateIn;
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


    public String getBankShortName() {
        return bankShortName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditChange that = (AuditChange) o;
        return auditId == that.auditId && bankId == that.bankId && idDis == that.idDis && user.equals(that.user) && dateIn.equals(that.dateIn) && entity.equals(that.entity) && entity_key.equals(that.entity_key) && action.equals(that.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auditId, user, dateIn, bankId, idDis, entity, entity_key, action);
    }
}
