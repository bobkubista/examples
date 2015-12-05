/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy;

import java.time.Duration;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.MetricsRegistry;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction.Transactions;

/**
 * @author Bob
 *
 */
public class ErrorRateBasedHealthPolicy implements HealthPolicy {
    private final MetricsRegistry metricsRegistry;
    private final int thresholdMinReqPerMin;

    public ErrorRateBasedHealthPolicy(final MetricsRegistry metricsRegistry) {
        this(metricsRegistry, 30);
    }

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
