/**
 *
 */
package bobkubista.examples.utils.rest.utils.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.IdentifiableDomainObject;
import bobkubista.examples.utils.rest.utils.service.IdentifiableService;

/**
 * Abstract Cacheloader that cleans its self and only retains used objects.
 *
 * @param <K>
 *            key {@link Serializable}
 * @param <V>
 *            value {@link IdentifiableDomainObject}
 *
 * @author bkubista
 *
 */
public abstract class AbstractIdentifiableAutoCache<K extends Serializable, V extends IdentifiableDomainObject<K>> extends CacheLoader<K, V> {

	public static final int INITIAL_CAPACITY = 150;
	private final LoadingCache<K, V> cache;

	/**
	 * Constructor
	 */
	public AbstractIdentifiableAutoCache() {
		this.cache = CacheBuilder.newBuilder().expireAfterAccess(15, TimeUnit.MINUTES).expireAfterWrite(30, TimeUnit.MINUTES).recordStats().concurrencyLevel(255)
				.initialCapacity(INITIAL_CAPACITY).ticker(Ticker.systemTicker()).build(this);
	}

	/**
	 * Clean up the cache. This will only replace the values when they are
	 * loaded, so old values will show up until then, so that the service will
	 * keep running
	 */
	public void cleanUp() {
		this.cache.cleanUp();
	}

	/**
	 *
	 * @param key
	 *            the <code>k</code> key to use
	 * @return <code>V</code> value, that is associated with that <code>K</code>
	 *         key
	 * @throws ExecutionException
	 *             ExecutionException - if a checked exception was thrown while
	 *             loading the value. (ExecutionException is thrown even if
	 *             computation was interrupted by an InterruptedException.)
	 *             UncheckedExecutionException - if an unchecked exception was
	 *             thrown while loading the value ExecutionError - if an error
	 *             was thrown while loading the value
	 */
	public V get(final K key) throws ExecutionException {
		return this.cache.get(key);
	}

	@PostConstruct
	public Map<K, V> getAll() {
		return this.cache.asMap();
	}

	/**
	 *
	 * @param keys
	 *            keys to return
	 * @return a {@link Map} of <code>K</code>, <code>V</code> pair of all the
	 *         keys
	 * @throws ExecutionException
	 *             ExecutionException - if a checked exception was thrown while
	 *             loading the value. (ExecutionException is thrown even if
	 *             computation was interrupted by an InterruptedException.)
	 *             UncheckedExecutionException - if an unchecked exception was
	 *             thrown while loading the value ExecutionError - if an error
	 *             was thrown while loading the value
	 */
	public Map<K, V> getAll(final Iterable<? extends K> keys) throws ExecutionException {
		return this.cache.getAll(keys);
	}

	@Override
	public V load(final K key) throws Exception {
		return this.getIdentifiableService().getByID(key);
	}

	@PostConstruct
	public void loadAll() {
		final Map<K, V> map = this.getIdentifiableService().getAll().stream().collect(Collectors.toMap(t -> t.getId(), t -> t));
		this.cache.putAll(map);
	}

	@Override
	public Map<K, V> loadAll(final Iterable<? extends K> keys) throws Exception {
		final Collection<V> all = this.getIdentifiableService().getAll();
		return all.parallelStream().collect(Collectors.toMap(value -> value.getId(), value -> value));
	}

	/**
	 * Refresh all keys
	 *
	 * @return true when done and succesfull
	 */
	public boolean refresh() {
		this.cache.asMap().keySet().stream().forEach(key -> this.cache.refresh(key));
		return true;
	}

	/**
	 * @param <T>
	 *            an {@link IdentifiableService}
	 * @return {@link IdentifiableService} for <code>V</code>.
	 */
	protected abstract IdentifiableService<V, K, ? extends DomainObjectCollection<V>> getIdentifiableService();
}
