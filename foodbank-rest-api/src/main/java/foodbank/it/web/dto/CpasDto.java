package foodbank.it.web.dto;


public class CpasDto {
	
	 private int cpasId;
	 
	 private String cpasZip;
	 
	 private String cpasName;
	 
	 private String cpasMail;
	 
	 private String cpasStreet;
	
	 private String cpasTel;
	
	 private String cpasGsm;
	
	 private String cpasContactName;
	
	 private String cpasContactSurname;
	
	 private Short civilite;
	
	 private String rem;
	 
	 private String password;
	 
	 private Short lBanque;
	 
	 private Short langue;
	 
	 private Long  totalRecords;
	 
	 

	public CpasDto(int cpasId, String cpasZip, String cpasName, String cpasMail, String cpasStreet, String cpasTel,
			String cpasGsm, String cpasContactName, String cpasContactSurname, Short civilite, String rem,
			String password, Short lBanque, Short langue,Long  totalRecords) {
		super();
		this.cpasId = cpasId;
		this.cpasZip = cpasZip;
		this.cpasName = cpasName;
		this.cpasMail = cpasMail;
		this.cpasStreet = cpasStreet;
		this.cpasTel = cpasTel;
		this.cpasGsm = cpasGsm;
		this.cpasContactName = cpasContactName;
		this.cpasContactSurname = cpasContactSurname;	
		this.rem = rem;
		this.password = password;
		if (lBanque != null) {
			this.lBanque = lBanque;
		}
		else {
			this.lBanque = 0;
		}
		if (langue != null) {
			this.langue = langue;
		}
		else {
			this.langue = 0;
		}
		if (civilite != null) {
			this.civilite = civilite;
		}
		else {
			this.civilite = 0;
		}
		this.totalRecords = totalRecords;
	}

	public int getCpasId() {
		return cpasId;
	}

	public void setCpasId(int cpasId) {
		this.cpasId = cpasId;
	}

	public String getCpasZip() {
		return cpasZip;
	}

	public void setCpasZip(String cpasZip) {
		this.cpasZip = cpasZip;
	}

	public String getCpasName() {
		return cpasName;
	}

	public void setCpasName(String cpasName) {
		this.cpasName = cpasName;
	}

	public String getCpasMail() {
		return cpasMail;
	}

	public void setCpasMail(String cpasMail) {
		this.cpasMail = cpasMail;
	}

	public String getCpasStreet() {
		return cpasStreet;
	}

	public void setCpasStreet(String cpasStreet) {
		this.cpasStreet = cpasStreet;
	}

	public String getCpasTel() {
		return cpasTel;
	}

	public void setCpasTel(String cpasTel) {
		this.cpasTel = cpasTel;
	}

	public String getCpasGsm() {
		return cpasGsm;
	}

	public void setCpasGsm(String cpasGsm) {
		this.cpasGsm = cpasGsm;
	}

	public String getCpasContactName() {
		return cpasContactName;
	}

	public void setCpasContactName(String cpasContactName) {
		this.cpasContactName = cpasContactName;
	}

	public String getCpasContactSurname() {
		return cpasContactSurname;
	}

	public void setCpasContactSurname(String cpasContactSurname) {
		this.cpasContactSurname = cpasContactSurname;
	}

	public Short getCivilite() {
		return civilite;
	}

	public void setCivilite(Short civilite) {
		this.civilite = civilite;
	}

	public String getRem() {
		return rem;
	}

	public void setRem(String rem) {
		this.rem = rem;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Short getLBanque() {
		return lBanque;
	}

	public void setlBanque(Short lBanque) {
		this.lBanque = lBanque;
	}

	public Short getLangue() {
		return langue;
	}

	public void setLangue(Short langue) {
		this.langue = langue;
	}
	public Long  getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long  totalRecords) {
		this.totalRecords = totalRecords;
	}
	 
}

