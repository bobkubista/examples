package bobkubista.examples.utils.clients.todo;

import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.rest.utils.service.ActiveService;

/**
 * Interface extending {@link ActiveService} for {@link TodoList}
 * 
 * @author Bob
 *
 */
public interface TodoServiceInteface extends ActiveService<TodoList, Long, TodoListCollection> {

}
