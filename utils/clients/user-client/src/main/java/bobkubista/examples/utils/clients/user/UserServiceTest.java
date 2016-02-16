/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.clients.user;

import static org.junit.Assert.fail;

import java.net.URI;

import javax.servlet.ServletRegistration;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Bob
 *
 */
@Ignore
public class UserServiceTest {

    private static final String BASE_URI = "http://localhost:18080/";

    private HttpServer server;

    @Before
    public void setUp() throws Exception {
        this.server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI));
        final WebappContext context = new WebappContext("Unittest webapp", "");
        final ServletRegistration registration = context.addServlet("RestApp", "org.glassfish.jersey.servlet.ServletContainer");
        registration.setInitParameter("javax.ws.rs.Application", "bobkubista.example.ApplicationConfig");
        registration.addMapping("/*");
        context.deploy(this.server);
        this.server.start();
    }

    @After
    public void tearDown() {
        this.server.shutdown();
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.service.IdentifiableService#create(bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject)}
     * .
     */
    @Test
    public void testCreate() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.service.IdentifiableService#delete(java.io.Serializable)}
     * .
     */
    @Test
    public void testDelete() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.service.IdentifiableService#getAll(java.util.List, java.lang.Integer, java.lang.Integer)}
     * .
     */
    @Test
    public void testGetAll() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.service.ActiveService#getAllActive(java.util.List, java.lang.Integer, java.lang.Integer)}
     * .
     */
    @Test
    public void testGetAllActive() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.service.ActiveService#getAllActiveASync(java.util.List, java.lang.Integer, java.lang.Integer)}
     * .
     */
    @Test
    public void testGetAllActiveASync() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.service.IdentifiableService#getAllAsync(java.util.List, java.lang.Integer, java.lang.Integer)}
     * .
     */
    @Test
    public void testGetAllAsync() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.service.FunctionalIdentifiableService#getByFunctionalId(java.lang.String)}
     * .
     */
    @Test
    public void testGetByFunctionalId() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.service.IdentifiableService#getByID(java.io.Serializable)}
     * .
     */
    @Test
    public void testGetByID() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.service.FunctionalIdentifiableService#getIdByFunctionalId(java.lang.String)}
     * .
     */
    @Test
    public void testGetIdByFunctionalId() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.clients.user.UserService#isAuthorized(java.lang.Long, java.lang.String)}
     * .
     */
    @Test
    public void testIsAuthorized() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.service.IdentifiableService#update(bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject)}
     * .
     */
    @Test
    public void testUpdate() {
        fail("Not yet implemented");
    }

}
