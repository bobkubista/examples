/**
 *
 */
package bobkubista.examples.services.rest.servlet.catalog;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

import bobkubista.examples.services.api.catalog.GameApi;
import bobkubista.examples.services.api.catalog.model.games.MainGame;
import bobkubista.examples.services.api.catalog.model.games.MainGameCollection;
import bobkubista.examples.services.rest.servlet.catalog.entity.MainGameEntity;
import bobkubista.examples.utils.service.jpa.persistance.facade.AbstractGenericActiveFacade;
import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

/**
 * @author Bob
 *
 */
@WebServlet("")
public class GameFacade extends AbstractGenericActiveFacade<MainGame, Long, MainGameEntity, MainGameCollection>implements GameApi {

    @Inject
    private MainGameConverter converter;
    @Inject
    private GameService gameService;

    @Override
    protected MainGameConverter getConverter() {
        return this.converter;
    }

    @Override
    protected ActiveEntityService<MainGameEntity, Long> getService() {
        return this.gameService;
    }

}
