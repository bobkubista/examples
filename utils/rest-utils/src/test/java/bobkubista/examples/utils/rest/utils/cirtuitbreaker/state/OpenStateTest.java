package bobkubista.examples.utils.rest.utils.cirtuitbreaker.state;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.time.Duration;

import org.junit.Test;
import org.mockito.Matchers;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.CircuitBreaker;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy.HealthPolicy;

public class OpenStateTest {

    @Test
    public void testIsRequestAllowed() throws InterruptedException {
        final HealthPolicy mockPolicy = mock(HealthPolicy.class);
        when(mockPolicy.isHealthy(Matchers.anyString())).thenReturn(true);

        final CircuitBreakerState mockHalfOpenState = mock(CircuitBreakerState.class);
        when(mockHalfOpenState.isRequestAllowed(Matchers.any(CircuitBreaker.class))).thenReturn(true);

        final CircuitBreaker mockCircuitBreaker = mock(CircuitBreaker.class);
        when(mockCircuitBreaker.getPolicy()).thenReturn(mockPolicy);
        when(mockCircuitBreaker.getScope()).thenReturn("host");
        when(mockCircuitBreaker.changeState(Matchers.any(CircuitBreakerState.class))).thenReturn(mockHalfOpenState);

        final OpenState state = new OpenState(Duration.ofMillis(1));
        Thread.sleep(2L);
        assertTrue(state.isRequestAllowed(mockCircuitBreaker));
    }

    @Test
    public void testIsRequestAllowedNo() {
        final HealthPolicy mockPolicy = mock(HealthPolicy.class);
        when(mockPolicy.isHealthy(Matchers.anyString())).thenReturn(false);

        final CircuitBreaker mockCircuitBreaker = mock(CircuitBreaker.class);
        when(mockCircuitBreaker.getPolicy()).thenReturn(mockPolicy);
        when(mockCircuitBreaker.getScope()).thenReturn("host");
        when(mockCircuitBreaker.getOpenStateTimeout()).thenReturn(Duration.ofDays(1));
        when(mockCircuitBreaker.changeState(Matchers.any(OpenState.class))).thenReturn(new OpenState(Duration.ofDays(1)));

        final OpenState state = new OpenState(Duration.ofDays(1));
        assertFalse(state.isRequestAllowed(mockCircuitBreaker));
    }

}
