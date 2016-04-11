package bobkubista.examples.utils.clients.todo;

import java.util.UUID;

import javax.servlet.ServletRegistration;

import org.glassfish.grizzly.servlet.WebappContext;

import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.services.api.todo.domain.TodoListCollection;

public class TodoProxyIT extends BaseClientRestIT<TodoList, Long, TodoListCollection> {

    // TODO add dbunit
    @Override
    public WebappContext getContext() {
        final WebappContext context = new WebappContext("Integration test webapp", "");
        final ServletRegistration registration = context.addServlet("Todo-rest-service", "org.glassfish.jersey.servlet.ServletContainer");
        registration.setInitParameter("jersey.config.server.provider.packages", "bobkubista.examples.services.rest.todo");
        registration.addMapping("/*");
        context.addContextInitParameter("contextConfigLocation", "classpath:spring/spring-config.xml");
        context.addListener("org.springframework.web.context.ContextLoaderListener");
        context.addListener("org.springframework.web.context.request.RequestContextListener");
        return context;
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

}
