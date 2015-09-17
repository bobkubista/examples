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
    private UserServiceInterface userService;

    @Override
    protected UserServiceInterface getFunctionalService() {
        return this.userService;
    }

}
