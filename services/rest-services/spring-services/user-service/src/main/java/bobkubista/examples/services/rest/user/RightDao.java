/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.user;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericDao;
import bobkubista.examples.utils.service.jpa.persistance.dao.ActiveDAO;

/**
 * @author Bob
 *
 */
@Repository
public class RightDao extends AbstractGenericDao<Rights, Long>implements ActiveDAO<Rights, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RightDao.class);

    @Override
    public Path<String> getFunctionalIdField(final Root<Rights> entity) {
        return entity.<String> get("name");
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

}
