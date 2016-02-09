/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.domain.model.annotation.http.cache;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 * @author Bob
 *
 */
public class CacheFilterFactoryTest {

    private static class MockMethod {

        @CacheNo
        @CacheMaxAge(time = 1L, unit = TimeUnit.MINUTES)
        @CacheMustRevalidate
        @CacheNoStore
        @CacheTransform
        @CachePrivate
        @CacheProxyRevalidate
        @CacheSMaxAge(time = 2L, unit = TimeUnit.SECONDS)
        public void testMethod() {
        }
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.domain.model.annotation.http.cache.CacheFilterFactory#configure(javax.ws.rs.container.ResourceInfo, javax.ws.rs.core.FeatureContext)}
     * .
     *
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    @Test
    public void testConfigure() throws NoSuchMethodException, SecurityException {
        final CacheFilterFactory factory = new CacheFilterFactory();

        final CacheNo mockCacheNo = Mockito.mock(CacheNo.class);
        Mockito.when(mockCacheNo.value())
                .thenReturn(true);

        final CacheMaxAge mockMaxAge = Mockito.mock(CacheMaxAge.class);
        Mockito.when(mockMaxAge.time())
                .thenReturn(1L);
        Mockito.when(mockMaxAge.unit())
                .thenReturn(TimeUnit.MINUTES);

        final ResourceInfo resourceInfo = Mockito.mock(ResourceInfo.class);
        Mockito.when(resourceInfo.getResourceMethod())
                .thenReturn(MockMethod.class.getDeclaredMethod("testMethod"));

        final FeatureContext featureContext = Mockito.mock(FeatureContext.class);

        factory.configure(resourceInfo, featureContext);

        Mockito.verify(featureContext)
                .register(Matchers.notNull(), Matchers.eq(Priorities.HEADER_DECORATOR));
    }

}
