package bobkubista.examples.utils.clients.user;

import javax.ws.rs.core.Response;

import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.services.api.user.UserApi;
import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestActiveProxy;

/**
 * @author Bob Kubista {@link AbstractGenericRestActiveProxy} for {@link User}
 */
public class UserProxy extends AbstractGenericRestActiveProxy<User, Long> implements UserApi {

	@Override
	public Response isAuthorized(final Long userId, final String right) {
		return this.getRequest(this.getServiceWithPaths(userId.toString(), right)).get();
	}

	@Override
	protected String getBasePath() {
		return ServerProperties.getString("rest.service.base.path");
	}

	@Override
	protected String getBaseUri() {
		return "";
	}

}
