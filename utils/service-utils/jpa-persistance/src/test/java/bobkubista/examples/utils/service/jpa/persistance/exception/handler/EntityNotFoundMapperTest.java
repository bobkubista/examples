package bobkubista.examples.utils.service.jpa.persistance.exception.handler;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Assert;
import org.junit.Test;

public class EntityNotFoundMapperTest {

    @Test
    public void testToResponse() {
        final EntityNotFoundMapper classToTest = new EntityNotFoundMapper();

        final Response result = classToTest.toResponse(new EntityNotFoundException());

        Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), result.getStatus());
    }
}
