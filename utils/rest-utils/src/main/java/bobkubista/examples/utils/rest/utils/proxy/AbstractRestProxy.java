/**
 *
 */
package bobkubista.examples.utils.rest.utils.proxy;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.RedirectionException;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status.Family;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.CircuitBreakerFilter;

/**
 * Abstract class to help with Rest proxies
 *
 * @author bkubista
 *
 */
// TODO refactor to be a builder
// TODO do 1 redirect if needed
public abstract class AbstractRestProxy {

    private static final Predicate<Response> SERVER_ERROR = response -> response.getStatusInfo()
            .getFamily()
            .equals(Family.SERVER_ERROR);

    private static final Predicate<Response> SUCCES = response -> response.getStatusInfo()
            .getFamily()
            .equals(Family.SUCCESSFUL);

    /**
     * Do the webServiceCall. Always closes the response
     *
     * @param webServiceCall
     *            a function that transforms the value to a result with the
     *            webcall
     * @param value
     *            the input value
     * @return result of the webservice call. Will throw instances of
     *         {@link WebApplicationException} if no succes code is returned
     */
    protected static <T, R> R call(final Function<T, R> webServiceCall, final T value) {
        return webServiceCall.apply(value);
    }

    /**
     * Do the webServiceCall. Always closes the response
     *
     * @param webServiceCall
     *            a function that transforms the value to a result with the
     *            webcall
     * @param responseProcessor
     *            If the webservice call returns with a {@link Status} of
     *            {@link Family} {@link Family#SUCCESSFUL}, process the response
     * @param value
     *            the input value
     * @return result of the webservice call. Will throw instances of
     *         {@link WebApplicationException} if no succes code is returned as
     *         per Jax-RS spec
     */
    protected static <T, R> R call(final Function<T, Response> webServiceCall, final Function<Response, R> responseProcessor, final T value) {
        final Response result = webServiceCall.apply(value);
        try {
            if (SUCCES.test(result)) {
                return responseProcessor.apply(result);
            } else {
                throw convertToException(result);
            }
        } finally {
            result.close();
        }
    }

    /**
     * Do the webServiceCall. Always closes the response
     *
     * @param webServiceCall
     *            a function that transforms the value to a result with the
     *            webcall
     * @param responseProcessor
     *            If the webservice call returns with a {@link Status} of
     *            {@link Family} {@link Family#SUCCESSFUL}, process the
     *            response. If of {@link Family#SERVER_ERROR}, the return the
     *            fallback
     * @param statusChecker
     *            check the status and then process the response if check is
     *            succesfull. Otherwise return the fallback
     * @param fallback
     *            fallback if response code doesn't pass the statusCheck
     * @param value
     *            the input value
     * @return result of the webservice call. Will throw instances of
     *         {@link WebApplicationException} if no succes or server error code
     *         is returned
     */
    protected static <T, R> R call(final Function<T, Response> webServiceCall, final Predicate<Response> statusChecker, final Function<Response, R> responseProcessor,
            final Supplier<R> fallback, final T value) {
        final Response result = webServiceCall.apply(value);
        try {
            if (SUCCES.or(SERVER_ERROR)
                    .test(result)) {
                if (statusChecker.test(result)) {
                    return responseProcessor.apply(result);
                } else {
                    return fallback.get();
                }
            } else {
                throw convertToException(result);
            }
        } finally {
            result.close();
        }
    }

    /**
     * Do the webServiceCall. Always closes the response
     *
     * @param webServiceCall
     *            a {@link Supplier} that transforms the value to a result with
     *            the webcall
     * @param responseProcessor
     *            If the webservice call returns with a {@link Status} of
     *            {@link Family} {@link Family#SUCCESSFUL}, process the
     *            response. If of {@link Family#SERVER_ERROR}, the return the
     *            fallback
     * @param value
     *            the input value
     * @return result of the webservice call. Will throw instances of
     *         {@link WebApplicationException} if no succes code is returned
     */
    protected static <R> R call(final Supplier<Response> webServiceCall, final Function<Response, R> responseProcessor) {
        final Response result = webServiceCall.get();
        try {
            if (SUCCES.test(result)) {
                return responseProcessor.apply(result);
            } else {
                throw convertToException(result);
            }
        } finally {
            result.close();
        }
    }

    /**
     * Do the webServiceCall. Always closes the response
     *
     * @param webServiceCall
     *            a {@link Supplier} that transforms the value to a result with
     *            the webcall
     * @param statusChecker
     *            check the status and then process the response if check is
     *            succesfull. Otherwise return the fallback
     * @param fallback
     *            fallback if response code doesn't pass the statusCheck
     * @param responseProcessor
     *            If the webservice call returns with a {@link Status} of
     *            {@link Family} {@link Family#SUCCESSFUL}, process the
     *            response. If of {@link Family#SERVER_ERROR}, the return the
     *            fallback
     * @param value
     *            the input value
     * @return result of the webservice call. Will throw instances of
     *         {@link WebApplicationException} if no succes code is returned
     */
    protected static <R> R call(final Supplier<Response> webServiceCall, final Predicate<Response> statusChecker, final Function<Response, R> responseProcessor,
            final Supplier<R> fallback) {
        final Response result = webServiceCall.get();
        try {
            if (SUCCES.or(SERVER_ERROR)
                    .test(result)) {
                if (statusChecker.test(result)) {
                    return responseProcessor.apply(result);
                } else {
                    return fallback.get();
                }
            } else {
                throw convertToException(result);
            }
        } finally {
            result.close();
        }
    }

    /**
     * This is the jax-rs spec
     *
     * @see org.glassfish.jersey.client.JerseyInvocation
     */
    private static WebApplicationException convertToException(final Response response) {
        try {
            // Buffer and close entity input stream (if any) to prevent
            // leaking connections (see JERSEY-2157).
            response.bufferEntity();

            final WebApplicationException webAppException;
            final int statusCode = response.getStatus();
            final Response.Status status = Response.Status.fromStatusCode(statusCode);

            if (status == null) {
                final Response.Status.Family statusFamily = response.getStatusInfo()
                        .getFamily();
                webAppException = createExceptionForFamily(response, statusFamily);
            } else {
                switch (status) {
                case BAD_REQUEST:
                    webAppException = new BadRequestException(response);
                    break;
                case UNAUTHORIZED:
                    webAppException = new NotAuthorizedException(response);
                    break;
                case FORBIDDEN:
                    webAppException = new ForbiddenException(response);
                    break;
                case NOT_FOUND:
                    webAppException = new NotFoundException(response);
                    break;
                case METHOD_NOT_ALLOWED:
                    webAppException = new NotAllowedException(response);
                    break;
                case NOT_ACCEPTABLE:
                    webAppException = new NotAcceptableException(response);
                    break;
                case UNSUPPORTED_MEDIA_TYPE:
                    webAppException = new NotSupportedException(response);
                    break;
                case INTERNAL_SERVER_ERROR:
                    webAppException = new InternalServerErrorException(response);
                    break;
                case SERVICE_UNAVAILABLE:
                    webAppException = new ServiceUnavailableException(response);
                    break;
                default:
                    final Response.Status.Family statusFamily = response.getStatusInfo()
                            .getFamily();
                    webAppException = createExceptionForFamily(response, statusFamily);
                }
            }

            return webAppException;
        } catch (final Throwable t) {
            return new WebApplicationException();
        }
    }

    /**
     * @see org.glassfish.jersey.client.JerseyInvocation
     */
    private static WebApplicationException createExceptionForFamily(final Response response, final Response.Status.Family statusFamily) {
        final WebApplicationException webAppException;
        switch (statusFamily) {
        case REDIRECTION:
            webAppException = new RedirectionException(response);
            break;
        case CLIENT_ERROR:
            webAppException = new ClientErrorException(response);
            break;
        case SERVER_ERROR:
            webAppException = new ServerErrorException(response);
            break;
        default:
            webAppException = new WebApplicationException(response);
        }
        return webAppException;
    }

    /**
     *
     * @return get the basePath
     */
    protected abstract String getBasePath();

    /**
     *
     * @return get the baseUri
     */
    protected abstract String getBaseUri();

    protected Builder getRequest(final WebTarget target) {
        return target.request(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON);
    }

    /**
     *
     * @param paths
     *            the paths to get for the rest service
     *
     * @return {@link javax.ws.rs.client.Invocation.Builder}
     */
    // TODO refactor to make use of template see page 84 JEE essentials
    protected WebTarget getServiceWithPaths(final String... paths) {
        WebTarget serviceWithPath = this.buildWebTarget();
        for (final String path : paths) {
            serviceWithPath = serviceWithPath.path(path);
        }
        return serviceWithPath;
    }

    protected WebTarget getServiceWithQueryParams(@NotNull final Map<String, Object> params, final String... paths) {
        WebTarget serviceWithQuery = this.getServiceWithPaths(paths);
        if (params != null) {
            for (final Entry<String, Object> queryParam : params.entrySet()) {
                serviceWithQuery = this.processQueryparam(serviceWithQuery, queryParam);
            }
        }
        return serviceWithQuery;
    }

    private WebTarget buildWebTarget() {
        final Client client = ClientBuilder.newClient();
        client.register(new CircuitBreakerFilter());
        // TODO refactor for service discovery
        final WebTarget service = client.target(this.getBaseUri());
        try {
            return service.path(this.getBasePath());
        } finally {
            client.close();
        }
    }

    private WebTarget processQueryparam(final WebTarget serviceWithQuery, final Entry<String, Object> queryParam) {
        WebTarget target = serviceWithQuery;
        if (queryParam.getValue() instanceof Collection) {
            target = target.queryParam(queryParam.getKey(), ((Collection<?>) queryParam.getValue()).toArray());
        } else {
            target = target.queryParam(queryParam.getKey(), queryParam.getValue());
        }
        return target;
    }
}
