
package bobkubista.examples.utils.domain.model.annotation.http.cache;

import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

/**
 * @see <a href=
 *      "http://stackoverflow.com/questions/10934316/jersey-default-cache-control-to-no-cache">
 *      source</a>
 * @author Bob
 *
 */
@Provider
public class CacheFilterFactory implements DynamicFeature {

    @Override
    public void configure(final ResourceInfo resourceInfo, final FeatureContext featureContext) {

        if (resourceInfo.getResourceMethod()
                .isAnnotationPresent(CacheNo.class)) {
            featureContext.register((ContainerResponseFilter) (requestContext, responseContext) -> responseContext.getHeaders()
                    .putSingle(HttpHeaders.CACHE_CONTROL, CacheNo.HEADER));
        }
        if (resourceInfo.getResourceMethod()
                .isAnnotationPresent(CacheMaxAge.class)) {
            final CacheMaxAge maxAge = resourceInfo.getResourceMethod()
                    .getDeclaredAnnotation(CacheMaxAge.class);
            featureContext.register((ContainerResponseFilter) (requestContext, responseContext) -> responseContext.getHeaders()
                    .putSingle(HttpHeaders.CACHE_CONTROL, CacheMaxAge.HEADER + Long.toString(maxAge.unit()
                            .toSeconds(maxAge.time()))));
        }
        if (resourceInfo.getResourceMethod()
                .isAnnotationPresent(CacheMustRevalidate.class)) {
            featureContext.register((ContainerResponseFilter) (requestContext, responseContext) -> responseContext.getHeaders()
                    .putSingle(HttpHeaders.CACHE_CONTROL, CacheMustRevalidate.HEADER));
        }
        if (resourceInfo.getResourceMethod()
                .isAnnotationPresent(CacheNoStore.class)) {
            featureContext.register((ContainerResponseFilter) (requestContext, responseContext) -> responseContext.getHeaders()
                    .putSingle(HttpHeaders.CACHE_CONTROL, CacheNoStore.HEADER));
        }
        if (resourceInfo.getResourceMethod()
                .isAnnotationPresent(CacheNoTransform.class)) {
            featureContext.register((ContainerResponseFilter) (requestContext, responseContext) -> responseContext.getHeaders()
                    .putSingle(HttpHeaders.CACHE_CONTROL, CacheNoTransform.HEADER));
        }
        if (resourceInfo.getResourceMethod()
                .isAnnotationPresent(CachePrivate.class)) {
            featureContext.register((ContainerResponseFilter) (requestContext, responseContext) -> responseContext.getHeaders()
                    .putSingle(HttpHeaders.CACHE_CONTROL, CachePrivate.HEADER));
        }
        if (resourceInfo.getResourceMethod()
                .isAnnotationPresent(CacheProxyRevalidate.class)) {
            featureContext.register((ContainerResponseFilter) (requestContext, responseContext) -> responseContext.getHeaders()
                    .putSingle(HttpHeaders.CACHE_CONTROL, CacheProxyRevalidate.HEADER));
        }
        if (resourceInfo.getResourceMethod()
                .isAnnotationPresent(CachePublic.class)) {
            featureContext.register((ContainerResponseFilter) (requestContext, responseContext) -> responseContext.getHeaders()
                    .putSingle(HttpHeaders.CACHE_CONTROL, CachePublic.HEADER));
        }
        if (resourceInfo.getResourceMethod()
                .isAnnotationPresent(CacheSMaxAge.class)) {
            final CacheSMaxAge maxAge = resourceInfo.getResourceMethod()
                    .getDeclaredAnnotation(CacheSMaxAge.class);
            featureContext.register((ContainerResponseFilter) (requestContext, responseContext) -> responseContext.getHeaders()
                    .putSingle(HttpHeaders.CACHE_CONTROL, CacheSMaxAge.HEADER + Long.toString(maxAge.unit()
                            .toSeconds(maxAge.time()))));
        }
    }
}
