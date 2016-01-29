/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;

import javax.ws.rs.core.Response;

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

    @Override
    public TYPE getByFunctionalId(final String functionalId) {
        final Response byFunctionalId = this.getProxy()
                .getByFunctionalId(functionalId);
        try {
            return byFunctionalId.readEntity(this.getDomainClass());
        } finally {
            byFunctionalId.close();
        }
    }

    @Override
    public ID getIdByFunctionalId(final String fId) {
        final Response idByFunctionalId = this.getProxy()
                .getIdByFunctionalId(fId);
        try {
            return idByFunctionalId.readEntity(this.getIdClass());
        } finally {
            idByFunctionalId.close();
        }
    }

    @Override
    protected abstract AbstractGenericRestFunctionalIdentifiableProxy<TYPE, ID> getProxy();
}
