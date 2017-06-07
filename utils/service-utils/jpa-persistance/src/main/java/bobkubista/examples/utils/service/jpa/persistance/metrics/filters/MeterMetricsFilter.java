package bobkubista.examples.utils.service.jpa.persistance.metrics.filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

public class MeterMetricsFilter implements ContainerResponseFilter {
	@Context
	private ResourceInfo resourceInfo;
	@Inject
    private MetricRegistry registry;
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		Meter meter = registry.meter(resourceInfo.getResourceMethod().getName());
		meter.mark();
	}
}
