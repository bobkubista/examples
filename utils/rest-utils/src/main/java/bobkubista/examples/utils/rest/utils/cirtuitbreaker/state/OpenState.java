package bobkubista.examples.utils.rest.utils.cirtuitbreaker.state;

import java.time.Instant;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.CircuitBreaker;

final class OpenState implements CircuitBreakerState {
	private final CircuitBreaker circuitBreaker;
	private final Instant exitDate;

	/**
	 * Constructor @param circuitBreaker
	 */
	OpenState(final CircuitBreaker circuitBreaker) {
		this.circuitBreaker = circuitBreaker;
		this.exitDate = Instant.now().plus(this.circuitBreaker.getOpenStateTimeout());
	}

	@Override
	public boolean isRequestAllowed() {
		return Instant.now().isAfter(this.exitDate) ? this.circuitBreaker.changeState(new HalfOpenState(this.circuitBreaker)).isRequestAllowed() : false;
	}
}