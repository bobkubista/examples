/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;

import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject;
import bobkubista.examples.utils.rest.utils.service.GenericETagModifiedDateDomainObjectDecorator;
import bobkubista.examples.utils.rest.utils.service.IdentifiableService;

/**
 * @author Bob
 *
 * @param <K>
 *            key extends {@link Serializable}
 * @param <V>
 *            Value extends {@link AbstractGenericIdentifiableDomainObject}
 * @param <COL>
 *            {@link AbstractGenericDomainObjectCollection}
 *
 */
public abstract class AbstractGenericIdentifiableAsyncCache<V extends AbstractGenericIdentifiableDomainObject, COL extends AbstractGenericDomainObjectCollection<V>>
		implements CacheLoader<Long, GenericETagModifiedDateDomainObjectDecorator<V>> {

	private static final int EXPIRE_AFTER_ACCESS = ServerProperties.get()
			.getInt("cache.expire.after.access", 5);

	private static final int EXPIRE_AFTER_WRITE = ServerProperties.get()
			.getInt("cache.expire.after.write", 10);

	private static final int INITIAL_CAPACITY = ServerProperties.get()
			.getInt("cache.initial.capacity", 150);

	private static final int REFRESH_AFTER_WRITE = ServerProperties.get()
			.getInt("cache.refresh.after.write", 1);

	private final AsyncLoadingCache<Long, GenericETagModifiedDateDomainObjectDecorator<V>> cache = Caffeine.newBuilder()
			.initialCapacity(INITIAL_CAPACITY)
			.expireAfterAccess(EXPIRE_AFTER_ACCESS, TimeUnit.MINUTES)
			.expireAfterWrite(EXPIRE_AFTER_WRITE, TimeUnit.MINUTES)
			.refreshAfterWrite(REFRESH_AFTER_WRITE, TimeUnit.MILLISECONDS)
			.ticker(Ticker.systemTicker())
			.recordStats()
			.buildAsync(this);

	/**
	 * Clean up the cache. This will only replace the values when they are
	 * loaded, so old values will show up until then, so that the service will
	 * keep running
	 */
	public void cleanUp() {
		this.cache.synchronous()
				.cleanUp();
	}

	/**
	 *
	 * @return get all cached objects
	 */
	public Map<Long, GenericETagModifiedDateDomainObjectDecorator<V>> getAll() {
		return this.cache.synchronous()
				.asMap();
	}

	public AsyncLoadingCache<Long, GenericETagModifiedDateDomainObjectDecorator<V>> getCache() {
		return this.cache;
	}

	/**
	 *
	 * @param keys
	 *            keys to return
	 * @return a {@link Map} of <code>K</code>, <code>V</code> pair of all the
	 *         keys
	 */
	public CompletableFuture<Map<Long, GenericETagModifiedDateDomainObjectDecorator<V>>> getCachedAll(
			final Iterable<? extends Long> keys) {
		return this.cache.getAll(keys);
	}

	@Override
	public GenericETagModifiedDateDomainObjectDecorator<V> load(final Long key) throws Exception {
		return this.getIdentifiableService()
				.getByID(key);
	}

	@Override
	public Map<Long, GenericETagModifiedDateDomainObjectDecorator<V>> loadAll(final Iterable<? extends Long> keys)
			throws Exception {
		final Map<Long, GenericETagModifiedDateDomainObjectDecorator<V>> result = new HashMap<>();

		keys.forEach(key -> {
			final GenericETagModifiedDateDomainObjectDecorator<V> item = this.getIdentifiableService()
					.getByID(key);
			result.put(key, item);
		});

		return result;
	}

	@Override
	public GenericETagModifiedDateDomainObjectDecorator<V> reload(final Long key,
			final GenericETagModifiedDateDomainObjectDecorator<V> oldValue) throws Exception {
		return this.getIdentifiableService()
				.getByID(oldValue);
	}

	/**
	 * Get all apropriate objects
	 *
	 * @return a {@link Collection} of V
	 */
	protected Collection<V> getAllObjects() {
		return this.getIdentifiableService()
				.getAll(null, null, null)
				.getDomainCollection();
	}

	/**
	 * @return {@link IdentifiableService} for <code>V</code>.
	 */
	protected abstract IdentifiableService<V, COL> getIdentifiableService();
}
