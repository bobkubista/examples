/**
 *
 */
package bobkubista.examples.utils.jee.interceptor;

import java.util.Arrays;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.utils.jee.annotation.Logging;

/**
 * @author Bob
 *
 */
@Interceptor
@Priority(Interceptor.Priority.PLATFORM_BEFORE)
@Logging
public class LoggingInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptor.class);

	@AroundInvoke
	public Object log(InvocationContext context) throws Exception {
		final String name = context.getMethod().getName();
		final String params = Arrays.toString(context.getParameters());
		LOGGER.info("Invoking methode {} with params {}", name, params);

		try {
			return context.proceed();
		} catch (final Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
	}
}
