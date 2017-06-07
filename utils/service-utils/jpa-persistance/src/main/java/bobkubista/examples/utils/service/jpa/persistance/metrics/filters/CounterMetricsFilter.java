package bobkubista.examples.utils.service.jpa.persistance.metrics.filters;

import java.io.IOException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

import bobkubista.examples.utils.service.jpa.persistance.metrics.annotations.Counted;

@Provider
@Priority(Priorities.USER)
@Counted
public class CounterMetricsFilter implements ContainerRequestFilter, ContainerResponseFilter {
	@Context
	private ResourceInfo resourceInfo;

	@Inject
	private MetricRegistry registry;

	@Inject
	private HttpServletRequest servletRequest;


	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		Counter counter = registry.counter(resourceInfo.getResourceMethod().getName());
		counter.inc();
		servletRequest.setAttribute("counter.context", counter);
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		Counter counter = (Counter) servletRequest.getAttribute("counter.context");
		if (counter != null) {
			counter.dec();
		}
	}
}
