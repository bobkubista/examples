package bobkubista.examples.services.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

/**
 * {@link ActiveEntityService} for the {@link UserEntity}
 *
 * @author Bob
 *
 */
@Service
public class UserService implements ActiveEntityService<UserEntity, Long> {

    @Autowired
    private UserDao dao;

    @Autowired
    private RightService rightService;

    @Override
    public UserDao getDAO() {
        return this.dao;
    }

    public boolean isAuthorized(final Long userId, final String right) {
        return this.getById(userId).isAuthorized(this.rightService.getByFunctionalId(right));
    }

}
