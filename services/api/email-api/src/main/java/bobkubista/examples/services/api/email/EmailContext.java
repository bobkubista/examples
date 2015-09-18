/**
 *
 */
package bobkubista.examples.services.api.email;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.registry.JAXRException;
import javax.xml.registry.infomodel.EmailAddress;

import org.hibernate.validator.constraints.Email;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObject;

/**
 * @author Bob
 *
 */
@XmlRootElement(name = "email")
@XmlAccessorType(XmlAccessType.NONE)
public class EmailContext implements DomainObject {

    public static final class EmailBuilder {

        private final EmailAddress recipient;
        private final Map<String, String> replacements = new HashMap<>();
        private final String subject;

        public EmailBuilder(final EmailAddress recipient, final String subject) throws JAXRException {
            this.subject = subject;
            this.recipient = recipient;
            this.replacements.put("email", recipient.getAddress());
        }

        public EmailBuilder addReplacement(final Replacement replacement) {
            this.replacements.put(replacement.getConstant(), replacement.getValue());
            return this;
        }

        public EmailContext build() throws JAXRException {
            return new EmailContext(this);
        }
    }

    private static final long serialVersionUID = -2560167778218837261L;
    @XmlElement(name = "recipient")
    @Email
    private EmailAddress recipient;
    @XmlElementWrapper(name = "replacements")
    @XmlElement(name = "replacement")
    private Map<String, String> replacements = new HashMap<String, String>();

    @XmlElement(name = "subject")
    private String subject;

    private EmailContext() {
        super();
    }

    /**
     * Constructor
     *
     * @param recipient
     *            recipient
     * @param subject
     *            subject
     * @param message
     *            message
     * @throws JAXRException
     */
    private EmailContext(final EmailBuilder builder) throws JAXRException {
        this.recipient = builder.recipient;
        this.subject = builder.subject;
        this.replacements = builder.replacements;
    }

    public EmailContext getEmail() {
        return this;
    }

    public EmailAddress getRecipient() {
        return this.recipient;
    }

    public Map<String, String> getReplacements() {
        return this.replacements;
    }

    public String getSubject() {
        return this.subject;
    }

    @Override
    public String toString() {
        return "Email{" + "recipient='" + this.recipient + '\'' + ", subject='" + this.subject + '\'' + '}';
    }
}
