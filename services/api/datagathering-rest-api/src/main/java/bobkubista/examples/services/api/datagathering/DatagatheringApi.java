/**
 *
 */
package bobkubista.examples.services.api.datagathering;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * @author Bob Kubista
 *
 */
@Path("/")
@FunctionalInterface
public interface DatagatheringApi {

    /**
     * Gather data based apon data in the context
     * 
     * @param servletRequest
     *            {@link HttpServletRequest}
     * @param info
     *            {@link UriInfo}
     * @param httpHeaders
     *            {@link HttpHeaders}
     * @param request
     *            {@link Request}
     * @param securityContext
     *            {@link SecurityContext}
     * @return {@link Response} when done processing
     */
    public Response gatherData(@Context HttpServletRequest servletRequest, @Context UriInfo info, @Context HttpHeaders httpHeaders, @Context Request request,
            @Context SecurityContext securityContext);

}
