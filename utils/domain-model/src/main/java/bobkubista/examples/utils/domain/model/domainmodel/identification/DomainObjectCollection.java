/**
 *
 */
package bobkubista.examples.utils.domain.model.domainmodel.identification;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author bkubista
 * @param <TYPE>
 *
 */
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DomainObjectCollection<TYPE extends DomainObject> implements DomainObject {

	private static final long serialVersionUID = -7020164336355743584L;

	public DomainObjectCollection() {
		super();
	}

	/**
	 * @return the domainCollection
	 */
	public abstract Collection<TYPE> getDomainCollection();

}
