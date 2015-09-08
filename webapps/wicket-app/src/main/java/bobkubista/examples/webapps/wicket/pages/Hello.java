package bobkubista.examples.webapps.wicket.pages;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;

import bobkubista.examples.services.api.todo.domain.TodoList;
import bobkubista.examples.utils.clients.todo.TodoService;
import bobkubista.examples.webapps.wicket.pages.base.BasePage;

public class Hello extends BasePage {

	private static final long serialVersionUID = -3796141589395048837L;

	@Inject
	private TodoService todoService;

	public Hello() {
		this.add(new Label("body", "This is in the body"));
		final Collection<TodoList> todos = this.todoService.getAll();
		this.add(new PropertyListView<TodoList>("todos", todos.stream().collect(Collectors.toList())) {

			private static final long serialVersionUID = -2690991382022338625L;

			@Override
			protected void populateItem(final ListItem<TodoList> item) {
				item.add(new Label("todoListName"));
			}
		});

	}

}
