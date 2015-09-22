/**
 *
 */
package bobkubista.examples.services.rest.cdi.email;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.services.api.email.EmailApi;
import bobkubista.examples.services.api.email.model.EmailContext;
import bobkubista.examples.services.rest.cdi.email.strategy.EmailStrategy;
import bobkubista.examples.services.rest.cdi.email.strategy.GeneralEmailStrategy;

/**
 * @author Bob
 *
 */
public class EmailFacade implements EmailApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailFacade.class);

    @Override
    public Response sendEmail(final EmailContext context) {
        LOGGER.info("Sending email to {}", context.getRecipient());
        return this.buildResponse(() -> new GeneralEmailStrategy(context).send());
    }

    private Response buildResponse(final EmailStrategy strategy) {
        if (strategy.send()) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }
}
