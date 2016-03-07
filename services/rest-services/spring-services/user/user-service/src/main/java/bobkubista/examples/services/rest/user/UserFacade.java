/**
 *
 */
package bobkubista.examples.services.rest.user;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bobkubista.examples.services.api.user.UserServerApi;
import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.services.api.user.domain.UserCollection;
import bobkubista.examples.utils.domain.model.annotation.http.cache.CacheNo;
import bobkubista.examples.utils.service.jpa.persistance.facade.AbstractGenericActiveFacade;

/**
 * @author Bob Kubista
 *
 */
@Service
@Path("/")
public class UserFacade extends AbstractGenericActiveFacade<User, Long, UserEntity, UserCollection>implements UserServerApi {

    @Autowired
    private UserConverter converter;

    @Autowired
    private UserService service;

    @CacheNo
    @Override
    public Response isAuthorized(final Long userId, final String right) {
        if (this.service.isAuthorized(userId, right)) {
            return Response.noContent()
                    .build();
        } else {
            return Response.status(Status.UNAUTHORIZED)
                    .build();
        }
    }

    @Override
    protected UserConverter getConverter() {
        return this.converter;
    }

    @Override
    protected UserService getService() {
        return this.service;
    }

}
