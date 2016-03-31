package bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.generic.Immutables;

/**
 * end implementation of transactions
 *
 * @author Bob
 *
 */
class TransactionsImpl implements Transactions {

    private static final int PERCENT = 100;

    private final Set<Transaction> transactions;

    /**
     *
     * Constructor
     *
     * @param transactions
     *            a {@link Set} of {@link Transaction}
     */
    public TransactionsImpl(final Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public Transactions failed() {
        return new TransactionsImpl(this.all()
                .stream()
                .parallel()
                .filter(transaction -> transaction.isFailed())
                .collect(Immutables.toSet()));
    }

    @Override
    public Transactions ofLast(final Duration duration) {
        return this.since(Instant.now()
                .minus(duration));
    }

    @Override
    public Duration percentile(final int percent) {
        if (this.transactions.isEmpty()) {
            return Duration.ZERO;
        } else {
            final int[] sortedValues = this.all()
                    .stream()
                    .mapToInt(transaction -> (int) transaction.getConsumedMillis()
                            .toMillis())
                    .sorted()
                    .toArray();

            if (percent == 0) {
                return Duration.ofMillis(sortedValues[0]);
            } else {
                return Duration.ofMillis(sortedValues[percent * sortedValues.length / PERCENT]);
            }
        }
    }

    @Override
    public Transactions running() {
        return new TransactionsImpl(this.all()
                .stream()
                .filter(transaction -> transaction.isRunning())
                .collect(Immutables.toSet()));
    }

    @Override
    public Transactions since(final Instant fromTime) {
        return new TransactionsImpl(this.all()
                .stream()
                .filter(transaction -> transaction.getStarttime()
                        .isAfter(fromTime))
                .collect(Immutables.toSet()));
    }

    @Override
    public int size() {
        return this.all()
                .size();
    }

    @Override
    public String toString() {
        return this.transactions.toString();
    }

    private Set<Transaction> all() {
        return this.transactions;
    }
}
