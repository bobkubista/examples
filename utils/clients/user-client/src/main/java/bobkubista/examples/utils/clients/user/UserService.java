/**
 *
 */
package bobkubista.examples.utils.clients.user;

import javax.resource.spi.IllegalStateException;

import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.utils.rest.utils.service.ActiveService;

/**
 * @author Bob Kubista
 *
 */
public interface UserService extends ActiveService<User, Long> {

    /**
     * Check with the webservice if the user is authorized
     * 
     * @param userId
     *            the user id
     * @param right
     *            the name of the right
     * @return true if autorized, false in not
     * @throws IllegalStateException
     *             thrown if a not expected response status is returned
     */
    boolean isAuthorized(Long userId, String right) throws IllegalStateException;
}
