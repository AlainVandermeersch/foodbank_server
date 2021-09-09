package foodbank.it.service;

public class SearchNotificationCriteria {
	private String language;
	private String audience;
	public SearchNotificationCriteria(String language, String audience) {
		super();
		this.language = language;
		this.audience = audience;
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
	
}
