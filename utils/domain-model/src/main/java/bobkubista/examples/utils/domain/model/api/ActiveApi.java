package bobkubista.examples.utils.domain.model.api;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;

/**
 * @author bkubista
 * @param <DMO>
 *            {@link AbstractGenericActiveDomainObject}
 * @param <ID>
 *            The idertifier
 */
public interface ActiveApi<DMO extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable> extends FunctionalIdentifiableApi<DMO, ID> {

    /**
     *
     * @return get all {@link AbstractGenericActiveDomainObject}s
     */
    @GET
    @Produces({ MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("active/")
    default Response getAllActive(@DefaultValue("id") @QueryParam(ApiConstants.SORT) final List<String> sort, @QueryParam(ApiConstants.PAGE) @DefaultValue("0") final Integer page,
            @QueryParam(ApiConstants.MAX) @DefaultValue("20") final Integer maxResults) {
        return IdentifiableApi.buildNotImplementedResponse();
    }
}
