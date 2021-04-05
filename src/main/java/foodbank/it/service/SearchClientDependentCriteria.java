package foodbank.it.service;

public class SearchClientDependentCriteria {
	
	private String searchField;
	private String searchValue;
	private Integer lienDis;
	private Integer lienMast;
	private Integer actif;
	public SearchClientDependentCriteria(Integer lienMast, Integer actif) {
		this.lienMast = lienMast;
		this.actif = actif;
	}
	
	
	public Integer getLienMast() {
		return lienMast;
	}
	public void setLienMast(Integer lienMast) {
		this.lienMast = lienMast;
	}
	public Integer isActif() {
		return actif;
	}
	public void setActif(Integer actif) {
		this.actif = actif;
	}
	
}
