/**
 *
 */
package bobkubista.examples.services.rest.user;

import javax.inject.Inject;
import javax.ws.rs.Path;

import bobkubista.examples.services.api.user.UserApi;
import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.services.api.user.domain.UserCollection;
import bobkubista.examples.utils.service.jpa.persistance.facade.GenericActiveFacade;

/**
 * @author Bob Kubista
 *
 */
@Path("/")
public class UserFacade extends GenericActiveFacade<User, Long, UserEntity, UserCollection>implements UserApi {

    @Inject
    private UserConverter converter;
    @Inject
    private UserService service;

    @Override
    protected UserConverter getConverter() {
        return this.converter;
    }

    @Override
    protected UserService getService() {
        return this.service;
    }

}
