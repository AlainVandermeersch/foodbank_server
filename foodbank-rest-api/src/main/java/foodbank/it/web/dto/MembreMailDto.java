package foodbank.it.web.dto;

public class MembreMailDto {
	private Integer batId;
	 	private String societe; // calculated field from organisation object
		private String nom;	
		private String prenom;
		private String batmail;
		public MembreMailDto(Integer batId,String societe, String nom, String prenom, String batmail) {
			super();
			this.batId = batId;
			this.societe = societe;
			this.nom = nom;
			this.prenom = prenom;
			this.batmail = batmail;
		}
		public Integer getBatId() {
			return batId;
		}
		public void setBatId(Integer batId) {
			this.batId = batId;
		}
		public String getSociete() {
			return societe;
		}
		public void setSociete(String societe) {
			this.societe = societe;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		public String getBatmail() {
			return batmail;
		}
		public void setBatmail(String batmail) {
			this.batmail = batmail;
		}	
		
		
}
