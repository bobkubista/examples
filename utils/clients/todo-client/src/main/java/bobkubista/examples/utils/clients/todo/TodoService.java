/**
 *
 */
package bobkubista.examples.utils.clients.todo;

import javax.inject.Inject;

import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.rest.utils.service.AbstractActiveService;

/**
 * @author Bob Kubista
 *
 */
public class TodoService extends AbstractActiveService<TodoList, Long, TodoListCollection>implements TodoServiceInteface {

    @Inject
    private TodoProxy proxy;

    @Override
    protected TodoProxy getProxy() {
        return this.proxy;
    }

}
