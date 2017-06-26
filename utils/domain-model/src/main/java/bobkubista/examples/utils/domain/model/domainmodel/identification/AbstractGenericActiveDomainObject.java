/**
 *
 */
package bobkubista.examples.utils.domain.model.domainmodel.identification;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author bkubista
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractGenericActiveDomainObject extends AbstractGenericFunctionalIdentifiableDomainObject {

	private static final long serialVersionUID = -7516244737080941032L;

	@NotNull
	@XmlElement(required = true)
	private boolean active;

	/**
	 * Constructor
	 */
	public AbstractGenericActiveDomainObject() {
	}

	/**
	 * Constructor
	 *
	 * @param active
	 *            active flag
	 * @param functionalId
	 *            the functional identifier
	 */
	public AbstractGenericActiveDomainObject(final boolean active, final String functionalId) {
		super(functionalId);
		this.active = active;
	}

	/**
	 * @return true if the object is active
	 */
	public final boolean isActive() {
		return this.active;
	}

	/**
	 * @param active
	 *            is the object active
	 */
	public final void setActive(final boolean active) {
		this.active = active;
	}

}
