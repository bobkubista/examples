package bobkubista.examples.utils.clients.user;

import javax.ws.rs.core.Response.Status;

import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.services.api.user.domain.UserCollection;
import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericActiveRestProxy;

/**
 * @author Bob Kubista {@link AbstractGenericRestActiveProxy} for {@link User}
 */
public class UserProxy extends AbstractGenericActiveRestProxy<User, Long, UserCollection>implements UserService {

    @Override
    public boolean isAuthorized(final Long userId, final String right) {
        final int status = this.getRequest(this.getServiceWithPaths(userId.toString(), right))
                .get()
                .getStatus();
        return status == Status.OK.getStatusCode();
    }

    @Override
    protected UserCollection getAllFallback() {
        return this.getEmptyCollection();
    }

    @Override
    protected String getBasePath() {
        return ServerProperties.getString("user.rest.service.base.path");
    }

    @Override
    protected String getBaseUri() {
        return "";
    }

    @Override
    protected UserCollection getEmptyCollection() {
        return new UserCollection();
    }

}
