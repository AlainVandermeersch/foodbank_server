package foodbank.it.service;

public class SearchTripCriteria {
	private Integer batId;
	private String membreNom;
	
	
	public SearchTripCriteria(Integer batId, String membreNom) {
		this.batId = batId;
		this.membreNom = membreNom;		
	}

	

	public Integer getBatId() {
		return batId;
	}



	public void setBatId(Integer batId) {
		this.batId = batId;
	}



	public String getMembreNom() {
		return membreNom;
	}

	public void setMembreNom(String membreNom) {
		this.membreNom = membreNom;
	}


	
	

}

