package bobkubista.examples.utils.clients.todo;

import java.io.File;
import java.util.ArrayList;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;

public class TodoProxyIT {

    private TodoProxy client;

    private Tomcat tomcat;

    @BeforeClass
    public void setUp() throws Exception {
        // TODO add dbunit
        // TODO make sure that the property file is gotten from this test
        // classpath instead of the server classpath

        this.tomcat = new Tomcat();
        final String serverPort = ServerProperties.getString("server.test.port");
        this.tomcat.setPort(Integer.valueOf(serverPort));
        final String tmpDirPath = System.getProperty("java.io.tmpdir");
        this.tomcat.setBaseDir(tmpDirPath);
        this.tomcat.getHost()
                .setAppBase(tmpDirPath);
        this.tomcat.getHost()
                .setAutoDeploy(true);
        this.tomcat.getHost()
                .setDeployOnStartup(true);
        // this.tomcat.addWebapp(this.tomcat.getHost(), docBase);

        final Context context = this.tomcat.addContext("/", tmpDirPath);
        context.setConfigFile(new File(tmpDirPath + "/WEB-INF/web.xml").toURI()
                .toURL());

        this.tomcat.start();
        this.setClient();
    }

    @AfterClass
    public void tearDown() throws LifecycleException {
        this.tomcat.stop();
    }

    @Test
    public void testASDF() {
        final TodoListCollection all = this.client.getAll(new ArrayList<String>(), 0, 2);
        Assert.assertNotNull(all);

    }

    protected void setClient() {
        this.client = new TodoProxy();
        this.client.base();
    }
}
