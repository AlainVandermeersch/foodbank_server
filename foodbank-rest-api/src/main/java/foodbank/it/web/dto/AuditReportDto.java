package foodbank.it.web.dto;

public class AuditReportDto {
  private String key; 
  private String category;
  private Long loginCount;
  

public AuditReportDto(String key,String category,  Long loginCount) {
	this.key = key;
	this.category = category;
	this.loginCount = loginCount;
}


public String getKey() {
	return key;
}


public void setKey(String key) {
	this.key = key;
}


public String getCategory() {
	return category;
}


public void setCategory(String category) {
	this.category = category;
}


public Long getLoginCount() {
	return loginCount;
}
public void setLoginCount(Long loginCount) {
	this.loginCount = loginCount;
}


  
  
}
