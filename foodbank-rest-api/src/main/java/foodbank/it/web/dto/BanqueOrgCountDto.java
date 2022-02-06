package foodbank.it.web.dto;

public class BanqueOrgCountDto {
	 private String bankShortName; 
	 private Long orgCount;
	public BanqueOrgCountDto(String bankShortName, Long orgCount) {
		super();
		this.bankShortName = bankShortName;
		this.orgCount = orgCount;
	}
	public String getBankShortName() {
		return bankShortName;
	}
	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}
	public Long getOrgCount() {
		return orgCount;
	}
	public void setOrgCount(Long orgCount) {
		this.orgCount = orgCount;
	}
	
	 
	 
}

