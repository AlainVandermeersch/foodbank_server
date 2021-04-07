package foodbank.it.service;

public class SearchTUserCriteria {
	
	private String searchField;
	private String searchValue;
	private Integer lienBanque;
	private Integer idOrg;
	
	public SearchTUserCriteria(String searchField, String searchValue, Integer lienBanque, Integer idOrg) {
		this.searchField = searchField;
		this.searchValue = searchValue;
		this.lienBanque = lienBanque;
		this.idOrg = idOrg;
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

	public Integer getIdOrg() {
		return idOrg;
	}

	public void setIdOrg(Integer idOrg) {
		this.idOrg = idOrg;
	}
	
}