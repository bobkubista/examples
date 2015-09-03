/**
 *
 */
package bobkubista.examples.utils.clients.todo;

import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.services.api.todo.TodoApi;
import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestActiveProxy;

/**
 * @author Bob Kubista
 *
 */
public class TodoProxy extends AbstractGenericRestActiveProxy<TodoList, Long>implements TodoApi {

	@Override
	protected String getBasePath() {
		return ServerProperties.getString("rest.service.base.path");
	}

	@Override
	protected String getBaseUri() {
		return "";
	}

}
