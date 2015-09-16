/**
 *
 */
package bobkubista.examples.services.api.todo.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObject;

/**
 * @author Bob Kubista
 *
 */
@XmlRootElement(name = "todoItem")
@XmlAccessorType(XmlAccessType.NONE)
public class Todo implements DomainObject {

    private static final long serialVersionUID = 8637857516325951534L;

    @XmlElement(nillable = false, required = true)
    private boolean active;
    @XmlElement(nillable = false, required = true)
    private String value;

    public String getValue() {
        return this.value;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
