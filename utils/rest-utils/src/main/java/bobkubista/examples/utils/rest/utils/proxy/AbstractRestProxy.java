/**
 *
 */
package bobkubista.examples.utils.rest.utils.proxy;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import bobkubista.examples.utils.rest.utils.cirtuitbreaker.CircuitBreakerFilter;

/**
 * Abstract class to help with Rest proxies
 *
 * @author bkubista
 *
 */
public abstract class AbstractRestProxy {

	private Client client;
	private WebTarget service;

	/**
	 * Get the base {@link Client} and {@link WebTarget}
	 */
	@PostConstruct
	public void base() {
		this.client = ClientBuilder.newClient();
		this.client.register(new CircuitBreakerFilter());
		this.service = this.client.target(this.getBaseUri());
	}

	/**
	 * close the connection before destroy
	 */
	@PreDestroy
	protected void close() {
		if (this.client != null) {
			this.client.close();
		}
	}

	/**
	 *
	 * @return get the basePath
	 */
	protected abstract String getBasePath();

	/**
	 *
	 * @return get the baseUri
	 */
	protected abstract String getBaseUri();

	/**
	 *
	 * @param paths the paths to get for the rest service
	 *
	 * @return {@link javax.ws.rs.client.Invocation.Builder}
	 */
	// TODO refactor to make use of template see page 84 JEE essentials
	protected Builder getRequest(final String... paths) {
		WebTarget serviceWithPath = this.getServiceWithPaths();
		for (final String path : paths) {
			serviceWithPath = serviceWithPath.path(path);
		}
		return serviceWithPath.request(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON);
	}

	private WebTarget getServiceWithPaths() {
		return this.service.path(this.getBasePath());
	}

}
