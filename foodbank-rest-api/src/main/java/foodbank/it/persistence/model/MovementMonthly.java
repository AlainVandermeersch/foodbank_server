package foodbank.it.persistence.model;

import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity(name="movements_monthly")

public class MovementMonthly implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="month")
    private int month;
    @Id
    @Column(name="bank_short_name")
    private String bankShortName;
    @Id
    @Column(name="id_org")
    private int idOrg;
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
    @Formula("(select d.n_fam from organisations d where d.id_dis = id_org)")
    private Long nfamilies;
    @Formula("(select d.n_pers from organisations d where d.id_dis = id_org)")
    private Long npersons;


    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }

    public int getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(int idOrg) {
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

    public void setLienDepot(Integer lienDepot) {
        this.lienDepot = lienDepot;
    }

    public Long getNfamilies() {
        return nfamilies;
    }

    public void setNfamilies(Long nfamilies) {
        this.nfamilies = nfamilies;
    }

    public Long getNpersons() {
        return npersons;
    }

    public void setNpersons(Long npersons) {
        this.npersons = npersons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovementMonthly that = (MovementMonthly) o;
        return month == that.month && idOrg == that.idOrg &&  Objects.equals(bankShortName, that.bankShortName) && Objects.equals(orgname, that.orgname) && Objects.equals(category, that.category) && Objects.equals(quantity, that.quantity) && Objects.equals(lastupdated, that.lastupdated) && Objects.equals(lienDepot, that.lienDepot) && Objects.equals(nfamilies, that.nfamilies) && Objects.equals(npersons, that.npersons);
    }

    @Override
    public int hashCode() {
        return Objects.hash( month, bankShortName, idOrg, orgname, category, quantity, lastupdated, lienDepot, nfamilies, npersons);
    }
}
