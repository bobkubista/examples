/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.servlet.catalog;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import bobkubista.examples.services.rest.servlet.catalog.entity.MainGameEntity;
import bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericActiveEntityDao;
import bobkubista.examples.utils.service.jpa.persistance.dao.ActiveDAO;

/**
 * @author Bob
 *
 */
public class MainGameDao extends AbstractGenericActiveEntityDao<MainGameEntity, Long>implements ActiveDAO<MainGameEntity, Long> {

    @Override
    protected Path<String> getFunctionalIdField(final Root<MainGameEntity> entity) {
        return entity.<String> get("seoName");
    }

}
