/**
 *
 */
package bobkubista.examples.services.rest.todo;

import javax.inject.Inject;

import bobkubista.examples.utils.service.jpa.persistance.dao.ActiveEntityDao;
import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

/**
 * @author Bob Kubista
 *
 */
public class TodoListService implements ActiveEntityService<TodoListEntity, Long> {

	@Inject
	private TodoListDao dao;

	@Override
	public ActiveEntityDao<TodoListEntity, Long> getDAO() {
		return this.dao;
	}

}
