/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.util.Collection;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;

/**
 * @author Bob Kubista
 *
 * @param <TYPE>
 *            The {@link AbstractGenericActiveDomainObject}
 * @param <ID>
 *            The identifier of the {@link AbstractGenericActiveDomainObject}
 * @param <COL>
 *            the {@link DomainObjectCollection} for <code>TYPE</code>
 */
public interface ActiveService<TYPE extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable, COL extends DomainObjectCollection<TYPE>>
		extends FunctionalIdentifiableService<TYPE, ID, COL> {

	/**
	 *
	 * @return Get all active {@link AbstractGenericActiveDomainObject}
	 */
	Collection<TYPE> getAllActive();
}
