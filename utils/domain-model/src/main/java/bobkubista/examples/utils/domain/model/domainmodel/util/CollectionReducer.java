package bobkubista.examples.utils.domain.model.domainmodel.util;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * A util class to reduce a {@link Stream} down to 1 element of that stream.
 * <a href ="https://www.voxxed.com/blog/2016/02/beware-findfirst-findany/">
 * Inspiration</a>
 *
 * @author Bob
 *
 */
public final class CollectionReducer {

    /**
     * private Constructor
     */
    private CollectionReducer() {
        super();
    }

    /**
     * Get a single result out of a collection based on a equals between the
     * given value and the value returned by the function
     *
     * @param <TYPE>
     *            {@link Object}
     * @param <ID>
     *            {@link Serializable}
     * @param <E>
     *            {@link RuntimeException}
     * @param value
     *            value to check against
     * @param stream
     *            {@link Stream} to search
     * @param filter
     *            the {@link Function} to apply for looking at the right value
     * @param supplier
     *            {@link Supplier} of a {@link RuntimeException}
     * @return {@link Optional} with <code>T</code> if none or one is found.
     *         DuplicateItemException is thrown if more then one value is found
     */
    public static final <TYPE extends Object, E extends RuntimeException> Optional<TYPE> findOnlyOne(final Object value, final Stream<TYPE> stream,
            final Function<TYPE, Object> filter, final Supplier<E> supplier) {
        return stream.filter(item -> filter.apply(item)
                .equals(value))
                .reduce((element, otherElement) -> {
                    throw supplier.get();
                });
    }
}
