package bobkubista.examples.utils.domain.model.api;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.FunctionalIdentifiableDomainObject;

/**
 * @author bkubista
 * @param <DMO>
 *            {@link FunctionalIdentifiableDomainObject}
 * @param <ID>
 *            the identifier of the {@link FunctionalIdentifiableDomainObject}
 */
public interface FunctionalIdentifiableFacade<DMO extends FunctionalIdentifiableDomainObject<ID>, ID extends Serializable> extends IdentifiableFacade<DMO, ID> {

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
	@Path("functionId/{id}")
	public Response getByFunctionalId(@PathParam("id") String identifier);

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
	Response searchByFunctionalID(String identifier);
}
