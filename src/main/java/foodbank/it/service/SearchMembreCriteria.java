package foodbank.it.service;

public class SearchMembreCriteria {
	private String searchField;
	private String searchValue;
	private Integer lienBanque;
	private Integer lienDis;
	
	
	public SearchMembreCriteria(String searchField, String searchValue, Integer lienBanque, Integer lienDis) {
		this.searchField = searchField;
		this.searchValue = searchValue;
		this.lienBanque = lienBanque;
		this.lienDis = lienDis;
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

	public Integer getLienBanque() {
		return lienBanque;
	}

	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}

	public Integer getLienDis() {
		return lienDis;
	}

	public void setLienDis(Integer lienDis) {
		this.lienDis = lienDis;
	}

	
}
