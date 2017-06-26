/**
 *
 */
package bobkubista.examples.services.rest.user;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import bobkubista.examples.services.api.user.UserServerApi;
import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.services.api.user.domain.UserCollection;
import bobkubista.examples.utils.domain.model.annotation.http.cache.CacheNo;
import bobkubista.examples.utils.service.jpa.persistance.facade.AbstractGenericActiveFacade;

/**
 * @author Bob Kubista
 *
 */
@Named
@Path("/")
public class UserFacade extends AbstractGenericActiveFacade<User, UserEntity, UserCollection> implements UserServerApi {

	@Inject
	private UserConverter converter;

	@Inject
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
