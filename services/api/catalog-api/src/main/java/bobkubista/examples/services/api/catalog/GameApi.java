/**
 *
 */
package bobkubista.examples.services.api.catalog;

import javax.ws.rs.core.Response;

import bobkubista.examples.services.api.catalog.model.Category;
import bobkubista.examples.services.api.catalog.model.Developer;
import bobkubista.examples.services.api.catalog.model.Serie;
import bobkubista.examples.services.api.catalog.model.games.MainGame;
import bobkubista.examples.utils.domain.model.api.ActiveApi;

/**
 * @author Bob
 *
 */
public interface GameApi extends ActiveApi<MainGame, Long> {
    /**
     *
     * Can be used for pagination
     *
     * @param identifier
     *            {@link Category} identifier
     *
     * @param startPosition
     *            amount of results to skip
     * @param maxResult
     *            amount of results
     * @return {@link MainGame} ids for a given {@link Category}
     */
    default Response getGamesForCategory(final Long identifier, final Integer startPosition, final Integer maxResult) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * Can be used for pagination
     *
     * @param identifier
     *            identifier of {@link Developer}
     *
     * @param startPosition
     *            amount of results to skip
     * @param maxResult
     *            amount of results
     * @return {@link MainGame} ids for a given {@link Developer}
     */
    default Response getGamesForDeveloper(final Long identifier, final Integer startPosition, final Integer maxResult) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * Can be used for pagination
     *
     * @param identifier
     *            identifier of {@link Serie}
     *
     * @param startPosition
     *            amount of results to skip
     * @param maxResult
     *            amount of results
     * @return {@link MainGame} ids for a given {@link Serie} id
     */
    default Response getGamesForSerie(final Long identifier, final Integer startPosition, final Integer maxResult) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * Can be used for pagination
     *
     * @param startPosition
     *            amount of results to skip
     * @param maxResult
     *            amount of results
     * @return {@link MainGame} ids sorted by launchdate
     */
    default Response getGamesSortedByLaunchDate(final Integer startPosition, final Integer maxResult) {
        throw new UnsupportedOperationException();
    }

    /**
     * Can be used for pagination
     *
     * @param startPosition
     *            amount of results to skip
     * @param maxResult
     *            amount of results
     * @return ids of {@link MainGame}s which are playable, so are active and
     *         are released
     */
    default Response getPlayableGames(final Integer startPosition, final Integer maxResult) {
        throw new UnsupportedOperationException();
    }
}
