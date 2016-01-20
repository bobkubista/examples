/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy;

import java.time.Duration;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.registry.MetricsRegistry;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction.Transactions;

/**
 * Implementation of {@link HealthPolicy}, that uses error rate to determain the
 * health of a service. Default threshold minimum requests per minute is 30.
 *
 *
 * @author Bob
 *
 */
public class ErrorRateBasedHealthPolicy implements HealthPolicy {
    private static final int THRESHOLD = 30;
    private final MetricsRegistry metricsRegistry;
    private final int thresholdMinReqPerMin;

    /**
     *
     * Constructor
     *
     * @param metricsRegistry
     *            {@link MetricsRegistry}
     */
    public ErrorRateBasedHealthPolicy(final MetricsRegistry metricsRegistry) {
        this(metricsRegistry, THRESHOLD);
    }

    /**
     *
     * Constructor
     * 
     * @param metricsRegistry
     *            {@link MetricsRegistry}
     * @param thresholdMinRatePerMin
     *            the minimum amount of requests per minute
     */
    public ErrorRateBasedHealthPolicy(final MetricsRegistry metricsRegistry, final int thresholdMinRatePerMin) {
        this.metricsRegistry = metricsRegistry;
        this.thresholdMinReqPerMin = thresholdMinRatePerMin;
    }

    @Override
    public boolean isHealthy(final String scope) {
        final Transactions transactions = this.metricsRegistry.transactions(scope).ofLast(Duration.ofMinutes(1));

        return !(transactions.size() > this.thresholdMinReqPerMin && // check
                                                                     // threshold
                                                                     // reached?
        transactions.failed().size() == transactions.size() // every
                                                            // call
                                                            // failed?
        ); // client connection pool limit reached?
    }
}
