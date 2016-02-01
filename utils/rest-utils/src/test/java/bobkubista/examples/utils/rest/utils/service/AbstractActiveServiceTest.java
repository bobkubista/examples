/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.service;

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
                .getId());
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
}
