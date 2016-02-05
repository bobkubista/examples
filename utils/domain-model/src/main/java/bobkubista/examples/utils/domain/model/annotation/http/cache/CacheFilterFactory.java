
package bobkubista.examples.utils.domain.model.annotation.http.cache;

import java.io.IOException;

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

    @Override
    public void configure(final ResourceInfo resourceInfo, final FeatureContext featureContext) {

        final NoCache noCache = resourceInfo.getResourceMethod()
                .getAnnotation(NoCache.class);
        if (noCache != null) {
            featureContext.register(new ContainerResponseFilter() {

                @Override
                public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) throws IOException {
                    // TODO must be a better way
                    requestContext.getHeaders()
                            .putSingle(HttpHeaders.CACHE_CONTROL, "no-cache");
                }
            });
        } else {
            final CacheMaxAge maxAge = resourceInfo.getResourceMethod()
                    .getAnnotation(CacheMaxAge.class);
            if (maxAge != null) {
                featureContext.register(new ContainerResponseFilter() {

                    @Override
                    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) throws IOException {
                        // TODO must be a better way
                        requestContext.getHeaders()
                                .putSingle(HttpHeaders.CACHE_CONTROL, Long.toString(maxAge.unit()
                                        .toSeconds(maxAge.time())));
                    }
                });
            }
            // TODO add more annotations
        }
    }
}
