/**
 *
 */
package bobkubista.examples.utils.domain.model.domainmodel.identification;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author bkubista
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ActiveDomainObject<ID extends Serializable> extends FunctionalIdentifiableDomainObject<ID> {

	private static final long serialVersionUID = -7516244737080941032L;

	public ActiveDomainObject() {
	}

	@NotBlank
	@XmlElement(required = true)
	public abstract boolean isActive();

	public abstract void setActive(boolean active);

}
