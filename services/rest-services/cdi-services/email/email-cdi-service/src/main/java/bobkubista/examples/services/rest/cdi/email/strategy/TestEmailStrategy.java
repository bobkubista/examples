package bobkubista.examples.services.rest.cdi.email.strategy;

import org.hibernate.validator.constraints.Email;

import bobkubista.examples.services.api.email.model.EmailContext;

/**
 * {@link EmailStrategy} for general emails
 *
 * @author bkubista
 *
 */
public class TestEmailStrategy extends AbstractEmailStrategy {

    private final EmailContext email;

    /**
     * Constructor
     *
     * @param email
     *            {@link Email}
     */
    public TestEmailStrategy(final EmailContext email) {
        this.email = email;
        final String templateFile = "template/testTemplate.tmpl";

        this.composeEmail(this.email, templateFile);
    }

    @Override
    public String toString() {
        return "GeneralEmailStrategy";
    }

    @Override
    EmailContext getEmail() {
        return this.email;
    }

}
