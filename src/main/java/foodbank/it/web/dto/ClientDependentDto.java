package foodbank.it.web.dto;

import javax.persistence.Column;

public class ClientDependentDto {
	private int idDep;
	private int lienDis;
	private int lienMast;
	private String prenom;
	private String nom;
	private String datenais;
	private boolean depTyp;
	private boolean actif;
	private boolean deleted;
	private Short lienBanque;
	private Short regio;
	private Short civilite;
	private int eq;
	private Long  totalRecords;
	public ClientDependentDto() {
		super();
	}
	public ClientDependentDto(int idDep, int lienDis, int lienMast, String prenom, String nom, String datenais,
			boolean depTyp, boolean actif, boolean deleted, Short lienBanque, Short regio, Short civilite, int eq, Long  totalRecords) {
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
		this.totalRecords = totalRecords;
	}
	public int getIdDep() {
		return idDep;
	}
	public void setIdDep(int idDep) {
		this.idDep = idDep;
	}
	public int getLienDis() {
		return lienDis;
	}
	public void setLienDis(int lienDis) {
		this.lienDis = lienDis;
	}
	public int getLienMast() {
		return lienMast;
	}
	public void setLienMast(int lienMast) {
		this.lienMast = lienMast;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDatenais() {
		return datenais;
	}
	public void setDatenais(String datenais) {
		this.datenais = datenais;
	}
	public boolean isDepTyp() {
		return depTyp;
	}
	public void setDepTyp(boolean depTyp) {
		this.depTyp = depTyp;
	}
	public boolean isActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public Short getLienBanque() {
		return lienBanque;
	}
	public void setLienBanque(Short lienBanque) {
		this.lienBanque = lienBanque;
	}
	public Short getRegio() {
		return regio;
	}
	public void setRegio(Short regio) {
		this.regio = regio;
	}
	public Short getCivilite() {
		return civilite;
	}
	public void setCivilite(Short civilite) {
		this.civilite = civilite;
	}
	public int getEq() {
		return eq;
	}
	public void setEq(int eq) {
		this.eq = eq;
	}
	public Long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	

}
