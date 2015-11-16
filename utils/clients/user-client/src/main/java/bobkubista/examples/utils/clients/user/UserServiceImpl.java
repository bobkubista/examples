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
        if (status == Status.OK.getStatusCode()) {
            return true;
        } else if (status == Status.UNAUTHORIZED.getStatusCode()) {
            return false;
        } else {
            throw new IllegalStateException(String.format("Something went wrong in getting the right '%s' for user '%s'", right, userId));
        }
    }

    @Override
    protected UserProxy getProxy() {
        return this.proxy;
    }

}
