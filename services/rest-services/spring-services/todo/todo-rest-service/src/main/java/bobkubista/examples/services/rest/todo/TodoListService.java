/**
 *
 */
package bobkubista.examples.services.rest.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

/**
 * @author Bob Kubista
 *
 */
@Service
public class TodoListService implements ActiveEntityService<TodoListEntity> {

	@Autowired
	private TodoListDao dao;

	/**
	 * Constructor
	 */
	public TodoListService() {
		super();
	}

	@Override
	public TodoListDao getDAO() {
		return this.dao;
	}
}
