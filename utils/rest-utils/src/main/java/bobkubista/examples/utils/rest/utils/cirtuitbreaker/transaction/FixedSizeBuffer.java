package bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
    public Set<Transaction> getTransactions() {
        final Set<Transaction> originalSet = new HashSet<>();

        for (final Transaction transaction : this.transactions) {
            if (transaction != null) {
                originalSet.add(transaction);
            }
        }
        return Collections.unmodifiableSet(originalSet);
    }

    private int incPos() {
        final int newPos = this.currentPos + 1;
        this.currentPos = newPos < this.numSlots ? newPos : 0;
        return this.currentPos;
    }
}
