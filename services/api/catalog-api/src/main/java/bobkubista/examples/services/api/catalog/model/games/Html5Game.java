/**
 *
 */
package bobkubista.examples.services.api.catalog.model.games;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import bobkubista.examples.services.api.catalog.model.Developer;

/**
 * @author Bob
 *
 */
@XmlRootElement(name = "html5game")
@XmlAccessorType(XmlAccessType.NONE)
public class Html5Game extends WebGame {

    private static final long serialVersionUID = 1087423146833179504L;

    public Html5Game() {
    }

    public Html5Game(final Long id, final boolean active, final String url, final Developer gameDeveloper) {
        super(id, active, url, gameDeveloper);
    }
}
