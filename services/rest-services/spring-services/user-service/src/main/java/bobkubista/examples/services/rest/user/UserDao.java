/**
 *
 */
package bobkubista.examples.services.rest.user;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericActiveEntityDao;
import bobkubista.examples.utils.service.jpa.persistance.dao.ActiveDAO;

/**
 * @author Bob Kubista
 *
 */
@Repository
public class UserDao extends AbstractGenericActiveEntityDao<UserEntity, Long>implements ActiveDAO<UserEntity, Long> {

    @Override
    protected Path<String> getFunctionalIdField(final Root<UserEntity> entity) {
        return entity.<String> get("email");
    }

}
