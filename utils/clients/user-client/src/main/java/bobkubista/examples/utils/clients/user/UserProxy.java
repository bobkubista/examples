package bobkubista.examples.utils.clients.user;
import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.services.api.user.UserApi;
import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestActiveProxy;

/**
 *
 */

/**
 * @author Bob Kubista
 *
 */
public class UserProxy extends AbstractGenericRestActiveProxy<User, Long>implements UserApi {

	@Override
	protected String getBasePath() {
		return ServerProperties.getString("rest.service.base.path");
	}

	@Override
	protected String getBaseUri() {
		return "";
	}

}
