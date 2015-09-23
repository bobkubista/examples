/**
 *
 */
package bobkubista.examples.services.api.catalog.model.games;

import java.util.Collection;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;

/**
 * @author Bob
 *
 */
@XmlRootElement(name = "masterGameCollection")
@XmlAccessorType(XmlAccessType.NONE)
public class MainGameCollection extends AbstractGenericDomainObjectCollection<MainGame> {

    private static final long serialVersionUID = 6379879920105796923L;

    @XmlElementWrapper(name = "masterGames")
    @XmlElement(name = "masterGame")
    private final Collection<MainGame> domainCollection = new LinkedList<MainGame>();

    /**
     * Default Constructor
     */
    public MainGameCollection() {
        super();
    }

    @Override
    public Collection<MainGame> getDomainCollection() {
        return this.domainCollection;
    }
}
