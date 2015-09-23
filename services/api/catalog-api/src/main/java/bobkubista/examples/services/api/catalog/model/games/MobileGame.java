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
public abstract class MobileGame extends DownloadableGame {

    private static final long serialVersionUID = -3058136388252614976L;

    public MobileGame() {
        super();
    }

    public MobileGame(final Long id, final boolean active, final String filePath, final Developer gameDeveloper) {
        super(id, active, filePath, gameDeveloper);
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
