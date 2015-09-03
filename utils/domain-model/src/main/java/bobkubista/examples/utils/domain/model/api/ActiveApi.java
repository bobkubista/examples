package bobkubista.examples.utils.domain.model.api;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.domainmodel.identification.ActiveDomainObject;

/**
 * @author bkubista
 * @param <DMO>
 *            {@link ActiveDomainObject}
 * @param <ID>
 *            The idertifier
 */
public interface ActiveApi<DMO extends ActiveDomainObject<ID>, ID extends Serializable> extends FunctionalIdentifiableApi<DMO, ID> {

	/**
	 *
	 * @return get all {@link ActiveDomainObject}s
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("active/")
	default Response getAllActive() {
		return IdentifiableApi.buildNotImplementedResponse();
	}
}
