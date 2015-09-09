/**
 *
 */
package bobkubista.examples.utils.clients.user;

import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.services.api.user.domain.UserCollection;
import bobkubista.examples.utils.rest.utils.service.ActiveService;

/**
 * @author Bob Kubista
 *
 */
public interface UserServiceInterface extends ActiveService<User, Long, UserCollection> {

}
