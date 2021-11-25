package foodbank.it.web.dto;

public class RegionDto {
	private int regId;
	private String regName;
	private short bankLink;
	private String bankShortName;
   
	public RegionDto() {
		super();
	}
	public RegionDto(int regId, String regName,short bankLink,  String bankShortName) {
		super();
		this.regId = regId;
		this.regName = regName;
		this.bankLink = bankLink;	
		this.bankShortName = bankShortName;			
	}
	public int getRegId() {
		return regId;
	}
	public void setRegId(int regId) {
		this.regId = regId;
	}
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}	 
   
	public short getBankLink() {
		return bankLink;
	}
	public void setBankLink(short bankLink) {
		this.bankLink = bankLink;
	}
	public String getBankShortName() {
		return bankShortName;
	}

	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}
	
	 
}
