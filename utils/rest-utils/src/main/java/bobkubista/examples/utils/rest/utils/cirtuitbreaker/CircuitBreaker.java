/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.cirtuitbreaker;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy.HealthPolicy;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.state.CircuitBreakerState;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.state.ClosedState;

/**
 * @author Bob
 *
 */
public class CircuitBreaker {
    private class CachedCircuitBreakerPolicy implements HealthPolicy {

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

    private final Duration openStateTimeout;

    private final HealthPolicy policy;

    private final Random random = new Random();

    private final String scope;

    private final AtomicReference<CircuitBreakerState> state = new AtomicReference<>(new ClosedState(this));

    CircuitBreaker(final String scope, final HealthPolicy healthPolicy, final Duration openStateTimeout) {
        this.scope = scope;
        this.policy = new CachedCircuitBreakerPolicy(healthPolicy, Duration.ofSeconds(3));
        this.openStateTimeout = openStateTimeout;
    }

    public CircuitBreakerState changeState(final CircuitBreakerState newState) {
        this.state.set(newState);
        return newState;
    }

    /**
     * @return the openStateTimeout
     */
    public Duration getOpenStateTimeout() {
        return this.openStateTimeout;
    }

    /**
     * @return the policy
     */
    public final HealthPolicy getPolicy() {
        return this.policy;
    }

    /**
     * @return the random
     */
    public Random getRandom() {
        return this.random;
    }

    /**
     * @return the scope
     */
    public String getScope() {
        return this.scope;
    }

    public boolean isRequestAllowed() {
        return this.state.get().isRequestAllowed();
    }
}
