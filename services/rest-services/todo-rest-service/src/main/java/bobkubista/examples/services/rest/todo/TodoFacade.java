package bobkubista.examples.services.rest.todo;

import javax.inject.Inject;
import javax.ws.rs.Path;

import bobkubista.examples.services.api.todo.TodoApi;
import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.service.jpa.persistance.facade.GenericActiveFacade;

@Path("/")
public class TodoFacade extends GenericActiveFacade<TodoList, Long, TodoListEntity, TodoListCollection>implements TodoApi {

	@Inject
	private TodoListConverter converter;
	@Inject
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
