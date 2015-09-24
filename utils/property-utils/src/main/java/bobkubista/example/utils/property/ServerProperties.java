package bobkubista.example.utils.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton get property config file with name server.prop from classpath
 *
 * @author Bob Kubista
 *
 */
public enum ServerProperties {
    INSTANCE;
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerProperties.class);

    private static Properties props;

    private static final String SERVER_PROP_FILE = "server.properties";

    static {
        if (props == null) {
            InputStream serverPropLocation = null;
            File configFile;
            try {
                try {
                    LOGGER.info("Getting resource file location from JDNI");
                    String propFolder;
                    // http://stackoverflow.com/questions/13956651/externalizing-tomcat-webapp-config-from-war-file

                    // get a handle on the JNDI root context
                    final Context ctx = new InitialContext();

                    // and access the environment variable for this web
                    // component
                    propFolder = (String) ctx.lookup("java:comp/env/configurationPath");
                    configFile = new File(propFolder + ServerProperties.SERVER_PROP_FILE);
                    serverPropLocation = new FileInputStream(configFile);
                } catch (NamingException | FileNotFoundException e1) {

                    LOGGER.warn("Defaulting back to classpath", e1);
                    LOGGER.debug("Getting resource file location from classpath");
                    serverPropLocation = Thread.currentThread().getContextClassLoader().getResourceAsStream(ServerProperties.SERVER_PROP_FILE);
                }

                loadProperties(serverPropLocation);
            } finally {
                if (serverPropLocation != null) {
                    try {
                        serverPropLocation.close();
                    } catch (final IOException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }
        }
    }

    /**
     * Get all properties
     *
     * @return
     */
    public static Properties getProperies() {
        return props;
    }

    /**
     *
     * @param key
     *            of the property
     * @return the property value
     */
    public static String getString(final String key) {
        String result = null;
        LOGGER.debug("Getting property for key {}", key);
        result = props.getProperty(key);
        LOGGER.debug("Got property for key {} and value {}", key, result);
        return result;
    }

    private static void loadProperties(final InputStream serverPropLocation) {
        props = new Properties();
        if (serverPropLocation != null) {
            try {
                LOGGER.debug("Loading resource file location from classpath");
                LOGGER.debug("Loading properties");
                props.load(serverPropLocation);
            } catch (final IOException e) {
                LOGGER.error("Could not load file", e);
            }
        }
    }

}
