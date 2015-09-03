/**
 *
 */
package bobkubista.examples.services.rest.todo;

import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.service.jpa.persistance.converter.AbstractEntityToDomainConverter;
import bobkubista.examples.utils.service.jpa.persistance.converter.AbstractTestEntityToDomainConverter;

/**
 * @author Bob Kubista
 *
 */
public class TodoListConverterTest extends AbstractTestEntityToDomainConverter<TodoList, TodoListCollection, TodoListEntity, Long> {

	@Override
	protected void assertDomainObject(final TodoList domainModelObject, final TodoListEntity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void assertEntity(final TodoList domainModelObject, final TodoListEntity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected AbstractEntityToDomainConverter<TodoList, TodoListCollection, TodoListEntity, Long> getConverter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TodoList getEmptyDomainObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TodoList getValidDomainObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TodoListEntity getValidEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
