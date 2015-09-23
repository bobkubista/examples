/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.cdi;

import bobkubista.examples.utils.service.jpa.persistance.AbstractBaseJerseyDbUnitTest;

/**
 * @author Bob
 *
 */
public abstract class AbstractBaseCdiJerseyDbUnitTest extends AbstractBaseJerseyDbUnitTest {

    private static final String CLASSPATH_JERSEY_CDI_DBUNIT_CONFIG_XML = "classpath:jersey-cdi-dbunit-config.xml";

    @Override
    protected String getContextConfigLocation() {
        return CLASSPATH_JERSEY_CDI_DBUNIT_CONFIG_XML;
    }

}
