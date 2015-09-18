/**
 *
 */
package bobkubista.examples.services.api.email;

import java.net.URI;

/**
 * @author Bob
 *
 */
public class LinkReplacement implements Replacement {

    private static final String LINK = "link";
    private final URI value;

    public LinkReplacement(final URI link) {
        this.value = link;
    }

    @Override
    public String getConstant() {
        return LINK;
    }

    @Override
    public String getValue() {
        return this.value.toString();
    }

}
