package foodbank.it.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity(name="dep")
public class ClientDependent implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6947907584472050582L;

	/** Primary key. */
	 protected static final String PK = "idDep";
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_dep", unique=true, nullable=false, precision=10)
	private int idDep;
	@Column(name="lien_dis", nullable=false, precision=10)
	private int lienDis;
	@Column(name="lien_mast", nullable=false, precision=10)
	private int lienMast;
	@Column(nullable=false, length=25)
	private String prenom;
	@Column(nullable=false, length=20)
	private String nom;
	@Column(nullable=false)
	private String datenais;
	@Column(name="dep_typ", nullable=false, length=3)
	private Short depTyp;
	@Column(nullable=false, length=3)
	private boolean actif;
	@Column(nullable=false, length=3)
	private boolean deleted;
	@Column(name="lien_banque", precision=3)
	private Short lienBanque;
	@Column(precision=3)
	private Short regio;
	@Column(precision=5)
	private Short civilite;
	@Column(nullable=false, precision=10)
	private int eq;

	/** Default constructor. */
	public ClientDependent() {
		super();
	}

	public ClientDependent(int idDep, int lienDis, int lienMast, String prenom, String nom, String datenais,
			Short depTyp, boolean actif, boolean deleted, Short lienBanque, Short regio, Short civilite, int eq) {
		super();
		this.idDep = idDep;
		this.lienDis = lienDis;
		this.lienMast = lienMast;
		this.prenom = prenom;
		this.nom = nom;
		this.datenais = datenais;
		this.depTyp = depTyp;
		this.actif = actif;
		this.deleted = deleted;
		this.lienBanque = lienBanque;
		this.regio = regio;
		this.civilite = civilite;
		this.eq = eq;
	}

	/**
	 * Access method for idDep.
	 *
	 * @return the current value of idDep
	 */
	public int getIdDep() {
		return idDep;
	}

	/**
	 * Setter method for idDep.
	 *
	 * @param aIdDep the new value for idDep
	 */
	public void setIdDep(int aIdDep) {
		idDep = aIdDep;
	}

	/**
	 * Access method for lienDis.
	 *
	 * @return the current value of lienDis
	 */
	public int getLienDis() {
		return lienDis;
	}

	/**
	 * Setter method for lienDis.
	 *
	 * @param aLienDis the new value for lienDis
	 */
	public void setLienDis(int aLienDis) {
		lienDis = aLienDis;
	}

	/**
	 * Access method for lienMast.
	 *
	 * @return the current value of lienMast
	 */
	public int getLienMast() {
		return lienMast;
	}

	/**
	 * Setter method for lienMast.
	 *
	 * @param aLienMast the new value for lienMast
	 */
	public void setLienMast(int aLienMast) {
		lienMast = aLienMast;
	}

	/**
	 * Access method for prenom.
	 *
	 * @return the current value of prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Setter method for prenom.
	 *
	 * @param aPrenom the new value for prenom
	 */
	public void setPrenom(String aPrenom) {
		prenom = aPrenom;
	}

	/**
	 * Access method for nom.
	 *
	 * @return the current value of nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter method for nom.
	 *
	 * @param aNom the new value for nom
	 */
	public void setNom(String aNom) {
		nom = aNom;
	}

	/**
	 * Access method for datenais.
	 *
	 * @return the current value of datenais
	 */
	public String getDatenais() {
		return datenais;
	}

	/**
	 * Setter method for datenais.
	 *
	 * @param aDatenais the new value for datenais
	 */
	public void setDatenais(String aDatenais) {
		datenais = aDatenais;
	}

	/**
	 * Access method for depTyp.
	 *
	 * @return true if and only if depTyp is currently true
	 */
	public Short getDepTyp() {
		return depTyp;
	}

	/**
	 * Setter method for depTyp.
	 *
	 * @param aDepTyp the new value for depTyp
	 */
	public void setDepTyp(Short aDepTyp) {
		depTyp = aDepTyp;
	}

	/**
	 * Access method for actif.
	 *
	 * @return true if and only if actif is currently true
	 */
	public boolean getActif() {
		return actif;
	}

	/**
	 * Setter method for actif.
	 *
	 * @param aActif the new value for actif
	 */
	public void setActif(boolean aActif) {
		actif = aActif;
	}

	/**
	 * Access method for deleted.
	 *
	 * @return true if and only if deleted is currently true
	 */
	public boolean getDeleted() {
		return deleted;
	}

	/**
	 * Setter method for deleted.
	 *
	 * @param aDeleted the new value for deleted
	 */
	public void setDeleted(boolean aDeleted) {
		deleted = aDeleted;
	}

	/**
	 * Access method for lienBanque.
	 *
	 * @return the current value of lienBanque
	 */
	public Short getLienBanque() {
		return lienBanque;
	}

	/**
	 * Setter method for lienBanque.
	 *
	 * @param aLienBanque the new value for lienBanque
	 */
	public void setLienBanque(Short aLienBanque) {
		lienBanque = aLienBanque;
	}

	/**
	 * Access method for regio.
	 *
	 * @return the current value of regio
	 */
	public Short getRegio() {
		return regio;
	}

	/**
	 * Setter method for regio.
	 *
	 * @param aRegio the new value for regio
	 */
	public void setRegio(Short aRegio) {
		regio = aRegio;
	}

	/**
	 * Access method for civilite.
	 *
	 * @return the current value of civilite
	 */
	public Short getCivilite() {
		return civilite;
	}

	/**
	 * Setter method for civilite.
	 *
	 * @param aCivilite the new value for civilite
	 */
	public void setCivilite(Short aCivilite) {
		civilite = aCivilite;
	}

	/**
	 * Access method for eq.
	 *
	 * @return the current value of eq
	 */
	public int getEq() {
		return eq;
	}

	/**
	 * Setter method for eq.
	 *
	 * @param aEq the new value for eq
	 */
	public void setEq(int aEq) {
		eq = aEq;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (actif ? 1231 : 1237);
		result = prime * result + civilite;
		result = prime * result + ((datenais == null) ? 0 : datenais.hashCode());
		result = prime * result + (deleted ? 1231 : 1237);
		result = prime * result + depTyp;
		result = prime * result + eq;
		result = prime * result + idDep;
		result = prime * result + lienBanque;
		result = prime * result + lienDis;
		result = prime * result + lienMast;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + regio;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientDependent other = (ClientDependent) obj;
		if (actif != other.actif)
			return false;
		if (civilite != other.civilite)
			return false;
		if (datenais == null) {
			if (other.datenais != null)
				return false;
		} else if (!datenais.equals(other.datenais))
			return false;
		if (deleted != other.deleted)
			return false;
		if (depTyp != other.depTyp)
			return false;
		if (eq != other.eq)
			return false;
		if (idDep != other.idDep)
			return false;
		if (lienBanque != other.lienBanque)
			return false;
		if (lienDis != other.lienDis)
			return false;
		if (lienMast != other.lienMast)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (regio != other.regio)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClientDependent [idDep=" + idDep + ", lienDis=" + lienDis + ", lienMast=" + lienMast + ", prenom="
				+ prenom + ", nom=" + nom + ", datenais=" + datenais + ", depTyp=" + depTyp + ", actif=" + actif
				+ ", deleted=" + deleted + ", lienBanque=" + lienBanque + ", regio=" + regio + ", civilite=" + civilite
				+ ", eq=" + eq + "]";
	}

}