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
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.mail.Message.RecipientType.TO;

@Component
public class FailureMessagesRedirectRoutesBuilder extends RouteBuilder {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${smtpuser}")
    private String smtpUser;
    @Value("${smtppassword}")
    private String smtpPassword;

    private static final Set<String> FAILURE_FROM_ADDRESSES = Set.of(
            "mailer-daemon@googlemail.com"
//            ,
//            "postmaster@kuurne.be",
//            "facturatie@sasbrugge.be"
    );

    @Override
    public void configure() {

        String imapParams = getParams(Map.of(
                "username", smtpUser,
                "password", smtpPassword,
                "unseen", "true"));

        String smtpParams = getParams(Map.of(
                "username", smtpUser,
                "password", smtpPassword));

        FAILURE_FROM_ADDRESSES.forEach(fromAddress ->
                from("imaps://imap.gmail.com:993?" + imapParams + "&searchTerm.from=" + fromAddress)
                        .to("direct:processEmail")
        );

        from("direct:processEmail")
                .process(this::processFailureEmail)
                .to("smtps://smtp.gmail.com:465?" + smtpParams);
    }

    private void processFailureEmail(Exchange exchange) throws MessagingException, IOException {
        MailMessage mailMessage = exchange.getIn(MailMessage.class);


        Message message = mailMessage.getMessage();
        MimeMessage newMessage = (MimeMessage) message.reply(false);
        log.info("Processing failure email: {} ({})", mailMessage.getMessageId(), message.getSubject());

        Optional<Address[]> originalMessageFrom = getFromAddressOfOriginalMessage(mailMessage);

        if (originalMessageFrom.isEmpty()) {
            return;
        }

        newMessage.setRecipients(TO, originalMessageFrom.orElseThrow(IllegalStateException::new));
        newMessage.setSubject("[Foodbanks] " + message.getSubject());
        newMessage.setFrom(new InternetAddress(smtpUser, "Foodbanks Mail Delivery Subsystem"));
        newMessage.setContent((Multipart) message.getContent());

        // send the message to the SMTP endpoint
        exchange.getOut().setBody(newMessage);
    }

    private Optional<Address[]> getFromAddressOfOriginalMessage(MailMessage mailMessage) throws MessagingException, IOException {
        MimeMultipart body = mailMessage.getBody(MimeMultipart.class);
        int count = body.getCount();

        for (int i = 0; i < count; i++) {
            IMAPBodyPart bodyPart = (IMAPBodyPart) body.getBodyPart(i);

            if (bodyPart.getContentType().equalsIgnoreCase("MESSAGE/RFC822")) {
                IMAPNestedMessage content = (IMAPNestedMessage) bodyPart.getDataHandler().getContent();
                return Optional.of(content.getFrom());
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
        builder.smtpUser = "XXXX";
        builder.smtpPassword = "XXXXXXXXXXXXX";
        context.addRoutes(builder);
        context.start();
        while (true) {
            Thread.sleep(10000);
        }
    }
}
