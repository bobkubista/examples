package bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.list.FixedSizeList;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.generic.Immutables;

public class TransactionMetrics implements Transactions {

    private static final class FixedSizeBuffer {
        private volatile int currentPos;
        // this should be an array, for the fixed size
        private final List<Transaction> transactions;

        public FixedSizeBuffer(final int numSlots) {
            this.transactions = FixedSizeList.decorate(new ArrayList<Transaction>(numSlots));
            this.currentPos = 0;
        }

        public void addEntry(final Transaction transaction) {
            this.transactions.set(this.incPos(), transaction);
        }

        public ImmutableSet<Transaction> getTransactions() {
            final Builder<Transaction> resultBuilder = new ImmutableSet.Builder<>();

            for (final Transaction transaction : this.transactions) {
                if (transaction != null) {
                    resultBuilder.add(transaction);
                }
            }
            return resultBuilder.build();
        }

        private int incPos() {
            final int newPos = this.currentPos + 1;
            this.currentPos = newPos < ((FixedSizeList) this.transactions).maxSize() ? newPos : 0;
            return this.currentPos;
        }
    }

    private class TransactionsImpl implements Transactions {
        private final ImmutableSet<Transaction> transactions;

        public TransactionsImpl(final ImmutableSet<Transaction> transactions) {
            this.transactions = transactions;
        }

        @Override
        public Transactions failed() {
            return new TransactionsImpl(this.all().stream().parallel().filter(transaction -> transaction.isFailed()).collect(Immutables.toSet()));
        }

        @Override
        public Transactions ofLast(final Duration duration) {
            return this.since(Instant.now().minus(duration));
        }

        @Override
        public Duration percentile(final int percent) {
            if (this.transactions.isEmpty()) {
                return Duration.ZERO;
            } else {
                final int[] sortedValues = this.all().stream().mapToInt(transaction -> (int) transaction.getConsumedMillis().toMillis()).sorted().toArray();

                if (percent == 0) {
                    return Duration.ofMillis(sortedValues[0]);
                } else {
                    return Duration.ofMillis(sortedValues[percent * sortedValues.length / 100]);
                }
            }
        }

        @Override
        public Transactions running() {
            return new TransactionsImpl(this.all().stream().parallel().filter(transaction -> transaction.isRunning()).collect(Immutables.toSet()));
        }

        @Override
        public Transactions since(final Instant fromTime) {
            return new TransactionsImpl(this.all().stream().parallel().filter(transaction -> transaction.getStarttime().isAfter(fromTime)).collect(Immutables.toSet()));
        }

        @Override
        public int size() {
            return this.all().size();
        }

        @Override
        public String toString() {
            return this.transactions.toString();
        }

        private ImmutableSet<Transaction> all() {
            return this.transactions;
        }
    }

    private final FixedSizeBuffer ringbuffer;

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
