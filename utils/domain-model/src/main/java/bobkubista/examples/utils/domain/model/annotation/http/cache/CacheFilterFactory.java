
package bobkubista.examples.utils.domain.model.annotation.http.cache;

import java.lang.annotation.Annotation;
import java.util.function.Function;

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

        this.setHeader(resourceInfo, featureContext, CacheNo.class, t -> CacheNo.HEADER);
        this.setHeader(resourceInfo, featureContext, CacheMustRevalidate.class, t -> CacheMustRevalidate.HEADER);
        this.setHeader(resourceInfo, featureContext, CacheNoStore.class, t -> CacheNoStore.HEADER);
        this.setHeader(resourceInfo, featureContext, CacheNoTransform.class, t -> CacheNoTransform.HEADER);
        this.setHeader(resourceInfo, featureContext, CachePrivate.class, t -> CachePrivate.HEADER);
        this.setHeader(resourceInfo, featureContext, CacheProxyRevalidate.class, t -> CacheProxyRevalidate.HEADER);
        this.setHeader(resourceInfo, featureContext, CachePublic.class, t -> CachePublic.HEADER);

        this.setHeader(resourceInfo, featureContext, CacheSMaxAge.class, maxAge -> CacheSMaxAge.HEADER + Long.toString(maxAge.unit()
                .toSeconds(maxAge.time())));
        this.setHeader(resourceInfo, featureContext, CacheMaxAge.class, maxAge -> CacheMaxAge.HEADER + Long.toString(maxAge.unit()
                .toSeconds(maxAge.time())));
    }

    protected <T extends Annotation> void setHeader(final ResourceInfo resourceInfo, final FeatureContext featureContext, final Class<T> annotationClass,
            final Function<T, String> header) {
        if (resourceInfo.getResourceMethod()
                .isAnnotationPresent(annotationClass)) {
            final T maxAge = resourceInfo.getResourceMethod()
                    .getDeclaredAnnotation(annotationClass);
            featureContext.register((ContainerResponseFilter) (requestContext, responseContext) -> responseContext.getHeaders()
                    .putSingle(HttpHeaders.CACHE_CONTROL, header.apply(maxAge)));
        }

    }
}
