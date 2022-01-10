package foodbank.it.web.dto;

public class OrganisationSummaryDto {
	private int idDis;
	private String societe;
	private String bankShortName;
	
	
	public OrganisationSummaryDto(int idDis, String societe, String bankShortName) {
		super();
		this.idDis = idDis;
		this.societe = societe;
		this.bankShortName = bankShortName;
	}
	public int getIdDis() {
		return idDis;
	}
	public void setIdDis(int idDis) {
		this.idDis = idDis;
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
	
}
