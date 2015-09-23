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
@XmlRootElement(name = "downloadgame")
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DownloadableGame extends AbstractGame {

    private static final long serialVersionUID = 6473039987442163043L;

    /**
     * Default constructor
     */
    public DownloadableGame() {
        super();
    }

    /**
     * Constructor
     *
     * @param id
     *            identifier
     * @param active
     *            active flag
     * @param filePath
     *            filepath
     * @param gameDeveloper
     *            {@link Developer}
     */
    public DownloadableGame(final Long id, final boolean active, final String filePath, final Developer gameDeveloper) {
        super(filePath, gameDeveloper, active, id);

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
