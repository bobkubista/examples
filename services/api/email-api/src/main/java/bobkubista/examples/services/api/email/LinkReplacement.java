/**
 *
 */
package bobkubista.examples.services.api.email;

import java.net.URI;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Bob
 *
 */
public class LinkReplacement extends Pair<String, URI> {

    private static final String LINK = "link";

    private static final long serialVersionUID = -6758766835084577364L;
    private URI value;

    public LinkReplacement(final URI link) {
        this.value = link;
    }

    @Override
    public String getLeft() {
        return LINK;
    }

    @Override
    public URI getRight() {
        return this.value;
    }

    @Override
    public URI setValue(final URI value) {
        this.value = value;
        return value;
    }

}
