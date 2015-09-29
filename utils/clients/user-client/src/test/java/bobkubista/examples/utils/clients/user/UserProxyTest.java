/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.clients.user;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import bobkubista.examples.services.api.user.domain.UserCollection;

/**
 * @author Bob
 *
 */
@Ignore
public class UserProxyTest {

    @Inject
    private UserProxy proxy;

    @Test
    public void testGetAll() {
        final Response response = this.proxy.getAll();
        Assert.assertNotNull(response);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        final UserCollection textItems = response.readEntity(UserCollection.class);
        Assert.assertNotNull(textItems);
        Assert.assertNotNull(textItems.getDomainCollection());
    }

    @Test
    public void testGetBasePath() {
        Assert.assertEquals("textitems", this.proxy.getBasePath());
    }

}
