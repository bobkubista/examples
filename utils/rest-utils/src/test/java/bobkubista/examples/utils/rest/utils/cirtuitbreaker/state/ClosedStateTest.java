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

public class ClosedStateTest {

    @Test
    public void testIsRequestAllowed() {
        final HealthPolicy mockPolicy = mock(HealthPolicy.class);
        when(mockPolicy.isHealthy(Matchers.anyString())).thenReturn(true);

        final CircuitBreaker mockCircuitBreaker = mock(CircuitBreaker.class);
        when(mockCircuitBreaker.getPolicy()).thenReturn(mockPolicy);
        when(mockCircuitBreaker.getScope()).thenReturn("host");

        final ClosedState state = new ClosedState();
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

        final ClosedState state = new ClosedState();
        assertFalse(state.isRequestAllowed(mockCircuitBreaker));
    }

}
