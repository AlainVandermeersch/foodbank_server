// Generated with g9.

package foodbank.it.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="function")
public class Function implements Serializable {
    /** Primary key. */
    protected static final String PK = "funcId";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="func_ID", unique=true, nullable=false, precision=10)
    private int funcId;
    @Column(name="fonction_Name", length=50)
    private String fonctionName;
    @Column(name="fonction_name_nl", length=50)
    private String fonctionNameNl;
    @Column(name="definition_fonction")
    private String definitionFonction;
    @Column(precision=3)
    private short actif;
    private String rem;
    @Column(name="lien_banque", precision=3)
    private Short lienBanque;
    @Column(name="lien_dis", precision=10)
    private Integer lienDis;

    public Function(int funcId, String fonctionName, String fonctionNameNl, String definitionFonction, short actif, String rem, Short lienBanque, Integer lienDis) {
        this.funcId = funcId;
        this.fonctionName = fonctionName;
        this.fonctionNameNl = fonctionNameNl;
        this.definitionFonction = definitionFonction;
        this.actif = actif;
        this.rem = rem;
        this.lienBanque = lienBanque;
        this.lienDis = lienDis;
    }

    public Function() {
        super();
    }

    /**
     * Access method for funcId.
     *
     * @return the current value of funcId
     */
    public int getFuncId() {
        return funcId;
    }

    /**
     * Setter method for funcId.
     *
     * @param aFuncId the new value for funcId
     */
    public void setFuncId(int aFuncId) {
        funcId = aFuncId;
    }

    /**
     * Access method for fonctionName.
     *
     * @return the current value of fonctionName
     */
    public String getFonctionName() {
        return fonctionName;
    }

    /**
     * Setter method for fonctionName.
     *
     * @param aFonctionName the new value for fonctionName
     */
    public void setFonctionName(String aFonctionName) {
        fonctionName = aFonctionName;
    }

    /**
     * Access method for fonctionNameNl.
     *
     * @return the current value of fonctionNameNl
     */
    public String getFonctionNameNl() {
        return fonctionNameNl;
    }

    /**
     * Setter method for fonctionNameNl.
     *
     * @param aFonctionNameNl the new value for fonctionNameNl
     */
    public void setFonctionNameNl(String aFonctionNameNl) {
        fonctionNameNl = aFonctionNameNl;
    }

    /**
     * Access method for definitionFonction.
     *
     * @return the current value of definitionFonction
     */
    public String getDefinitionFonction() {
        return definitionFonction;
    }

    /**
     * Setter method for definitionFonction.
     *
     * @param aDefinitionFonction the new value for definitionFonction
     */
    public void setDefinitionFonction(String aDefinitionFonction) {
        definitionFonction = aDefinitionFonction;
    }

    /**
     * Access method for actif.
     *
     * @return the current value of actif
     */
    public short getActif() {
        return actif;
    }

    /**
     * Setter method for actif.
     *
     * @param aActif the new value for actif
     */
    public void setActif(short aActif) {
        actif = aActif;
    }

    /**
     * Access method for rem.
     *
     * @return the current value of rem
     */
    public String getRem() {
        return rem;
    }

    /**
     * Setter method for rem.
     *
     * @param aRem the new value for rem
     */
    public void setRem(String aRem) {
        rem = aRem;
    }

    public Short getLienBanque() {
        return lienBanque;
    }

    public void setLienBanque(Short lienBanque) {
        this.lienBanque = lienBanque;
    }

    public Integer getLienDis() {
        return lienDis;
    }

    public void setLienDis(Integer lienDis) {
        this.lienDis = lienDis;
    }
}