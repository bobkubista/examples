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
@XmlRootElement(name = "mobileGame")
@XmlAccessorType(XmlAccessType.NONE)
public abstract class MobileGame extends AbstractDownloadableGame {

    private static final long serialVersionUID = -3058136388252614976L;

    /**
     * Default constructor
     */
    public MobileGame() {
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
    public MobileGame(final Long id, final boolean active, final String filePath, final Developer gameDeveloper) {
        super(id, active, filePath, gameDeveloper);
    }


}
