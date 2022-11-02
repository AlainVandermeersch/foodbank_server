package foodbank.it.web.dto;

public class EmailDto {
	private String sentDate;
	private String from;	
	private String to;
	private String subject;
	private String bodyText;
	private String attachmentFileNames;

	private boolean bccMode;
	
	public String getSentDate() {
		return sentDate;
	}
	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBodyText() {
		return bodyText;
	}
	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
	public String getAttachmentFileNames() {
		return attachmentFileNames;
	}
	public void setAttachmentFileNames(String attachmentFileNames) {
		this.attachmentFileNames = attachmentFileNames;
	}

	public boolean isBccMode() {
		return bccMode;
	}

	public void setBccMode(boolean bccMode) {
		this.bccMode = bccMode;
	}
}
