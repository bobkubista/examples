package bobkubista.examples.services.rest.user;

import javax.inject.Inject;

import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

/**
 * {@link ActiveEntityService} for the {@link UserEntity}
 * 
 * @author Bob
 *
 */
public class UserService implements ActiveEntityService<UserEntity, Long> {

	@Inject
	private UserDao dao;

	@Override
	public UserDao getDAO() {
		return this.dao;
	}

}
