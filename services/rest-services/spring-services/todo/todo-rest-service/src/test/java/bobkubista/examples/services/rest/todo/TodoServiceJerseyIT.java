/**
 *
 */
package bobkubista.examples.services.rest.todo;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;

import com.google.common.collect.Ordering;

import bobkubista.examples.services.api.todo.domain.Todo;
import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.service.jpa.persistance.spring.jersey.dbunit.AbstractActiveJerseyIT;

/**
 * @author Bob Kubista
 *
 */
public class TodoServiceJerseyIT extends AbstractActiveJerseyIT<TodoList, TodoListCollection> {

	private static final String FUNCTIONALID = "todoitem.a";
	private static final Long ID = 1L;
	private static final String PARTIAL_FUNCTIONAL_ID = "todoitem";

	@Override
	public ResourceConfig configure(final ResourceConfig rc) {
		return rc.register(TodoFacade.class);
	}

	@Override
	protected void checkResponseGetAll(final TodoListCollection response, final int size) {
		Assert.assertNotNull(response);
		Assert.assertEquals(size, response.getDomainCollection()
				.size());
		Assert.assertEquals(this.getId(), response.getDomainCollection()
				.iterator()
				.next()
				.getId());
	}

	@Override
	protected void checkSingle(final TodoList response) {
		Assert.assertNotNull(response);
		Assert.assertEquals(this.getFunctionalId(), response.getFunctionalId());
		Assert.assertNotNull(response.getTodoList());
		Assert.assertFalse(response.getTodoList()
				.isEmpty());
	}

	@Override
	protected void checkSorting(final TodoListCollection response, final boolean reverse) {
		if (reverse) {
			Assert.assertTrue(Ordering.from((o1, o2) -> (int) (((TodoList) o2).getId() - ((TodoList) o1).getId()))
					.isOrdered(response.getDomainCollection()));
		} else {
			Assert.assertTrue(Ordering.from((o1, o2) -> (int) (((TodoList) o1).getId() - ((TodoList) o2).getId()))
					.isOrdered(response.getDomainCollection()));
		}
	}

	@Override
	protected void checkUpdated(final TodoList response) {
		Assert.assertNotNull(response);
		Assert.assertEquals("todo.abc", response.getFunctionalId());
		Assert.assertNotNull(response.getTodoList());
		Assert.assertEquals(2, response.getTodoList()
				.size());
	}

	@Override
	protected TodoList create() {
		final TodoList todoList = new TodoList();

		todoList.setActive(true);
		todoList.setFunctionalId("bla");
		todoList.setId(2L);
		final Todo todo = new Todo();
		todo.setActive(true);
		todo.setValue("something");
		todoList.getTodoList()
				.add(todo);

		return todoList;
	}

	@Override
	protected int expectedSize() {
		return 1;
	}

	@Override
	protected String getFunctionalId() {
		return FUNCTIONALID;
	}

	@Override
	protected Long getId() {
		return ID;
	}

	@Override
	protected String getIdField() {
		return "id";
	}

	@Override
	protected String getPartionFunctionalId() {
		return PARTIAL_FUNCTIONAL_ID;
	}

	@Override
	protected TodoList update(final TodoList response) {
		response.setFunctionalId("todo.abc");
		return response;
	}

}
