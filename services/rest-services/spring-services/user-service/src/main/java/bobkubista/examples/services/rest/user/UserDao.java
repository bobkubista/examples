/**
 *
 */
package bobkubista.examples.services.rest.user;

import javax.enterprise.inject.Default;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import bobkubista.examples.utils.service.jpa.persistance.dao.ActiveDAO;
import bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericActiveEntityDao;

/**
 * @author Bob Kubista
 *
 */
@Default
public class UserDao extends AbstractGenericActiveEntityDao<UserEntity, Long>implements ActiveDAO<UserEntity, Long> {

	@Override
	protected Path<String> getFunctionalIdField(final Root<UserEntity> entity) {
		return entity.<String> get("email");
	}

}
