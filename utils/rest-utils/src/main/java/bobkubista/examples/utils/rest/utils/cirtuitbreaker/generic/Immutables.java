package bobkubista.examples.utils.rest.utils.cirtuitbreaker.generic;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * Transformer class to build immutable collections
 *
 * @author Bob
 *
 */
public class Immutables {

    /**
     *
     * Constructor
     */
    private Immutables() {
        super();
    }

    /**
     *
     * @return immutable {@link Collector} to {@link List}
     */
    public static <T> Collector<T, ?, ImmutableList<T>> toList() {
        final Collector<T, ?, List<T>> collector = Collectors.toList();
        return Collectors.collectingAndThen(collector, ImmutableList::copyOf);
    }

    /**
     * @param keyMapper
     *            a {@link Function} to map the keys
     * @param valueMapper
     *            a {@link Function} to map the values
     * @return immutable {@link Collector} to {@link Map}
     */
    public static <T, K, U> Collector<T, ?, ImmutableMap<K, U>> toMap(final Function<? super T, ? extends K> keyMapper, final Function<? super T, ? extends U> valueMapper) {
        final Collector<T, ?, Map<K, U>> collector = Collectors.toMap(keyMapper, valueMapper);
        return Collectors.collectingAndThen(collector, ImmutableMap::copyOf);
    }

    /**
     *
     * @return immutable {@link Collector} to {@link Set}
     */
    public static <T> Collector<T, ?, ImmutableSet<T>> toSet() {
        final Collector<T, ?, List<T>> collector = Collectors.toList();
        return Collectors.collectingAndThen(collector, ImmutableSet::copyOf);
    }

    /**
     * @param fromList
     *            the input {@link List}
     * @param function
     *            the {@link Function} to transform the {@link List}
     * @return transform lists
     */
    public static <F, T> ImmutableList<T> transform(final List<F> fromList, final Function<? super F, ? extends T> function) {
        return fromList.stream().map(function).collect(toList());
    }

    /**
     *
     * @param fromMap
     *            the input {@link Map}
     * @param keyFunction
     *            the {@link Function} to generate the keys
     * @param valueFunction
     *            the {@link Function} to generate the values
     * @return an immutable {@link Map}
     */
    public static <K1, K2, V1, V2> ImmutableMap<K2, V2> transform(final Map<K1, V1> fromMap, final Function<? super K1, K2> keyFunction,
            final Function<? super V1, V2> valueFunction) {
        final Collector<Entry<K1, V1>, ?, ImmutableMap<K2, V2>> collector = toMap(entry -> keyFunction.apply(entry.getKey()), entry -> valueFunction.apply(entry.getValue()));
        return fromMap.entrySet().stream().collect(collector);
    }

    /**
     *
     * @param fromList
     *            the input {@link List}
     * @param function
     *            the {@link Function} to transform to a {@link Set}
     * @return an immutable {@link Set}
     */
    public static <F, T> ImmutableSet<T> transform(final Set<F> fromList, final Function<? super F, ? extends T> function) {
        return fromList.stream().map(function).collect(toSet());
    }

    /**
     *
     * @param fromMap
     *            the input {@link Map}
     * @param function
     *            the function to transform to a {@link Map}
     * @return an immutable {@link Map}
     */
    public static <K1, K2, V> ImmutableMap<K2, V> transformKeys(final Map<K1, V> fromMap, final Function<? super K1, K2> function) {
        final Collector<Entry<K1, V>, ?, ImmutableMap<K2, V>> collector = toMap(entry -> function.apply(entry.getKey()), Entry::getValue);
        return fromMap.entrySet().stream().collect(collector);
    }

    /**
     *
     * @param fromMap
     *            the input {@link Map}
     * @param function
     *            the function to transform to a {@link Map}
     * @return an immutable {@link Map}
     */
    public static <K, V1, V2> ImmutableMap<K, V2> transformValues(final Map<K, V1> fromMap, final Function<? super V1, V2> function) {
        final Collector<Entry<K, V1>, ?, ImmutableMap<K, V2>> collector = toMap(Entry::getKey, entry -> function.apply(entry.getValue()));
        return fromMap.entrySet().stream().collect(collector);
    }
}
