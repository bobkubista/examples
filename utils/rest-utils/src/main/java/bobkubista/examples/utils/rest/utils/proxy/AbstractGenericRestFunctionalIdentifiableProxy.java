package bobkubista.examples.utils.rest.utils.proxy;

import java.io.Serializable;

import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.api.FunctionalIdentifiableClientApi;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericFunctionalIdentifiableDomainObject;

/**
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            {@link AbstractGenericFunctionalIdentifiableDomainObject}
 * @param <ID>
 *            {@link Serializable}
 */
public abstract class AbstractGenericRestFunctionalIdentifiableProxy<TYPE extends AbstractGenericFunctionalIdentifiableDomainObject<ID>, ID extends Serializable>
        extends AbstractGenericRestIdentifiableProxy<TYPE, ID>implements FunctionalIdentifiableClientApi<TYPE, ID> {

    @Override
    public Response getByFunctionalId(final String functionalId) {
        return this.getRequest(this.getServiceWithPaths("/functionId/", functionalId))
                .get();
    }

    @Override
    public Response getIdByFunctionalId(final String fId) {
        return this.getRequest(this.getServiceWithPaths("id", fId))
                .get();
    }

}
