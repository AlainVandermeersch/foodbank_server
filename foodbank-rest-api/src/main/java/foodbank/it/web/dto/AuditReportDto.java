package foodbank.it.web.dto;

public class AuditReportDto {
  private String key; 
  private Long loginCount;
  

public AuditReportDto(String key,  Long loginCount) {
	this.key = key;
	this.loginCount = loginCount;
}


public String getKey() {
	return key;
}


public void setKey(String key) {
	this.key = key;
}


public Long getLoginCount() {
	return loginCount;
}
public void setLoginCount(Long loginCount) {
	this.loginCount = loginCount;
}


  
  
}
