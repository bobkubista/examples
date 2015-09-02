package bobkubista.examples.services.rest.todo;

import bobkubista.examples.services.api.todo.TodoApi;
import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.service.jpa.persistance.facade.GenericActiveFacade;

public class TodoFacade extends GenericActiveFacade<TodoList, Long, TodoListEntity, TodoListCollection>implements TodoApi {

	private TodoListConverter converter;
	private TodoListService service;

	@Override
	protected TodoListConverter getConverter() {
		return this.converter;
	}

	@Override
	protected TodoListService getService() {
		return this.service;
	}

}
