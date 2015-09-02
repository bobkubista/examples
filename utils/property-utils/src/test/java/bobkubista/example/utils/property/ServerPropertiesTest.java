/**
 *
 */
package bobkubista.example.utils.property;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bob Kubista
 *
 */
public class ServerPropertiesTest {

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
