package foodbank.it.web.dto;

public class AuditReportDto {
  private String key; 
  private String application;
  private Long loginCount;
  

public AuditReportDto(String key,String application,  Long loginCount) {
	this.key = key;
	this.application = application;
	this.loginCount = loginCount;
}


public String getKey() {
	return key;
}


public void setKey(String key) {
	this.key = key;
}


public String getApplication() {
	return application;
}


public void setApplication(String application) {
	this.application = application;
}


public Long getLoginCount() {
	return loginCount;
}
public void setLoginCount(Long loginCount) {
	this.loginCount = loginCount;
}


  
  
}
