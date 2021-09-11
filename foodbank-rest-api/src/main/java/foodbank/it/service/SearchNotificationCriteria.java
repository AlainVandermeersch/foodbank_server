package foodbank.it.service;

public class SearchNotificationCriteria {
	private String language;
	private String audience;
	private Integer bankId;
	private Integer orgId;
	public SearchNotificationCriteria(String language, String audience, Integer bankId, Integer orgId) {
		super();
		this.language = language;
		this.audience = audience;
		this.bankId = bankId;
		this.orgId = orgId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getAudience() {
		return audience;
	}
	public void setAudience(String audience) {
		this.audience = audience;
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
