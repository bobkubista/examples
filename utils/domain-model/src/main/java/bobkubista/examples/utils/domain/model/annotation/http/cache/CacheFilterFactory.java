
package bobkubista.examples.utils.domain.model.annotation.http.cache;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.function.Function;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
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

    private final class CacheFilter<T> implements ContainerResponseFilter {
        private final T annotation;
        private final Function<T, String> function;

        private CacheFilter(final Function<T, String> function, final T annotation) {
            this.function = function;
            this.annotation = annotation;
        }

        @Override
        public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) throws IOException {
            requestContext.getHeaders()
                    .add(HttpHeaders.CACHE_CONTROL, this.function.apply(this.annotation));
        }
    }

    @Override
    public void configure(final ResourceInfo resourceInfo, final FeatureContext featureContext) {

        this.addToHeader(resourceInfo, featureContext, CacheNo.class, CacheNo.HEADER);
        this.addToHeader(resourceInfo, featureContext, CacheMaxAge.class, CacheMaxAge.HEADER);
        this.addToHeader(resourceInfo, featureContext, CachePrivate.class, CachePrivate.HEADER);
        this.addToHeader(resourceInfo, featureContext, CacheNoStore.class, CacheNoStore.HEADER);
        this.addToHeader(resourceInfo, featureContext, CachePublic.class, CachePublic.HEADER);
        this.addToHeader(resourceInfo, featureContext, CacheNoTransform.class, CacheNoTransform.HEADER);
        this.addToHeader(resourceInfo, featureContext, CacheSMaxAge.class, CacheSMaxAge.HEADER);
        this.addToHeader(resourceInfo, featureContext, CacheMustRevalidate.class, CacheMustRevalidate.HEADER);
        this.addToHeader(resourceInfo, featureContext, CacheProxyRevalidate.class, CacheProxyRevalidate.HEADER);
    }

    private <T extends Annotation> void addToHeader(final ResourceInfo resourceInfo, final FeatureContext featureContext, final Class<T> annotationClass,
            final Function<T, String> function) {
        if (resourceInfo.getResourceMethod()
                .isAnnotationPresent(annotationClass)) {
            final T annotation = resourceInfo.getResourceMethod()
                    .getDeclaredAnnotation(annotationClass);

            featureContext.register(new CacheFilter<T>(function, annotation));
        }
    }
}
