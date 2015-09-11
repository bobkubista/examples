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
public class TodoListService implements ActiveEntityService<TodoListEntity, Long> {

	@Autowired
	private TodoListDao dao;

	@Override
	public TodoListDao getDAO() {
		return this.dao;
	}

}
