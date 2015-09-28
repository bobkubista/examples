/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.servlet.catalog;

import javax.inject.Inject;

import bobkubista.examples.services.rest.servlet.catalog.entity.MainGameEntity;
import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

/**
 * @author Bob
 *
 */
public class GameService implements ActiveEntityService<MainGameEntity, Long> {

    @Inject
    private MainGameDao dao;

    @Override
    public MainGameDao getDAO() {
        return this.dao;
    }

}