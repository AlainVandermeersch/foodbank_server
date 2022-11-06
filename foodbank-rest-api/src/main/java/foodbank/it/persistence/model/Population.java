// Generated with g9.

package foodbank.it.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name="population")
public class Population implements Serializable {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="pop_id", unique=true, nullable=false, precision=10)
    private int popId;
    @Column(name="date_stat", nullable=false)
    private LocalDate dateStat;
    @Column(name="id_dis", precision=10)
    private int idDis;
    @Column(name="n_fam", precision=10)
    private int nFam;
    @Column(name="n_pers", precision=10)
    private int nPers;
    @Column(name="n_nour", precision=10)
    private int nNour;
    @Column(name="n_bebe", precision=10)
    private int nBebe;
    @Column(name="n_enf", precision=10)
    private int nEnf;
    @Column(name="n_ado", precision=10)
    private int nAdo;
    @Column(name="n_18_24", precision=10)
    private int n1824;
    @Column(name="n_eq", length=12)
    private String nEq;
    @Column(name="n_sen", precision=10)
    private int nSen;
    @Column(name="lien_dep", precision=10)
    private int lienDep;
    @Column(name="lien_banque", precision=10)
    private int lienBanque;
    @Column(name="date_stamp")
    private LocalDateTime dateStamp;

    /** Default constructor. */
    public Population() {
        super();
    }

    public Population(int popId, LocalDate dateStat, int idDis, int nFam, int nPers, int nNour, int nBebe, int nEnf,
			int nAdo, int n1824, String nEq, int nSen, int lienDep, int lienBanque, LocalDateTime dateStamp) {
		super();
		this.popId = popId;
		this.dateStat = dateStat;
		this.idDis = idDis;
		this.nFam = nFam;
		this.nPers = nPers;
		this.nNour = nNour;
		this.nBebe = nBebe;
		this.nEnf = nEnf;
		this.nAdo = nAdo;
		this.n1824 = n1824;
		this.nEq = nEq;
		this.nSen = nSen;
		this.lienDep = lienDep;
		this.lienBanque = lienBanque;
		this.dateStamp = dateStamp;
	}

	/**
     * Access method for popId.
     *
     * @return the current value of popId
     */
    public int getPopId() {
        return popId;
    }

    /**
     * Setter method for popId.
     *
     * @param aPopId the new value for popId
     */
    public void setPopId(int aPopId) {
        popId = aPopId;
    }

    /**
     * Access method for dateStat.
     *
     * @return the current value of dateStat
     */
    public LocalDate getDateStat() {
        return dateStat;
    }

    /**
     * Setter method for dateStat.
     *
     * @param aDateStat the new value for dateStat
     */
    public void setDateStat(LocalDate aDateStat) {
        dateStat = aDateStat;
    }

    /**
     * Access method for idDis.
     *
     * @return the current value of idDis
     */
    public int getIdDis() {
        return idDis;
    }

    /**
     * Setter method for idDis.
     *
     * @param aIdDis the new value for idDis
     */
    public void setIdDis(int aIdDis) {
        idDis = aIdDis;
    }

    /**
     * Access method for nFam.
     *
     * @return the current value of nFam
     */
    public int getNFam() {
        return nFam;
    }

    /**
     * Setter method for nFam.
     *
     * @param aNFam the new value for nFam
     */
    public void setNFam(int aNFam) {
        nFam = aNFam;
    }

    /**
     * Access method for nPers.
     *
     * @return the current value of nPers
     */
    public int getNPers() {
        return nPers;
    }

    /**
     * Setter method for nPers.
     *
     * @param aNPers the new value for nPers
     */
    public void setNPers(int aNPers) {
        nPers = aNPers;
    }

    /**
     * Access method for nNour.
     *
     * @return the current value of nNour
     */
    public int getNNour() {
        return nNour;
    }

    /**
     * Setter method for nNour.
     *
     * @param aNNour the new value for nNour
     */
    public void setNNour(int aNNour) {
        nNour = aNNour;
    }

    /**
     * Access method for nBebe.
     *
     * @return the current value of nBebe
     */
    public int getNBebe() {
        return nBebe;
    }

    /**
     * Setter method for nBebe.
     *
     * @param aNBebe the new value for nBebe
     */
    public void setNBebe(int aNBebe) {
        nBebe = aNBebe;
    }

    /**
     * Access method for nEnf.
     *
     * @return the current value of nEnf
     */
    public int getNEnf() {
        return nEnf;
    }

    /**
     * Setter method for nEnf.
     *
     * @param aNEnf the new value for nEnf
     */
    public void setNEnf(int aNEnf) {
        nEnf = aNEnf;
    }

    /**
     * Access method for nAdo.
     *
     * @return the current value of nAdo
     */
    public int getNAdo() {
        return nAdo;
    }

    /**
     * Setter method for nAdo.
     *
     * @param aNAdo the new value for nAdo
     */
    public void setNAdo(int aNAdo) {
        nAdo = aNAdo;
    }

    public int getN1824() {
		return n1824;
	}

	public void setN1824(int n1824) {
		this.n1824 = n1824;
	}

	/**
     * Access method for nEq.
     *
     * @return the current value of nEq
     */
    public String getNEq() {
        return nEq;
    }

    /**
     * Setter method for nEq.
     *
     * @param aNEq the new value for nEq
     */
    public void setNEq(String aNEq) {
        nEq = aNEq;
    }

    /**
     * Access method for nSen.
     *
     * @return the current value of nSen
     */
    public int getNSen() {
        return nSen;
    }

    /**
     * Setter method for nSen.
     *
     * @param aNSen the new value for nSen
     */
    public void setNSen(int aNSen) {
        nSen = aNSen;
    }

    /**
     * Access method for lienDep.
     *
     * @return the current value of lienDep
     */
    public int getLienDep() {
        return lienDep;
    }

    /**
     * Setter method for lienDep.
     *
     * @param aLienDep the new value for lienDep
     */
    public void setLienDep(int aLienDep) {
        lienDep = aLienDep;
    }

    /**
     * Access method for lienBanque.
     *
     * @return the current value of lienBanque
     */
    public int getLienBanque() {
        return lienBanque;
    }

    /**
     * Setter method for lienBanque.
     *
     * @param aLienBanque the new value for lienBanque
     */
    public void setLienBanque(int aLienBanque) {
        lienBanque = aLienBanque;
    }

    /**
     * Access method for dateStamp.
     *
     * @return the current value of dateStamp
     */
    public LocalDateTime getDateStamp() {
        return dateStamp;
    }

    /**
     * Setter method for dateStamp.
     *
     * @param aDateStamp the new value for dateStamp
     */
    public void setDateStamp(LocalDateTime aDateStamp) {
        dateStamp = aDateStamp;
    }

}