/**
 * Bob Kubista's examples
 */
package bobkubista.example.utils.property;

import java.util.Properties;

import org.apache.commons.configuration2.Configuration;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bob
 *
 */
public class ServerPropertiesTest {

    /**
     * Test method for
     * {@link bobkubista.example.utils.property.ServerProperties#get()}.
     */
    @Test
    public void testGetDefaults() {
        final Configuration properties = ServerProperties.get();
        Assert.assertNotNull(properties);
        Assert.assertNotNull(properties.getKeys());
        Assert.assertEquals("test1", properties.getString("test"));
        Assert.assertNotNull(properties.getString("java.version"));
    }

    @Test
    public void testGetProperties() {
        final Properties properties = ServerProperties.getProperties();
        Assert.assertEquals("test1", properties.getProperty("test"));
        Assert.assertNotNull(properties.getProperty("user.home"));
    }

}
