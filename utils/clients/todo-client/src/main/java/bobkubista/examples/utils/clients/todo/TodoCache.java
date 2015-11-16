/**
 *
 */
package bobkubista.examples.utils.clients.todo;

import javax.inject.Inject;

import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.utils.rest.utils.cache.AbstractActiveAutoCache;

/**
 * @author Bob
 *
 */
public class TodoCache extends AbstractActiveAutoCache<Long, TodoList> {

    @Inject
    private TodoServiceInteface todoService;

    @Override
    protected TodoServiceInteface getActiveService() {
        return this.todoService;
    }

}
