package bobkubista.examples.webapps.wicket.pages;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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

	private final Map<String, Long> functionalIdMap = new HashMap<>();

	private final Map<Long, TodoList> list = new HashMap<>();

	private TodoServiceMock() {
		for (long i = 0; i < 3; i++) {
			final TodoList todoList = this.buildTodoList(i);
			this.functionalIdMap.put(todoList.getFunctionalId(), todoList.getId());
			this.list.put(todoList.getId(), todoList);
		}
	}

	@Override
	public void create(final TodoList object) {
		this.list.put(object.getId(), object);
		this.functionalIdMap.put(object.getFunctionalId(), object.getId());
	}

	@Override
	public void delete(final Long id) {
		this.functionalIdMap.remove(this.list.get(id).getFunctionalId());
		this.list.remove(id);
	}

	@Override
	public Collection<TodoList> getAll() {
		return this.list.values();
	}

	@Override
	public Collection<TodoList> getAllActive() {
		return this.getAll();
	}

	@Override
	public TodoList getByFunctionalId(final String functionalId) {
		return this.list.get(this.functionalIdMap.get(functionalId));
	}

	@Override
	public TodoList getByID(final Long id) {
		return this.list.get(id);
	}

	@Override
	public Long getIdByFunctionalId(final String fId) {
		return this.functionalIdMap.get(fId);
	}

	@Override
	public TodoList update(final TodoList object) {
		this.list.replace(object.getId(), object);
		this.functionalIdMap.put(object.getFunctionalId(), object.getId());
		return object;
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

}
