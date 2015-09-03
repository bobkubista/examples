package bobkubista.examples.utils.rest.utils.proxy;

import java.io.Serializable;

import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.api.FunctionalIdentifiableApi;
import bobkubista.examples.utils.domain.model.domainmodel.identification.FunctionalIdentifiableDomainObject;

/**
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            {@link FunctionalIdentifiableDomainObject}
 * @param <ID>
 *            {@link Serializable}
 */
public abstract class AbstractGenericRestFunctionalIdentifiableProxy<TYPE extends FunctionalIdentifiableDomainObject<ID>, ID extends Serializable>
		extends AbstractGenericRestIdentifiableProxy<TYPE, ID>implements FunctionalIdentifiableApi<TYPE, ID> {

	@Override
	public Response getByFunctionalId(final String functionalId) {
		return this.getRequest("/functionId/", functionalId).get();
	}

	@Override
	public Response getIdByFunctionalId(final String fId) {
		return this.getRequest("id", fId).get();
	}

	@Override
	public Response searchByFunctionalID(final String identifier) {
		return this.getRequest("searchByFunctionalId", identifier).get();
	}

}
