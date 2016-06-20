package bobkubista.examples.services.rest.cdi.email.strategy;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
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

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.services.api.email.model.EmailContext;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

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
        try {
            final String swOut;
            if (ServerProperties.get()
                    .getString("template.engine", "Velocity")
                    .equals("Velocity")) {
                swOut = this.processVelocityTemplate(email, templateFile);
            } else {
                swOut = this.processFreeMarkerTemplate(email, templateFile);
            }

            email.setMessage(swOut);
        } catch (final IOException | TemplateException e) {
            LOGGER.error("Cannot process template file : {}\n{}", templateFile, e);
        }
    }

    protected Properties getEmailProperties() {
        final Properties props = new Properties();
        props.put("mail.smtp.host", ServerProperties.get()
                .getString("email.smtp.host"));
        props.put("mail.smtp.socketFactory.port", ServerProperties.get()
                .getString("email.smtp.socket.factory.port"));
        props.put("mail.smtp.socketFactory.class", ServerProperties.get()
                .getString("email.smtp.socket.factory.class"));
        props.put("mail.smtp.auth", ServerProperties.get()
                .getString("email.smtp.auth"));
        props.put("mail.smtp.port", ServerProperties.get()
                .getString("email.smtp.port"));
        LOGGER.debug("Email propertires : {}", props);
        return props;
    }

    protected MimeMessage prepareMessage(final EmailContext email) throws MessagingException {
        final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ServerProperties.get()
                        .getString("email.login"),
                        ServerProperties.get()
                                .getString("email.password"));
            }
        });
        final MimeMessage message = new MimeMessage(session);
        message.setSubject(email.getSubject());
        message.setFrom(new InternetAddress(ServerProperties.get()
                .getString("email.from")));

        final MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(email.getMessage(), "text/html; charset=UTF-8");

        final Multipart mp = new MimeMultipart();
        mp.addBodyPart(textPart);
        message.setContent(mp);
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getRecipient()));

        return message;
    }

    abstract EmailContext getEmail();

    private String processFreeMarkerTemplate(final EmailContext email, final String templateFile) throws TemplateException, IOException {
        final Configuration cfg = new Configuration();
        final Template template = cfg.getTemplate(templateFile, "UTF-8");
        final Writer swOut = new StringWriter();
        template.process(email.getReplacements(), swOut);
        return swOut.toString();
    }

    private String processVelocityTemplate(final EmailContext email, final String templateFile) throws IOException {
        final VelocityContext context = new VelocityContext();
        email.getReplacements()
                .entrySet()
                .stream()
                .forEach((final Entry<String, ? extends Object> replacement) -> context.put(replacement.getKey(), replacement.getValue()));
        final Writer swOut = new StringWriter();
        Velocity.evaluate(context, swOut, templateFile, IOUtils.toString(this.getClass()
                .getClassLoader()
                .getResource(templateFile), Charsets.UTF_8));
        return swOut.toString();
    }
}
