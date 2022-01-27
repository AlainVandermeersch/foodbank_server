package foodbank.it.web.dto;

public class AuditReportDto {
  private String date; 
  private String application;
  private String shortBankName;
  private String societe;
  private String userName;
  private String rights;
  private Long loginCount;
  
public AuditReportDto(String date, String application, String shortBankName, String societe, String userName,
		String rights, Long loginCount) {
	super();
	this.date = date;
	this.application = application;
	this.shortBankName = shortBankName;
	this.societe = societe;
	this.userName = userName;
	this.rights = rights;
	this.loginCount = loginCount;
}
public AuditReportDto(String societe,  Long loginCount) {
	this.societe = societe;
	this.loginCount = loginCount;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public String getApplication() {
	return application;
}

public void setApplication(String application) {
	this.application = application;
}

public String getShortBankName() {
	return shortBankName;
}

public void setShortBankName(String shortBankName) {
	this.shortBankName = shortBankName;
}

public String getSociete() {
	return societe;
}

public void setSociete(String societe) {
	this.societe = societe;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getRights() {
	return rights;
}

public void setRights(String rights) {
	this.rights = rights;
}
public Long getLoginCount() {
	return loginCount;
}
public void setLoginCount(Long loginCount) {
	this.loginCount = loginCount;
}


  
  
}
