/**
 *
 */
package bobkubista.examples.services.rest.cdi.todo;

import javax.inject.Inject;

import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

/**
 * @author Bob Kubista
 *
 */
public class TodoListService implements ActiveEntityService<TodoListEntity, Long> {

	@Inject
	private TodoListDao dao;

	@Override
	public TodoListDao getDAO() {
		return this.dao;
	}

}
