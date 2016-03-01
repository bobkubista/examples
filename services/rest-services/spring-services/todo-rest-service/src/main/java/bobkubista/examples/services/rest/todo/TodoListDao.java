/**
 *
 */
package bobkubista.examples.services.rest.todo;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericDao;
import bobkubista.examples.utils.service.jpa.persistance.dao.ActiveDAO;

/**
 * @author Bob Kubista
 *
 */
@Repository
class TodoListDao extends AbstractGenericDao<TodoListEntity, Long>implements ActiveDAO<TodoListEntity, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoListDao.class);

    @Override
    public Path<String> getFunctionalIdField(final Root<TodoListEntity> entity) {
        return entity.<String> get("todoListName");
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

}
