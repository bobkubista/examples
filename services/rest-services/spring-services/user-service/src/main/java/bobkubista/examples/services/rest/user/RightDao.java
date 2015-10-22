/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.user;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericActiveEntityDao;

/**
 * @author Bob
 *
 */
public class RightDao extends AbstractGenericActiveEntityDao<Right, Long> {

    @Override
    protected Path<String> getFunctionalIdField(final Root<Right> entity) {
        return entity.<String> get("name");
    }

}
