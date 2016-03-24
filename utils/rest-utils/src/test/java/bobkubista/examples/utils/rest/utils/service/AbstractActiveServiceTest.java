/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.service;

import java.time.Instant;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.EntityTag;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bobkubista.examples.utils.rest.utils.mocks.MockActiveDomainObject;
import bobkubista.examples.utils.rest.utils.mocks.MockActiveService;

/**
 * @author Bob
 *
 */
public class AbstractActiveServiceTest {

    private MockActiveService service;

    @Before
    public void init() {
        this.service = new MockActiveService();
    }

    @Test
    public void testGetAll() {
        Assert.assertEquals(2, this.service.getAll(null, 0, 2)
                .getDomainCollection()
                .size());
    }

    @Test
    public void testGetAllActive() {
        Assert.assertEquals(2, this.service.getAllActive(null, 0, 100)
                .getDomainCollection()
                .size());
    }

    @Test
    public void testGetByFunctionalId() {
        Assert.assertEquals("F1", this.service.getByFunctionalId("F1")
                .getFunctionalId());
    }

    @Test
    public void testGetById() {
        Assert.assertEquals(new Integer(1), this.service.getByID(1)
                .getObject()
                .getId());
    }

    @Test(expected = WebApplicationException.class)
    public void testGetByIdEtagError() {
        final MockActiveDomainObject mockDomainObject = new MockActiveDomainObject();
        final GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject> object = new GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject>(
                new EntityTag("tag"), Instant.MIN, mockDomainObject, null);
        this.service.getByID(object)
                .getObject();
        Assert.fail();
    }

    @Test
    public void testGetByIdEtagModified() {
        final GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject> object = new GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject>(
                new EntityTag("tag"), Instant.EPOCH, new MockActiveDomainObject(), null);
        Assert.assertEquals(object.getObject(), this.service.getByID(object)
                .getObject());
    }

    @Test
    public void testGetByIdEtagUnModified() {
        final MockActiveDomainObject mockDomainObject = new MockActiveDomainObject();
        final GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject> object = new GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject>(
                new EntityTag("tag"), Instant.MAX, mockDomainObject, null);
        Assert.assertEquals(object, this.service.getByID(object));
    }

    @Test
    public void testGetIdByFunctionalId() {
        Assert.assertEquals(new Integer(1), this.service.getIdByFunctionalId("F1"));
    }

    @Test
    public void testUpdate() {
        Assert.assertEquals(new Integer(1), this.service.update(new MockActiveDomainObject(1, "F1"))
                .getId());
    }

    @Test(expected = WebApplicationException.class)
    public void testUpdateModified() {
        final MockActiveDomainObject mockDomainObject = new MockActiveDomainObject();
        final GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject> object = new GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject>(
                new EntityTag("tag"), Instant.MIN, mockDomainObject, null);
        this.service.update(object);
        Assert.fail();
    }

    @Test
    public void testUpdateUnModified() {
        final MockActiveDomainObject mockDomainObject = new MockActiveDomainObject();
        final GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject> object = new GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject>(
                new EntityTag("tag"), Instant.MAX, mockDomainObject, null);
        Assert.assertEquals(new Integer(1), this.service.update(object)
                .getObject()
                .getId());
    }
}
