/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.cirtuitbreaker;

import java.io.IOException;

/**
 * Exception thrown when circuit is open
 *
 * @author Bob
 *
 */
public class CircuitOpenedException extends IOException {

    private static final long serialVersionUID = -747053695169142046L;

    /**
     *
     * Constructor
     *
     * @param message
     *            the message
     */
    public CircuitOpenedException(final String message) {
        super(message);
    }

}
