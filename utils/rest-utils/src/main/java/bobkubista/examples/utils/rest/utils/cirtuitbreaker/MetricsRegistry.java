package bobkubista.examples.utils.rest.utils.cirtuitbreaker;

import java.util.Map;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.Maps;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction.TransactionMetrics;

/**
 *
 * @author Bob
 *
 */
public class MetricsRegistry {

	private static final int BUFFERSIZE = 1000;

	private static final int MAX_ENTRIES = 100;
	/**
	 * Key is the scope, aka the host. Value is a {@link TransactionMetrics}
	 */
	private final Map<String, TransactionMetrics> metricsMap = Maps.newConcurrentMap();

	public TransactionMetrics transactions(final String scope) {
		Validate.notNull(scope);

		TransactionMetrics metrics = this.metricsMap.get(scope);
		if (metrics == null && this.metricsMap.size() < MAX_ENTRIES) {
			metrics = new TransactionMetrics(BUFFERSIZE);
			this.metricsMap.put(scope, metrics);
		}

		return metrics;
	}
}
