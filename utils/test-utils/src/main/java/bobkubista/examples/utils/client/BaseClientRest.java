package bobkubista.examples.utils.client;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
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
import bobkubista.examples.utils.rest.utils.service.GenericETagModifiedDateDomainObjectDecorator;

public abstract class BaseClientRest<TYPE extends AbstractGenericActiveDomainObject, COL extends AbstractGenericDomainObjectCollection<TYPE>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseClientRest.class);

	private static final int port = new Random().nextInt(1000) + 10000;

	protected static final String BASE_URI = "http://localhost:" + port;

	private static ComboPooledDataSource source;

	private static IDatabaseConnection connection;

	private static IDataSet dataset;

	@AfterClass
	public static void afterClass() throws SQLException {
		// TODO close dbunit database connection
		connection.close();
		source.close();
	}

	@BeforeClass
	public static void beforeClass() throws PropertyVetoException, DatabaseUnitException, SQLException {
		// TODO dbunit

		final String schema = ServerProperties.get()
				.getString("database.defaultSchema");
		source = new ComboPooledDataSource();
		source.setDriverClass("org.postgresql.Driver");
		source.setJdbcUrl(ServerProperties.get()
				.getString("database.url"));
		source.setUser(ServerProperties.get()
				.getString("database.username"));
		source.setPassword(ServerProperties.get()
				.getString("database.password"));
		source.setMinPoolSize(Integer.valueOf(ServerProperties.get()
				.getString("database.minPoolSize")));
		source.setMaxPoolSize(Integer.valueOf(ServerProperties.get()
				.getString("database.maxPoolSize")));
		source.setIdleConnectionTestPeriod(Integer.valueOf(ServerProperties.get()
				.getString("database.idleConnectionTestPeriod")));
		connection = new DatabaseConnection(source.getConnection(), schema);

		final FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
		flatXmlDataSetBuilder.setColumnSensing(true);
		final InputStream dataSet = BaseClientRest.class.getResourceAsStream("/dataset/given/FacadeIT.xml");
		LOGGER.info("Loading database content for testing: {}", dataset);
		dataset = flatXmlDataSetBuilder.build(dataSet);
	}

	private HttpServer server;

	private AbstractGenericActiveRestProxy<TYPE, COL> client;

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
	}

	@After
	public void tearDown() {
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

	@Test
	public void testUpdate() {
		final Long id = this.getIdentifier();
		final GenericETagModifiedDateDomainObjectDecorator<TYPE> object = this.client.getByID(id);
		this.updateObject(object);
		final GenericETagModifiedDateDomainObjectDecorator<TYPE> result = this.client.update(object);
		Assert.assertNotEquals(object.getETag(), result.getETag());
	}

	protected abstract void buildContext(WebappContext context);

	protected abstract TYPE buildNew();

	protected abstract AbstractGenericActiveRestProxy<TYPE, COL> getClient();

	protected abstract Long getIdentifier();

	protected abstract void updateObject(GenericETagModifiedDateDomainObjectDecorator<TYPE> object);

	private void cleanBD() throws DatabaseUnitException, SQLException {
		DatabaseOperation.DELETE_ALL.execute(connection, dataset);
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
	}
}
