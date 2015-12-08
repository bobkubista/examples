package bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

/**
 * A buffer of a fixed size
 *
 * @author Bob
 *
 */
final class FixedSizeBuffer {
    private volatile int currentPos;
    private final int numSlots;
    // this should be an array, for the fixed size
    private final Transaction[] transactions;

    /**
     *
     * Constructor
     * 
     * @param numSlots
     *            buffer size
     */
    public FixedSizeBuffer(final int numSlots) {
        this.numSlots = numSlots;
        this.transactions = new Transaction[numSlots];
        this.currentPos = 0;
    }

    /**
     * Add an entry to the buffer, if the buffer is full, replace the first
     * entry
     *
     * @param transaction
     *            the {@link Transaction} to store
     */
    public void addEntry(final Transaction transaction) {
        this.transactions[this.incPos()] = transaction;
    }

    /**
     *
     * @return get a {@link Set} of all the {@link Transaction}
     */
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
        this.currentPos = newPos < this.numSlots ? newPos : 0;
        return this.currentPos;
    }
}
