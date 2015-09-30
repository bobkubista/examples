/**
 *
 */
package bobkubista.examples.services.rest.user;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.HibernateException;
import org.hibernate.internal.SessionImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.postgresql.ds.PGPoolingDataSource;

import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.services.api.user.domain.UserCollection;
import bobkubista.examples.utils.service.jpa.persistance.cdi.AbstractFunctionalCdiJerseyIT;

/**
 * @author Bob
 *
 */
@Ignore
public class UserFacadeIT extends AbstractFunctionalCdiJerseyIT<User, Long, UserCollection> {

    protected static IDatabaseConnection connection;

    protected static IDataSet dataset;
    protected static EntityManager entityManager;

    protected static EntityManagerFactory entityManagerFactory;
    private static final String FUNCTIONALID = "bla@foo.bar";
    private static final Long ID = 1L;
    private static final String PARTIAL_FUNCTIONAL_ID = "bla@";

    @BeforeClass
    public static void setUpClass() throws NamingException, HibernateException, DatabaseUnitException {
        // Create initial context
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
        System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
        final InitialContext ic = new InitialContext();

        ic.createSubcontext("java:");
        ic.createSubcontext("java:jboss");
        ic.createSubcontext("java:jboss/datasources");

        ic.createSubcontext("java:comp");
        ic.createSubcontext("java:comp/env");
        ic.bind("java:comp/env/configurationPath", "");

        // Construct DataSource
        final PGPoolingDataSource ds = new PGPoolingDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("Examples");
        ds.setDataSourceName("Examples");
        ds.setPortNumber(5432);
        ds.setUser(ServerProperties.getString("database.username"));
        ds.setPassword(ServerProperties.getString("database.password"));

        ic.bind("java:jboss/datasources/Examples", ds);

        entityManagerFactory = Persistence.createEntityManagerFactory("jpaData");
        entityManager = entityManagerFactory.createEntityManager();
        connection = new DatabaseConnection(((SessionImpl) entityManager.getDelegate()).connection());
        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());

    }

    @Override
    public ResourceConfig configure(final ResourceConfig rc) {
        return rc.register(UserFacade.class);
    }

    @Override
    protected void checkResponseGetAll(final UserCollection response, final int size) {
        Assert.assertNotNull(response);
        Assert.assertEquals(size, response.getDomainCollection().size());
        Assert.assertEquals(this.getId(), response.getDomainCollection().iterator().next().getId());

    }

    @Override
    protected void checkSingle(final User response) {
        Assert.assertNotNull(response);
        Assert.assertEquals(this.getFunctionalId(), response.getFunctionalId());
        Assert.assertTrue(response.isActive());

    }

    @Override
    protected void checkUpdated(final User response) {
        Assert.assertNotNull(response);
        Assert.assertEquals("123", response.getEncryptedPassword());
    }

    @Override
    protected User create() {
        final User user = new User();

        user.setActive(true);
        user.setEncryptedPassword("123");
        user.setFunctionalId("bla2@foo.bar");
        user.setId(2L);
        user.setName("Foo Bar2");

        return user;
    }

    @Override
    protected String getFunctionalId() {
        return FUNCTIONALID;
    }

    @Override
    protected Long getId() {
        return ID;
    }

    @Override
    protected String getPartionFunctionalId() {
        return PARTIAL_FUNCTIONAL_ID;
    }

    @Override
    protected User update(final User response) {
        response.setEncryptedPassword("123");
        return response;
    }

}
