/**
 *
 */
package bobkubista.examples.services.rest.todo;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bobkubista.examples.utils.service.jpa.persistance.dao.ActiveDAO;
import bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericActiveEntityDao;

/**
 * @author Bob Kubista
 *
 */
@Repository
class TodoListDao extends AbstractGenericActiveEntityDao<TodoListEntity, Long>implements ActiveDAO<TodoListEntity, Long> {

	@Override
	protected Path<String> getFunctionalIdField(final Root<TodoListEntity> entity) {
		return entity.<String> get("todoListName");
	}

}
