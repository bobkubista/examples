package bobkubista.examples.utils.rest.utils.cirtuitbreaker.state;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.Random;

import org.junit.Test;
import org.mockito.Matchers;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.CircuitBreaker;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy.HealthPolicy;

public class HalfOpenStateTest {

    @Test
    public void testIsRequestAllowed() {
        final CircuitBreaker circuitBreaker = mock(CircuitBreaker.class);
        final HealthPolicy policy = mock(HealthPolicy.class);
        when(policy.isHealthy(Matchers.anyString())).thenReturn(false);
        when(circuitBreaker.getPolicy()).thenReturn(policy);
        final Random mockRandom = mock(Random.class);
        when(mockRandom.nextDouble()).thenReturn(0.5D);
        when(circuitBreaker.getRandom()).thenReturn(mockRandom);

        final CircuitBreakerState state = new HalfOpenState(circuitBreaker);

        assertFalse(state.isRequestAllowed(circuitBreaker));
    }
}
