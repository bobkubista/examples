package bobkubista.examples.utils.service.jpa.persistance;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.CharEncoding;
import org.flywaydb.core.Flyway;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.example.utils.property.ServerProperties;

/**
 * @author bkubista
 *
 */
public class FlywayIntegrator implements Integrator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlywayIntegrator.class);

    @Override
    public void disintegrate(final SessionFactoryImplementor sessionFactory, final SessionFactoryServiceRegistry serviceRegistry) {
        // not needed

    }

    @Override
    public void integrate(final Configuration configuration, final SessionFactoryImplementor sessionFactory, final SessionFactoryServiceRegistry serviceRegistry) {

        // Create the Flyway instance
        final Flyway flyway = new Flyway();

        // Point it to the database
        flyway.setDataSource(ServerProperties.getString("database.url"), ServerProperties.getString("database.username"), ServerProperties.getString("database.password"));

        flyway.setEncoding(CharEncoding.UTF_8);
        flyway.setOutOfOrder(true);
        flyway.setLocations("classpath:sql");
        final Map<String, String> placeHolders = new HashMap<>();
        final String defaultSchema = ServerProperties.getString("database.defaultSchema");
        placeHolders.put("schema", defaultSchema);
        flyway.setPlaceholders(placeHolders);
        flyway.setSchemas(defaultSchema);
        flyway.setTable("schema_version");

        LOGGER.info("Starting database migration");
        flyway.migrate();
        LOGGER.info("Finished database migration");

        LOGGER.info("Starting database validation");
        flyway.validate();
        LOGGER.info("Finished database validation");
    }

    @Override
    public void integrate(final MetadataImplementor metadata, final SessionFactoryImplementor sessionFactory, final SessionFactoryServiceRegistry serviceRegistry) {
        // not needed

    }

}
