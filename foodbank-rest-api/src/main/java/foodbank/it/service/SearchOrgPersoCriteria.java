package foodbank.it.service;

public class SearchOrgPersoCriteria {
	private Integer lienAsso;
	private Integer deleted;

	private Boolean inMailing;
	public SearchOrgPersoCriteria(Integer lienAsso, Integer deleted, Boolean inMailing) {
		this.lienAsso = lienAsso;
		this.deleted = deleted;
		this.inMailing = inMailing;
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

	public Boolean getInMailing() {
		return inMailing;
	}

	public void setInMailing(Boolean inMailing) {
		this.inMailing = inMailing;
	}
}
