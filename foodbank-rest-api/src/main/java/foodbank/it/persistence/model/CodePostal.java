package foodbank.it.persistence.model;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name="codepostal")
public class CodePostal implements Serializable {
    protected static final String PK = "zipCode";
    @Id
    @Column(name="cod_id", unique=true, nullable=false)
    private int zipCode;
    @Column(name="com_name", nullable=false, length=30)
    private String city;
    @Column(name="l_cpas", nullable=false)
    private int lcpas;

    @Formula("(select b.cpas_zip from cpas b where b.cpas_ID = l_cpas)")
    private Short zipCodeCpas;
    @Formula("(select b.l_banque from cpas b where b.cpas_ID = l_cpas)")
    private Integer lienBanque;
    @Formula("(select b.cpas_name from cpas b where b.cpas_ID = l_cpas)")
    private String cityCpas;
    @Formula("(select b.cpas_mail from cpas b where b.cpas_ID = l_cpas)")
    private String mailCpas;
    @Formula("(select b.rem from cpas b where b.cpas_ID = l_cpas)")
    private String remCpas;

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLcpas() {
        return lcpas;
    }

    public void setLcpas(int lcpas) {
        this.lcpas = lcpas;
    }

    public Integer getLienBanque() {
        return lienBanque;
    }

    public Short getZipCodeCpas() {
        return zipCodeCpas;
    }

    public String getCityCpas() {
        return cityCpas;
    }

    public String getMailCpas() {
        return mailCpas;
    }

    public String getRemCpas() {
        return remCpas;
    }

    @Override
    public String toString() {
        return "CodePostal{" +
                "zipCode=" + zipCode +
                ", city='" + city + '\'' +
                ", l_Cpas=" + lcpas +
                ", zipCodeCpas=" + zipCodeCpas +
                ", cityCpas='" + cityCpas + '\'' +
                ", mailCpas='" + mailCpas + '\'' +
                ", remCpas='" + remCpas + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodePostal that = (CodePostal) o;
        return zipCode == that.zipCode && lcpas == that.lcpas && city.equals(that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, city, lcpas);
    }
}
