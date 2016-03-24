package bobkubista.examples.utils.rest.utils.proxy;

import java.io.Serializable;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericFunctionalIdentifiableDomainObject;
import bobkubista.examples.utils.rest.utils.service.FunctionalIdentifiableService;

public abstract class AbstractGenericFunctionalIdentifiableRestProxy<TYPE extends AbstractGenericFunctionalIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends AbstractGenericIdentifiableRestProxy<TYPE, ID, COL>implements FunctionalIdentifiableService<TYPE, ID, COL> {

    @Override
    public TYPE getByFunctionalId(final String functionalId) {
        return this.getRequest(this.getServiceWithPaths("/functionId/", functionalId))
                .get(this.getDomainClass());
    }

    @Override
    public ID getIdByFunctionalId(final String fId) {
        return this.getRequest(this.getServiceWithPaths("id", fId))
                .get(this.getIdClass());
    }
}
