/**
 *
 */
package bobkubista.examples.utils.clients.todo;

import javax.inject.Inject;

import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.utils.rest.utils.cache.AbstractFunctionalAutoCache;

/**
 * @author Bob
 *
 */
public class TodoCache extends AbstractFunctionalAutoCache<Long, TodoList> {

    @Inject
    private TodoServiceInteface todoService;

    @Override
    protected TodoServiceInteface getFunctionalService() {
        return this.todoService;
    }

}
