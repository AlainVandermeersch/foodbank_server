package foodbank.it.web.dto;

public class OrgMemberReportDto {
	private String societe;
	private long nbMembers;
	public OrgMemberReportDto(String societe, long nbMembers) {
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
	public long getNbMembers() {
		return nbMembers;
	}
	public void setNbMembers(long nbMembers) {
		this.nbMembers = nbMembers;
	}
	
	
}
