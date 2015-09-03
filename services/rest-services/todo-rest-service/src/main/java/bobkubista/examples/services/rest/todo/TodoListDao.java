/**
 *
 */
package bobkubista.examples.services.rest.todo;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import bobkubista.examples.utils.service.jpa.persistance.dao.ActiveDAO;
import bobkubista.examples.utils.service.jpa.persistance.dao.ActiveEntityDao;

/**
 * @author Bob Kubista
 *
 */
class TodoListDao extends ActiveEntityDao<TodoListEntity, Long>implements ActiveDAO<TodoListEntity, Long> {

	@Override
	protected Path<String> getFunctionalIdField(final Root<TodoListEntity> entity) {
		return entity.<String> get("todoListName");
	}

}