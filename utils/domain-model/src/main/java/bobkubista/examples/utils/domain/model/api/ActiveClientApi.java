package bobkubista.examples.utils.domain.model.api;

import java.io.Serializable;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
public interface ActiveClientApi<DMO extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable> extends FunctionalIdentifiableClientApi<DMO, ID> {

    /**
     *
     * @param searchBean
     *            {@link SearchBean} filled with criteria, sorts and limits
     * @return get all {@link AbstractGenericActiveDomainObject}s
     */
    @GET
    @Produces({ MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("active/")
    default Response getAllActive(@Valid @BeanParam final SearchBean searchBean) {
        return IdentifiableServerApi.buildMethodNotAllowedResponse(searchBean);
    }
}
