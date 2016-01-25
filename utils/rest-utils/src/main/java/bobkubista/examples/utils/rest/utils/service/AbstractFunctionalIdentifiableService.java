/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericFunctionalIdentifiableDomainObject;
import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestFunctionalIdentifiableProxy;

/**
 * @author Bob Kubista
 * @param <TYPE>
 *            {@link AbstractGenericFunctionalIdentifiableDomainObject}
 * @param <ID>
 *            Identifier
 * @param <COL>
 *            {@link AbstractGenericDomainObjectCollection} for TYPE
 */
public abstract class AbstractFunctionalIdentifiableService<TYPE extends AbstractGenericFunctionalIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends AbstractIdentifiableService<TYPE, ID, COL>implements FunctionalIdentifiableService<TYPE, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFunctionalIdentifiableService.class);

    @Override
    public TYPE getByFunctionalId(final String functionalId) {
        return this.getProxy()
                .getByFunctionalId(functionalId)
                .readEntity(this.getDomainClass());
    }

    @Override
    public ID getIdByFunctionalId(final String fId) {
        return this.getProxy()
                .getIdByFunctionalId(fId)
                .readEntity(this.getIdClass());
    }

    @Override
    protected abstract AbstractGenericRestFunctionalIdentifiableProxy<TYPE, ID> getProxy();
}
