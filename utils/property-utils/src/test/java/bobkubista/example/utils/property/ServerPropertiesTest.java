/**
 *
 */
package bobkubista.example.utils.property;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bob Kubista
 *
 */
public class ServerPropertiesTest {

    @Test
    public void testGetProperies() {
        final Properties result = ServerProperties.getProperies();
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(1, result.size());
    }

    /**
     * Test method for
     * {@link bobkubista.example.utils.property.ServerProperties#getString(java.lang.String)}
     * .
     */
    @Test
    public void testGetString() {
        final String value = ServerProperties.getString("test");
        Assert.assertEquals("test1", value);
    }

}
