package bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction;

import java.time.Duration;
import java.time.Instant;

/**
 * A class that represents a call to a service
 *
 * @author Bob
 *
 */
public class Transaction {
    private volatile Instant endTime = null;

    private volatile boolean isFailed = false;
    private final Instant startTime = Instant.now();

    /**
     * Close a connection
     *
     * @param transaction
     *            the transaction to close
     * @param isFailed
     *            note if the transaction should be marked as failed
     */
    public static void close(final Object transaction, final boolean isFailed) {
        if (transaction instanceof Transaction) {
            ((Transaction) transaction).close(isFailed);
        }
    }

    /**
     * close this transaction
     *
     * @param isFailed
     *            mark the transaction as failed
     */
    public void close(final boolean isFailed) {
        this.endTime = Instant.now();
        this.isFailed = isFailed;
    }

    /**
     *
     * @return the amount of millis consummed by the transaction
     */
    public Duration getConsumedMillis() {
        if (this.endTime == null) {
            return Duration.between(this.startTime, Instant.now());
        } else {
            return Duration.between(this.startTime, this.endTime);
        }
    }

    /**
     *
     * @return {@link Instant} start
     */
    public Instant getStarttime() {
        return this.startTime;
    }

    /**
     *
     * @return did the transaction fail
     */
    public boolean isFailed() {
        return this.isFailed;
    }

    /**
     *
     * @return is the transaction still running
     */
    public boolean isRunning() {
        return this.endTime == null;
    }
}
