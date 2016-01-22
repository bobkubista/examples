/**
 *
 */
package bobkubista.examples.utils.clients.user;

import javax.inject.Inject;
import javax.resource.spi.IllegalStateException;
import javax.ws.rs.core.Response.Status;

import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.services.api.user.domain.UserCollection;
import bobkubista.examples.utils.rest.utils.service.AbstractActiveService;

/**
 * @author Bob Kubista
 *
 */
public class UserServiceImpl extends AbstractActiveService<User, Long, UserCollection> implements UserService {

	@Inject
	private UserProxy proxy;

	@Override
	public boolean isAuthorized(final Long userId, final String right) throws IllegalStateException {
		final int status = this.getProxy().isAuthorized(userId, right).getStatus();
		return status == Status.OK.getStatusCode();
	}

	@Override
	protected UserProxy getProxy() {
		return this.proxy;
	}

}
