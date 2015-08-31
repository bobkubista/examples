/**
 *
 */
package bobkubista.examples.utils.domain.model.domainmodel.identification;

import java.util.Collection;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * @author bkubista
 *
 */
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class IdentifierCollection implements DomainObject {

	private static final long serialVersionUID = -8623470897577981147L;

	@XmlElementWrapper(name = "ids")
	@XmlElement(name = "id")
	private Collection<Integer> collection = new LinkedList<Integer>();

	public IdentifierCollection() {
		super();
	}

	public IdentifierCollection(final Collection<Integer> collection) {
		this.collection = collection;
	}

	public Collection<Integer> getIdentifiers() {
		return this.collection;
	}
}
