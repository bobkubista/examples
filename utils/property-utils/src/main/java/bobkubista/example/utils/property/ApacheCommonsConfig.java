package bobkubista.example.utils.property;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

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
                // SystemConfiguration systemBuilder = new
                // SystemConfiguration();
                // config = systemBuilder.interpolatedConfiguration();
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
        config.addProperty(key, value);
    }

    @Override
    public void clear() {
        config.clear();
    }

    @Override
    public void clearProperty(final String key) {
        config.clearProperty(key);
    }

    @Override
    public boolean containsKey(final String key) {
        return config.containsKey(key);
    }

    @Override
    public BigDecimal getBigDecimal(final String key) {
        return config.getBigDecimal(key);
    }

    @Override
    public BigDecimal getBigDecimal(final String key, final BigDecimal defaultValue) {
        return config.getBigDecimal(key, defaultValue);
    }

    @Override
    public BigInteger getBigInteger(final String key) {
        return config.getBigInteger(key);
    }

    @Override
    public BigInteger getBigInteger(final String key, final BigInteger defaultValue) {
        return config.getBigInteger(key, defaultValue);
    }

    @Override
    public boolean getBoolean(final String key) {
        return config.getBoolean(key);
    }

    @Override
    public boolean getBoolean(final String key, final boolean defaultValue) {
        return config.getBoolean(key, defaultValue);
    }

    @Override
    public Boolean getBoolean(final String key, final Boolean defaultValue) {
        return config.getBoolean(key, defaultValue);
    }

    @Override
    public byte getByte(final String key) {
        return config.getByte(key);
    }

    @Override
    public byte getByte(final String key, final byte defaultValue) {
        return config.getByte(key, defaultValue);
    }

    @Override
    public Byte getByte(final String key, final Byte defaultValue) {
        return config.getByte(key, defaultValue);
    }

    @Override
    public double getDouble(final String key) {
        return config.getDouble(key);
    }

    @Override
    public double getDouble(final String key, final double defaultValue) {
        return config.getDouble(key, defaultValue);
    }

    @Override
    public Double getDouble(final String key, final Double defaultValue) {
        return config.getDouble(key, defaultValue);
    }

    @Override
    public float getFloat(final String key) {
        return config.getFloat(key);
    }

    @Override
    public float getFloat(final String key, final float defaultValue) {
        return config.getFloat(key, defaultValue);
    }

    @Override
    public Float getFloat(final String key, final Float defaultValue) {
        return config.getFloat(key, defaultValue);
    }

    @Override
    public int getInt(final String key) {
        return config.getInt(key);
    }

    @Override
    public int getInt(final String key, final int defaultValue) {
        return config.getInt(key, defaultValue);
    }

    @Override
    public Integer getInteger(final String key, final Integer defaultValue) {
        return config.getInteger(key, defaultValue);
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
    public List<Object> getList(final String key) {
        return config.getList(key);
    }

    @Override
    public List<Object> getList(final String key, final List<?> defaultValue) {
        return config.getList(key, defaultValue);
    }

    @Override
    public long getLong(final String key) {
        return config.getLong(key);
    }

    @Override
    public long getLong(final String key, final long defaultValue) {
        return config.getLong(key, defaultValue);
    }

    @Override
    public Long getLong(final String key, final Long defaultValue) {
        return config.getLong(key, defaultValue);
    }

    @Override
    public Properties getProperties(final String key) {
        return config.getProperties(key);
    }

    @Override
    public Object getProperty(final String key) {
        return config.getProperty(key);
    }

    @Override
    public short getShort(final String key) {
        return config.getShort(key);
    }

    @Override
    public short getShort(final String key, final short defaultValue) {
        return config.getShort(key, defaultValue);
    }

    @Override
    public Short getShort(final String key, final Short defaultValue) {
        return config.getShort(key, defaultValue);
    }

    @Override
    public String getString(final String key) {
        return config.getString(key);
    }

    @Override
    public String getString(final String key, final String defaultValue) {
        return config.getString(key, defaultValue);
    }

    @Override
    public String[] getStringArray(final String key) {
        return config.getStringArray(key);
    }

    @Override
    public boolean isEmpty() {
        return config.isEmpty();
    }

    @Override
    public void setProperty(final String key, final Object value) {
        config.setProperty(key, value);
    }

    @Override
    public Configuration subset(final String prefix) {
        return config.subset(prefix);
    }

}
