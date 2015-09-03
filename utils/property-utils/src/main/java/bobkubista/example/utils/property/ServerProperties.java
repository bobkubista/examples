package bobkubista.example.utils.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

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

	private static Properties props;;

	private static final String SERVER_PROP_FILE = "server.properties";

	static {
		if (props == null) {
			LOGGER.debug("Getting resource file location from classpath");
			final URL serverPropLocation = Thread.currentThread().getContextClassLoader().getResource(ServerProperties.SERVER_PROP_FILE);
			props = new Properties();

			if (serverPropLocation != null) {
				final File configFile;
				InputStream stream = null;
				try {
					LOGGER.debug("Loading resource file location from classpath");
					configFile = new File(serverPropLocation.toURI());
					stream = new FileInputStream(configFile);
					LOGGER.debug("Loading properties");
					props.load(stream);
				} catch (final URISyntaxException | IOException e) {
					LOGGER.error("Could not load file", e);
				} finally {
					if (stream != null) {
						try {
							stream.close();
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
