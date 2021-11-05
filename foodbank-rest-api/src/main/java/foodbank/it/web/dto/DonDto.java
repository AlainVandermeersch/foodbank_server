package foodbank.it.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DonDto {
	private int idDon;
	 private int amount; 
	 private int lienBanque;
	 private int donateurId;
	 private String dateEntered;
	 private boolean appended;
	 private boolean checked;
	 private LocalDate date;
	 
	public DonDto(int idDon, int amount, int lienBanque, int donateurId, LocalDateTime dateEntered, boolean appended,
			boolean checked, LocalDate date) {
		super();
		this.idDon = idDon;
		this.amount = amount;
		this.lienBanque = lienBanque;
		this.donateurId = donateurId;
		if (dateEntered == null) {
			this.dateEntered = "";
		}
		else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			this.dateEntered = dateEntered.format(formatter);
		}
		this.appended = appended;
		this.checked = checked;
		this.date = date;
	}

	public int getIdDon() {
		return idDon;
	}

	public void setIdDon(int idDon) {
		this.idDon = idDon;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getLienBanque() {
		return lienBanque;
	}

	public void setLienBanque(int lienBanque) {
		this.lienBanque = lienBanque;
	}

	public int getDonateurId() {
		return donateurId;
	}

	public void setDonateurId(int donateurId) {
		this.donateurId = donateurId;
	}

	public String getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(String dateEntered) {
		this.dateEntered = dateEntered;
	}

	public boolean isAppended() {
		return appended;
	}

	public void setAppended(boolean appended) {
		this.appended = appended;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	 
	 
}
