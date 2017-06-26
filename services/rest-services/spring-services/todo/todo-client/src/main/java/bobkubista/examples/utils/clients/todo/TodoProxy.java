/**
 *
 */
package bobkubista.examples.utils.clients.todo;

import java.util.Optional;
import java.util.function.Supplier;

import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericActiveRestProxy;

/**
 * @author Bob Kubista
 *
 */
public class TodoProxy extends AbstractGenericActiveRestProxy<TodoList, TodoListCollection>
		implements TodoServiceInteface {

	private final String baseUri;

	public TodoProxy() {
		this(() -> ServerProperties.get()
				.getString("todo.rest.service.base.uri"));
	}

	public TodoProxy(final String baseUri) {
		this(() -> baseUri);
	}

	public TodoProxy(final Supplier<String> baseUri) {
		super();
		this.baseUri = baseUri.get();
	}

	@Override
	protected TodoListCollection getAllFallback() {
		return this.getEmptyCollection();
	}

	@Override
	protected String getBasePath() {
		final Optional<String> path = Optional.ofNullable(ServerProperties.get()
				.getString("todo.rest.service.base.path"));
		return path.orElse("");
	}

	@Override
	protected String getBaseUri() {
		return this.baseUri;
	}

	@Override
	protected TodoListCollection getEmptyCollection() {
		return new TodoListCollection();
	}
}
