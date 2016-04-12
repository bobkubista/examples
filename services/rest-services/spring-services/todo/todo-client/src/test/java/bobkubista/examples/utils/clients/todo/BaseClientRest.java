package bobkubista.examples.utils.clients.todo;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.catalina.LifecycleException;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericActiveRestProxy;

public abstract class BaseClientRest<TYPE extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseClientRest.class);

    private static final int port = new Random().nextInt(1000) + 10000;

    protected static final String BASE_URI = "http://localhost:" + port;

    private static ComboPooledDataSource source;

    private static IDatabaseConnection connection;

    private static IDataSet dataset;

    private HttpServer server;

    private AbstractGenericActiveRestProxy<TYPE, ID, COL> client;

    @AfterClass
    public static void afterClass() throws SQLException {
        // TODO close dbunit database connection
        connection.close();
        source.close();
    }

    @BeforeClass
    public static void beforeClass() throws PropertyVetoException, DatabaseUnitException, SQLException {
        // TODO dbunit

        final String schema = ServerProperties.getString("database.defaultSchema");
        source = new ComboPooledDataSource();
        source.setDriverClass("org.postgresql.Driver");
        source.setJdbcUrl(ServerProperties.getString("database.url"));
        source.setUser(ServerProperties.getString("database.username"));
        source.setPassword(ServerProperties.getString("database.password"));
        source.setMinPoolSize(Integer.valueOf(ServerProperties.getString("database.minPoolSize")));
        source.setMaxPoolSize(Integer.valueOf(ServerProperties.getString("database.maxPoolSize")));
        source.setIdleConnectionTestPeriod(Integer.valueOf(ServerProperties.getString("database.idleConnectionTestPeriod")));
        connection = new DatabaseConnection(source.getConnection(), schema);

        final FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
        flatXmlDataSetBuilder.setColumnSensing(true);
        final InputStream dataSet = BaseClientRest.class.getResourceAsStream("/dataset/given/FacadeIT.xml");
        LOGGER.info("Loading database content for testing: {}", dataset);
        dataset = flatXmlDataSetBuilder.build(dataSet);
    }

    @Before
    public void setUp() throws Exception {
        // TODO maybe put this in the before class
        this.server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI));
        final WebappContext context = new WebappContext("Integration test webapp", "");
        this.buildContext(context);
        context.deploy(this.server);
        this.server.start();

        this.cleanBD();
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
        Assert.assertTrue(all.getAmount() > 0);
        Assert.assertFalse(all.getDomainCollection()
                .isEmpty());
        Assert.assertTrue(all.getDomainCollection()
                .size() < 3);
    }

    protected abstract void buildContext(WebappContext context);

    protected abstract TYPE buildNew();

    protected abstract AbstractGenericActiveRestProxy<TYPE, ID, COL> getClient();

    private void cleanBD() throws DatabaseUnitException, SQLException {
        // DatabaseOperation.DELETE_ALL.execute(connection, dataset);
        // DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
    }
}
