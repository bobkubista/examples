package bobkubista.examples.utils.rest.utils.cirtuitbreaker.state;

import java.time.Duration;
import java.time.Instant;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.CircuitBreaker;

final class OpenState implements CircuitBreakerState {
    private final Instant exitDate;

    /**
     * Constructor
     *
     * @param circuitBreaker
     */
    OpenState(final Duration openStateTimeout) {
        this.exitDate = Instant.now().plus(openStateTimeout);
    }

    @Override
    public boolean isRequestAllowed(final CircuitBreaker circuitBreaker) {
        if (Instant.now().isAfter(this.exitDate)) {
            return circuitBreaker.changeState(new HalfOpenState(circuitBreaker)).isRequestAllowed(circuitBreaker);
        } else {
            return false;
        }
    }
}
