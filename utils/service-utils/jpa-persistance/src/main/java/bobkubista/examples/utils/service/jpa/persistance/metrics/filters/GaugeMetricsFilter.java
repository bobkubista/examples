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

import bobkubista.examples.utils.service.jpa.persistance.metrics.annotations.Histogrammed;

@Provider
@Priority(Priorities.USER)
@Histogrammed
public class GaugeMetricsFilter implements ContainerRequestFilter, ContainerResponseFilter {
	@Context
	private ResourceInfo resourceInfo;

	@Inject
	private MetricRegistry registry;

	@Inject
	private HttpServletRequest servletRequest;

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
