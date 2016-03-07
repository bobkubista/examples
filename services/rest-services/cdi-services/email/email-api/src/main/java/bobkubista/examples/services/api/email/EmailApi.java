/**
 *
 */
package bobkubista.examples.services.api.email;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
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
    @PUT
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response sendEmail(EmailContext context);
}
