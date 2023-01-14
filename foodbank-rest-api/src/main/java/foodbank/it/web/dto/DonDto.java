package foodbank.it.web.dto;

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
	 private String date;
	 private String donateurNom;
	 private String donateurPrenom;
	 private long totalAmount;
	 private Long  totalRecords;
	 


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

	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDonateurPrenom() {
		return donateurPrenom;
	}

	public String getDonateurNom() {
		return donateurNom;
	}

	public void setDonateurNom(String donateurNom) {
		this.donateurNom = donateurNom;
	}

	public void setDonateurPrenom(String donateurPrenom) {
		this.donateurPrenom = donateurPrenom;
	}

	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public long getTotalAmount() {
		return totalAmount;
	}
}
