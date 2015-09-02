package bobkubista.examples.utils.rest.utils.proxy;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.api.FunctionalIdentifiableApi;
import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObject;
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

	/**
	 * Get the <code>TYPE</code> by functional ID
	 *
	 * @param identifier
	 *            the functional identifier
	 * @return <code>TYPE</code>
	 */
	@Override
	public Response getByFunctionalId(final String functionalId) {
		return this.getRequest("/functionId/", functionalId).get();
	}

	@Override
	public Response searchByFunctionalID(final String identifier) {
		return this.getRequest("searchByFunctionalId", identifier).get();
	}

}
