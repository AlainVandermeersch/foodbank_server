package foodbank.it.web.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditDto {

	private int auditId;
	   
    private String user;
   
    private String dateIn;
    
    private String ipAddress;
    
    private Integer idDis;
    
    private String societe;
  
    private Integer lienBanque;
    
    private Long  totalRecords;

	public AuditDto(int auditId, String user, LocalDateTime dateIn, String ipAddress, Integer idDis, String societe,
			Integer lienBanque,Long  totalRecords) {
		super();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.auditId = auditId;
		this.user = user;
		if (dateIn == null) {
			this.dateIn = "";
		}
		else {				
			this.dateIn = dateIn.format(formatter);
		}
		this.ipAddress = ipAddress;
		this.idDis = idDis;
		this.societe = societe;
		this.lienBanque = lienBanque;
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

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public Integer getLienBanque() {
		return lienBanque;
	}

	public void setLienBanque(Integer lienBanque) {
		this.lienBanque = lienBanque;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	
    
}
