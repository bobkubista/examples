
package bobkubista.examples.utils.domain.model.annotation.http.cache;

import java.lang.annotation.Annotation;
import java.util.function.BiConsumer;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.CacheControl;
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
        final CacheControl cacheControl = new CacheControl();

        this.setCacheControl(resourceInfo, featureContext, CacheNo.class, cacheControl, (t, u) -> t.setNoCache(u.value()));
        this.setCacheControl(resourceInfo, featureContext, CacheMustRevalidate.class, cacheControl, (t, u) -> t.setMustRevalidate(u.value()));
        this.setCacheControl(resourceInfo, featureContext, CacheNoStore.class, cacheControl, (t, u) -> t.setNoStore(u.value()));
        this.setCacheControl(resourceInfo, featureContext, CacheTransform.class, cacheControl, (t, u) -> t.setNoTransform(u.value()));
        this.setCacheControl(resourceInfo, featureContext, CachePrivate.class, cacheControl, (t, u) -> t.setPrivate(u.value()));
        this.setCacheControl(resourceInfo, featureContext, CacheProxyRevalidate.class, cacheControl, (t, u) -> t.setProxyRevalidate(u.value()));
        this.setCacheControl(resourceInfo, featureContext, CacheSMaxAge.class, cacheControl, (t, maxAge) -> t.setSMaxAge((int) maxAge.unit()
                .toSeconds(maxAge.time())));
        this.setCacheControl(resourceInfo, featureContext, CacheMaxAge.class, cacheControl, (t, maxAge) -> t.setMaxAge((int) maxAge.unit()
                .toSeconds(maxAge.time())));

        featureContext.register(this.getResponseFilter(cacheControl), Priorities.HEADER_DECORATOR);
    }

    private ContainerResponseFilter getResponseFilter(final CacheControl cacheControl) {
        return (ContainerResponseFilter) (requestContext, responseContext) -> {
            responseContext.getHeaders()
                    .add(HttpHeaders.CACHE_CONTROL, cacheControl);

        };
    }

    private <T extends Annotation> void setCacheControl(final ResourceInfo resourceInfo, final FeatureContext featureContext, final Class<T> annotationClass,
            final CacheControl cacheControl, final BiConsumer<CacheControl, T> header) {
        if (resourceInfo.getResourceMethod()
                .isAnnotationPresent(annotationClass)) {
            final T annotation = resourceInfo.getResourceMethod()
                    .getDeclaredAnnotation(annotationClass);
            header.accept(cacheControl, annotation);
        }
    }
}
