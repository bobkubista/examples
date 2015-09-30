/**
 * Bob Kubista's examples
 */
package bobkubista.examples.webapps.jsf;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.utils.clients.user.UserCache;

/**
 * @author Bob
 *
 */
@ManagedBean
@RequestScoped
public class UserBean {

    @Inject
    private UserCache userCache;

    /**
     *
     * @return
     */
    public Collection<User> getUsers() {
        return this.userCache.getAll().values();
    }
}
