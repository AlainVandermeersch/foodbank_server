package foodbank.it.web.dto;

public class AuditAssocDto {
	private long auditId;
	   
    private int lienDis;
   
    private int lienDep;
    
    private int auditorNr;
    
    private String demunisYNRem;
   
    private String hygCheck;
   
    private String servUsage;
   
    private String probSug;
   
    private String auditDate;
    
    private boolean benefCheck;
    
    private String societe;
    
    private String depotName;
   
    private Short lienBanque; 
    private String auditorName; 
    
    private Long  totalRecords;
    
    

	public AuditAssocDto(long auditId, int lienDis, int lienDep, int auditorNr, String demunisYNRem, String hygCheck,
			String servUsage, String probSug, String auditDate, boolean benefCheck,
			String societe, String depotName,Short lienBanque,String auditorName, Long  totalRecords) {
		super();
		this.auditId = auditId;
		this.lienDis = lienDis;
		this.lienDep = lienDep;
		this.auditorNr = auditorNr;
		this.demunisYNRem = demunisYNRem;
		this.hygCheck = hygCheck;
		this.servUsage = servUsage;
		this.probSug = probSug;
		this.auditDate = auditDate;
		this.benefCheck = benefCheck;
		this.societe = societe;
		this.depotName = depotName;
		this.auditorName = auditorName;
		this.lienBanque = lienBanque;
		this.totalRecords = totalRecords;
	}

	public long getAuditId() {
		return auditId;
	}

	public void setAuditId(long auditId) {
		this.auditId = auditId;
	}

	public int getLienDis() {
		return lienDis;
	}

	public void setLienDis(int lienDis) {
		this.lienDis = lienDis;
	}

	public int getLienDep() {
		return lienDep;
	}

	public void setLienDep(int lienDep) {
		this.lienDep = lienDep;
	}

	public int getAuditorNr() {
		return auditorNr;
	}

	public void setAuditorNr(int auditorNr) {
		this.auditorNr = auditorNr;
	}

	public String getDemunisYNRem() {
		return demunisYNRem;
	}

	public void setDemunisYNRem(String demunisYNRem) {
		this.demunisYNRem = demunisYNRem;
	}

	public String getHygCheck() {
		return hygCheck;
	}

	public void setHygCheck(String hygCheck) {
		this.hygCheck = hygCheck;
	}

	public String getServUsage() {
		return servUsage;
	}

	public void setServUsage(String servUsage) {
		this.servUsage = servUsage;
	}

	public String getProbSug() {
		return probSug;
	}

	public void setProbSug(String probSug) {
		this.probSug = probSug;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public boolean isBenefCheck() {
		return benefCheck;
	}

	public void setBenefCheck(boolean benefCheck) {
		this.benefCheck = benefCheck;
	}
	
	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public String getDepotName() {
		return depotName;
	}

	public void setDepotName(String depotName) {
		this.depotName = depotName;
	}

	public Short getLienBanque() {
		return lienBanque;
	}

	public void setLienBanque(Short lienBanque) {
		this.lienBanque = lienBanque;
	}
	
	
	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
    
    
}


