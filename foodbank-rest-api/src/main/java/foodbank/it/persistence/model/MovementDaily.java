package foodbank.it.persistence.model;


import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@IdClass(MovementDailyKey.class)
@Entity(name="movements_daily")

public class MovementDaily implements Serializable
{
    @Id
    @Column(name="day")
    private LocalDate day;
    @Column(name="bank_short_name")
    private String bankShortName;
    @Column(name="id_org")
    private Integer idOrg;
    @Column(name="orgname")
    private String orgname;
    @Column(name="category")
    private String category;
    @Column(name="quantity")
    private Float quantity;
    @Column(name="lastupdated")
    private LocalDateTime lastupdated;
    @Formula("(select d.lien_depot from organisations d where d.id_dis = id_org)")
    private Integer lienDepot;

    public String getDay() {
        return day.toString();
    }


    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }

    public Integer getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(Integer idOrg) {
        this.idOrg = idOrg;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(LocalDateTime lastupdated) {
        this.lastupdated = lastupdated;
    }

    public Integer getLienDepot() {
        return lienDepot;
    }

    @Override
    public String toString() {
        return "MovementDaily{" +
                "day=" + day +
                ", bankShortName='" + bankShortName + '\'' +
                ", idOrg=" + idOrg +
                ", orgname='" + orgname + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", lastupdated=" + lastupdated +
                '}';
    }
}
