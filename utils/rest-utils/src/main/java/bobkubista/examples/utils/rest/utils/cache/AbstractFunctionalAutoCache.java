/**
 *
 */
package bobkubista.examples.utils.rest.utils.cache;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericFunctionalIdentifiableDomainObject;
import bobkubista.examples.utils.rest.utils.service.FunctionalIdentifiableService;
import bobkubista.examples.utils.rest.utils.service.IdentifiableService;

/**
 * @author bkubista
 * @param <K>
 *            key, which is {@link Serializable}
 * @param <V>
 *            value, which is
 *            {@link AbstractGenericFunctionalIdentifiableDomainObject}
 */
public abstract class AbstractFunctionalAutoCache<K extends Serializable, V extends AbstractGenericFunctionalIdentifiableDomainObject<K>>
        extends AbstractIdentifiableAsyncCaffeineCache<K, V> {
    private static final int EXPIRE_AFTER_ACCESS = 5;
    private static final int EXPIRE_AFTER_WRITE = 10;
    private static final int INITIAL_CAPACITY = 150;
    private static final int REFRESH_AFTER_WRITE = 1;
    private final AsyncLoadingCache<String, K> functionalToIdentifiercache = Caffeine.newBuilder()
            .initialCapacity(INITIAL_CAPACITY)
            .expireAfterAccess(EXPIRE_AFTER_ACCESS, TimeUnit.MINUTES)
            .expireAfterWrite(EXPIRE_AFTER_WRITE, TimeUnit.MINUTES)
            .refreshAfterWrite(REFRESH_AFTER_WRITE, TimeUnit.MILLISECONDS)
            .ticker(Ticker.systemTicker())
            .recordStats()
            .buildAsync(key -> AbstractFunctionalAutoCache.this.getFunctionalService().getIdByFunctionalId(key));

    /**
     * Constructor
     */
    public AbstractFunctionalAutoCache() {
        super();
    }

    /**
     * Get a value based on the functional ID
     *
     * @param functionalId
     *            functional Id
     * @return value of type <code>V</code>
     * @throws ExecutionException
     *             ExecutionException - if a checked exception was thrown while
     *             loading the value. (ExecutionException is thrown even if
     *             computation was interrupted by an InterruptedException.)
     *             UncheckedExecutionException - if an unchecked exception was
     *             thrown while loading the value ExecutionError - if an error
     *             was thrown while loading the value
     * @throws InterruptedException
     */
    public CompletableFuture<V> get(final String functionalId) throws ExecutionException, InterruptedException {
        return this.get(this.functionalToIdentifiercache.get(functionalId, this.getFunctionalService()::getIdByFunctionalId).get());
    }

    @Override
    public V reload(final K key, final V oldValue) throws Exception {
        final V reload = super.reload(key, oldValue);
        if (reload == null) {
            throw new Exception("Could not load value for key: " + key);
        } else {
            this.functionalToIdentifiercache.put(reload.getFunctionalId(), CompletableFuture.completedFuture(key));
            return reload;
        }
    }

    /**
     * @return the {@link FunctionalIdentifiableService}
     */
    protected abstract FunctionalIdentifiableService<V, K> getFunctionalService();

    @Override
    protected final IdentifiableService<V, K> getIdentifiableService() {
        return this.getFunctionalService();
    }

}
