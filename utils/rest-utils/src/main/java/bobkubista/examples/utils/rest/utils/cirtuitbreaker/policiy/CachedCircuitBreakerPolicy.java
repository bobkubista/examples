package bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy;

import java.time.Duration;
import java.time.Instant;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * implementation of the {@link HealthPolicy}
 *
 * @author Bob
 *
 */
public class CachedCircuitBreakerPolicy implements HealthPolicy {

    /**
     * the cache object
     *
     * @author Bob
     *
     */
    private class CachedResult {
        private final boolean isHealthy;
        private final Instant validTo;

        /**
         *
         * Constructor
         *
         * @param isHealthy
         *            is the cache object healthy
         * @param ttl
         *            duration to expire
         */
        CachedResult(final boolean isHealthy, final Duration ttl) {
            this.isHealthy = isHealthy;
            this.validTo = Instant.now().plus(ttl);
        }

        /**
         *
         * @return is the cached object past it's expiration time
         */
        public boolean isExpired() {
            return Instant.now().isAfter(this.validTo);
        }

        /**
         *
         * @return is the cached object healthy
         */
        public boolean isHealthy() {
            return this.isHealthy;
        }
    }

    private final Cache<String, CachedResult> cache = CacheBuilder.newBuilder().maximumSize(1000).build();
    private final Duration cacheTtl;
    private final HealthPolicy healthPolicy;

    /**
     *
     * Constructor
     *
     * @param healthPolicy
     *            the {@link HealthPolicy} to cache
     * @param cacheTtl
     *            the {@link Duration} to cache the {@link HealthPolicy}
     */
    public CachedCircuitBreakerPolicy(final HealthPolicy healthPolicy, final Duration cacheTtl) {
        this.healthPolicy = healthPolicy;
        this.cacheTtl = cacheTtl;
    }

    @Override
    public boolean isHealthy(final String scope) {

        CachedResult cachedResult = this.cache.getIfPresent(scope);
        if (cachedResult == null || cachedResult.isExpired()) {
            cachedResult = new CachedResult(this.healthPolicy.isHealthy(scope), this.cacheTtl);
            this.cache.put(scope, cachedResult);
        }

        return cachedResult.isHealthy();
    }
}
