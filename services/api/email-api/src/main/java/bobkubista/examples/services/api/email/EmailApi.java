/**
 *
 */
package bobkubista.examples.services.api.email;

import bobkubista.examples.services.api.email.model.EmailContext;

/**
 * @author Bob
 *
 */
@FunctionalInterface
public interface EmailApi {

    /**
     * Send an email
     * 
     * @param context
     *            {@link EmailContext}
     */
    public void sendEmail(EmailContext context);
}
