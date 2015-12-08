package bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy;

import java.time.Duration;
import java.time.Instant;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CachedCircuitBreakerPolicy implements HealthPolicy {

    private class CachedResult {
        private final boolean isHealthy;
        private final Instant validTo;

        CachedResult(final boolean isHealthy, final Duration ttl) {
            this.isHealthy = isHealthy;
            this.validTo = Instant.now().plus(ttl);
        }

        public boolean isExpired() {
            return Instant.now().isAfter(this.validTo);
        }

        public boolean isHealthy() {
            return this.isHealthy;
        }
    }

    private final Cache<String, CachedResult> cache = CacheBuilder.newBuilder().maximumSize(1000).build();
    private final Duration cacheTtl;
    private final HealthPolicy healthPolicy;

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
