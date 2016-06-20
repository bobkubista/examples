/**
 *
 */
package bobkubista.examples.services.api.email;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bobkubista.examples.services.api.email.model.EmailContext;

/**
 * @author Bob
 *
 */
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
    Response sendEmail(EmailContext context);

    @GET
    @Path("{template}")
    Response sendEmail(EmailContext context, @PathParam("template") String template);
}
