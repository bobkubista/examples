/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.clients.user;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bob
 *
 */
public class UserProxyTest {

    private final UserProxy proxy = new UserProxy();

    @Test
    public void testGetBasePath() {
        Assert.assertEquals("", this.proxy.getBasePath());
    }

}
