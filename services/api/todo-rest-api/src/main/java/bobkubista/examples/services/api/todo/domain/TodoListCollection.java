/**
 *
 */
package bobkubista.examples.services.api.todo.domain;

import java.util.Collection;
import java.util.LinkedList;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;

/**
 * @author Bob Kubista
 *
 */
public class TodoListCollection extends DomainObjectCollection<TodoList> {

	private static final long serialVersionUID = -8246993373889211905L;

	private final Collection<TodoList> todoList = new LinkedList<>();

	@Override
	public Collection<TodoList> getDomainCollection() {
		return this.todoList;
	}

}
