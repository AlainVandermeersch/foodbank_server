package foodbank.it.web.dto;

public class OrgMemberReportDto {
	private String societe;
	private int nbMembers;
	public OrgMemberReportDto(String societe, int nbMembers) {
		super();
		this.societe = societe;
		this.nbMembers = nbMembers;
	}
	public String getSociete() {
		return societe;
	}
	public void setSociete(String societe) {
		this.societe = societe;
	}
	public int getNbMembers() {
		return nbMembers;
	}
	public void setNbMembers(int nbMembers) {
		this.nbMembers = nbMembers;
	}
	
	
}
