/**
 *
 */
package bobkubista.examples.webapps.wicket.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiFunction;

import bobkubista.examples.services.api.todo.domain.Todo;
import bobkubista.examples.services.api.todo.domain.TodoList;

/**
 * @author Bob Kubista
 *
 */
public class TodoServiceMock implements TodoService {

	private static TodoService todoService;

	/**
	 * Singleton pattern
	 *
	 * @return {@link TodoService} mock
	 */
	public static TodoService getInstance() {
		if (todoService == null) {
			todoService = new TodoServiceMock();
		}
		return todoService;
	}

	private TodoServiceMock() {
	}

	@Override
	public Collection<TodoList> getAll() {
		final Collection<TodoList> result = new ArrayList<>();
		for (long i = 0; i < 3; i++) {
			final TodoList todoList = this.buildTodoList(i);

			result.add(todoList);
		}
		return result;
	}

	@Override
	public Collection<TodoList> getAllActive() {
		return this.getAll();
	}

	@Override
	public TodoList getByFunctionalId(final String functionalId) {
		return this.buildTodoList(functionalId);
	}

	@Override
	public TodoList getByID(final Long id) {
		return this.buildTodoList(id);
	}

	@Override
	public Long getIdByFunctionalId(final String fId) {
		return 1L;
	}

	private Todo buildTodo() {
		final Todo todo = new Todo();
		todo.setActive(true);
		todo.setValue("value");
		return todo;
	}

	private TodoList buildTodoList(final long i) {
		return this.buildTodoList(i, "functionalId" + i);
	}

	private TodoList buildTodoList(final long i, final String functionalId) {
		final BiFunction<Long, String, TodoList> biFunction = (id, functional) -> {
			final TodoList todoList = new TodoList();
			todoList.setId(id);
			todoList.setFunctionalId(functional);
			todoList.setActive(true);
			final Todo todo = this.buildTodo();
			todoList.getTodoList().add(todo);
			return todoList;
		};
		return biFunction.apply(i, functionalId);
	}

	private TodoList buildTodoList(final String functionalId) {
		return this.buildTodoList(1l, functionalId);
	}

}
