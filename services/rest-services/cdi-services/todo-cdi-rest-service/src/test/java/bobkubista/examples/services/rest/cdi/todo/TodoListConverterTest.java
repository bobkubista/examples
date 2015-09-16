/**
 *
 */
package bobkubista.examples.services.rest.cdi.todo;

import org.junit.Assert;

import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.services.rest.cdi.todo.TodoListConverter;
import bobkubista.examples.services.rest.cdi.todo.TodoListEntity;
import bobkubista.examples.utils.service.jpa.persistance.converter.AbstractTestEntityToDomainConverter;

/**
 * @author Bob Kubista
 *
 */
public class TodoListConverterTest extends AbstractTestEntityToDomainConverter<TodoList, TodoListCollection, TodoListEntity, Long> {

    private final TodoListConverter converter = new TodoListConverter();

    @Override
    protected void assertDomainObject(final TodoList domainModelObject, final TodoListEntity entity) {
        Assert.assertNotNull(domainModelObject);
        Assert.assertEquals(entity.getId(), domainModelObject.getId());
        Assert.assertEquals(entity.isActive(), domainModelObject.isActive());
        Assert.assertEquals(entity.getFunctionalId(), domainModelObject.getFunctionalId());
        Assert.assertEquals(entity.getTodoList().size(), domainModelObject.getTodoList().size());
    }

    @Override
    protected void assertEntity(final TodoList domainModelObject, final TodoListEntity entity) {
        Assert.assertNotNull(entity);
        Assert.assertEquals(entity.getId(), domainModelObject.getId());
        Assert.assertEquals(entity.isActive(), domainModelObject.isActive());
        Assert.assertEquals(entity.getFunctionalId(), domainModelObject.getFunctionalId());
        Assert.assertEquals(entity.getTodoList().size(), domainModelObject.getTodoList().size());
    }

    @Override
    protected TodoListConverter getConverter() {
        return this.converter;
    }

    @Override
    protected TodoList getEmptyDomainObject() {
        return new TodoList();
    }

    @Override
    protected TodoList getValidDomainObject() {
        final TodoList todoList = new TodoList();

        todoList.setActive(true);
        todoList.setFunctionalId("functionalId");
        todoList.setId(1L);

        return todoList;
    }

    @Override
    protected TodoListEntity getValidEntity() {
        final TodoListEntity todoList = new TodoListEntity();

        todoList.setActive(true);
        todoList.setFunctionalId("functionalId");
        todoList.setId(1L);

        return todoList;
    }

}
