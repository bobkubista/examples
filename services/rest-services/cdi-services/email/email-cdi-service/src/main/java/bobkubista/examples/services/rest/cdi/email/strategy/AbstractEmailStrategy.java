package bobkubista.examples.services.rest.cdi.email.strategy;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Map.Entry;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.services.api.email.model.EmailContext;

/**
 * Abstract class for shared email logic for {@link EmailStrategy}
 *
 * @author bkubista
 *
 */
public abstract class AbstractEmailStrategy implements EmailStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractEmailStrategy.class);

    @Override
    public boolean send() {
        LOGGER.info("Sending email : {})", this.getEmail());
        boolean hasBeenSent;

        try {
            final MimeMessage message = this.prepareMessage(this.getEmail());
            Transport.send(message);
            hasBeenSent = true;
        } catch (final MessagingException e) {
            LOGGER.error("Cannot send email : {}\n{}", this.getEmail(), e);
            hasBeenSent = false;
        }
        LOGGER.info("Email has been sent : {})", this.getEmail());
        return hasBeenSent;
    }

    protected final void composeEmail(final EmailContext email, final String templateFile) {
        final URL url = Resources.getResource(templateFile);
        try {
            final String textToProcess = Resources.toString(url, Charsets.UTF_8);
            final VelocityContext context = new VelocityContext();
            email.getReplacements()
                    .entrySet()
                    .stream()
                    .forEach((final Entry<String, ? extends Object> replacement) -> context.put(replacement.getKey(), replacement.getValue()));
            final Writer swOut = new StringWriter();
            Velocity.evaluate(context, swOut, templateFile, textToProcess);

            email.setMessage(swOut.toString());
        } catch (final IOException e) {
            LOGGER.error("Cannot load template file : {}\n{}", templateFile, e);
        }
    }

    protected Properties getEmailProperties() {
        final Properties props = new Properties();
        props.put("mail.smtp.host", ServerProperties.getString("email.smtp.host"));
        props.put("mail.smtp.socketFactory.port", ServerProperties.getString("email.smtp.socket.factory.port"));
        props.put("mail.smtp.socketFactory.class", ServerProperties.getString("email.smtp.socket.factory.class"));
        props.put("mail.smtp.auth", ServerProperties.getString("email.smtp.auth"));
        props.put("mail.smtp.port", ServerProperties.getString("email.smtp.port"));
        LOGGER.debug("Email propertires : {}", props);
        return props;
    }

    protected MimeMessage prepareMessage(final EmailContext email) throws MessagingException {
        final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ServerProperties.getString("email.login"), ServerProperties.getString("email.password"));
            }
        });
        final MimeMessage message = new MimeMessage(session);
        message.setSubject(email.getSubject());
        message.setFrom(new InternetAddress(ServerProperties.getString("email.from")));

        final MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(email.getMessage(), "text/html; charset=UTF-8");

        final Multipart mp = new MimeMultipart();
        mp.addBodyPart(textPart);
        message.setContent(mp);
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getRecipient()));

        return message;
    }

    abstract EmailContext getEmail();
}
