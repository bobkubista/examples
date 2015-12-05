package bobkubista.examples.utils.rest.utils.cirtuitbreaker.state;

public interface CircuitBreakerState {
    boolean isRequestAllowed();
}
