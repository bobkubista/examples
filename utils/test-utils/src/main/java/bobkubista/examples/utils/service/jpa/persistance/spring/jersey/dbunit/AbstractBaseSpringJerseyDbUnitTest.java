package bobkubista.examples.utils.service.jpa.persistance.spring.jersey.dbunit;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bobkubista.examples.utils.service.jpa.persistance.AbstractBaseJerseyDbUnitTest;

/**
 *
 * @author Bob
 *
 *         Jersey test base
 */
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractBaseSpringJerseyDbUnitTest extends AbstractBaseJerseyDbUnitTest {

    private static final String CLASSPATH_JERSEY_DBUNIT_CONFIG_XML = "classpath:jersey-dbunit-config.xml";

    @Override
    protected String getContextConfigLocation() {
        return CLASSPATH_JERSEY_DBUNIT_CONFIG_XML;
    }

}
