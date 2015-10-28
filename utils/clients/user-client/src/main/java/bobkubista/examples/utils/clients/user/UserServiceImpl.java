/**
 *
 */
package bobkubista.examples.utils.clients.user;

import javax.inject.Inject;

import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.services.api.user.domain.UserCollection;
import bobkubista.examples.utils.rest.utils.service.AbstractActiveService;

/**
 * @author Bob Kubista
 *
 */
public class UserServiceImpl extends AbstractActiveService<User, Long, UserCollection>implements UserService {

    @Inject
    private UserProxy proxy;

    @Override
    protected UserProxy getProxy() {
        return this.proxy;
    }

}
