package foodbank.it.service;

public class SearchNotificationCriteria {
	private String language;
	private String admin;
	private Integer bankId;
	private Integer orgId;
	public SearchNotificationCriteria(String language, String audience, Integer bankId, Integer orgId) {
		super();
		this.language = language;
		this.admin = audience;
		this.bankId = bankId;
		this.orgId = orgId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public Integer getBankId() {
		return bankId;
	}
	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
}
