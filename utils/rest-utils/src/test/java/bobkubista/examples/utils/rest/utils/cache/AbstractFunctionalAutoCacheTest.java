/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.cache;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bobkubista.examples.utils.rest.utils.mocks.MockActiveDomainObject;
import bobkubista.examples.utils.rest.utils.mocks.MockCache;

/**
 * @author Bob
 *
 */
public class AbstractFunctionalAutoCacheTest {
    private AbstractFunctionalAutoCache<Integer, MockActiveDomainObject> mock;

    /**
     * @return the mock
     */
    public AbstractFunctionalAutoCache<Integer, MockActiveDomainObject> getMock() {
        return this.mock;
    }

    @Before
    public void init() {
        this.setMock(new MockCache());
        this.getMock().loadAll();
    }

    /**
     * @param mock
     *            the mock to set
     */
    public void setMock(final MockCache mock) {
        this.mock = mock;
    }

    @Test
    public void testGetAll() {
        final Map<Integer, MockActiveDomainObject> result = this.getMock().getAll();
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void testGetAllKeys() throws ExecutionException {
        final Map<Integer, MockActiveDomainObject> result = this.getMock().getAll(Collections.singletonList(new Integer(1)));
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testGetWithFunctionalId() throws ExecutionException {
        final MockActiveDomainObject result = this.getMock().get("F1");
        Assert.assertEquals("F1", result.getFunctionalId());
    }

    @Test
    public void testGetWithId() throws ExecutionException {
        final MockActiveDomainObject result = this.getMock().get(1);
        Assert.assertEquals(new Integer(1), result.getId());
    }

    @Test
    public void testLoadAll() throws Exception {
        final Map<Integer, MockActiveDomainObject> result = this.getMock().loadAll(Collections.singletonList(new Integer(2)));
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void testRefresh() {
        Assert.assertTrue(this.getMock().refresh());
    }
}
