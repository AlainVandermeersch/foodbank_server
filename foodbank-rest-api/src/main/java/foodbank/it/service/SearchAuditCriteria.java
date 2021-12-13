package foodbank.it.service;

public class SearchAuditCriteria {
	
	private String user;
	private String shortBankName;         
    private Integer idDis;    
    private String societe;
    private String userName;
    private Boolean isJavaApp;
	public SearchAuditCriteria(String user, String shortBankName, Integer idDis, String societe, String userName, Boolean isJavaApp) {
		super();
		this.user = user;
		this.shortBankName = shortBankName;
		this.idDis = idDis;
		this.societe = societe;
		this.userName = userName;
		this.isJavaApp = isJavaApp;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public Integer getIdDis() {
		return idDis;
	}
	public void setIdDis(Integer idDis) {
		this.idDis = idDis;
	}
	public String getSociete() {
		return societe;
	}
	public void setSociete(String societe) {
		this.societe = societe;
	}
	public String getShortBankName() {
		return shortBankName;
	}
	public void setShortBankName(String shortBankName) {
		this.shortBankName = shortBankName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Boolean getIsJavaApp() {
		return isJavaApp;
	}
	public void setIsJavaApp(Boolean isJavaApp) {
		this.isJavaApp = isJavaApp;
	}
	
	
  
   

}
