package bobkubista.examples.services.rest.user;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.transaction.annotation.Transactional;

import bobkubista.examples.utils.domain.model.RestConstants;
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

    public void changePassword(final Long userId, final String oldPassword, final String password, final String passwordCheck) {
        final UserEntity user = this.dao.getById(userId)
                .orElseThrow(() -> new NotAuthorizedException(Response.status(Status.UNAUTHORIZED)
                        .build()));
        if (user.getEncryptedPassword()
                .equals(DigestUtils.sha512Hex(oldPassword)) && password.equals(passwordCheck)) {
            user.setEncryptedPassword(DigestUtils.sha512Hex(password));
            this.dao.update(user);
        } else if (!password.equals(passwordCheck)) {
            throw new ClientErrorException("New passwords are not the same", RestConstants.UNPROCESSABLE_ENTITY);
        } else {
            new NotAuthorizedException(Response.status(Status.UNAUTHORIZED)
                    .build());
        }
    }

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
                        .orElseThrow(() -> new NotAuthorizedException(Response.status(Status.UNAUTHORIZED)
                                .build())));
    }

}
