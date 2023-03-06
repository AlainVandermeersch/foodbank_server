package foodbank.it.web.controller;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.sun.mail.smtp.SMTPMessage;
import foodbank.it.service.IFilesStorageService;
import foodbank.it.service.IMailService;
import foodbank.it.service.SearchMailListCriteria;
import foodbank.it.service.impl.FileInfo;
import foodbank.it.service.impl.ResponseMessage;
import foodbank.it.web.dto.EmailDto;
import foodbank.it.web.dto.MailAddressDto;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

@RestController
public class EmailController {
private IMailService MailService;

	@Value("${smtpuser}")
	private String smtpUser;
	@Value("${smtppassword}")
	private String smtpPassword;

    public EmailController(IMailService MailService) {
        this.MailService = MailService;
    }
	
	@Autowired
	  IFilesStorageService storageService;
	 @GetMapping("mailaddresses/")
	    public Collection<MailAddressDto> find( 
	    		@RequestParam(required = false) String lienBanque ,
	    		@RequestParam(required = false) String lienDis,
	    		@RequestParam(required = false) String regId,
	    		@RequestParam(required = false) Boolean agreed,
	    		@RequestParam(required = false) Boolean feadN,
	    		@RequestParam(required = false) Boolean isDepot,
	    		@RequestParam(required = false) String langue,
	    		@RequestParam(required = false) String mailGroup
	    		) {
	    	
	        
	        Integer lienBanqueInteger = Optional.ofNullable(lienBanque).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
	        Integer lienDisInteger = Optional.ofNullable(lienDis).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
	        Integer regIdInteger = Optional.ofNullable(regId).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
	        Integer langueInteger = Optional.ofNullable(langue).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
			SearchMailListCriteria criteria = new SearchMailListCriteria(lienBanqueInteger, lienDisInteger,regIdInteger,agreed,feadN,isDepot,langueInteger, mailGroup);
			List<MailAddressDto> mailAddressDtos = this.MailService.find(criteria);			

			return mailAddressDtos;
	    }

	
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
	public EmailDto create(@RequestBody EmailDto newEmail) throws Exception {
		 System.out.printf("\nMailing Send was called from smtp user '%s'\n",smtpUser);
		 if (smtpUser.equals("dummy")) {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not Supported on Test System");
		 }
		try {

			String to = newEmail.getTo();
			String from = newEmail.getFrom();
			String subject = newEmail.getSubject();
			String bodyText = newEmail.getBodyText();
			String language = newEmail.getLanguage();
			boolean bccMode = newEmail.isBccMode();
			String attachmentFileNames = newEmail.getAttachmentFileNames();
			MimeMessage newMimeMessage = createEmail(smtpUser, smtpPassword, to, from, subject,language, bodyText,attachmentFileNames,bccMode);
			Transport.send(newMimeMessage);
			newEmail.setBodyText(" ");
			return newEmail;
		} catch (Exception ex) {
			String errorMsg = ex.getMessage();
			System.out.println(errorMsg);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
		}
	}

	public static MimeMessage createEmail(String username, String password, String to, String from, String subject, String language,String bodyText, String attachmentFileNames, boolean bccMode)
			throws MessagingException {
		Path root = Paths.get("uploads");
		InternetAddress adresses = new InternetAddress(from);
		String fromAddress= adresses.getAddress();
		System.out.printf("\nEmailFrom: '%s'.\n", fromAddress);
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

		SMTPMessage email = new SMTPMessage(session);
		email.setEnvelopeFrom(fromAddress);
// from becomes cc and from  becomes username
		email.setFrom(new InternetAddress(username));
		email.addRecipient(javax.mail.Message.RecipientType.CC, new InternetAddress(from));
		if (bccMode) {
			email.addRecipients(javax.mail.Message.RecipientType.BCC, InternetAddress.parse(to));
		}
		else {
			email.addRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
		}
		email.setReplyTo(InternetAddress.parse(from));

		email.setSubject(subject);
		String headerLine= String.format("<p>----- Please Reply to: %s -----</p>", fromAddress);

		if (language.equals("fr")) {
			headerLine= String.format("<p>----- Répondez svp à: %s -----</p>", fromAddress);
		}
		if (language.equals("nl")) {
			headerLine= String.format("<p>----- Antwoord aub aan: %s -----</p>", fromAddress);
		}
		String modifiedBodyText = headerLine + bodyText;
		if (attachmentFileNames.length()==0) {
			email.setContent(modifiedBodyText , "text/html; charset=utf-8");
		}
		else {
			Multipart multipart = new MimeMultipart();
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(modifiedBodyText, "text/html; charset=utf-8");
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
