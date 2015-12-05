/**
 *
 */
package bobkubista.examples.utils.clients.todo;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"/applicationContext.xml"})
import bobkubista.examples.services.api.todo.domain.TodoListCollection;

/**
 * @author Bob Kubista
 *
 */
@Ignore // TODO
public class TodoProxyTest {

    @Inject
    private TodoProxy proxy;

    @Test
    public void testGetAll() {
        final Response response = this.proxy.getAll();
        Assert.assertNotNull(response);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        final TodoListCollection textItems = (TodoListCollection) response.getEntity();
        Assert.assertNotNull(textItems);
        Assert.assertNotNull(textItems.getDomainCollection());
    }

    @Test
    public void testGetBasePath() {
        Assert.assertEquals("textitems", this.proxy.getBasePath());
    }

}
