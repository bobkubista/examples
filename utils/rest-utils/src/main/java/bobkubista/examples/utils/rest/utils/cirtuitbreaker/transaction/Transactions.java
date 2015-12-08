package bobkubista.examples.utils.rest.utils.cirtuitbreaker.transaction;

import java.time.Duration;
import java.time.Instant;

/**
 * @author Bob
 *
 */
public interface Transactions {

    /**
     *
     * @return did the transaction fail?
     */
    Transactions failed();

    /**
     *
     * @param duration
     *            a {@link Duration} for which to return the
     *            {@link Transactions}
     * @return {@link Transactions} during the last {@link Duration}
     */
    Transactions ofLast(Duration duration);

    /**
     *
     * @param percent
     * @return
     */
    Duration percentile(int percent);

    /**
     *
     * @return the transactions still running
     */
    Transactions running();

    /**
     *
     * @param fromTime
     *            begin {@link Instant} from which the transactions start
     * @return the {@link Transactions} starting from an {@link Instant}
     */
    Transactions since(Instant fromTime);

    /**
     *
     * @return the amount of transactions monitored
     */
    int size();
}
