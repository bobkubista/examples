/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.cirtuitbreaker;

import java.io.IOException;

/**
 * @author Bob
 *
 */
public class CircuitOpenedException extends IOException {

    private static final long serialVersionUID = -747053695169142046L;

    public CircuitOpenedException(final String message) {
        super(message);
    }

}
