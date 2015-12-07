package bobkubista.examples.utils.rest.utils.cirtuitbreaker.policiy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.registry.MetricsRegistry;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction.TransactionMetrics;
import bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction.Transactions;

public class ErrorRateBasedHealthPolicyTest {

    ErrorRateBasedHealthPolicy policy;

    @Before
    public void init() {
    }

    @Test
    public void testIsHealthyFailedSizeHealthy() {
        final MetricsRegistry metricsRegistry = mock(MetricsRegistry.class);

        final Transactions transactions = mock(Transactions.class);
        when(transactions.size()).thenReturn(1);
        when(transactions.failed()).thenReturn(transactions);

        final TransactionMetrics transactionMetrics = mock(TransactionMetrics.class);
        when(transactionMetrics.ofLast(Matchers.any(Duration.class))).thenReturn(transactions);

        when(metricsRegistry.transactions("scope")).thenReturn(transactionMetrics);

        this.policy = new ErrorRateBasedHealthPolicy(metricsRegistry, 2);

        assertTrue(this.policy.isHealthy("scope"));
    }

    @Test
    public void testIsHealthyFailedSizeUnhealthy() {
        final MetricsRegistry metricsRegistry = mock(MetricsRegistry.class);

        final Transactions transactions = mock(Transactions.class);
        when(transactions.size()).thenReturn(3);
        when(transactions.failed()).thenReturn(transactions);

        final TransactionMetrics transactionMetrics = mock(TransactionMetrics.class);
        when(transactionMetrics.ofLast(Matchers.any(Duration.class))).thenReturn(transactions);

        when(metricsRegistry.transactions("scope")).thenReturn(transactionMetrics);

        this.policy = new ErrorRateBasedHealthPolicy(metricsRegistry, 2);

        assertFalse(this.policy.isHealthy("scope"));
    }

    @Test
    public void testIsHealthyThreshold() {
        final MetricsRegistry metricsRegistry = mock(MetricsRegistry.class);

        final Transactions transactions = mock(Transactions.class);
        when(transactions.size()).thenReturn(3);
        Transactions failedTransactions = mock(Transactions.class);
        when(failedTransactions.size()).thenReturn(1);
        when(transactions.failed()).thenReturn(failedTransactions = mock(Transactions.class));

        final TransactionMetrics transactionMetrics = mock(TransactionMetrics.class);
        when(transactionMetrics.ofLast(Matchers.any(Duration.class))).thenReturn(transactions);

        when(metricsRegistry.transactions("scope")).thenReturn(transactionMetrics);

        this.policy = new ErrorRateBasedHealthPolicy(metricsRegistry);

        assertTrue(this.policy.isHealthy("scope"));
    }
}
