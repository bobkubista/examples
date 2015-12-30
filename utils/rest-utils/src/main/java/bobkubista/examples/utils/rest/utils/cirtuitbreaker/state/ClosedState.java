package bobkubista.examples.utils.rest.utils.cirtuitbreaker.state;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.CircuitBreaker;

/**
 * The state where the requests can pass
 *
 * @author Bob
 *
 */
public final class ClosedState implements CircuitBreakerState {

	/**
	 * Constructor
	 *
	 */
	public ClosedState() {
		super();
	}

	@Override
	public boolean isRequestAllowed(final CircuitBreaker circuitBreaker) {
		if (circuitBreaker.getPolicy().isHealthy(circuitBreaker.getScope())) {
			return true;
		} else {
			return circuitBreaker.changeState(new OpenState(circuitBreaker.getOpenStateTimeout())).isRequestAllowed(circuitBreaker);
		}
	}
}
