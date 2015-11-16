/**
 *
 */
package bobkubista.examples.utils.clients.user;

import javax.inject.Inject;

import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.utils.rest.utils.cache.AbstractActiveAutoCache;

/**
 * @author Bob
 *
 */
public class UserCache extends AbstractActiveAutoCache<Long, User> {

    @Inject
    private UserService userService;

    @Override
    protected UserService getActiveService() {
        return this.userService;
    }

}
