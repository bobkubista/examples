package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;

import bobkubista.examples.utils.domain.model.domainmodel.identification.FunctionalIdentifiableDomainObject;

/**
 * A more specific interface, which builds on the {@link IdentifiableService}.
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            The {@link FunctionalIdentifiableDomainObject}
 * @param <ID>
 *            the identifier of the {@link FunctionalIdentifiableDomainObject}
 */
public interface FunctionalIdentifiableService<TYPE extends FunctionalIdentifiableDomainObject<ID>, ID extends Serializable> extends IdentifiableService<TYPE, ID> {

	/**
	 *
	 * @param functionalId
	 *            the functional identifier
	 * @return the {@link FunctionalIdentifiableDomainObject}
	 */
	TYPE getByFunctionalId(String functionalId);

	/**
	 * Map a functional id to an identifier
	 *
	 * @param fId
	 *            the functional id
	 * @return the identifier
	 */
	ID getIdByFunctionalId(String fId);

}
