/**
 *
 */
package bobkubista.examples.services.api.datagathering;

import java.net.URI;
import java.util.List;
import java.util.function.BiConsumer;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Bob Kubista
 *
 */
public class DatagatheringFacade implements DatagatheringApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatagatheringFacade.class);

	private @Context Application application;

	private final BiConsumer<? super String, ? super Object> keyValueObjectLogger = (t, u) -> LOGGER.debug("key: {}, value: {}", t, u);

	private final BiConsumer<? super String, ? super List<String>> keyValueStringLogger = (t, u) -> LOGGER.debug("key: {}, value: {}", t, u);

	private @Context Providers providers;

	@Override
	public Response gatherData(final HttpServletRequest servletRequest, final UriInfo info, final HttpHeaders httpHeaders, final Request request,
			final SecurityContext securityContext) {
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
		this.application.getProperties().forEach(this.keyValueObjectLogger);
		LOGGER.debug("Logging http request headers");
		httpHeaders.getRequestHeaders().forEach(this.keyValueStringLogger);
		LOGGER.debug("Request methode: {}", request.getMethod());
		LOGGER.debug("is secure: {}", securityContext.isSecure());
		LOGGER.debug("auth schema used: {}", securityContext.getAuthenticationScheme());

		return Response.ok().build();
	}

	@Override
	public void getAsync(@Suspended final AsyncResponse response) {
		new Thread(() -> {
			LOGGER.debug("waiting");
			try {
				Thread.sleep(2000);
			} catch (final Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
			LOGGER.debug("resuming request");
			response.resume("resuming");
		});
	}
}
