package bobkubista.examples.utils.rest.utils.cirtuitbreaker.state;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.CircuitBreaker;

/**
 * The state where the requests can pass
 * 
 * @author Bob
 *
 */
public final class ClosedState implements CircuitBreakerState {

    private final CircuitBreaker circuitBreaker;

    /**
     * Constructor
     *
     * @param circuitBreaker
     */
    public ClosedState(final CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    @Override
    public boolean isRequestAllowed() {
        return this.circuitBreaker.getPolicy().isHealthy(this.circuitBreaker.getScope()) ? true
                : this.circuitBreaker.changeState(new OpenState(this.circuitBreaker)).isRequestAllowed();
    }
}
