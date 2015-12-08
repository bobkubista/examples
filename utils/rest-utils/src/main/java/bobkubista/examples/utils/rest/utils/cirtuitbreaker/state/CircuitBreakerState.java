package bobkubista.examples.utils.rest.utils.cirtuitbreaker.state;

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
     * @return is a request allowed?
     */
    boolean isRequestAllowed();
}
