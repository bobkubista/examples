
package bobkubista.examples.utils.domain.model.annotation.http.cache;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

/**
 * A Cache filter factory. Creates {@link ContainerResponseFilter}s that add
 * cache-control headers to the response, based on the annotations on the
 * implemented methodes.
 *
 * Example: a method annotated with the {@link CacheNo} annotation, will have
 * the cache-control header "no-cache"
 *
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

        this.setHeader(resourceInfo, featureContext, CacheNo.class, t -> Collections.singletonList(CacheNo.HEADER));
        this.setHeader(resourceInfo, featureContext, CacheMustRevalidate.class, t -> Collections.singletonList(CacheMustRevalidate.HEADER));
        this.setHeader(resourceInfo, featureContext, CacheNoStore.class, t -> Collections.singletonList(CacheNoStore.HEADER));
        this.setHeader(resourceInfo, featureContext, CacheNoTransform.class, t -> Collections.singletonList(CacheNoTransform.HEADER));
        this.setHeader(resourceInfo, featureContext, CachePrivate.class, t -> Collections.singletonList(CachePrivate.HEADER));
        this.setHeader(resourceInfo, featureContext, CacheProxyRevalidate.class, t -> Collections.singletonList(CacheProxyRevalidate.HEADER));
        this.setHeader(resourceInfo, featureContext, CachePublic.class, t -> Collections.singletonList(CachePublic.HEADER));

        this.setHeader(resourceInfo, featureContext, CacheSMaxAge.class, maxAge -> Collections.singletonList(CacheSMaxAge.HEADER + Long.toString(maxAge.unit()
                .toSeconds(maxAge.time()))));
        this.setHeader(resourceInfo, featureContext, CacheMaxAge.class, maxAge -> Collections.singletonList(CacheMaxAge.HEADER + Long.toString(maxAge.unit()
                .toSeconds(maxAge.time()))));
    }

    /**
     * Create the filter and register it
     *
     * @param resourceInfo
     *            {@link ResourceInfo}
     * @param featureContext
     *            {@link FeatureContext}
     * @param annotationClass
     *            the annotation class
     * @param header
     *            {@link Function} of the cache annotation to {@link String}
     */
    protected <T extends Annotation> void setHeader(final ResourceInfo resourceInfo, final FeatureContext featureContext, final Class<T> annotationClass,
            final Function<T, List<Object>> header) {
        if (resourceInfo.getResourceMethod()
                .isAnnotationPresent(annotationClass)) {
            final T annotation = resourceInfo.getResourceMethod()
                    .getDeclaredAnnotation(annotationClass);
            // TODO only process one annotation. This is not correct
            featureContext.register((ContainerResponseFilter) (requestContext, responseContext) -> responseContext.getHeaders()
                    .merge(HttpHeaders.CACHE_CONTROL, header.apply(annotation), (t, u) -> {
                        t.addAll(u);
                        return t;
                    }));
        }
    }
}
