/**
 *
 */
package bobkubista.examples.services.api.todo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;

/**
 * @author Bob Kubista
 *
 */
@XmlRootElement(name = "todoList")
@XmlAccessorType(XmlAccessType.NONE)
public class TodoList extends AbstractGenericActiveDomainObject {
	private static final long serialVersionUID = 435396239252623825L;

	@XmlElement(required = true)
	private Long id;

	@XmlElementWrapper(name = "todos")
	@XmlElement(name = "todo")
	private final List<Todo> todos = new ArrayList<>();

	/**
	 * default constructor
	 */
	public TodoList() {
		super();
	}

	/**
	 * Constructor
	 *
	 * @param active
	 *            active flag
	 * @param todoListName
	 *            Name of the todolist
	 */
	public TodoList(final boolean active, final String todoListName) {
		super(active, todoListName);
	}

	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * @return
	 */
	public List<Todo> getTodoList() {
		return this.todos;
	}

	@Override
	public void setId(final Long id) {
		this.id = id;
	}
}
