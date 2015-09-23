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
@XmlRootElement(name = "pcgame")
@XmlAccessorType(XmlAccessType.NONE)
public class PcGame extends DownloadableGame {

    private static final long serialVersionUID = 149579132556095676L;

    /**
     * Default constructor
     */
    public PcGame() {
        super();
    }

    /**
     * Constructor
     * 
     * @param id
     *            identifier
     * @param active
     *            active flag
     * @param url
     *            url
     * @param gameDeveloper
     *            {@link Developer}
     */
    public PcGame(final Long id, final boolean active, final String url, final Developer gameDeveloper) {
        super(id, active, url, gameDeveloper);
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
