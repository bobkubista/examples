/**
 *
 */
package bobkubista.examples.services.rest.todo;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;

import bobkubista.examples.services.api.todo.domain.Todo;
import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.service.jpa.persistance.spring.jersey.dbunit.AbstractFunctionalJerseyIT;

/**
 * @author Bob Kubista
 *
 */
public class TodoServiceJerseyIT extends AbstractFunctionalJerseyIT<TodoList, Long, TodoListCollection> {

    private static final String FUNCTIONALID = "todoitem.a";
    private static final Long ID = 1L;
    private static final String PARTIAL_FUNCTIONAL_ID = "todoitem";

    @Override
    public void checkResponseGetAll(final TodoListCollection response, final int size) {
        Assert.assertNotNull(response);
        Assert.assertEquals(size, response.getDomainCollection().size());
        Assert.assertEquals(this.getId(), response.getDomainCollection().iterator().next().getId());
    }

    @Override
    public void checkSingle(final TodoList response) {
        Assert.assertNotNull(response);
        Assert.assertEquals(this.getFunctionalId(), response.getFunctionalId());
        Assert.assertNotNull(response.getTodoList());
        Assert.assertFalse(response.getTodoList().isEmpty());
    }

    @Override
    public void checkUpdated(final TodoList response) {
        Assert.assertNotNull(response);
        Assert.assertEquals("something", response.getFunctionalId());
        Assert.assertNotNull(response.getTodoList());
        Assert.assertEquals(1, response.getTodoList().size());
    }

    @Override
    public ResourceConfig configure(final ResourceConfig rc) {
        return rc.register(TodoFacade.class);
    }

    @Override
    public TodoList create() {
        final TodoList todoList = new TodoList();

        todoList.setActive(true);
        todoList.setFunctionalId("bla");
        todoList.setId(2L);
        final Todo todo = new Todo();
        todo.setActive(true);
        todo.setValue("something");
        todoList.getTodoList().add(todo);

        return todoList;
    }

    @Override
    public Long getId() {
        return ID;
    }

    @Override
    public TodoList update(final TodoList response) {
        response.setFunctionalId("todo.abc");
        return response;
    }

    @Override
    protected String getFunctionalId() {
        return FUNCTIONALID;
    }

    @Override
    protected String getPartionFunctionalId() {
        return PARTIAL_FUNCTIONAL_ID;
    }

}
