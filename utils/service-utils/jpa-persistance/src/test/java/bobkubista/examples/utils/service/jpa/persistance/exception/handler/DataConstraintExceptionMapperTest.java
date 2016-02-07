package bobkubista.examples.utils.service.jpa.persistance.exception.handler;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import bobkubista.examples.utils.domain.model.RestConstants;

public class DataConstraintExceptionMapperTest {

    @Test
    public void testToResponse() {
        final DataConstraintExceptionMapper classToTest = new DataConstraintExceptionMapper();

        final Response result = classToTest.toResponse(new DataIntegrityViolationException(""));

        Assert.assertEquals(RestConstants.UNPROCESSABLE_ENTITY, result.getStatus());
    }

}
