package bobkubista.example.utils.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Singleton get property config file with name server.prop from classpath
 *
 * @author Bob Kubista
 *
 */
public enum ServerProperties {

	INSTANCE;

	private Properties props;

	private Properties getPropertiesFromFile() throws IOException {
		if (this.props == null) {
			final URL serverPropLocation = Thread.currentThread().getContextClassLoader().getResource("server.prop");
			this.props = new Properties();

			if (serverPropLocation != null) {
				final File configFile;
				try {
					configFile = new File(serverPropLocation.toURI());
					final InputStream stream = new FileInputStream(configFile);
					this.props.load(stream);
				} catch (final Exception e) {
				}
			}
		}
		return this.props;
	}

	public String getString(final String key) {
		String result = null;
		try {
			final Properties props = this.getPropertiesFromFile();
			result = props.getProperty(key);
		} catch (final IOException e) {

		}
		return result;
	}

}
