package foodbank.it.service;

public class SearchClientDependentCriteria {
	
	private String searchField;
	private String searchValue;
	private Integer lienDis;
	private Integer lienMast;
	private Integer actif;
	public SearchClientDependentCriteria(String searchField, String searchValue,Integer lienDis, Integer lienMast, Integer actif) {
		this.searchField = searchField;
		this.searchValue = searchValue;
		this.lienDis = lienDis;
		this.lienMast = lienMast;
		this.actif = actif;
	}
	
	
	public String getSearchField() {
		return searchField;
	}


	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}


	public String getSearchValue() {
		return searchValue;
	}


	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}


	public Integer getLienDis() {
		return lienDis;
	}
	public void setLienDis(Integer lienDis) {
		this.lienDis = lienDis;
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
