package bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction;

import java.time.Duration;
import java.time.Instant;

public class Transaction {
    private volatile Instant endTime = null;

    private volatile boolean isFailed = false;
    private final Instant startTime = Instant.now();

    public static void close(final Object transaction, final boolean isFailed) {
        if (transaction != null && transaction instanceof Transaction) {
            ((Transaction) transaction).close(isFailed);
        }
    }

    public void close(final boolean isFailed) {
        this.endTime = Instant.now();
        this.isFailed = isFailed;
    }

    public Duration getConsumedMillis() {
        if (this.endTime == null) {
            return Duration.between(this.startTime, Instant.now());
        } else {
            return Duration.between(this.startTime, this.endTime);
        }
    }

    public Instant getStarttime() {
        return this.startTime;
    }

    public boolean isFailed() {
        return this.isFailed;
    }

    public boolean isRunning() {
        return this.endTime == null;
    }
}
