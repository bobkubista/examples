/**
 *
 */
package bobkubista.examples.webapps.jsf;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.utils.clients.user.UserServiceInterface;

/**
 * @author Bob Kubista
 *
 */
@RequestScoped
@Named
public class UserRequestBean {
	@Inject
	private UserServiceInterface userService;

	public Collection<User> getAllUsers() {
		return this.userService.getAll();
	}

}
