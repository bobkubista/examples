/**
 *
 */
package bobkubista.examples.services.api.user.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import bobkubista.examples.utils.domain.model.domainmodel.identification.ActiveDomainObject;

/**
 * @author Bob Kubista
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class User extends ActiveDomainObject<Long> {

	private static final long serialVersionUID = 8804522919297114084L;

	@XmlElement(required = true)
	private boolean active;
	@XmlElement(required = true)
	private String email;
	@XmlElement(required = true)
	private String encryptedPassword;
	@XmlElement(required = true)
	private Long id;
	@XmlElement(required = true)
	private String name;

	public String getEncryptedPassword() {
		return this.encryptedPassword;
	}

	@Override
	public String getFunctionalId() {
		return this.email;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public boolean isActive() {
		return this.active;
	}

	@Override
	public void setActive(final boolean active) {
		this.active = active;
	}

	public void setEncryptedPassword(final String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	@Override
	public void setFunctionalId(final String functionalId) {
		this.email = functionalId;
	}

	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
