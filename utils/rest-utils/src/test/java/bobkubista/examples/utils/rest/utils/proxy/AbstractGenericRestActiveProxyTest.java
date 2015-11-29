/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.proxy;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Condition.delete;
import static com.xebialabs.restito.semantics.Condition.get;
import static com.xebialabs.restito.semantics.Condition.post;
import static com.xebialabs.restito.semantics.Condition.put;

import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.xebialabs.restito.semantics.Action;
import com.xebialabs.restito.server.StubServer;

import bobkubista.examples.utils.rest.utils.mocks.MockActiveDomainObject;
import bobkubista.examples.utils.rest.utils.mocks.MockActiveProxy;

/**
 * @see <a href=
 *      "http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/test/web/client/MockRestServiceServer.html">
 *      mock rest server</a>
 * @author Bob
 *
 */
public class AbstractGenericRestActiveProxyTest {

    private final AbstractGenericRestActiveProxy<MockActiveDomainObject, Integer> proxy = new MockActiveProxy();
    private StubServer server;

    @Before
    public void start() {
        this.server = new StubServer().run();
        this.proxy.base();
    }

    @After
    public void stop() {
        this.server.stop();
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestIdentifiableProxy#create(bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject)}
     * .
     */
    @Test
    public void testCreate() {
        whenHttp(this.server).match(post("http://localhost/")).then(Action.status(HttpStatus.CREATED_201));
        final Response result = this.proxy.create(new MockActiveDomainObject());
        Assert.assertEquals(201, result.getStatus());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestIdentifiableProxy#delete(java.io.Serializable)}
     * .
     */
    @Test
    public void testDelete() {
        whenHttp(this.server).match(delete("/")).then(Action.status(HttpStatus.OK_200));
        final Response result = this.proxy.delete(1);
        Assert.assertEquals(200, result.getStatus());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestIdentifiableProxy#getAll()}
     * .
     */
    @Test
    public void testGetAll() {
        whenHttp(this.server).match(get("/")).then(Action.status(HttpStatus.OK_200));
        final Response result = this.proxy.getAll();
        Assert.assertEquals(200, result.getStatus());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestActiveProxy#getAllActive()}
     * .
     */
    @Test
    public void testGetAllActive() {
        whenHttp(this.server).match(get("/")).then(Action.status(HttpStatus.OK_200));
        final Response result = this.proxy.getAllActive();
        Assert.assertEquals(200, result.getStatus());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestFunctionalIdentifiableProxy#getByFunctionalId(java.lang.String)}
     * .
     */
    @Test
    public void testGetByFunctionalId() {
        whenHttp(this.server).match(get("/functionId/blaat")).then(Action.status(HttpStatus.OK_200));
        final Response result = this.proxy.getByFunctionalId("blaat");
        Assert.assertEquals(200, result.getStatus());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestIdentifiableProxy#getByID(java.io.Serializable)}
     * .
     */
    @Test
    public void testGetByID() {
        whenHttp(this.server).match(get("/functionId/1")).then(Action.status(HttpStatus.OK_200));
        final Response result = this.proxy.getByID(1);
        Assert.assertEquals(200, result.getStatus());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestFunctionalIdentifiableProxy#getIdByFunctionalId(java.lang.String)}
     * .
     */
    @Test
    public void testGetIdByFunctionalId() {
        whenHttp(this.server).match(get("/functionId/blaat")).then(Action.status(HttpStatus.OK_200));
        final Response result = this.proxy.getIdByFunctionalId("blaat");
        Assert.assertEquals(200, result.getStatus());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestFunctionalIdentifiableProxy#searchByFunctionalID(java.lang.String)}
     * .
     */
    @Test
    public void testSearchByFunctionalID() {
        whenHttp(this.server).match(get("/searchByFunctionalId/bla")).then(Action.status(HttpStatus.OK_200));
        final Response result = this.proxy.searchByFunctionalID("bla");
        Assert.assertEquals(200, result.getStatus());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestIdentifiableProxy#update(bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject)}
     * .
     */
    @Test
    public void testUpdate() {
        whenHttp(this.server).match(put("/")).then(Action.status(HttpStatus.OK_200));
        final Response result = this.proxy.update(new MockActiveDomainObject());
        Assert.assertEquals(200, result.getStatus());
    }
}
