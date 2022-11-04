package foodbank.it.persistence.model;

public class BanqueCount {
	 private String bankShortName; 
	 private Long count;
	public BanqueCount(String bankShortName, Long orgCount) {
		super();
		this.bankShortName = bankShortName;
		this.count = orgCount;
	}
	public String getBankShortName() {
		return bankShortName;
	}
	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long orgCount) {
		this.count = orgCount;
	}
	
	 
	 
}

