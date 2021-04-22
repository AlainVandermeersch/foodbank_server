package foodbank.it.service;

public class SearchOrganisationCriteria {
	private String searchField;
	private String searchValue;
	private Integer lienBanque;
	private Integer idDis;
	
	
	public SearchOrganisationCriteria(String searchField, String searchValue, Integer lienBanque, Integer lienDis) {
		this.searchField = searchField;
		this.searchValue = searchValue;
		this.lienBanque = lienBanque;
		this.idDis = lienDis;
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

	public Integer getIdDis() {
		return idDis;
	}

	public void setIdDis(Integer idDis) {
		this.idDis = idDis;
	}

	
}
