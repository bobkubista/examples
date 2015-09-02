package bobkubista.examples.utils.rest.utils.proxy;

import java.io.Serializable;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.api.IdentifiableApi;
import bobkubista.examples.utils.domain.model.domainmodel.identification.IdentifiableDomainObject;

/**
 * An abstract service to get {@link IdentifiableDomainObject}s from cache
 *
 * @author bkubista
 * @param <ID>
 *            the identifier of the {@link IdentifiableDomainObject}
 * @param <TYPE>
 *            the type of {@link IdentifiableDomainObject}
 */
public abstract class AbstractGenericRestIdentifiableProxy<TYPE extends IdentifiableDomainObject<ID>, ID extends Serializable> extends AbstractRestProxy
		implements IdentifiableApi<TYPE, ID> {

	private WebTarget service;

	/**
	 * Create the object of <code>TYPE</code>
	 *
	 * @param object
	 *            the object to be created
	 * @return the created object
	 */
	@Override
	public Response create(final TYPE object) {
		return this.getRequest().post(Entity.entity(object, MediaType.APPLICATION_JSON));
	}

	/**
	 * Deleting is not possible, override this methode
	 *
	 * @param identifier
	 *            the <code>ID</code> to delete
	 */
	@Override
	public Response delete(final ID identifier) {
		return this.getRequest(identifier.toString()).delete();
	}

	@Override
	public Response getAll() {
		return this.getRequest().get();
	}

	@Override
	public Response getByID(final ID identifier) {
		return this.getRequest(identifier.toString()).get();
	}

	public WebTarget getService() {
		return this.service;
	}

	/**
	 * Updating is not possible, override this methode
	 *
	 * @param object
	 *            the <code>TYPE</code> to update
	 * @return the updated object
	 */
	@Override
	public Response update(final TYPE object) {
		return this.getRequest().put(Entity.entity(object, MediaType.APPLICATION_JSON));
	}

}
