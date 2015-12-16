package bobkubista.examples.utils.rest.utils.cirtuitbreaker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mock;

import java.time.Duration;

import org.junit.Test;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy.CachedCircuitBreakerPolicy;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy.HealthPolicy;

public class CircuitBreakerTest {

    @Test
    public void testGetPolicy() {
        final HealthPolicy mockPolicy = mock(HealthPolicy.class);
        final CircuitBreaker breaker = new CircuitBreaker("host", mockPolicy, Duration.ofSeconds(3));
        assertTrue(breaker.getPolicy() instanceof CachedCircuitBreakerPolicy);
    }

    @Test
    public void testGetScope() {
        final HealthPolicy mockPolicy = mock(HealthPolicy.class);
        final CircuitBreaker breaker = new CircuitBreaker("host", mockPolicy, Duration.ofSeconds(3));
        assertEquals("host", breaker.getScope());
    }

}
