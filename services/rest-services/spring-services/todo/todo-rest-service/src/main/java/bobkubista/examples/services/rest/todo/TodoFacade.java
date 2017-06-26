package bobkubista.examples.services.rest.todo;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bobkubista.examples.services.api.todo.TodoApi;
import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.service.jpa.persistance.facade.AbstractGenericActiveFacade;

/**
 *
 * @author Bob
 *
 *         Implementation of {@link TodoApi}
 */
@Service
@Path("/")
public class TodoFacade extends AbstractGenericActiveFacade<TodoList, TodoListEntity, TodoListCollection>
		implements TodoApi {

	@Autowired
	private TodoListConverter converter;
	@Autowired
	private TodoListService service;

	/**
	 * Constructor
	 */
	public TodoFacade() {
		super();
	}

	@Override
	protected TodoListConverter getConverter() {
		return this.converter;
	}

	@Override
	protected TodoListService getService() {
		return this.service;
	}

}
