/**
 *
 */
package bobkubista.examples.services.api.catalog.model.games;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import bobkubista.examples.services.api.catalog.model.Developer;

/**
 * @author Bob
 *
 */
@XmlRootElement(name = "downloadgame")
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractDownloadableGame extends AbstractGame {

    private static final long serialVersionUID = 6473039987442163043L;

    private Long id;

    /**
     * Default constructor
     */
    public AbstractDownloadableGame() {
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
    public AbstractDownloadableGame(final Long id, final boolean active, final String filePath, final Developer gameDeveloper) {
        super(filePath, gameDeveloper, active);
        this.id = id;

    }


    @Override
    @XmlElement()
    public Long getId() {
        return this.id;
    }


    @Override
    public void setId(final Long id) {
        this.id = id;
    }
}
