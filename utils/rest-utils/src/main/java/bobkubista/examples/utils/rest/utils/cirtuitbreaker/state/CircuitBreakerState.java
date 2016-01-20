package bobkubista.examples.utils.rest.utils.cirtuitbreaker.state;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.CircuitBreaker;

/**
 * A state of a circuitbreaker
 *
 * @author Bob
 *
 */
@FunctionalInterface
public interface CircuitBreakerState {

	/**
	 *
	 * @param circuitBreaker
	 *            the {@link CircuitBreaker}
	 * @return is a request allowed?
	 */
	boolean isRequestAllowed(CircuitBreaker circuitBreaker);
}
