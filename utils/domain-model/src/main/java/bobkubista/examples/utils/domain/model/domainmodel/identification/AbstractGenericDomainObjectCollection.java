/**
 *
 */
package bobkubista.examples.utils.domain.model.domainmodel.identification;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author bkubista
 * @param <TYPE>
 *            {@link DomainObject}
 *
 */
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractGenericDomainObjectCollection<TYPE extends DomainObject> implements DomainObject {

    private static final long serialVersionUID = -7020164336355743584L;

    @XmlElement
    private long count;

    @XmlElement(name = "link")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    private List<Link> links;

    /**
     * Constructor
     */
    public AbstractGenericDomainObjectCollection() {
        super();
    }

    /**
     *
     * @return count
     */
    public long getAmount() {
        return this.count;
    }

    /**
     * @return the domainCollection
     */
    public abstract Collection<TYPE> getDomainCollection();

    /**
     * @return the links
     */
    public final List<Link> getLinks() {
        return this.links;
    }

    /**
     *
     * @param amount
     *            count
     */
    public void setAmount(final long amount) {
        this.count = amount;
    }

    /**
     * @param links
     *            the links to set
     */
    public final void setLinks(final List<Link> links) {
        this.links = links;
    }

}
