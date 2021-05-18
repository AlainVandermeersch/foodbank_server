package foodbank.it.service;

public class SearchClientCriteria {

	private String searchField;
	private String searchValue;
	private String bankShortName;
	private Integer lienDis;
	private Integer actif;

	public SearchClientCriteria(String searchField, String searchValue, String bankShortName, Integer lienDis, Integer actif) {
		this.searchField = searchField;
		this.searchValue = searchValue;
		this.bankShortName = bankShortName;
		this.lienDis = lienDis;
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

	public Integer getActif() {
		return actif;
	}

	public void setActif(Integer actif) {
		this.actif = actif;
	}
	
}
