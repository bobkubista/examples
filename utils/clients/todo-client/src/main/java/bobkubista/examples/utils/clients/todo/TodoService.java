/**
 *
 */
package bobkubista.examples.utils.clients.todo;

import java.util.Collection;

import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.rest.utils.ActiveService;

/**
 * @author Bob Kubista
 *
 */
// TODO make this generic
public class TodoService implements ActiveService<TodoList, Long, TodoListCollection> {

	private TodoProxy proxy;

	@Override
	public Collection<TodoList> getAll() {
		return this.proxy.getAll().readEntity(TodoListCollection.class).getDomainCollection();
	}

	@Override
	public Collection<TodoList> getAllActive() {
		return this.proxy.getAllActive().readEntity(TodoListCollection.class).getDomainCollection();
	}

	@Override
	public TodoList getByFunctionalId(final String functionalId) {
		return this.proxy.getByFunctionalId(functionalId).readEntity(TodoList.class);
	}

	@Override
	public TodoList getByID(final Long id) {
		return this.proxy.getByID(id).readEntity(TodoList.class);
	}

	@Override
	public Long getIdByFunctionalId(final String fId) {
		return this.proxy.getIdByFunctionalId(fId).readEntity(Long.class);
	}

}
