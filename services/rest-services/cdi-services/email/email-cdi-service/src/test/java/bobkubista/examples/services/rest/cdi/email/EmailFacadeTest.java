/**
 *
 */
package bobkubista.examples.services.rest.cdi.email;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dumbster.smtp.SimpleSmtpServer;

import bobkubista.example.utils.property.ApacheCommonsConfig;
import bobkubista.examples.services.api.email.model.EmailContext;
import bobkubista.examples.services.api.email.model.EmailContext.EmailBuilder;

/**
 * @author Bob
 *
 */
public class EmailFacadeTest {

    private static SimpleSmtpServer server;

    private final EmailFacade facade = new EmailFacade();

    @After
    public void afterClass() {
        server.stop();
    }

    @Before
    public void beforeClass() {
        server = SimpleSmtpServer.start(Integer.valueOf(ApacheCommonsConfig.INSTANCE.get()
                .getString("email.smtp.port")));
    }

    /**
     * Test method for
     * {@link bobkubista.examples.services.rest.cdi.email.EmailFacade#sendEmail(bobkubista.examples.services.api.email.model.EmailContext)}
     * .
     */
    @Test
    public void testSendEmail() {
        final EmailContext context = new EmailBuilder("bla@foo.bar", "foobar").build();
        final Response result = this.facade.sendEmail(context);

        Assert.assertNotNull(result);
        Assert.assertEquals(200, result.getStatus());
    }

}
