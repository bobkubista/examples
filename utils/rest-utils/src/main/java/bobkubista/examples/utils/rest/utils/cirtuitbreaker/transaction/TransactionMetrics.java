package bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction;

import java.time.Duration;
import java.time.Instant;

/**
 * Metrics for {@link Transactions}
 *
 * @author Bob
 *
 */
public class TransactionMetrics implements Transactions {

    private final FixedSizeBuffer ringbuffer;

    /**
     *
     * Constructor
     *
     * @param bufferSize
     *            buffer size
     */
    public TransactionMetrics(final int bufferSize) {
        this.ringbuffer = new FixedSizeBuffer(bufferSize);
    }

    @Override
    public Transactions failed() {
        return this.all().failed();
    }

    @Override
    public Transactions ofLast(final Duration duration) {
        return this.all().ofLast(duration);
    }

    /**
     *
     * @return an open transaction
     */
    public Transaction openTransaction() {
        final Transaction transaction = new Transaction();
        this.ringbuffer.addEntry(transaction);

        return transaction;
    }

    @Override
    public Duration percentile(final int percent) {
        return this.all().percentile(percent);
    }

    @Override
    public Transactions running() {
        return this.all().running();
    }

    @Override
    public Transactions since(final Instant fromTime) {
        return this.all().since(fromTime);
    }

    @Override
    public int size() {
        return this.all().size();
    }

    private Transactions all() {
        return new TransactionsImpl(this.ringbuffer.getTransactions());
    }
}
