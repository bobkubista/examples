/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.servlet.catalog;

import bobkubista.examples.services.api.catalog.model.games.MainGame;
import bobkubista.examples.services.api.catalog.model.games.MainGameCollection;
import bobkubista.examples.services.rest.servlet.catalog.entity.MainGameEntity;
import bobkubista.examples.utils.service.jpa.persistance.converter.AbstractEntityToDomainConverter;
import bobkubista.examples.utils.service.jpa.persistance.converter.EntityToDomainConverter;
import bobkubista.examples.utils.service.jpa.persistance.services.IdentifiableEntityService;

/**
 * @author Bob
 *
 */
public class MainGameConverter extends AbstractEntityToDomainConverter<MainGame, MainGameCollection, MainGameEntity, Long>
        implements EntityToDomainConverter<MainGame, MainGameCollection, MainGameEntity> {

    @Override
    protected MainGame doConvertToDomainObject(final MainGameEntity entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected MainGameEntity doConvertToEntity(final MainGame domainModelObject) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void doConvertToEntity(final MainGame domainModelObject, final MainGameEntity entityObject) {
        // TODO Auto-generated method stub

    }

    @Override
    protected MainGameCollection getNewDomainObjectCollection() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected IdentifiableEntityService<MainGameEntity, Long> getService() {
        // TODO Auto-generated method stub
        return null;
    }

}
