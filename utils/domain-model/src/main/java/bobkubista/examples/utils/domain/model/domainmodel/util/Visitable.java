/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.domain.model.domainmodel.util;

/**
 * Visitable interface, that can make use of a {@link Visitor}
 *
 * @author Bob
 *
 */
public interface Visitable {

    /**
     * Visit this object.
     *
     * @param visitor
     *            {@link Visitor} that visits this object
     */
    public default <T extends Visitor> void accept(final T visitor) {
        visitor.accept(this);
    }
}
