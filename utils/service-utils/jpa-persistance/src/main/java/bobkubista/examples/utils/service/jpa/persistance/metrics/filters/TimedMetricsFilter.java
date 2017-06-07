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

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import bobkubista.examples.utils.service.jpa.persistance.metrics.annotations.Timed;

@Provider
@Priority(Priorities.USER)
@Timed
public class TimedMetricsFilter implements ContainerRequestFilter, ContainerResponseFilter {
	@Context
	private ResourceInfo resourceInfo;
	
	@Inject
    private MetricRegistry registry;

    @Inject
    private HttpServletRequest servletRequest;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Timer timer = registry.timer(resourceInfo.getResourceMethod().getName());
        Timer.Context counter = timer.time();
        servletRequest.setAttribute("timer.context", counter);
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        Timer.Context tc = (Timer.Context) servletRequest.getAttribute("timer.context");
        if (tc != null) {
            tc.stop();
            servletRequest.removeAttribute("timer.context");
        }

    }
}
