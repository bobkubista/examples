/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.cdi.email.strategy;

import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.services.api.email.model.EmailContext;

/**
 * @author Bob
 *
 */
public class TemplateEmailStrategy extends AbstractEmailStrategy {

    private final EmailContext email;

    public TemplateEmailStrategy(final EmailContext email, final String template) {
        this.email = email;
        final String templateFile = ServerProperties.get()
                .getString("email.template.location") + template + ".tmpl";

        this.composeEmail(this.email, templateFile);
    }

    @Override
    public String toString() {
        return "TemplateEmailStrategy";
    }

    @Override
    EmailContext getEmail() {
        return this.email;
    }
}
