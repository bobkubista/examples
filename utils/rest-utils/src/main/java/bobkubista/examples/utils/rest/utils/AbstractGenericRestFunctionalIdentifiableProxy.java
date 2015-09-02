package bobkubista.examples.utils.rest.utils;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.api.FunctionalIdentifiableFacade;
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
		extends AbstractGenericRestIdentifiableProxy<TYPE, ID>implements FunctionalIdentifiableFacade<TYPE, ID> {

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

	/**
	 * set the environment of the service you want to use
	 *
	 * @param database
	 *            the environment type. Can be local, dev, resources, preprod or
	 *            live
	 */
	public void setDatabase(final String database) {
		throw new UnsupportedOperationException();
	}

	/**
	 * update all environments for the given {@link DomainObject}
	 *
	 * @param domainObject
	 *            the {@link DomainObject} to update
	 * @param environments
	 *            a {@link List} of environments to update
	 * @return the environments that have been updated
	 */
	public String updateAllEnvironments(final TYPE domainObject, final List<String> environments) {
		final StringBuilder environmentsUpdated = new StringBuilder();

		for (final String environment : environments) {
			if (this.existsInEnvironment(environment, domainObject.getFunctionalId())) {
				environmentsUpdated.append(environment + ",");
				this.update(domainObject);
			}
		}
		return environmentsUpdated.substring(0, environmentsUpdated.length() - 1);
	}

	private boolean existsInEnvironment(final String database, final String functionalId) {
		this.setDatabase(database);
		return this.getByFunctionalId(functionalId) != null;
	}
}
