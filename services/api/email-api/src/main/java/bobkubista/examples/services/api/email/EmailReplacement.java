/**
 *
 */
package bobkubista.examples.services.api.email;

import javax.xml.registry.infomodel.EmailAddress;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Bob
 *
 */
public class EmailReplacement extends Pair<String, EmailAddress> {

    private static final String EMAIL = "email";

    private static final long serialVersionUID = -374936343510336318L;
    private EmailAddress value;

    /**
     * Constructor
     * 
     * @param recipient
     *            {@link EmailAddress}
     */
    public EmailReplacement(final EmailAddress recipient) {
        this.value = recipient;
    }

    @Override
    public String getLeft() {
        return EMAIL;
    }

    @Override
    public EmailAddress getRight() {
        return this.value;
    }

    @Override
    public EmailAddress setValue(final EmailAddress value) {
        this.value = value;
        return value;
    }

}
