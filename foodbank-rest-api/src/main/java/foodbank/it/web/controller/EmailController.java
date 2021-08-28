package foodbank.it.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import foodbank.it.service.IFilesStorageService;
import foodbank.it.service.impl.FileInfo;
import foodbank.it.service.impl.ResponseMessage;
import foodbank.it.web.dto.EmailDto;

@RestController
public class EmailController {
	
	@Autowired
	  IFilesStorageService storageService;
	
	@PostMapping("mailing/upload/")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    System.out.println("Mailing Upload was called");
	    try {
	      storageService.save(file);

	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      System.out.printf("Could not upload the file: %s %s ",file.getOriginalFilename(), e.getMessage());
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }

	@PostMapping("mailing/")
	@ResponseStatus(HttpStatus.CREATED)
	public EmailDto create(@RequestBody EmailDto newEmail) throws MessagingException {
		 System.out.println("Mailing Send was called");
		try {

			String to = newEmail.getTo();
			String from = newEmail.getFrom();
			String subject = newEmail.getSubject();
			String bodyText = newEmail.getBodyText();
			String attachmentFileNames = newEmail.getAttachmentFileNames();
			MimeMessage newMimeMessage = createEmail(to, from, subject, bodyText,attachmentFileNames);
			// Todo Alain Use Google Message Type Message newMessage =
			// createMessageWithEmail(newMimeMessage);
			Transport.send(newMimeMessage);	
			newEmail.setBodyText(" ");
			return newEmail;
		} catch (Exception ex) {
			String errorMsg = ex.getMessage();
			System.out.println(errorMsg);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
		}
	}

	public static MimeMessage createEmail(String to, String from, String subject, String bodyText, String attachmentFileNames)
			throws MessagingException {
		Path root = Paths.get("uploads");
		String username = "foodbankshelpdesk@gmail.com";
		String password = "V0lunt11rs$";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		MimeMessage email = new MimeMessage(session);
// from becomes cc and to becomes username
		email.setFrom(new InternetAddress(username));
		email.addRecipient(javax.mail.Message.RecipientType.CC, new InternetAddress(from));
		email.addRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
		email.setSubject(subject);
		if (attachmentFileNames.length()==0) {
		email.setContent(bodyText, "text/html; charset=utf-8");
		}
		else {
			Multipart multipart = new MimeMultipart();
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(bodyText, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart);
			String[] fileNameArray = attachmentFileNames.split(",");
			 for (int i = 0; i <fileNameArray.length; i++) {
				  
		            // accessing each element of array
		            String fileName = fileNameArray[i];
		            File file = root.resolve(fileName).toFile();	
		            mimeBodyPart = new MimeBodyPart();
		            DataSource source = new FileDataSource(file);
		            mimeBodyPart.setDataHandler(new DataHandler(source));
		            mimeBodyPart.setFileName(file.getName());
		            multipart.addBodyPart(mimeBodyPart);
		        }
			 email.setContent(multipart);

		}
		return email;
	}
	

	/**
	 * Create a message from an email.
	 *
	 * @param emailContent Email to be set to raw of message
	 * @return a message containing a base64url encoded email
	 * @throws IOException
	 * @throws MessagingException
	 */
	// Todo Alain Use Google Message Type
	public static Message createMessageWithEmail(MimeMessage emailContent) throws MessagingException, IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		emailContent.writeTo(buffer);
		byte[] bytes = buffer.toByteArray();
		String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
		Message message = new Message();
		message.setRaw(encodedEmail);
		return message;
	}

	/**
	 * Send an email from the user's mailbox to its recipient.
	 *
	 * @param service      Authorized Gmail API instance.
	 * @param userId       User's email address. The special value "me" can be used
	 *                     to indicate the authenticated user.
	 * @param emailContent Email to be sent.
	 * @return The sent message
	 * @throws MessagingException
	 * @throws IOException
	 */
	// Todo Alain Use Google Message Type
	// Must create a authenticated Google service first
	public static Message sendMessage(Gmail service, String userId, MimeMessage emailContent)
			throws MessagingException, IOException {
		Message message = createMessageWithEmail(emailContent);
		message = service.users().messages().send(userId, message).execute();

		System.out.println("Message id: " + message.getId());
		System.out.println(message.toPrettyString());
		return message;
	}
	
	
	  @GetMapping("mailing/files/")
	  public ResponseEntity<List<FileInfo>> getListFiles() {
	    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
	      String filename = path.getFileName().toString();
	      String url = MvcUriComponentsBuilder
	          .fromMethodName(EmailController.class, "getFile", path.getFileName().toString()).build().toString();

	      return new FileInfo(filename, url);
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	  }

	  @GetMapping("mailing/files/{filename:.+}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	    Resource file = storageService.load(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }

}
