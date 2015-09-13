package bobkubista.examples.services.rest.todo;

import javax.annotation.PostConstruct;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bobkubista.examples.services.api.todo.TodoApi;
import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.service.jpa.persistance.facade.GenericActiveFacade;

@Service
@Path("/")
public class TodoFacade extends GenericActiveFacade<TodoList, Long, TodoListEntity, TodoListCollection>implements TodoApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(TodoFacade.class);

	@Autowired
	private TodoListConverter converter;
	@Autowired
	private TodoListService service;

	public TodoFacade() {
		super();
		LOGGER.info("Created facade");
	}

	@PostConstruct
	public void doNothing() {
		LOGGER.info("Postconstruct facade");
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
