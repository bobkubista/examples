/**
 *
 */
package bobkubista.examples.services.rest.cdi.todo;

import javax.enterprise.inject.Default;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import bobkubista.examples.utils.service.jpa.persistance.dao.ActiveDAO;
import bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericActiveEntityDao;

/**
 * @author Bob Kubista
 *
 */
@Default
class TodoListDao extends AbstractGenericActiveEntityDao<TodoListEntity, Long>implements ActiveDAO<TodoListEntity, Long> {

	@Override
	protected Path<String> getFunctionalIdField(final Root<TodoListEntity> entity) {
		return entity.<String> get("todoListName");
	}

}
