package bobkubista.examples.utils.rest.utils.proxy;

import java.io.Serializable;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericFunctionalIdentifiableDomainObject;
import bobkubista.examples.utils.rest.utils.service.FunctionalIdentifiableService;

/**
 *
 * @author Bob
 *
 * @param <TYPE>
 *            {@link AbstractGenericFunctionalIdentifiableDomainObject}
 * @param <ID>
 *            {@link Serializable}
 * @param <COL>
 *            {@link AbstractGenericDomainObjectCollection}
 */
public abstract class AbstractGenericFunctionalIdentifiableRestProxy<TYPE extends AbstractGenericFunctionalIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends AbstractGenericIdentifiableRestProxy<TYPE, ID, COL>implements FunctionalIdentifiableService<TYPE, ID, COL> {

    @Override
    public TYPE getByFunctionalId(final String functionalId) {
        return call(t -> this.getRequest(this.getServiceWithPaths("/functionId/", t))
                .get(), t -> t.readEntity(this.getDomainClass()), functionalId);
    }

    @Override
    public ID getIdByFunctionalId(final String fId) {
        return call(t -> this.getRequest(this.getServiceWithPaths("id", fId))
                .get(), t -> t.readEntity(this.getIdClass()), fId);
    }
}
