package bobkubista.examples.services.rest.cdi.todo;

import javax.inject.Inject;
import javax.ws.rs.Path;

import bobkubista.examples.services.api.todo.TodoApi;
import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.jee.annotation.Logging;
import bobkubista.examples.utils.service.jpa.persistance.facade.AbstractGenericActiveFacade;

/**
 *
 * @author Bob
 *
 */
@Path("/")
@Logging
public class TodoFacade extends AbstractGenericActiveFacade<TodoList, Long, TodoListEntity, TodoListCollection>implements TodoApi {

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
