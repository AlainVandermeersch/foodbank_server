package foodbank.it.web.dto;

public class OrganisationSummaryDto {
	private int idDis;
	private String societe;
	
	
	public OrganisationSummaryDto(int idDis, String societe) {
		super();
		this.idDis = idDis;
		this.societe = societe;
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
	
}
