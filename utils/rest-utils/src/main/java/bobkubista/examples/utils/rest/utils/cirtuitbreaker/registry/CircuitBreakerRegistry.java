package bobkubista.examples.utils.rest.utils.cirtuitbreaker.registry;

import java.time.Duration;
import java.util.Map;

import com.google.common.collect.Maps;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.CircuitBreaker;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy.HealthPolicy;

/**
 * Resistry for circuit breakers. This holds a {@link Map} of hosts and
 * circuitbreakers and a {@link HealthPolicy} It also has a max of 5 hosts which
 * it tracks.
 *
 * @author Bob
 *
 */
public class CircuitBreakerRegistry {

    private final Map<String, CircuitBreaker> circuitBreakerMap = Maps.newConcurrentMap();

    private final HealthPolicy healthPolicy;

    private final int maxEntries = 5;

    /**
     *
     * Constructor
     *
     * @param healthPolicy
     *            {@link HealthPolicy}
     */
    public CircuitBreakerRegistry(final HealthPolicy healthPolicy) {
        this.healthPolicy = healthPolicy;
    }

    /**
     *
     * @param scope
     *            host name
     * @return the {@link CircuitBreaker} for that host
     */
    public CircuitBreaker get(String scope) {
        if (scope == null) {
            scope = "__<NULL>__";
        }

        CircuitBreaker circuitBreaker = this.circuitBreakerMap.get(scope);
        if (circuitBreaker == null && this.circuitBreakerMap.size() < this.maxEntries) {
            circuitBreaker = new CircuitBreaker(scope, this.healthPolicy, Duration.ofSeconds(3));
            this.circuitBreakerMap.put(scope, circuitBreaker);
        }

        return circuitBreaker;
    }
}
