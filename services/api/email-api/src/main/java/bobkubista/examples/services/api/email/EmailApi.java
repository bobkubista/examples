/**
 *
 */
package bobkubista.examples.services.api.email;

import javax.ws.rs.core.Response;

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
     * @return {@link Response} with response code
     */
    public Response sendEmail(EmailContext context);
}
