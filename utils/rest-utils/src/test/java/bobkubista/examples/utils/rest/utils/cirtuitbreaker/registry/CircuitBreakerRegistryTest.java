package bobkubista.examples.utils.rest.utils.cirtuitbreaker.registry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.CircuitBreaker;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy.HealthPolicy;

public class CircuitBreakerRegistryTest {

    @Test
    public void testGet() {
        final HealthPolicy mockPolicy = mock(HealthPolicy.class);
        final CircuitBreakerRegistry registry = new CircuitBreakerRegistry(mockPolicy);

        final CircuitBreaker breaker = registry.get(null);
        assertEquals("__<NULL>__", breaker.getScope());
        assertNotNull(breaker.getPolicy());
    }

}
