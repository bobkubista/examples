package bobkubista.examples.services.api.user;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.utils.domain.model.api.ActiveServerApi;
import bobkubista.examples.utils.domain.model.api.IdentifiableServerApi;

/**
 * @author Bob Kubista
 *
 */
public interface UserServerApi extends ActiveServerApi<User> {

	/**
	 * Checks if the user is authorized for a given right.
	 *
	 * @param userId
	 *            the identifier of the user
	 * @param right
	 *            the name of the right
	 * @return Status 200 if authorized, otherwise 401
	 */
	@GET
	@Path("{userId}/{right}")
	default Response isAuthorized(@PathParam("userId") final Long userId, @PathParam("right") final String right) {
		return IdentifiableServerApi.buildMethodNotAllowedResponse(userId, right);
	}
}
