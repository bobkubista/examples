/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.service.jpa.persistance.exception.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.dao.DataIntegrityViolationException;

import bobkubista.examples.utils.domain.model.RestConstants;

/**
 * @author Bob
 *
 */
@Provider
public class DataConstraintExceptionMapper implements ExceptionMapper<DataIntegrityViolationException> {

    @Override
    public Response toResponse(final DataIntegrityViolationException exception) {
        return Response.status(RestConstants.UNPROCESSABLE_ENTITY)
                .entity(exception.getMessage())
                .build();
    }

}
