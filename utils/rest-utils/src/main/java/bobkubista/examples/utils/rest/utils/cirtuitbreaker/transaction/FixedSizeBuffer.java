package bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

final class FixedSizeBuffer {
    private volatile int currentPos;
    private final int numSlots;
    // this should be an array, for the fixed size
    private final Transaction[] transactions;

    public FixedSizeBuffer(final int numSlots) {
        this.numSlots = numSlots;
        this.transactions = new Transaction[numSlots];
        this.currentPos = 0;
    }

    public void addEntry(final Transaction transaction) {
        this.transactions[this.incPos()] = transaction;
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
        this.currentPos = newPos < this.numSlots ? newPos : 0;
        return this.currentPos;
    }
}