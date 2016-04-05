/**
 *
 */
package bobkubista.examples.services.rest.user;

import javax.inject.Named;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericDao;
import bobkubista.examples.utils.service.jpa.persistance.dao.GenericActiveDAO;
import bobkubista.examples.utils.service.jpa.persistance.dao.GenericFunctionalIdentifiableDao;

/**
 * @author Bob Kubista
 *
 */
@Named
public class UserDao extends AbstractGenericDao<UserEntity, Long>implements GenericActiveDAO<UserEntity, Long>, GenericFunctionalIdentifiableDao<UserEntity, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

    @Override
    public Path<String> getFunctionalIdField(final Root<UserEntity> entity) {
        return entity.<String> get("email");
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

}
