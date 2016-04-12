/**
 *
 */
package bobkubista.examples.services.rest.todo;

import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bobkubista.examples.services.api.todo.domain.Todo;
import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.service.jpa.persistance.converter.AbstractEntityToDomainConverter;
import bobkubista.examples.utils.service.jpa.persistance.converter.EntityToDomainConverter;

/**
 * @author Bob Kubista
 *
 */
@Component
public class TodoListConverter extends AbstractEntityToDomainConverter<TodoList, TodoListCollection, TodoListEntity, Long>
        implements EntityToDomainConverter<TodoList, TodoListCollection, TodoListEntity> {

    @Autowired
    private TodoListService service;

    @Override
    protected TodoList doConvertToDomainObject(final TodoListEntity entity) {
        final TodoList result = new TodoList();
        if (entity != null) {
            result.setActive(entity.isActive());
            result.setFunctionalId(entity.getFunctionalId());
            result.setId(entity.getId());
            result.getTodoList()
                    .addAll(entity.getTodoList()
                            .stream()
                            .map(t -> {
                                final Todo todo = new Todo();
                                todo.setActive(t.isActive());
                                todo.setValue(t.getValue());
                                return todo;
                            })
                            .collect(Collectors.toList()));
        }
        return result;
    }

    @Override
    protected TodoListEntity doConvertToEntity(final TodoList domainModelObject) {
        final TodoListEntity result = new TodoListEntity();
        this.doConvertToEntity(domainModelObject, result);
        return result;
    }

    @Override
    protected void doConvertToEntity(final TodoList domainModelObject, final TodoListEntity entityObject) {
        Validate.notNull(entityObject);
        Validate.notNull(domainModelObject);

        entityObject.setActive(domainModelObject.isActive());
        entityObject.setFunctionalId(domainModelObject.getFunctionalId());
        entityObject.setId(domainModelObject.getId());
        entityObject.getTodoList()
                .clear();
        entityObject.getTodoList()
                .addAll(domainModelObject.getTodoList()
                        .stream()
                        .map(t -> {
                            final TodoEntity todo = new TodoEntity();
                            todo.setActive(t.isActive());
                            todo.setValue(t.getValue());
                            todo.setListEntity(entityObject);
                            return todo;
                        })
                        .collect(Collectors.toList()));
    }

    @Override
    protected TodoListCollection getNewDomainObjectCollection() {
        return new TodoListCollection();
    }

    @Override
    protected TodoListService getService() {
        return this.service;
    }

}
