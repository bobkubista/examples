/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.cache;

import org.junit.Before;

import bobkubista.examples.utils.rest.utils.mocks.MockActiveCache;
import bobkubista.examples.utils.rest.utils.mocks.MockActiveDomainObject;
import bobkubista.examples.utils.rest.utils.mocks.MockDomainCollection;

/**
 * @author Bob
 *
 */
public class AbstractActiveAutoCacheTest extends AbstractFunctionalAutoCacheTest {

    private MockActiveCache mock;

    @Override
    public AbstractFunctionalAutoCache<Integer, MockActiveDomainObject, MockDomainCollection> getMock() {
        return this.mock;
    }

    @Before
    @Override
    public void init() {
        this.mock = new MockActiveCache();
        this.mock.loadAll();
    }
}
