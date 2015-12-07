package bobkubista.examples.utils.rest.utils.cirtuitbreaker;

import java.time.Duration;
import java.util.Map;

import com.google.common.collect.Maps;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy.HealthPolicy;

public class CircuitBreakerRegistry {

	private final Map<String, CircuitBreaker> circuitBreakerMap = Maps.newConcurrentMap();

	private final HealthPolicy healthPolicy;

	private final int maxEntries = 100;

	public CircuitBreakerRegistry(final HealthPolicy healthPolicy) {
		this.healthPolicy = healthPolicy;
	}

	public CircuitBreaker get(String scope) {
		if (scope == null) {
			scope = "__<NULL>__";
		}

		CircuitBreaker circuitBreaker = this.circuitBreakerMap.get(scope);
		if (circuitBreaker == null && this.circuitBreakerMap.size() < this.maxEntries) {
			circuitBreaker = new CircuitBreaker(scope, this.healthPolicy, Duration.ofSeconds(3));
			this.circuitBreakerMap.put(scope, circuitBreaker);
		}

		return circuitBreaker;
	}
}
