/**
 *
 */
package bobkubista.examples.webapps.wicket.pages;

import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.rest.utils.service.ActiveService;

/**
 * @author Bob Kubista
 *
 */
public interface TodoService extends ActiveService<TodoList, Long, TodoListCollection> {

}
