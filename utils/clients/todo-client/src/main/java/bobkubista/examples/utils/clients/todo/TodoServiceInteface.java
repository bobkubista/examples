package bobkubista.examples.utils.clients.todo;

import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.rest.utils.ActiveService;

public interface TodoServiceInteface extends ActiveService<TodoList, Long, TodoListCollection> {

}
