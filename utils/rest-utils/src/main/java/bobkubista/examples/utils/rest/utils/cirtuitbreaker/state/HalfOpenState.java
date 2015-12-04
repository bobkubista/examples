package bobkubista.examples.utils.rest.utils.cirtuitbreaker.state;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.CircuitBreaker;

final class HalfOpenState implements CircuitBreakerState {
    private final double chance = 0.02; // 2% will be passed through

    private final CircuitBreaker circuitBreaker;

    /**
     * Constructor
     * 
     * @param circuitBreaker
     */
    HalfOpenState(final CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    @Override
    public boolean isRequestAllowed() {
        return this.circuitBreaker.getPolicy().isHealthy(this.circuitBreaker.getScope()) ? this.circuitBreaker.changeState(new ClosedState(this.circuitBreaker)).isRequestAllowed()
                : this.circuitBreaker.getRandom().nextDouble() <= this.chance;
    }
}
