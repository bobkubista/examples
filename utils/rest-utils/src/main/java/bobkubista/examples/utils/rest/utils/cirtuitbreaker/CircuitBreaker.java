/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.cirtuitbreaker;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy.CachedCircuitBreakerPolicy;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy.HealthPolicy;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.state.CircuitBreakerState;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.state.ClosedState;

/**
 * @author Bob
 *
 */
public class CircuitBreaker {
    private final Duration openStateTimeout;

    private final HealthPolicy policy;

    private final Random random = new Random();

    private final String scope;

    private final AtomicReference<CircuitBreakerState> state = new AtomicReference<>(new ClosedState(this));

    public CircuitBreaker(final String scope, final HealthPolicy healthPolicy, final Duration openStateTimeout) {
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
    public HealthPolicy getPolicy() {
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
