/**
 *
 */
package bobkubista.examples.utils.domain.model.api;

import java.io.Serializable;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObject;

/**
 * An interface discribing the rest paths, accepted {@link MediaType} and http
 * request methodes. This facade should be used by all applications, except
 * admin applications.
 *
 * The interface extends the {@link IdentifiableApi} which describes the input
 * and return types possible. For Rest services, we need to return only
 * {@link Response} types as the single objects and collection of objects. The
 * identifier stays the same.
 *
 * @param <DMO>
 *            The {@link DomainObject}
 * @param <ID>
 *            The {@link Serializable} identifier
 * @author bkubista
 *
 */
@Path("/")
public interface IdentifiableApi<DMO extends DomainObject, ID extends Serializable> {

	/**
	 * Create the object of {@link DomainObject} type
	 *
	 * @param object
	 *            the object to create
	 * @return the <code>DMO</code> that was created
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	default Response create(final DMO object) {
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}

	/**
	 * delete the specific object of {@link DomainObject}
	 *
	 * @param identifier
	 *            the identfier
	 * @return
	 */
	@DELETE
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("{id}")
	default Response delete(@PathParam("id") final ID identifier) {
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}

	/**
	 * get all known {@link DomainObject} of that type
	 *
	 * @return a {@link Collection} of {@link DomainObject} of the same type
	 */
	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	default Response getAll() {
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}

	/**
	 * get the {@link DomainObject}
	 *
	 * @param identifier
	 *            the identfier
	 * @return the {@link DomainObject}
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("{id}")
	default Response getByID(@PathParam("id") final ID identifier) {
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}

	/**
	 * update the object of {@link DomainObject}
	 *
	 * @param object
	 *            the object to update
	 * @return the updated object
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	default Response update(final DMO object) {
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}

}
