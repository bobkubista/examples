package bobkubista.example.utils.property;

import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Supplier;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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
                final Configuration systemConfig = new SystemConfiguration();
                final Configurations configs = new Configurations();
                Configuration defaultConfig;
                try {
                    final Optional<URL> serverPropLocation = Optional.ofNullable(Thread.currentThread()
                            .getContextClassLoader()
                            .getResource(SERVER_PROP_FILE));

                    defaultConfig = configs.properties(serverPropLocation.map(l -> l.toString())
                            .orElse(loadPropertyLocationFromJDNI().orElse(Thread.currentThread()
                                    .getContextClassLoader()
                                    .getResource(ServerProperties.SERVER_PROP_FILE)
                                    .toString())));
                    defaultConfig.getKeys()
                            .forEachRemaining(key -> ServerProperties.setDefaults(key, systemConfig, defaultConfig.getProperty(key)));
                } catch (final ConfigurationException e) {
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

    private static Optional<String> loadPropertyLocationFromJDNI() {
        // http://stackoverflow.com/questions/13956651/externalizing-tomcat-webapp-config-from-war-file
        String propFolder;
        // get a handle on the JNDI root context
        Context ctx;
        // and access the environment variable for this web
        // component
        try {
            ctx = new InitialContext();
            propFolder = (String) ctx.lookup("java:comp/env/configurationPath");
            return Optional.of(propFolder + ServerProperties.SERVER_PROP_FILE);
        } catch (final NamingException e) {
            LOGGER.debug("Defaulting back to classpath", e);
            LOGGER.debug("Getting resource file location from classpath");
            return Optional.empty();
        }

    }

    private static void setDefaults(final String key, final Configuration systemConfig, final Object defaults) {
        if (!systemConfig.containsKey(key)) {
            LOGGER.debug("Setting default for key: {} and value: {}", key, defaults);
            systemConfig.addProperty(key, defaults);
        }
    }
}
