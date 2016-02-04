package bobkubista.examples.utils.service.jpa.persistance.exception.handler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import bobkubista.examples.utils.domain.model.domainmodel.identification.ConstraintViolationEntity;

/**
 * <a href
 * ="https://github.com/abhirockzz/JAX-RS-and-Bean-Validation/blob/master/src/main/java/abhirockzz/wordpress/com/payara611/BeanValConstrainViolationExceptionMapper.java">
 * source</a>
 *
 * @author Bob
 *
 */
@Provider
public class BeanValidationConstrainViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(final ConstraintViolationException e) {
        final ConstraintViolation<?> cv = (ConstraintViolation<?>) e.getConstraintViolations()
                .toArray()[0];
        return Response.status(422)
                .entity(new ConstraintViolationEntity(cv.getMessage()))
                .build();
    }

}
