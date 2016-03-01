/**
 *
 */
package bobkubista.examples.services.rest.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericDao;
import bobkubista.examples.utils.service.jpa.persistance.dao.GenericIdentifiableDao;

/**
 * @author Bob
 *
 */
public class TodoEntityDao extends AbstractGenericDao<TodoEntity, Long>implements GenericIdentifiableDao<TodoEntity, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoEntityDao.class);

    /**
     * Constructor
     */
    public TodoEntityDao() {
        super();
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
