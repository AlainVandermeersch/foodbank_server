package foodbank.it.web.dto;

public class MailAddressDto {
		private String societe; // calculated field from organisation object
		private String nom;	
		private String prenom;
		private String email;
		public MailAddressDto(String societe, String nom, String prenom, String email) {
			super();
			
			this.societe = societe;
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}	
		
		
}
