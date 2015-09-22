/**
 *
 */
package bobkubista.examples.services.rest.cdi.email;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.jboss.weld.environment.se.Weld;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;

import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.services.api.email.model.DateReplacement;
import bobkubista.examples.services.api.email.model.EmailContext;
import bobkubista.examples.services.api.email.model.EmailContext.EmailBuilder;
import bobkubista.examples.services.api.email.model.LinkReplacement;

/**
 * @author bkubista
 *
 */
@Ignore
// TODO fix test.
public class EmailFacadeIT extends JerseyTest {

    private static final String EMAIL_SUBJECT_HEADER = "Subject";

    private static SimpleSmtpServer server;

    @After
    public void afterClass() {
        server.stop();
    }

    @Before
    public void beforeClass() {
        server = SimpleSmtpServer.start(Integer.valueOf(ServerProperties.getString("email.smtp.port")));
    }

    /**
     * Test method for
     * {@link com.gamehouse.gameflix.services.webservices.emailservice.EmailFacade#sendEmail(com.gamehouse.gameflix.services.webservices.emailservice.api.domain.Email)}
     * .
     *
     * @throws URISyntaxException
     */
    @Test
    public void testSendEmail() throws URISyntaxException {
        final EmailContext email = new EmailBuilder("bla@foo.bar", "foobar").addReplacement(new DateReplacement(new Date()))
                .addReplacement(new LinkReplacement(new URI("http://bla.bla"))).build();
        final Response response = this.target("/").request(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON).put(Entity.entity(email.getEmail(), MediaType.APPLICATION_XML),
                Response.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(1, server.getReceivedEmailSize());
        final SmtpMessage recievedEmail = (SmtpMessage) server.getReceivedEmail().next();
        Assert.assertEquals("bla subject", recievedEmail.getHeaderValue(EMAIL_SUBJECT_HEADER));
    }

    @Override
    protected Application configure() {

        final Weld weld = new Weld();
        weld.initialize();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> weld.shutdown()));

        final ResourceConfig resourceConfig = new ResourceConfig(EmailFacade.class);

        return resourceConfig;
    }

}
