package bobkubista.example.utils.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton get property config file with name server.prop from classpath
 *
 * @author Bob Kubista
 *
 */
public final class ServerProperties {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerProperties.class);

	private static Properties props;

	private static final String SERVER_PROP_FILE = "server.properties";

	static {
		if (props == null) {
			LOGGER.debug("Getting resource file location from classpath");
			final InputStream serverPropLocation = Thread.currentThread().getContextClassLoader().getResourceAsStream(ServerProperties.SERVER_PROP_FILE);
			props = new Properties();

			if (serverPropLocation != null) {
				try {
					LOGGER.debug("Loading resource file location from classpath");
					LOGGER.debug("Loading properties");
					props.load(serverPropLocation);
				} catch (final IOException e) {
					LOGGER.error("Could not load file", e);
				} finally {
					if (serverPropLocation != null) {
						try {
							serverPropLocation.close();
						} catch (final IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public static Properties getProperies() {
		return props;
	}

	public static String getString(final String key) {
		String result = null;
		LOGGER.debug("Getting property for key {}", key);
		result = props.getProperty(key);
		LOGGER.debug("Got property for key {} and value {}", key, result);
		return result;
	}

}
