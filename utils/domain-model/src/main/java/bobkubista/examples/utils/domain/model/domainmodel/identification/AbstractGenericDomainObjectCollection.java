/**
 *
 */
package bobkubista.examples.utils.domain.model.domainmodel.identification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bobkubista.examples.utils.domain.model.domainmodel.util.CollectionReducer;

/**
 * @author bkubista
 * @param <TYPE>
 *            {@link DomainObject}
 *
 */
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractGenericDomainObjectCollection<TYPE extends DomainObject> implements DomainObject, LinkedDomainObject {

	private static final long serialVersionUID = -7020164336355743584L;

	@XmlElement
	private long count;

	@XmlElementWrapper(name = "links")
	@XmlElement(name = "link")
	@XmlJavaTypeAdapter(Link.JaxbAdapter.class)
	@NotNull
	private final List<Link> links = new ArrayList<>();

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

	@Override
	public List<Link> getLinks() {
		return this.links;
	}

	/**
	 * 
	 * @return the next {@link Link}
	 */
	@XmlTransient
	public Link getNext() {
		return CollectionReducer.findOnlyOne("next", this.links.stream(), link -> link.getRel(), IllegalStateException::new).orElseGet(() -> null);
	}

	/**
	 * 
	 * @return the previous {@link Link}
	 */
	@XmlTransient
	public Link getPrevious() {
		return CollectionReducer.findOnlyOne("previous", this.links.stream(), link -> link.getRel(), IllegalStateException::new).orElseGet(() -> null);
	}

	/**
	 *
	 * @param amount
	 *            count
	 */
	public void setAmount(final long amount) {
		this.count = amount;
	}

}
