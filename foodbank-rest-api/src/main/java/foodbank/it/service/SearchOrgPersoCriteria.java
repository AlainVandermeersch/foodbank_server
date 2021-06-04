package foodbank.it.service;

public class SearchOrgPersoCriteria {
	private Integer lienAsso;
	private Integer deleted;
	public SearchOrgPersoCriteria(Integer lienAsso, Integer deleted) {
		this.lienAsso = lienAsso;
		this.deleted = deleted;
	}
	
	
	public Integer getLienAsso() {
		return lienAsso;
	}
	public void setLienAsso(Integer lienAsso) {
		this.lienAsso = lienAsso;
	}
	public Integer isDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}
