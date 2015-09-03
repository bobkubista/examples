/**
 *
 */
package bobkubista.examples.services.rest.todo;

import org.glassfish.jersey.server.ResourceConfig;

import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.service.jpa.persistance.AbstractFunctionalJerseyIT;

/**
 * @author Bob Kubista
 *
 */
public class TodoServiceJerseyIT extends AbstractFunctionalJerseyIT<TodoList, Long, TodoListCollection> {

	@Override
	public ResourceConfig configure(final ResourceConfig rc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void checkResponse(final TodoListCollection response, final int i) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void checkSingle(final TodoList types) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void checkUpdated(final TodoList response) {
		// TODO Auto-generated method stub

	}

	@Override
	protected TodoList create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getFunctionalId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPartionFunctionalId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TodoList update(final TodoList response) {
		// TODO Auto-generated method stub
		return null;
	}

}
