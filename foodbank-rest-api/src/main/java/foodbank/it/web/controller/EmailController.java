package foodbank.it.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import foodbank.it.web.dto.EmailDto;
import foodbank.it.web.dto.TUserDto;

@RestController
public class EmailController {

	@PostMapping("mailing/")
	@ResponseStatus(HttpStatus.CREATED)
	public EmailDto create(@RequestBody EmailDto newEmail) throws MessagingException {
		try {

			String to = newEmail.getTo();
			String from = newEmail.getFrom();
			String subject = newEmail.getSubject();
			String bodyText = newEmail.getBodyText();
			MimeMessage newMimeMessage = createEmail(to, from, subject, bodyText);
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

	public static MimeMessage createEmail(String to, String from, String subject, String bodyText)
			throws MessagingException {
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
		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
		email.setSubject(subject);
		email.setContent(bodyText, "text/html; charset=utf-8");
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

}
