package bobkubista.examples.utils.service.jpa.persistance;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.web.filter.RequestContextFilter;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

/**
 *
 * @author Bob
 *
 *         Jersey test base
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/jersey-dbunit-config.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "dbUnitDatabaseConnection")
public abstract class BaseJerseyDbUnitTest extends JerseyTest {

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

        rc.property("contextConfigLocation", "classpath:jersey-dbunit-config.xml");
        return this.configure(rc);
    }

}
