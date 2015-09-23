/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.springframework.web.filter.RequestContextFilter;

import com.github.springtestdbunit.annotation.DbUnitConfiguration;

/**
 * @author Bob
 *
 */

@DbUnitConfiguration(databaseConnection = "dbUnitDatabaseConnection")
public abstract class AbstractBaseJerseyDbUnitTest extends JerseyTest {
    @PersistenceContext
    private EntityManager em;

    /**
     * Clear cache
     */
    @Before
    public void clearCache() {
        this.em.getEntityManagerFactory().getCache().evictAll();
    }

    /**
     * Configure
     *
     * @param rc
     *            {@link ResourceConfig}
     * @return {@link ResourceConfig}
     */
    public abstract ResourceConfig configure(ResourceConfig rc);

    @Override
    protected Application configure() {
        final ResourceConfig rc = new ResourceConfig().register(RequestContextFilter.class);

        rc.property("contextConfigLocation", this.getContextConfigLocation());
        return this.configure(rc);
    }

    protected abstract String getContextConfigLocation();
}
