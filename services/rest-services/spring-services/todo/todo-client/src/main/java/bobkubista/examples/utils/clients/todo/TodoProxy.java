/**
 *
 */
package bobkubista.examples.utils.clients.todo;

import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericActiveRestProxy;

/**
 * @author Bob Kubista
 *
 */
public class TodoProxy extends AbstractGenericActiveRestProxy<TodoList, Long, TodoListCollection>implements TodoServiceInteface {

    @Override
    protected TodoListCollection getAllFallback() {
        return this.getEmptyCollection();
    }

    @Override
    protected String getBasePath() {
        return ServerProperties.getString("todo.rest.service.base.path");
    }

    @Override
    protected String getBaseUri() {
        return "";
    }

    @Override
    protected TodoListCollection getEmptyCollection() {
        return new TodoListCollection();
    }
}
