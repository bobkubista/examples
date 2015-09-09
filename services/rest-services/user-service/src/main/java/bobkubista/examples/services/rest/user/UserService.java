package bobkubista.examples.services.rest.user;

import javax.inject.Inject;

import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

public class UserService implements ActiveEntityService<UserEntity, Long> {

	@Inject
	private UserDao dao;

	@Override
	public UserDao getDAO() {
		return this.dao;
	}

}
