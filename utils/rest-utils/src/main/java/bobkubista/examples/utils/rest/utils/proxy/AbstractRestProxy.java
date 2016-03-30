/**
 *
 */
package bobkubista.examples.utils.rest.utils.proxy;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.constraints.NotNull;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.CircuitBreakerFilter;

/**
 * Abstract class to help with Rest proxies
 *
 * @author bkubista
 *
 */
// TODO refactor to be a builder
public abstract class AbstractRestProxy {

    private Client client;

    private WebTarget service;

    protected static <T, R> R call(final Function<T, R> webServiceCall, final T value) {
        return webServiceCall.apply(value);
    }

    protected static <T, R> R call(final Function<T, Response> webServiceCall, final Function<Response, R> responseProcessor, final T value) {
        final Response result = webServiceCall.apply(value);
        try {
            return responseProcessor.apply(result);
        } finally {
            result.close();
        }
    }

    /**
     * Get the base {@link Client} and {@link WebTarget}
     */
    @PostConstruct
    public void base() {
        this.client = ClientBuilder.newClient();
        this.client.register(new CircuitBreakerFilter());
        // TODO refactor for service discovery
        this.service = this.client.target(this.getBaseUri());
    }

    /**
     * close the connection before destroy
     */
    @PreDestroy
    protected void close() {
        if (this.client != null) {
            this.client.close();
        }
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
        WebTarget serviceWithPath = this.getServiceWithPaths();
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

    private WebTarget getServiceWithPaths() {
        return this.service.path(this.getBasePath());
    }

    private WebTarget processQueryparam(final WebTarget serviceWithQuery, final Entry<String, Object> queryParam) {
        WebTarget target = serviceWithQuery;
        if (queryParam.getValue() instanceof Collection) {
            for (final Object value : (Collection<?>) queryParam.getValue()) {
                target = target.queryParam(queryParam.getKey(), value);
            }
        } else {
            target = target.queryParam(queryParam.getKey(), queryParam.getValue());
        }
        return target;
    }
}
