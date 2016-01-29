/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.service.jpa.persistance.exception.handler;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Bob
 *
 */
@Provider
public class EntityNotFoundMapper implements ExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(final EntityNotFoundException exception) {
        return Response.status(Status.NOT_FOUND)
                .build();
    }

}
