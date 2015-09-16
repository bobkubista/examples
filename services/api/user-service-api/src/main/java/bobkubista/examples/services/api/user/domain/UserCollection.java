/**
 *
 */
package bobkubista.examples.services.api.user.domain;

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
@XmlRootElement(name = "UserCollection")
@XmlAccessorType(XmlAccessType.NONE)
public class UserCollection extends DomainObjectCollection<User> {

	private static final long serialVersionUID = 6132048339242151935L;
	@XmlElementWrapper(name = "userlist")
	@XmlElement(name = "user")
	private final Collection<User> userList = new LinkedList<>();

	@Override
	public Collection<User> getDomainCollection() {
		return this.userList;
	}

}
