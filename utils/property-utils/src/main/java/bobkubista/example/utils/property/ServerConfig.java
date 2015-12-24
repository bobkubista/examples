package bobkubista.example.utils.property;

import java.math.BigDecimal;
import java.util.Iterator;

/**
 * Interface for defining my config needs. This can have multiple
 * implementations as different frameworks are used. @author Bob Kubista
 *
 */
public interface ServerConfig {

	/**
	 * Add a property to the configuration. If it already exists then the value
	 * stated here will be added to the configuration entry. For example, if the
	 * property:
	 *
	 * <pre> resource.loader = file </pre>
	 *
	 * is already present in the configuration and you call
	 *
	 * <pre> addProperty("resource.loader", "classpath") </pre>
	 *
	 * Then you will end up with a List like the following:
	 *
	 * <pre> ["file", "classpath"] </pre>
	 *
	 * @param key The key to add the property to. @param value The value to add.
	 */
	void addProperty(String key, Object value);

	/**
	 * Check if the configuration contains the specified key.
	 *
	 * @param key the key whose presence in this configuration is to be tested
	 *
	 * @return {@code true} if the configuration contains a value for this key,
	 * {@code false} otherwise
	 */
	boolean contains(String key);

	/**
	 * Get a {@link BigDecimal} associated with the given configuration key.
	 *
	 * @param key The configuration key. @return The associated BigDecimal if
	 * key is found and has valid format
	 */
	BigDecimal getBigDecimal(String key);

	/**
	 * Get a boolean associated with the given configuration key.
	 *
	 * @param key The configuration key. @return The associated boolean.
	 *
	 * @throws ConversionException is thrown if the key maps to an object that
	 * is not a Boolean.
	 */
	boolean getBoolean(String key);

	/**
	 * Get a double associated with the given configuration key.
	 *
	 * @param key The configuration key. @return The associated double.
	 *
	 * @throws ConversionException is thrown if the key maps to an object that
	 * is not a Double.
	 */
	double getDouble(String key);

	/**
	 * Get a int associated with the given configuration key.
	 *
	 * @param key The configuration key. @return The associated int.
	 *
	 * @throws ConversionException is thrown if the key maps to an object that
	 * is not a Integer.
	 */
	int getInt(String key);

	/**
	 * Get the list of the keys contained in the configuration. The returned
	 * iterator can be used to obtain all defined keys. Note that the exact
	 * behavior of the iterator's {@code remove()} method is specific to a
	 * concrete implementation. It <em>may</em> remove the corresponding
	 * property from the configuration, but this is not guaranteed. In any case
	 * it is no replacement for calling {@link #clearProperty(String)} for this
	 * property. So it is highly recommended to avoid using the iterator's
	 * {@code remove()} method.
	 *
	 * @return An Iterator.
	 */
	Iterator<String> getKeys();

	/**
	 * Get the list of the keys contained in the configuration that match the
	 * specified prefix. For instance, if the configuration contains the
	 * following keys:<br> {@code db.user, db.pwd, db.url, window.xpos,
	 * window.ypos},<br> an invocation of {@code getKeys("db");}<br> will return
	 * the keys below:<br> {@code db.user, db.pwd, db.url}.<br> Note that the
	 * prefix itself is included in the result set if there is a matching key.
	 * The exact behavior - how the prefix is actually interpreted - depends on
	 * a concrete implementation.
	 *
	 * @param prefix The prefix to test against. @return An Iterator of keys
	 * that match the prefix. @see #getKeys()
	 */
	Iterator<String> getKeys(String prefix);

	/**
	 * Get a long associated with the given configuration key.
	 *
	 * @param key The configuration key. @return The associated long.
	 *
	 * @throws ConversionException is thrown if the key maps to an object that
	 * is not a Long.
	 */
	long getLong(String key);

	/**
	 * Gets a property from the configuration. This is the most basic get method
	 * for retrieving values of properties. In a typical implementation of the
	 * {@code Configuration} interface the other get methods (that return
	 * specific data types) will internally make use of this method. On this
	 * level variable substitution is not yet performed. The returned object is
	 * an internal representation of the property value for the passed in key.
	 * It is owned by the {@code Configuration} object. So a caller should not
	 * modify this object. It cannot be guaranteed that this object will stay
	 * constant over time (i.e. further update operations on the configuration
	 * may change its internal state).
	 *
	 * @param key property to retrieve @return the value to which this
	 * configuration maps the specified key, or null if the configuration
	 * contains no mapping for this key.
	 */
	Object getProperty(String key);

	/**
	 * Get a string associated with the given configuration key.
	 *
	 * @param key The configuration key. @return The associated string.
	 *
	 * @throws ConversionException is thrown if the key maps to an object that
	 * is not a String.
	 */
	String getString(String key);

	/**
	 * Get an array of strings associated with the given configuration key. If
	 * the key doesn't map to an existing object an empty array is returned
	 *
	 * @param key The configuration key. @return The associated string array if
	 * key is found.
	 *
	 * @throws ConversionException is thrown if the key maps to an object that
	 * is not a String/List of Strings.
	 */
	String[] getStringArray(String key);

	/**
	 * Set a property, this will replace any previously set values. Set values
	 * is implicitly a call to clearProperty(key), addProperty(key, value).
	 *
	 * @param key The key of the property to change @param value The new value
	 */
	void setProperty(String key, Object value);

}
