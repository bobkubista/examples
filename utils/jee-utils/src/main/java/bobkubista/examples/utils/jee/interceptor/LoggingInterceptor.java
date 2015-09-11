/**
 *
 */
package bobkubista.examples.utils.jee.interceptor;

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
		final String params = context.getParameters().toString();
		LOGGER.info("Invoking methode {} with params {}", name, params);

		return context.proceed();
	}
}
