package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericFunctionalIdentifiableDomainObject;

/**
 * A more specific interface, which builds on the {@link IdentifiableService}.
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            The {@link AbstractGenericFunctionalIdentifiableDomainObject}
 * @param <ID>
 *            the identifier of the
 *            {@link AbstractGenericFunctionalIdentifiableDomainObject}
 * @param <COL>
 *            The {@link AbstractGenericDomainObjectCollection}
 */
public interface FunctionalIdentifiableService<TYPE extends AbstractGenericFunctionalIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends IdentifiableService<TYPE, ID, COL> {

    /**
     *
     * @param functionalId
     *            the functional identifier
     * @return the {@link AbstractGenericFunctionalIdentifiableDomainObject}
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
