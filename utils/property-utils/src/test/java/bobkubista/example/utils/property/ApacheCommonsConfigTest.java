/**
 * Bob Kubista's examples
 */
package bobkubista.example.utils.property;

import org.apache.commons.configuration2.Configuration;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bob
 *
 */
public class ApacheCommonsConfigTest {

    /**
     * Test method for
     * {@link bobkubista.example.utils.property.ApacheCommonsConfig#get()}.
     */
    @Test
    public void testGetDefaults() {
        final Configuration properties = ApacheCommonsConfig.INSTANCE.get();
        Assert.assertNotNull(properties);
        Assert.assertNotNull(properties.getKeys());
        Assert.assertEquals("test1", properties.getString("test"));
        Assert.assertNotNull(properties.getString("java.version"));
    }

}
