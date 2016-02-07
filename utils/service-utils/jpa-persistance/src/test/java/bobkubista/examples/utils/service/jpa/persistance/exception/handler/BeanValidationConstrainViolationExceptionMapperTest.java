package bobkubista.examples.utils.service.jpa.persistance.exception.handler;

import java.util.Collections;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import bobkubista.examples.utils.domain.model.RestConstants;

public class BeanValidationConstrainViolationExceptionMapperTest {

    @Test
    public void testToResponse() {
        final BeanValidationConstrainViolationExceptionMapper classToTest = new BeanValidationConstrainViolationExceptionMapper();

        final ConstraintViolation<?> mockViolation = Mockito.mock(ConstraintViolation.class);
        final Response result = classToTest.toResponse(new ConstraintViolationException(Collections.singleton(mockViolation)));

        Assert.assertEquals(RestConstants.UNPROCESSABLE_ENTITY, result.getStatus());
    }

}
