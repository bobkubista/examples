package bobkubista.examples.utils.clients.todo;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Random;

import org.apache.catalina.LifecycleException;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericActiveRestProxy;

public abstract class BaseClientRestIT<TYPE extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>> {

    private static final int port = new Random().nextInt(1000) + 10000;

    protected static final String BASE_URI = "http://localhost:" + port;

    private HttpServer server;

    private AbstractGenericActiveRestProxy<TYPE, ID, COL> client;

    @Before
    public void setUp() throws Exception {
        // TODO add dbunit
        this.server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI));
        final WebappContext context = this.getContext();
        context.deploy(this.server);
        this.server.start();

        this.client = this.getClient();
        this.client.base();
    }

    @After
    public void tearDown() throws LifecycleException {
        this.server.shutdown();
    }

    @Test
    public void testCreate() {
        final TYPE object = this.buildNew();
        final String result = this.client.create(object);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.startsWith(BASE_URI));
    }

    @Test
    public void testGetAll() {
        final COL all = this.client.getAll(new ArrayList<String>(), 0, 2);
        Assert.assertNotNull(all);
    }

    protected abstract TYPE buildNew();

    protected abstract AbstractGenericActiveRestProxy<TYPE, ID, COL> getClient();

    protected abstract WebappContext getContext();
}
