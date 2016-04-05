package bobkubista.examples.services.rest.user;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

/**
 * {@link ActiveEntityService} for the {@link UserEntity}
 *
 * @author Bob
 *
 */
@Named
@Transactional
public class UserService implements ActiveEntityService<UserEntity, Long> {

    @Inject
    private UserDao dao;

    @Override
    public UserDao getDAO() {
        return this.dao;
    }

    /**
     * Authorize a user
     *
     * @param userId
     *            the user id
     * @param right
     *            the name of the right
     * @return true if autorized
     */
    public boolean isAuthorized(final Long userId, final String right) {
        return UserEntity.isAuthorized(right)
                .test(this.getById(userId)
                        .get());
    }

}
