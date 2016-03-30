package bobkubista.example.utils.property;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.function.Supplier;

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
public enum ApacheCommonsConfig implements Configuration {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApacheCommonsConfig.class);

    private static Supplier<Configuration> config = () -> {
        return getConfig();
    };

    private synchronized static Configuration getConfig() {
        class InternalConfigFactory implements Supplier<Configuration> {

            private final Configuration config = this.create();

            @Override
            public Configuration get() {
                return this.config;
            }

            private Configuration create() {
                try {
                    final JNDIConfiguration jndiBuilder = new JNDIConfiguration();
                    LOGGER.info("Loaded JNDI config");
                    return jndiBuilder.interpolatedConfiguration();
                } catch (final NamingException e) {
                    LOGGER.info("could not load form jndi", e);
                    // SystemConfiguration systemBuilder = new
                    // SystemConfiguration();
                    // config = systemBuilder.interpolatedConfiguration();
                    try {
                        final DefaultConfigurationBuilder propertiesBuilder = new DefaultConfigurationBuilder("server.properties");
                        LOGGER.info("Loaded server.properties config");
                        return propertiesBuilder.getConfiguration(true);
                    } catch (final ConfigurationException e1) {
                        try {
                            final DefaultConfigurationBuilder xmlBuilder = new DefaultConfigurationBuilder("server.xml");
                            LOGGER.info("Loaded server.xml config", e1);
                            return xmlBuilder.getConfiguration(true);
                        } catch (final ConfigurationException e2) {
                            final BaseConfiguration builder = new BaseConfiguration();
                            LOGGER.info("Loaded in memory config", e2);
                            return builder.interpolatedConfiguration();
                        }
                    }
                }
            }
        }

        if (!InternalConfigFactory.class.isInstance(config)) {
            config = new InternalConfigFactory();
        }
        return config.get();
    }

    @Override
    public void addProperty(final String key, final Object value) {
        config.get()
                .addProperty(key, value);
    }

    @Override
    public void clear() {
        config.get()
                .clear();
    }

    @Override
    public void clearProperty(final String key) {
        config.get()
                .clearProperty(key);
    }

    @Override
    public boolean containsKey(final String key) {
        return config.get()
                .containsKey(key);
    }

    @Override
    public BigDecimal getBigDecimal(final String key) {
        return config.get()
                .getBigDecimal(key);
    }

    @Override
    public BigDecimal getBigDecimal(final String key, final BigDecimal defaultValue) {
        return config.get()
                .getBigDecimal(key, defaultValue);
    }

    @Override
    public BigInteger getBigInteger(final String key) {
        return config.get()
                .getBigInteger(key);
    }

    @Override
    public BigInteger getBigInteger(final String key, final BigInteger defaultValue) {
        return config.get()
                .getBigInteger(key, defaultValue);
    }

    @Override
    public boolean getBoolean(final String key) {
        return config.get()
                .getBoolean(key);
    }

    @Override
    public boolean getBoolean(final String key, final boolean defaultValue) {
        return config.get()
                .getBoolean(key, defaultValue);
    }

    @Override
    public Boolean getBoolean(final String key, final Boolean defaultValue) {
        return config.get()
                .getBoolean(key, defaultValue);
    }

    @Override
    public byte getByte(final String key) {
        return config.get()
                .getByte(key);
    }

    @Override
    public byte getByte(final String key, final byte defaultValue) {
        return config.get()
                .getByte(key, defaultValue);
    }

    @Override
    public Byte getByte(final String key, final Byte defaultValue) {
        return config.get()
                .getByte(key, defaultValue);
    }

    @Override
    public double getDouble(final String key) {
        return config.get()
                .getDouble(key);
    }

    @Override
    public double getDouble(final String key, final double defaultValue) {
        return config.get()
                .getDouble(key, defaultValue);
    }

    @Override
    public Double getDouble(final String key, final Double defaultValue) {
        return config.get()
                .getDouble(key, defaultValue);
    }

    @Override
    public float getFloat(final String key) {
        return config.get()
                .getFloat(key);
    }

    @Override
    public float getFloat(final String key, final float defaultValue) {
        return config.get()
                .getFloat(key, defaultValue);
    }

    @Override
    public Float getFloat(final String key, final Float defaultValue) {
        return config.get()
                .getFloat(key, defaultValue);
    }

    @Override
    public int getInt(final String key) {
        return config.get()
                .getInt(key);
    }

    @Override
    public int getInt(final String key, final int defaultValue) {
        return config.get()
                .getInt(key, defaultValue);
    }

    @Override
    public Integer getInteger(final String key, final Integer defaultValue) {
        return config.get()
                .getInteger(key, defaultValue);
    }

    @Override
    public Iterator<String> getKeys() {
        return config.get()
                .getKeys();
    }

    @Override
    public Iterator<String> getKeys(final String prefix) {
        return config.get()
                .getKeys(prefix);
    }

    @Override
    public List<Object> getList(final String key) {
        return config.get()
                .getList(key);
    }

    @Override
    public List<Object> getList(final String key, final List<?> defaultValue) {
        return config.get()
                .getList(key, defaultValue);
    }

    @Override
    public long getLong(final String key) {
        return config.get()
                .getLong(key);
    }

    @Override
    public long getLong(final String key, final long defaultValue) {
        return config.get()
                .getLong(key, defaultValue);
    }

    @Override
    public Long getLong(final String key, final Long defaultValue) {
        return config.get()
                .getLong(key, defaultValue);
    }

    @Override
    public Properties getProperties(final String key) {
        return config.get()
                .getProperties(key);
    }

    @Override
    public Object getProperty(final String key) {
        return config.get()
                .getProperty(key);
    }

    @Override
    public short getShort(final String key) {
        return config.get()
                .getShort(key);
    }

    @Override
    public short getShort(final String key, final short defaultValue) {
        return config.get()
                .getShort(key, defaultValue);
    }

    @Override
    public Short getShort(final String key, final Short defaultValue) {
        return config.get()
                .getShort(key, defaultValue);
    }

    @Override
    public String getString(final String key) {
        return config.get()
                .getString(key);
    }

    @Override
    public String getString(final String key, final String defaultValue) {
        return config.get()
                .getString(key, defaultValue);
    }

    @Override
    public String[] getStringArray(final String key) {
        return config.get()
                .getStringArray(key);
    }

    @Override
    public boolean isEmpty() {
        return config.get()
                .isEmpty();
    }

    @Override
    public void setProperty(final String key, final Object value) {
        config.get()
                .setProperty(key, value);
    }

    @Override
    public Configuration subset(final String prefix) {
        return config.get()
                .subset(prefix);
    }

}
