/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.cirtuitbreaker;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy.ErrorRateBasedHealthPolicy;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy.HealthPolicy;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.registry.CircuitBreakerRegistry;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.registry.MetricsRegistry;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction.Transaction;

/**
 * Client filter for circuitbreakers
 *
 * @author Bob
 *
 */
public class CircuitBreakerFilter implements ClientRequestFilter, ClientResponseFilter {
    private static final String TRANSACTION = "transaction";
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final MetricsRegistry metricsRegistry;

    /**
     * Default Constructor.
     *
     * Uses {@link ErrorRateBasedHealthPolicy} as a {@link HealthPolicy}
     */
    public CircuitBreakerFilter() {
        this.metricsRegistry = new MetricsRegistry();
        this.circuitBreakerRegistry = new CircuitBreakerRegistry(new ErrorRateBasedHealthPolicy(this.metricsRegistry));
    }

    @Override
    public void filter(final ClientRequestContext requestContext) throws IOException, CircuitOpenedException {
        final String scope = requestContext.getUri().getHost();

        if (!this.circuitBreakerRegistry.get(scope).isRequestAllowed()) {
            throw new CircuitOpenedException("circuit is open");
        }

        final Transaction transaction = this.metricsRegistry.transactions(scope).openTransaction();
        requestContext.setProperty(TRANSACTION, transaction);
    }

    @Override
    public void filter(final ClientRequestContext requestContext, final ClientResponseContext responseContext) throws IOException {
        final boolean isFailed = responseContext.getStatus() >= 500;
        Transaction.close(requestContext.getProperty(TRANSACTION), isFailed);
    }
}
