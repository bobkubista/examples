package bobkubista.examples.utils.domain.model.api;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericFunctionalIdentifiableDomainObject;

/**
 * @author bkubista
 *
 * @param <DMO>
 *            {@link AbstractGenericFunctionalIdentifiableDomainObject}
 * @param <ID>
 *            the identifier of the
 *            {@link AbstractGenericFunctionalIdentifiableDomainObject}
 *
 */
public interface FunctionalIdentifiableServerApi<DMO extends AbstractGenericFunctionalIdentifiableDomainObject<ID>, ID extends Serializable> extends IdentifiableServerApi<DMO, ID> {

    /**
     * get the <code>DMO</code>
     *
     * @param identifier
     *            the identfier
     * @return{@link Response}
     */
    @GET
    @Produces({ MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("functionId/{id}")
    default Response getByFunctionalId(@PathParam("id") final String identifier) {
        return IdentifiableServerApi.buildMethodNotAllowedResponse(identifier);
    }

    /**
     * Get the identifier that goes with the functional id
     *
     * @param fId
     *            functional id
     * @return {@link Response}
     */
    @GET
    @Produces({ MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("id/{functionalId}")
    default Response getIdByFunctionalId(@PathParam("functionalId") final String fId) {
        return IdentifiableServerApi.buildMethodNotAllowedResponse(fId);
    }

}
