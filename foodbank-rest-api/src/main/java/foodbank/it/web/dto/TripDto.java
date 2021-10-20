package foodbank.it.web.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TripDto {
	
	private int tripId;
    
    private String tripDate;
    
    private String tripDepart;
   
    private String tripArrivee;
   
    private String tripKm;
    
    private int batId;
   
    private String dateEnreg;
    
    private boolean actif;
    private String membreNom;
    
    private Long  totalRecords;

	public TripDto(int tripId, String tripDate, String tripDepart, String tripArrivee, String tripKm, int batId,
			LocalDateTime dateEnreg, boolean actif, String membreNom, Long totalRecords) {
		super();
		this.tripId = tripId;
		this.tripDate = tripDate;
		this.tripDepart = tripDepart;
		this.tripArrivee = tripArrivee;
		this.tripKm = tripKm;
		this.batId = batId;
		if (dateEnreg == null) {
			this.dateEnreg = "";
		}
		else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			this.dateEnreg = dateEnreg.format(formatter);
		}
		this.actif = actif;
		this.membreNom = membreNom;
		this.totalRecords = totalRecords;
	}

	public int getTripId() {
		return tripId;
	}

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

	public String getTripDate() {
		return tripDate;
	}

	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}

	public String getTripDepart() {
		return tripDepart;
	}

	public void setTripDepart(String tripDepart) {
		this.tripDepart = tripDepart;
	}

	public String getTripArrivee() {
		return tripArrivee;
	}

	public void setTripArrivee(String tripArrivee) {
		this.tripArrivee = tripArrivee;
	}

	public String getTripKm() {
		return tripKm;
	}

	public void setTripKm(String tripKm) {
		this.tripKm = tripKm;
	}

	public int getBatId() {
		return batId;
	}

	public void setBatId(int batId) {
		this.batId = batId;
	}

	public String getDateEnreg() {
		return dateEnreg;
	}

	public void setDateEnreg(String dateEnreg) {
		this.dateEnreg = dateEnreg;
	}

	public boolean getActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public String getMembreNom() {
		return membreNom;
	}

	public void setMembreNom(String membreNom) {
		this.membreNom = membreNom;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
    
    
}
