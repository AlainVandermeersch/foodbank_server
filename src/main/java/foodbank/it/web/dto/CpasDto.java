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
	
	 private short civilite;
	
	 private String rem;
	 
	 private String password;
	 
	 private short lBanque;
	 
	 private short langue;
	 
	 

	public CpasDto(int cpasId, String cpasZip, String cpasName, String cpasMail, String cpasStreet, String cpasTel,
			String cpasGsm, String cpasContactName, String cpasContactSurname, short civilite, String rem,
			String password, short lBanque, short langue) {
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
		this.civilite = civilite;
		this.rem = rem;
		this.password = password;
		this.lBanque = lBanque;
		this.langue = langue;
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

	public short getCivilite() {
		return civilite;
	}

	public void setCivilite(short civilite) {
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

	public short getLBanque() {
		return lBanque;
	}

	public void setlBanque(short lBanque) {
		this.lBanque = lBanque;
	}

	public short getLangue() {
		return langue;
	}

	public void setLangue(short langue) {
		this.langue = langue;
	}
	 
	 
}

