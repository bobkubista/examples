/**
 *
 */
package bobkubista.examples.utils.rest.utils;

import java.io.Serializable;
import java.util.Collection;

import bobkubista.examples.utils.domain.model.domainmodel.identification.ActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;

/**
 * @author Bob Kubista
 *
 * @param <TYPE>
 *            The {@link ActiveDomainObject}
 * @param <ID>
 *            The identifier of the {@link ActiveDomainObject}
 * @param <COL>
 *            the {@link DomainObjectCollection} for <code>TYPE</code>
 */
public interface ActiveService<TYPE extends ActiveDomainObject<ID>, ID extends Serializable, COL extends DomainObjectCollection<TYPE>>
		extends FunctionalIdentifiableService<TYPE, ID, COL> {

	/**
	 *
	 * @return Get all active {@link ActiveDomainObject}
	 */
	Collection<TYPE> getAllActive();
}
