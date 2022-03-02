package foodbank.it.web.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditDto {

	private int auditId;
	   
    private String user;
   
    private String dateIn;
    
    private String ipAddress;
    
    private Integer idDis;
    private String application;
    
    private String societe;
  
    private String bankShortName;
    private String userName;
    private String rights;
    
    private Long  totalRecords;

	public AuditDto(int auditId, String user, LocalDateTime dateIn, String ipAddress, Integer idDis, String application,
			String societe,	String bankShortName,String userName, String rights, Long  totalRecords) {
		super();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.auditId = auditId;
		this.user = user;
		if (dateIn == null) {
			this.dateIn = "";
		}
		else {	
			this.dateIn = dateIn.plusHours(1).format(formatter);
		}
		this.ipAddress = ipAddress;
		this.idDis = idDis;
		if (application == null) {
			this.application = "PHP";
		}
		else {
			this.application = application;
		}
		this.societe = societe;
		this.bankShortName = bankShortName;
		this.userName = userName;
		this.rights = rights;
		this.totalRecords = totalRecords;
	}

	public int getAuditId() {
		return auditId;
	}

	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDateIn() {
		return dateIn;
	}

	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Integer getIdDis() {
		return idDis;
	}

	public void setIdDis(Integer idDis) {
		this.idDis = idDis;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	
	public String getBankShortName() {
		return bankShortName;
	}

	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
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

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	
    
}
