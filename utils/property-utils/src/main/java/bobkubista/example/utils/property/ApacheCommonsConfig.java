package bobkubista.example.utils.property;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.naming.NamingException;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.commons.configuration.JNDIConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Config wrapper.
 *
 * @author Bob Kubista
 *
 */
public enum ApacheCommonsConfig implements ServerConfig {
	INSTANCE;

	private static Configuration config;
	private static final Logger LOGGER = LoggerFactory.getLogger(ApacheCommonsConfig.class);

	static {
		if (config == null) {
			try {
				final JNDIConfiguration jndiBuilder = new JNDIConfiguration();
				config = jndiBuilder.interpolatedConfiguration();
				LOGGER.info("Loaded JNDI config");
			} catch (final NamingException e) {
				LOGGER.info("could not load form jndi", e);
				// SystemConfiguration systemBuilder = new SystemConfiguration()
				// config = systemBuilder.interpolatedConfiguration()
				try {
					final DefaultConfigurationBuilder propertiesBuilder = new DefaultConfigurationBuilder("server.properties");
					config = propertiesBuilder.getConfiguration(true);
					LOGGER.info("Loaded server.properties config");
				} catch (final ConfigurationException e1) {
					try {
						final DefaultConfigurationBuilder xmlBuilder = new DefaultConfigurationBuilder("server.xml");
						config = xmlBuilder.getConfiguration(true);
						LOGGER.info("Loaded server.xml config", e1);
					} catch (final ConfigurationException e2) {
						final BaseConfiguration builder = new BaseConfiguration();
						config = builder.interpolatedConfiguration();
						LOGGER.info("Loaded in memory config", e2);
					}
				}
			}
		}
	}

	@Override
	public void addProperty(final String key, final Object value) {
		config.setProperty(key, value);
	}

	@Override
	public boolean contains(final String key) {
		return config.containsKey(key);
	}

	@Override
	public BigDecimal getBigDecimal(final String key) {
		return config.getBigDecimal(key);
	}

	@Override
	public boolean getBoolean(final String key) {
		return config.getBoolean(key);
	}

	@Override
	public double getDouble(final String key) {
		return config.getDouble(key);
	}

	@Override
	public int getInt(final String key) {
		return config.getInt(key);
	}

	@Override
	public Iterator<String> getKeys() {
		return config.getKeys();
	}

	@Override
	public Iterator<String> getKeys(final String prefix) {
		return config.getKeys(prefix);
	}

	@Override
	public long getLong(final String key) {
		return config.getLong(key);
	}

	@Override
	public Object getProperty(final String key) {
		return config.getProperty(key);
	}

	@Override
	public String getString(final String key) {
		return config.getString(key);
	}

	@Override
	public String[] getStringArray(final String key) {
		return config.getStringArray(key);
	}

	@Override
	public void setProperty(final String key, final Object value) {
		config.setProperty(key, value);
	}
}
