package foodbank.it.service;

public class SearchClientCriteria {

	private String searchField;
	private String searchValue;
	private String bankShortName;
	private Integer lienDis;

	public SearchClientCriteria(String searchField, String searchValue, String bankShortName, Integer lienDis) {
		this.searchField = searchField;
		this.searchValue = searchValue;
		this.bankShortName = bankShortName;
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

	public String getBankShortName() {
		return bankShortName;
	}

	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}

	public Integer getLienDis() {
		return lienDis;
	}

	public void setLienDis(Integer lienDis) {
		this.lienDis = lienDis;
	}
}
