/**
 *
 */
package bobkubista.examples.utils.clients.user;

import javax.inject.Inject;

import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.utils.rest.utils.cache.AbstractFunctionalAutoCache;

/**
 * @author Bob
 *
 */
public class UserCache extends AbstractFunctionalAutoCache<Long, User> {

    @Inject
    private UserService userService;

    @Override
    protected UserService getFunctionalService() {
        return this.userService;
    }

}
