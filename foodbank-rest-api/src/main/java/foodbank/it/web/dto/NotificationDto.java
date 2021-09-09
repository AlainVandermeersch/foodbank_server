package foodbank.it.web.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;

public class NotificationDto {
	private Integer notificationId;   
    private String creationdate;
    private String author;
    private String subject;
    private String audience;
    private Short importance;
    private String language;
    private String content;
    private Long  totalRecords;
    
	public NotificationDto(Integer notificationId, LocalDate creationDate, String author, String subject, String audience,
			Short importance, String language, String content, Long  totalRecords) {
		super();
		this.notificationId = notificationId;
		if (creationDate == null) {
			this.creationdate = "";
		}
		else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			this.creationdate = creationDate.format(formatter);
		}
		this.author = author;
		this.subject = subject;
		this.audience = audience;
		this.importance = importance;
		this.language = language;
		this.content = content;
		this.totalRecords = totalRecords;
	}
	public Integer getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}
	public String getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAudience() {
		return audience;
	}
	public void setAudience(String audience) {
		this.audience = audience;
	}
	public Short getImportance() {
		return importance;
	}
	public void setImportance(Short importance) {
		this.importance = importance;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
    
    

}
