package bobkubista.examples.utils.rest.utils.cirtuitbreaker.registry;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    private static final int MAX_ENTRIES = 5;

    private static final Duration TIMEOUT = Duration.ofSeconds(3);

    // host, CircuitBreaker
    private final Map<String, CircuitBreaker> circuitBreakerMap = new ConcurrentHashMap<String, CircuitBreaker>();

    private final HealthPolicy healthPolicy;

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
    public CircuitBreaker get(final String scope) {
        String host = scope;
        if (host == null) {
            host = "none";
        }
        CircuitBreaker circuitBreaker = this.circuitBreakerMap.get(host);
        if (circuitBreaker == null && this.circuitBreakerMap.size() < CircuitBreakerRegistry.MAX_ENTRIES) {
            circuitBreaker = new CircuitBreaker(host, this.healthPolicy, TIMEOUT);
            this.circuitBreakerMap.put(host, circuitBreaker);
        }
        return circuitBreaker;
    }
}
