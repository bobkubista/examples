package bobkubista.examples.utils.clients.todo;

import java.net.URI;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletRegistration;

import org.apache.catalina.LifecycleException;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;

public class TodoProxyIT {

    private static final int port = new Random().nextInt(1000) + 10000;

    private static final String BASE_URI = "http://localhost:" + port;

    private TodoProxy client;

    private HttpServer server;

    @Before
    public void setUp() throws Exception {
        // TODO add dbunit
        this.server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI));
        final WebappContext context = new WebappContext("Integration test webapp", "");
        final ServletRegistration registration = context.addServlet("Todo-rest-service", "org.glassfish.jersey.servlet.ServletContainer");
        registration.setInitParameter("jersey.config.server.provider.packages", "bobkubista.examples.services.rest.todo");
        registration.addMapping("/*");
        context.addContextInitParameter("contextConfigLocation", "classpath:spring/spring-config.xml");
        context.addListener("org.springframework.web.context.ContextLoaderListener");
        context.addListener("org.springframework.web.context.request.RequestContextListener");
        context.deploy(this.server);
        this.server.start();

        this.setClient();
    }

    @After
    public void tearDown() throws LifecycleException {
        this.server.shutdown();
    }

    @Test
    public void testCreate() {
        final TodoList object = new TodoList(true, UUID.randomUUID()
                .toString());
        final String result = this.client.create(object);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.startsWith(BASE_URI));
    }

    @Test
    public void testGetAll() {
        final TodoListCollection all = this.client.getAll(new ArrayList<String>(), 0, 2);
        Assert.assertNotNull(all);
    }

    protected void setClient() {
        this.client = new TodoProxy(BASE_URI);
        this.client.base();
    }
}
