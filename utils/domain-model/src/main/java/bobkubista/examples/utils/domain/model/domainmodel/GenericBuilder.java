package bobkubista.examples.utils.domain.model.domainmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * A generic builder
 *
 * @param <T>
 *            the object to create
 * @author Bob
 *
 *
 * @see <a href=
 *      "http://stackoverflow.com/questions/31754786/how-to-implement-the-builder-pattern-in-java-8">
 *      http://stackoverflow.com/questions/31754786/how-to-implement-the-
 *      builder- pattern-in-java-8</a>
 *
 *      <P>
 *      Usage: <BR>
 *      <code>Person value =
 *      GenericBuilder.of(Person::new).with(Person::setName,
 *      "Otto").with(Person::setAge, 5).build();</code
 *
 */
public class GenericBuilder<T> {

    private final List<Consumer<T>> instanceModifiers = new ArrayList<>();

    private final Supplier<T> instantiator;

    private final Collection<Predicate<T>> predicates = new ArrayList<>();

    /**
     * Constructor
     *
     * @param instantiator
     *            init method
     */
    public GenericBuilder(final Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }

    /**
     *
     * @param instantiator
     *            {@link Supplier} of T
     * @return {@link GenericBuilder}
     */
    public static <T> GenericBuilder<T> of(final Supplier<T> instantiator) {
        return new GenericBuilder<T>(instantiator);
    }

    /**
     *
     * @return the build object T
     */
    public T build() {
        final T value = this.instantiator.get();
        this.instanceModifiers.forEach(modifier -> modifier.accept(value));
        this.verifyPredicates(value);
        this.instanceModifiers.clear();
        return value;
    }

    /**
     *
     * @param predicate
     *            {@link Predicate} that are violation constraints
     * @return {@link GenericBuilder}
     */
    public GenericBuilder<T> voilation(final Predicate<T> predicate) {
        this.predicates.add(predicate);
        return this;
    }

    /**
     * Set properties
     *
     * @param consumer
     *            {@link BiConsumer} for type T and value U
     * @param value
     *            the value U to set
     * @return {@link GenericBuilder}
     */
    public <U> GenericBuilder<T> with(final BiConsumer<T, U> consumer, final U value) {
        final Consumer<T> c = instance -> consumer.accept(instance, value);
        this.instanceModifiers.add(c);
        return this;
    }

    private void verifyPredicates(final T value) {
        final List<Predicate<T>> violated = this.predicates.stream().filter(e -> !e.test(value)).collect(Collectors.toList());
        if (!violated.isEmpty()) {
            throw new IllegalStateException(value.toString() + " violates predicates " + violated);
        }
    }
}
