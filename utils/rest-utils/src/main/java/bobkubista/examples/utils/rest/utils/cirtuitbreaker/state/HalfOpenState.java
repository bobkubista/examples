package bobkubista.examples.utils.rest.utils.cirtuitbreaker.state;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.CircuitBreaker;

final class HalfOpenState implements CircuitBreakerState {

    // 2% will be passed through
    private static final double CHANCE = 0.02;

    /**
     * Constructor
     *
     * @param circuitBreaker
     */
    HalfOpenState() {
        super();
    }

    @Override
    public boolean isRequestAllowed(final CircuitBreaker circuitBreaker) {
        if (circuitBreaker.getPolicy()
                .isHealthy(circuitBreaker.getScope())) {
            return circuitBreaker.changeState(new ClosedState())
                    .isRequestAllowed(circuitBreaker);
        } else {
            return circuitBreaker.getRandom()
                    .nextDouble() <= HalfOpenState.CHANCE;
        }
    }
}
