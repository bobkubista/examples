/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.util.Collection;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.IdentifiableDomainObject;

/**
 * @author bkubista
 *
 *         This interface is intended for controller applications only. Do not
 *         use it in webservices which access the database. We want the actual
 *         implementation to to make us of {@link IdentifiableDomainObject}s and
 *         the {@link Collection} of them as return types.
 *
 * @param <TYPE>
 *            The {@link IdentifiableDomainObject}
 * @param <ID>
 *            The identifier of the {@link IdentifiableDomainObject}
 * @param <COL>
 *            the {@link DomainObjectCollection} for <code>TYPE</code>
 *
 */
public interface IdentifiableService<TYPE extends IdentifiableDomainObject<ID>, ID extends Serializable, COL extends DomainObjectCollection<TYPE>> {

	/**
	 * Get a {@link DomainObjectCollection} <code>COL</code> of
	 * <code>TYPE</code>
	 *
	 * @return all instances of that type
	 */
	Collection<TYPE> getAll();

	/**
	 * Get a <code>TYPE</code> with <code>ID</code>
	 *
	 * @param id
	 *            the identifier
	 * @return the single instance of the <code>TYPE</code>
	 */
	TYPE getByID(ID id);

}
