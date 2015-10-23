/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.user;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericActiveEntityDao;

/**
 * @author Bob
 *
 */
@Repository
public class RightDao extends AbstractGenericActiveEntityDao<Rights, Long> {

    @Override
    protected Path<String> getFunctionalIdField(final Root<Rights> entity) {
        return entity.<String> get("name");
    }

}
