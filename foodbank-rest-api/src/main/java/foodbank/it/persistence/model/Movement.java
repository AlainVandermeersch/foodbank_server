package foodbank.it.persistence.model;
// Generated with g9.

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="mouvements")

public class Movement implements Serializable {

    /**
     * Primary key.
     */
    protected static final String PK = "idMouvement";

    /**
     * The optimistic lock. Available via standard bean get/set operations.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MOUVEMENT", unique = true, nullable = false, precision = 10)
    private int idMouvement;
    @Column(name = "ID_COMPANY", length = 10)
    private String idCompany;
    @Column(name = "QUANTITE", precision = 11, scale = 3)
    private BigDecimal quantite;
    @Column(name = "ID_FOURNISSEUR", length = 10)
    private String idFournisseur;
    @Column(name = "ID_ASSO", length = 10)
    private String idAsso;
    @Column(name = "EMPLACEMENT", length = 15)
    private String emplacement;
    @Column(name = "ID_PALETTE", length = 15)
    private String idPalette;
    @Column(name = "DATE")
    private LocalDate date;
    @Column(name = "TS")
    private LocalDateTime ts;
    @Column(name = "DATETIME")
    private LocalDateTime datetime;
    @Column(name = "ID_RECEPT", length = 10)
    private String idRecept;
    @Column(name = "ID_USER", length = 50)
    private String idUser;
    @Column(name = "LOT_INTERNE", length = 20)
    private String lotInterne;
    @Column(name = "LOT_FOURNISSEUR", length = 20)
    private String lotFournisseur;
    @Column(name = "ID_STOCK", precision = 10)
    private int idStock;
    @Column(name = "COMMENT", length = 60)
    private String comment;
    @Column(name = "BL", length = 20)
    private String bl;
    @Column(name = "DLD")
    private LocalDate dld;
    @Column(name = "POIDS_UNITE", precision = 7)
    private BigDecimal poidsUnite;
    @Column(name = "DESCOMP", length = 40)
    private String descomp;
    @Column(name = "PREPARATION", precision = 10)
    private int preparation;
    @Column(name = "POIDS_BRUT_UNITE", precision = 7)
    private BigDecimal poidsBrutUnite;
    @Column(name = "UCART", precision = 10)
    private int ucart;
    @Column(name = "DEMANDE", precision = 10)
    private int demande;
    @Column(name = "INFOS_COMPL", length = 20)
    private String infosCompl;

    /**
     * Default constructor.
     */
    public Movement() {
        super();
    }

    /**
     * Access method for idMouvement.
     *
     * @return the current value of idMouvement
     */
    public int getIdMouvement() {
        return idMouvement;
    }

    /**
     * Setter method for idMouvement.
     *
     * @param aIdMouvement the new value for idMouvement
     */
    public void setIdMouvement(int aIdMouvement) {
        idMouvement = aIdMouvement;
    }

    /**
     * Access method for idCompany.
     *
     * @return the current value of idCompany
     */
    public String getIdCompany() {
        return idCompany;
    }

    /**
     * Setter method for idCompany.
     *
     * @param aIdCompany the new value for idCompany
     */
    public void setIdCompany(String aIdCompany) {
        idCompany = aIdCompany;
    }

    /**
     * Access method for quantite.
     *
     * @return the current value of quantite
     */
    public BigDecimal getQuantite() {
        return quantite;
    }

    /**
     * Setter method for quantite.
     *
     * @param aQuantite the new value for quantite
     */
    public void setQuantite(BigDecimal aQuantite) {
        quantite = aQuantite;
    }

    /**
     * Access method for idFournisseur.
     *
     * @return the current value of idFournisseur
     */
    public String getIdFournisseur() {
        return idFournisseur;
    }

    /**
     * Setter method for idFournisseur.
     *
     * @param aIdFournisseur the new value for idFournisseur
     */
    public void setIdFournisseur(String aIdFournisseur) {
        idFournisseur = aIdFournisseur;
    }

    /**
     * Access method for idAsso.
     *
     * @return the current value of idAsso
     */
    public String getIdAsso() {
        return idAsso;
    }

    /**
     * Setter method for idAsso.
     *
     * @param aIdAsso the new value for idAsso
     */
    public void setIdAsso(String aIdAsso) {
        idAsso = aIdAsso;
    }

    /**
     * Access method for emplacement.
     *
     * @return the current value of emplacement
     */
    public String getEmplacement() {
        return emplacement;
    }

    /**
     * Setter method for emplacement.
     *
     * @param aEmplacement the new value for emplacement
     */
    public void setEmplacement(String aEmplacement) {
        emplacement = aEmplacement;
    }

    /**
     * Access method for idPalette.
     *
     * @return the current value of idPalette
     */
    public String getIdPalette() {
        return idPalette;
    }

    /**
     * Setter method for idPalette.
     *
     * @param aIdPalette the new value for idPalette
     */
    public void setIdPalette(String aIdPalette) {
        idPalette = aIdPalette;
    }

    /**
     * Access method for date.
     *
     * @return the current value of date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Setter method for date.
     *
     * @param aDate the new value for date
     */
    public void setDate(LocalDate aDate) {
        date = aDate;
    }

    /**
     * Access method for ts.
     *
     * @return the current value of ts
     */
    public LocalDateTime getTs() {
        return ts;
    }

    /**
     * Setter method for ts.
     *
     * @param aTs the new value for ts
     */
    public void setTs(LocalDateTime aTs) {
        ts = aTs;
    }

    /**
     * Access method for datetime.
     *
     * @return the current value of datetime
     */
    public LocalDateTime getDatetime() {
        return datetime;
    }

    /**
     * Setter method for datetime.
     *
     * @param aDatetime the new value for datetime
     */
    public void setDatetime(LocalDateTime aDatetime) {
        datetime = aDatetime;
    }

    /**
     * Access method for idRecept.
     *
     * @return the current value of idRecept
     */
    public String getIdRecept() {
        return idRecept;
    }

    /**
     * Setter method for idRecept.
     *
     * @param aIdRecept the new value for idRecept
     */
    public void setIdRecept(String aIdRecept) {
        idRecept = aIdRecept;
    }

    /**
     * Access method for idUser.
     *
     * @return the current value of idUser
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * Setter method for idUser.
     *
     * @param aIdUser the new value for idUser
     */
    public void setIdUser(String aIdUser) {
        idUser = aIdUser;
    }

    /**
     * Access method for lotInterne.
     *
     * @return the current value of lotInterne
     */
    public String getLotInterne() {
        return lotInterne;
    }

    /**
     * Setter method for lotInterne.
     *
     * @param aLotInterne the new value for lotInterne
     */
    public void setLotInterne(String aLotInterne) {
        lotInterne = aLotInterne;
    }

    /**
     * Access method for lotFournisseur.
     *
     * @return the current value of lotFournisseur
     */
    public String getLotFournisseur() {
        return lotFournisseur;
    }

    /**
     * Setter method for lotFournisseur.
     *
     * @param aLotFournisseur the new value for lotFournisseur
     */
    public void setLotFournisseur(String aLotFournisseur) {
        lotFournisseur = aLotFournisseur;
    }

    /**
     * Access method for idStock.
     *
     * @return the current value of idStock
     */
    public int getIdStock() {
        return idStock;
    }

    /**
     * Setter method for idStock.
     *
     * @param aIdStock the new value for idStock
     */
    public void setIdStock(int aIdStock) {
        idStock = aIdStock;
    }

    /**
     * Access method for comment.
     *
     * @return the current value of comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Setter method for comment.
     *
     * @param aComment the new value for comment
     */
    public void setComment(String aComment) {
        comment = aComment;
    }

    /**
     * Access method for bl.
     *
     * @return the current value of bl
     */
    public String getBl() {
        return bl;
    }

    /**
     * Setter method for bl.
     *
     * @param aBl the new value for bl
     */
    public void setBl(String aBl) {
        bl = aBl;
    }

    /**
     * Access method for dld.
     *
     * @return the current value of dld
     */
    public LocalDate getDld() {
        return dld;
    }

    /**
     * Setter method for dld.
     *
     * @param aDld the new value for dld
     */
    public void setDld(LocalDate aDld) {
        dld = aDld;
    }

    /**
     * Access method for poidsUnite.
     *
     * @return the current value of poidsUnite
     */
    public BigDecimal getPoidsUnite() {
        return poidsUnite;
    }

    /**
     * Setter method for poidsUnite.
     *
     * @param aPoidsUnite the new value for poidsUnite
     */
    public void setPoidsUnite(BigDecimal aPoidsUnite) {
        poidsUnite = aPoidsUnite;
    }

    /**
     * Access method for descomp.
     *
     * @return the current value of descomp
     */
    public String getDescomp() {
        return descomp;
    }

    /**
     * Setter method for descomp.
     *
     * @param aDescomp the new value for descomp
     */
    public void setDescomp(String aDescomp) {
        descomp = aDescomp;
    }

    /**
     * Access method for preparation.
     *
     * @return the current value of preparation
     */
    public int getPreparation() {
        return preparation;
    }

    /**
     * Setter method for preparation.
     *
     * @param aPreparation the new value for preparation
     */
    public void setPreparation(int aPreparation) {
        preparation = aPreparation;
    }

    /**
     * Access method for poidsBrutUnite.
     *
     * @return the current value of poidsBrutUnite
     */
    public BigDecimal getPoidsBrutUnite() {
        return poidsBrutUnite;
    }

    /**
     * Setter method for poidsBrutUnite.
     *
     * @param aPoidsBrutUnite the new value for poidsBrutUnite
     */
    public void setPoidsBrutUnite(BigDecimal aPoidsBrutUnite) {
        poidsBrutUnite = aPoidsBrutUnite;
    }

    /**
     * Access method for ucart.
     *
     * @return the current value of ucart
     */
    public int getUcart() {
        return ucart;
    }

    /**
     * Setter method for ucart.
     *
     * @param aUcart the new value for ucart
     */
    public void setUcart(int aUcart) {
        ucart = aUcart;
    }

    /**
     * Access method for demande.
     *
     * @return the current value of demande
     */
    public int getDemande() {
        return demande;
    }

    /**
     * Setter method for demande.
     *
     * @param aDemande the new value for demande
     */
    public void setDemande(int aDemande) {
        demande = aDemande;
    }

    /**
     * Access method for infosCompl.
     *
     * @return the current value of infosCompl
     */
    public String getInfosCompl() {
        return infosCompl;
    }

    /**
     * Setter method for infosCompl.
     *
     * @param aInfosCompl the new value for infosCompl
     */
    public void setInfosCompl(String aInfosCompl) {
        infosCompl = aInfosCompl;
    }


}
