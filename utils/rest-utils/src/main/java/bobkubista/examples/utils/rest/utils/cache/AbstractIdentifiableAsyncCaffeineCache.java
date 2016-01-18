package bobkubista.examples.utils.rest.utils.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject;
import bobkubista.examples.utils.rest.utils.service.IdentifiableService;

/**
 * Class which wraps the caffeine cache
 *
 *
 * @author Bob
 *
 * @param <K>
 *            {@link Serializable} identifier
 * @param <V>
 *            {@link AbstractGenericIdentifiableDomainObject} value
 */
public abstract class AbstractIdentifiableAsyncCaffeineCache<K extends Serializable, V extends AbstractGenericIdentifiableDomainObject<K>> implements CacheLoader<K, V> {

    private static final int EXPIRE_AFTER_ACCESS = 5;
    private static final int EXPIRE_AFTER_WRITE = 10;
    private static final int INITIAL_CAPACITY = 150;
    private static final int REFRESH_AFTER_WRITE = 1;

    private final AsyncLoadingCache<K, V> cache = Caffeine.newBuilder()
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
        this.cache.synchronous().cleanUp();
    }

    /**
     *
     * @param key
     *            the <code>k</code> key to use
     * @return <code>V</code> value, that is associated with that <code>K</code>
     *         key
     */
    public CompletableFuture<V> get(final K key) {
        return this.cache.get(key);
    }

    /**
     *
     * @return get all cached objects
     */
    public Map<K, V> getAll() {
        return this.cache.synchronous().asMap();
    }

    /**
     *
     * @param keys
     *            keys to return
     * @return a {@link Map} of <code>K</code>, <code>V</code> pair of all the
     *         keys
     */
    public CompletableFuture<Map<K, V>> getAll(final Iterable<? extends K> keys) {
        return this.cache.getAll(keys);
    }

    @Override
    public V load(final K key) throws Exception {
        return this.getIdentifiableService().getByID(key);
    }

    /**
     * Load all on init
     */
    @PostConstruct
    public void loadAll() {
        final Map<K, V> map = this.getAllObjects().stream().collect(Collectors.toMap(t -> t.getId(), Function.identity()));
        this.cache.synchronous().putAll(map);
    }

    @Override
    public Map<K, V> loadAll(final Iterable<? extends K> keys) throws Exception {
        final Collection<V> all = this.getAllObjects();
        return all.parallelStream().collect(Collectors.toMap(value -> value.getId(), Function.identity()));
    }

    /**
     * Get all apropriate objects
     *
     * @return a {@link Collection} of V
     */
    protected Collection<V> getAllObjects() {
        return this.getIdentifiableService().getAll(null, null, null);
    }

    /**
     * @return {@link IdentifiableService} for <code>V</code>.
     */
    protected abstract IdentifiableService<V, K> getIdentifiableService();
}
