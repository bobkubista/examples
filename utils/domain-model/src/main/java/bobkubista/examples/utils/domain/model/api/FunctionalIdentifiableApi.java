package bobkubista.examples.utils.domain.model.api;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.FunctionalIdentifiableDomainObject;

/**
 * @author bkubista
 * @param <DMO>
 *            {@link FunctionalIdentifiableDomainObject}
 * @param <ID>
 *            the identifier of the {@link FunctionalIdentifiableDomainObject}
 */
public interface FunctionalIdentifiableApi<DMO extends FunctionalIdentifiableDomainObject<ID>, ID extends Serializable> extends IdentifiableApi<DMO, ID> {

	/**
	 * get the <code>DMO</code>
	 *
	 * @param identifier
	 *            the identfier
	 * @return the <code>DMO</code>
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("functionId/{id}")
	default Response getByFunctionalId(@PathParam("id") final String identifier) {
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}

	/**
	 * Get the identifier that goes with the functional id
	 *
	 * @param fId
	 *            functional id
	 * @return the <code>ID</code>
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("id/{functionalId}")
	default Response getIdByFunctionalId(@PathParam("functionalId") final String fId) {
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
	@Path("searchByFunctionalId/{id}")
	default Response searchByFunctionalID(@PathParam("id") final String identifier) {
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}
}
