/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.cdi;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import bobkubista.examples.utils.service.jpa.persistance.AbstractBaseJerseyDbUnitTest;

/**
 * @author Bob
 *
 */
@ContextConfiguration(locations = { "/jersey-cdi-dbunit-config.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "dbUnitDatabaseConnection")
public abstract class AbstractBaseCdiJerseyDbUnitTest extends AbstractBaseJerseyDbUnitTest {

    private static final String CLASSPATH_JERSEY_CDI_DBUNIT_CONFIG_XML = "classpath:jersey-cdi-dbunit-config.xml";

    @Override
    protected String getContextConfigLocation() {
        return CLASSPATH_JERSEY_CDI_DBUNIT_CONFIG_XML;
    }

}
