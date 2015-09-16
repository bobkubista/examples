/**
 *
 */
package bobkubista.examples.services.rest.datagathering;

import java.net.URI;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.services.api.datagathering.DatagatheringApi;

/**
 * @author Bob Kubista
 *
 */
@Path("/")
public class DatagatheringFacade implements DatagatheringApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatagatheringFacade.class);

    private @Context Application application;

    private final Consumer<? super String> keyLogger = (t) -> LOGGER.debug("key: {}", t);

    private final BiConsumer<? super String, ? super List<String>> keyValueStringLogger = (t, u) -> LOGGER.debug("key: {}, value: {}", t, u);

    private @Context Providers providers;

    @GET
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Override
    public Response gatherData(@Context final HttpServletRequest servletRequest, @Context final UriInfo info, @Context final HttpHeaders httpHeaders,
            @Context final Request request, @Context final SecurityContext securityContext) {
        LOGGER.debug("remote adress: {}", servletRequest.getRemoteAddr());
        final MultivaluedMap<String, String> queryParams = info.getQueryParameters();
        LOGGER.debug("Logging query params");
        queryParams.forEach(this.keyValueStringLogger);
        final MultivaluedMap<String, String> pathParams = info.getPathParameters();
        LOGGER.debug("Logging path params");
        pathParams.forEach(this.keyValueStringLogger);
        final URI path = info.getAbsolutePath();
        LOGGER.debug("path: {}", path.toString());
        LOGGER.debug("Logging application properties");
        this.application.getProperties().keySet().forEach(this.keyLogger);
        LOGGER.debug("Logging http request headers");
        httpHeaders.getRequestHeaders().forEach(this.keyValueStringLogger);
        LOGGER.debug("Request methode: {}", request.getMethod());
        LOGGER.debug("is secure: {}", securityContext.isSecure());
        LOGGER.debug("auth schema used: {}", securityContext.getAuthenticationScheme());

        return Response.ok().build();
    }

}
