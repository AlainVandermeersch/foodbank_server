package foodbank.it.job;

import com.sun.mail.imap.IMAPBodyPart;
import com.sun.mail.imap.IMAPNestedMessage;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mail.MailMessage;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.mail.Message.RecipientType.TO;

//@Component // disabled
public class FailureMessagesRedirectRoutesBuilder extends RouteBuilder {

    public static final String X_FOODBANKS_MAIL_ORIGINAL_MESSAGE = "X-Foodbanks-Mail-OriginalMessage";
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${smtpuser}")
    private String smtpUser;
    @Value("${smtppassword}")
    private String smtpPassword;

    private static final Set<String> FAILURE_FROM_ADDRESSES = Set.of("mailer-daemon@googlemail.com");

    @Override
    public void configure() {

        String imapParams = getParams(Map.of(
                "username", smtpUser,
                "password", smtpPassword,
                "unseen", "true"));

        String smtpParams = getParams(Map.of(
                "username", smtpUser,
                "password", smtpPassword));

        createOneRouteForEachFailureEmailAddress(imapParams);
        createRouteForMicrosoftFailureEmails(imapParams);

        from("direct:processEmail")
                .routeId("failure-messages-redirect-processEmail")
                .process(this::processFailureEmail)
                .to("smtps://smtp.gmail.com:465?" + smtpParams);
    }

    private void createOneRouteForEachFailureEmailAddress(String imapParams) {
        FAILURE_FROM_ADDRESSES.forEach(fromAddress ->
                from("imaps://imap.gmail.com:993?" + imapParams + "&searchTerm.from=" + fromAddress)
                        .routeId("failure-messages-redirect-"+ fromAddress)
                        .process(this::extractOriginalMessage)
                        .filter(this::hasOriginalMessage)
                        .to("direct:processEmail")
        );
    }

    private void createRouteForMicrosoftFailureEmails(String imapParams) {
        from("imaps://imap.gmail.com:993?" + imapParams + "&searchTerm.subject=Undeliverable:")
                .routeId("failure-messages-redirect-microsoft-office365-undeliverable")
                .process(this::extractOriginalMessage)
                .filter(this::hasOriginalMessage)
                .filter(this::looksLikeAnOffice365FailureMessage)
                .to("direct:processEmail");
    }

    private void extractOriginalMessage(Exchange exchange) throws MessagingException, IOException {
        MailMessage mailMessage = exchange.getIn(MailMessage.class);
        Optional<IMAPNestedMessage> originalMessage = getOriginalMessage(mailMessage);

        originalMessage.ifPresent(
                message -> exchange.getIn().setHeader(X_FOODBANKS_MAIL_ORIGINAL_MESSAGE, message));
    }

    private boolean hasOriginalMessage(Exchange exchange) {
        return exchange.getIn().getHeaders().containsKey(X_FOODBANKS_MAIL_ORIGINAL_MESSAGE);
    }

    private boolean looksLikeAnOffice365FailureMessage(Exchange exchange) {
        MailMessage mailMessage = exchange.getIn(MailMessage.class);
        IMAPNestedMessage originalMessage = exchange.getIn().getHeader(X_FOODBANKS_MAIL_ORIGINAL_MESSAGE, IMAPNestedMessage.class);

        try {
            String failureMessageSubject = mailMessage.getMessage().getSubject();
            String originalMessageSubject = originalMessage.getSubject();
            return failureMessageSubject.equals("Undeliverable: " + originalMessageSubject);
        } catch (MessagingException e) {
            log.error("Error while processing failure message", e);
        }

        return false;
    }

    private void processFailureEmail(Exchange exchange) throws MessagingException, IOException {
        MailMessage mailMessage = exchange.getIn(MailMessage.class);
        IMAPNestedMessage originalMessage = exchange.getIn().getHeader(X_FOODBANKS_MAIL_ORIGINAL_MESSAGE, IMAPNestedMessage.class);

        Message message = mailMessage.getMessage();
        MimeMessage newMessage = (MimeMessage) message.reply(false);
        log.info("Processing failure email: {} ({})", mailMessage.getMessageId(), message.getSubject());

        Address[] originalMessageFrom = Arrays.stream(originalMessage.getFrom())
                .filter(address -> !address.toString().equalsIgnoreCase(smtpUser))
                .toArray(Address[]::new);

        if (originalMessageFrom.length == 0) {
            log.debug("Orignal message from value is {}. No need to redirect", smtpUser);
            return;
        }

        newMessage.setRecipients(TO, originalMessageFrom);
        newMessage.setSubject("[Foodbanks] " + message.getSubject());
        newMessage.setFrom(new InternetAddress(smtpUser, "Foodbanks Mail Delivery Subsystem"));
        newMessage.setContent((Multipart) message.getContent());

        // send the message to the SMTP endpoint
        exchange.getMessage().setBody(newMessage);
    }

    private Optional<IMAPNestedMessage> getOriginalMessage(MailMessage mailMessage) throws MessagingException, IOException {
        MimeMultipart body = mailMessage.getBody(MimeMultipart.class);
        int count = body.getCount();

        for (int i = 0; i < count; i++) {
            IMAPBodyPart bodyPart = (IMAPBodyPart) body.getBodyPart(i);

            if (bodyPart.getContentType().equalsIgnoreCase("MESSAGE/RFC822")) {
                IMAPNestedMessage content = (IMAPNestedMessage) bodyPart.getDataHandler().getContent();
                return Optional.of(content);
            }
        }

        return Optional.empty();
    }

    private String getParams(Map<String, String> paramMap) {
        return paramMap
                .entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
    }

    // This is useful for testing the routes without starting the rest of the application
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        FailureMessagesRedirectRoutesBuilder builder = new FailureMessagesRedirectRoutesBuilder();
        builder.smtpUser = "emanuel.ciuca@gmail.com";
        builder.smtpPassword = "tmbvzixmbygkmsjg";
        context.addRoutes(builder);
        context.start();
        while (true) {
            Thread.sleep(10000);
        }
    }
}
