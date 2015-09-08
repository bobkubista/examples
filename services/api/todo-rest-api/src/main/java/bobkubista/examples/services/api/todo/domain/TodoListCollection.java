/**
 *
 */
package bobkubista.examples.services.api.todo.domain;

import java.util.Collection;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;

/**
 * @author Bob Kubista
 *
 */
@XmlRootElement(name = "todolistcollection")
@XmlAccessorType(XmlAccessType.NONE)
public class TodoListCollection extends DomainObjectCollection<TodoList> {

	private static final long serialVersionUID = -8246993373889211905L;

	@XmlElementWrapper(name = "todolists")
	@XmlElement(name = "todolist")
	private final Collection<TodoList> todoList = new LinkedList<>();

	@Override
	public Collection<TodoList> getDomainCollection() {
		return this.todoList;
	}

}
