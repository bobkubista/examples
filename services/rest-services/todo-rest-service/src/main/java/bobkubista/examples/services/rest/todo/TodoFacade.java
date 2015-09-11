package bobkubista.examples.services.rest.todo;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bobkubista.examples.services.api.todo.TodoApi;
import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.jee.annotation.Logging;
import bobkubista.examples.utils.service.jpa.persistance.facade.GenericActiveFacade;

@Path("/")
@Logging
@Service
public class TodoFacade extends GenericActiveFacade<TodoList, Long, TodoListEntity, TodoListCollection>implements TodoApi {

	@Autowired
	private TodoListConverter converter;
	@Autowired
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
