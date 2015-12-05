package bobkubista.examples.utils.rest.utils.cirtuitbreaker.state;

import java.time.Instant;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.CircuitBreaker;

final class OpenState implements CircuitBreakerState {
    private final CircuitBreaker circuitBreaker;

    /**
     * Constructor
     * @param circuitBreaker
     */
    OpenState(CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    private final Instant exitDate = Instant.now().plus(this.circuitBreaker.getOpenStateTimeout());

    @Override
    public boolean isRequestAllowed() {
        return Instant.now().isAfter(this.exitDate) ? this.circuitBreaker.changeState(new HalfOpenState(this.circuitBreaker)).isRequestAllowed() : false;
    }
}