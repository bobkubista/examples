/**
 *
 */
package bobkubista.examples.utils.jee.interceptor;

import java.lang.reflect.Method;

import javax.interceptor.InvocationContext;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Bob
 *
 */
public class LoggingInterceptorTest {

    /**
     * Test method for
     * {@link bobkubista.examples.utils.jee.interceptor.LoggingInterceptor#log(javax.interceptor.InvocationContext)}
     * .
     *
     * @throws Exception
     */
    @Test
    public void testLog() throws Exception {
        final LoggingInterceptor interceptor = new LoggingInterceptor();
        final InvocationContext context = Mockito.mock(InvocationContext.class);

        final Method mockMethod = new Object() {
        }.getClass().getEnclosingMethod();
        Mockito.when(context.getMethod()).thenReturn(mockMethod);

        final Object[] array = { "param" };
        Mockito.when(context.getParameters()).thenReturn(array);

        interceptor.log(context);

        Mockito.verify(context).proceed();
    }

}
