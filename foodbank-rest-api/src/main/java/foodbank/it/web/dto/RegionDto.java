package foodbank.it.web.dto;

public class RegionDto {
	private int regId;
	private String regName;
	private String bankName;
    private String bankShortName;
   
	public RegionDto() {
		super();
	}
	public RegionDto(int regId, String regName,  String bankShortName, String bankName) {
		super();
		this.regId = regId;
		this.regName = regName;
		this.bankShortName = bankShortName;
		this.bankName = bankName;		
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
	 
    public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankShortName() {
		return bankShortName;
	}

	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}
	
	 
}
