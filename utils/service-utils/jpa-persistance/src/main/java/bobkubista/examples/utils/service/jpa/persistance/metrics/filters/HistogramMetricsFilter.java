package bobkubista.examples.utils.service.jpa.persistance.metrics.filters;

import java.io.IOException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

import bobkubista.examples.utils.service.jpa.persistance.metrics.annotations.Histogrammed;

@Provider
@Priority(Priorities.USER)
@Histogrammed
public class HistogramMetricsFilter implements ContainerResponseFilter {
	@Context
	private ResourceInfo resourceInfo;

	@Inject
	private MetricRegistry registry;

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		Histogram histogram = registry.histogram(MetricRegistry.name(resourceInfo.getResourceClass(), resourceInfo.getResourceMethod().getName()));
		histogram.update(responseContext.getLength());
	}
}
