package bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class TransactionMetricsTest {

    TransactionMetrics transactions = new TransactionMetrics(2);

    @Test
    public void testOpenTransaction() {
        this.transactions.openTransaction();
        this.transactions.openTransaction();
        assertEquals(2, this.transactions.size());

        this.transactions.openTransaction();
        assertEquals(2, this.transactions.size());
    }

    @Test
    public void testRunning() {
        final Transactions result = this.transactions.running();
        Assert.assertNotNull(result);
        assertEquals(0, result.running().size());
    }
}
