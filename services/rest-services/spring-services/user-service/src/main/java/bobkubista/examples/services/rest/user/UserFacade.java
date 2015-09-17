/**
 *
 */
package bobkubista.examples.services.rest.user;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bobkubista.examples.services.api.user.UserApi;
import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.services.api.user.domain.UserCollection;
import bobkubista.examples.utils.service.jpa.persistance.facade.AbstractGenericActiveFacade;

/**
 * @author Bob Kubista
 *
 */
@Service
@Path("/")
public class UserFacade extends AbstractGenericActiveFacade<User, Long, UserEntity, UserCollection>implements UserApi {

    @Autowired
    private UserConverter converter;
    @Autowired
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
