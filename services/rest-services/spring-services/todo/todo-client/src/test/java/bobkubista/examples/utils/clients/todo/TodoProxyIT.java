package bobkubista.examples.utils.clients.todo;

import java.util.UUID;

import javax.servlet.ServletRegistration;

import org.glassfish.grizzly.servlet.WebappContext;
import org.junit.Ignore;

import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;
import bobkubista.examples.utils.client.BaseClientRest;
import bobkubista.examples.utils.rest.utils.service.GenericETagModifiedDateDomainObjectDecorator;

@Ignore
public class TodoProxyIT extends BaseClientRest<TodoList, TodoListCollection> {

	@Override
	public void buildContext(final WebappContext context) {
		final ServletRegistration registration = context.addServlet("Todo-rest-service",
				"org.glassfish.jersey.servlet.ServletContainer");
		registration.setInitParameter("jersey.config.server.provider.packages",
				"bobkubista.examples.services.rest.todo");
		registration.addMapping("/*");
		context.addContextInitParameter("contextConfigLocation", "classpath:spring/spring-config.xml");
		context.addListener("org.springframework.web.context.ContextLoaderListener");
		context.addListener("org.springframework.web.context.request.RequestContextListener");
	}

	@Override
	protected TodoList buildNew() {
		return new TodoList(true, UUID.randomUUID()
				.toString());
	}

	@Override
	protected TodoProxy getClient() {
		return new TodoProxy(BASE_URI);
	}

	@Override
	protected Long getIdentifier() {
		return 1L;
	}

	@Override
	protected void updateObject(final GenericETagModifiedDateDomainObjectDecorator<TodoList> object) {
		object.getObject()
				.getTodoList()
				.clear();
	}

}
