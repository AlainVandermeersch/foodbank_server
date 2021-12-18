package foodbank.it.service;

public class SearchAuditAssocCriteria {
	private Integer lienBanque;
	private Integer lienDep;
	
	public SearchAuditAssocCriteria( Integer lienBanque, Integer lienDep) {
		super();
		this.lienBanque = lienBanque;
		this.lienDep = lienDep;
	}
	
	public Integer getLienBanque() {
		return lienBanque;
	}
	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}

	public Integer getLienDep() {
		return lienDep;
	}

	public void setLienDep(Integer lienDep) {
		this.lienDep = lienDep;
	}
}
