package bobkubista.example.utils.property;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Supplier;

import javax.resource.spi.IllegalStateException;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.SystemConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Config wrapper.
 *
 * @author Bob Kubista
 *
 */
public enum ServerProperties {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerProperties.class);

    private static final String SERVER_PROP_FILE = "server.properties";

    private static Supplier<Configuration> config = () -> ServerProperties.getConfig();

    public static Configuration get() {
        return ServerProperties.config.get();
    }

    public static Properties getProperties() {
        final Properties props = new Properties();
        ServerProperties.config.get()
                .getKeys()
                .forEachRemaining(key -> props.put(key, ServerProperties.config.get()
                        .getProperty(key)));
        return props;
    }

    private static synchronized Configuration getConfig() {
        class InternalConfigFactory implements Supplier<Configuration> {

            private final Configuration config = this.create();

            @Override
            public Configuration get() {
                return this.config;
            }

            private Configuration create() {
                // load default properties from classpath

                // loop through default properties
                // check if system properties contains the default properties
                // if not available, then set default property to system
                // property
                // return system properties
                final SystemConfiguration systemConfig = new SystemConfiguration();
                final Configurations configs = new Configurations();
                final Configuration defaultConfig;
                try {
                    final Optional<URL> serverPropLocation = Optional.ofNullable(Thread.currentThread()
                            .getContextClassLoader()
                            .getResource(SERVER_PROP_FILE));

                    defaultConfig = configs.properties(new File(serverPropLocation.orElseThrow(() -> new IllegalStateException("No default server.properties file found"))
                            .toURI()));
                    defaultConfig.getKeys()
                            .forEachRemaining(key -> ServerProperties.setDefaults(key, systemConfig, defaultConfig.getProperty(key)));
                } catch (final ConfigurationException | URISyntaxException | IllegalStateException e) {
                    LOGGER.error("Could not find default properties", e);
                }
                return systemConfig;
            }
        }

        if (!InternalConfigFactory.class.isInstance(config)) {
            config = new InternalConfigFactory();
        }
        return config.get();

    }

    private static void setDefaults(final String key, final SystemConfiguration systemConfig, final Object defaults) {
        if (!systemConfig.containsKey(key)) {
            systemConfig.addProperty(key, defaults);
        }
    }
}
