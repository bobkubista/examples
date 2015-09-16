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
public class TodoList extends AbstractGenericActiveDomainObject<Long> {
    private static final long serialVersionUID = 435396239252623825L;

    @XmlElement(required = true)
    private boolean active;
    @XmlElement(required = true)
    private Long id;
    @XmlElement(required = true)
    private String todoListName;

    @XmlElementWrapper(name = "todos")
    @XmlElement(name = "todo")
    private final List<Todo> todos = new ArrayList<>();

    @Override
    public String getFunctionalId() {
        return this.todoListName;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public List<Todo> getTodoList() {
        return this.todos;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(final boolean active) {
        this.active = active;
    }

    @Override
    public void setFunctionalId(final String functionalId) {
        this.todoListName = functionalId;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }
}
