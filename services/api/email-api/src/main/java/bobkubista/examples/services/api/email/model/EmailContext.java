/**
 *
 */
package bobkubista.examples.services.api.email.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.validator.constraints.Email;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObject;

/**
 * @author Bob
 *
 */
@XmlRootElement(name = "email")
@XmlAccessorType(XmlAccessType.NONE)
public final class EmailContext implements DomainObject {

    /**
     * Email builder
     *
     * @author Bob
     *
     */
    public static final class EmailBuilder {

        private final String recipient;
        private final List<Pair<String, ? extends Object>> replacement = new ArrayList<>();
        private final String subject;

        /**
         * Constructor
         *
         * @param recipient
         *            email adress
         * @param subject
         *            subject
         */
        public EmailBuilder(final String recipient, final String subject) {
            this.subject = subject;
            this.recipient = recipient;
            this.replacement.add(new EmailReplacement(recipient));
        }

        /**
         * @param replacement
         *            {@link Pair} to add
         * @return the {@link EmailBuilder}
         */
        public <T extends Pair<String, ? extends Object>> EmailBuilder addReplacement(final T replacement) {
            this.replacement.add(replacement);
            return this;
        }

        /**
         *
         * @return {@link EmailContext}
         */
        public EmailContext build() {
            return new EmailContext(this);
        }
    }

    private static final long serialVersionUID = -2560167778218837261L;
    private String message;
    @XmlElement(name = "recipient")
    @Email
    private String recipient;

    @XmlElementWrapper(name = "replacements")
    @XmlElement(name = "replacement")
    private List<Pair<String, ? extends Object>> replacements = new ArrayList<>();
    @XmlElement(name = "subject")
    private String subject;

    /**
     * private constructor
     */
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
     */
    private EmailContext(final EmailBuilder builder) {
        this.recipient = builder.recipient;
        this.subject = builder.subject;
        this.replacements = builder.replacement;
    }

    public EmailContext getEmail() {
        return this;
    }

    /**
     * @return the message
     */
    public final String getMessage() {
        return this.message;
    }

    /**
     * @return
     */
    public String getRecipient() {
        return this.recipient;
    }

    /**
     * @return {@link List} of {@link Pair}
     */
    public List<Pair<String, ? extends Object>> getReplacements() {
        return this.replacements;
    }

    /**
     * @return
     */
    public String getSubject() {
        return this.subject;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Email{" + "recipient='" + this.recipient + '\'' + ", subject='" + this.subject + '\'' + '}';
    }
}
