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
public abstract class FunctionalIdentifiableDomainObject<ID extends Serializable> extends IdentifiableDomainObject<ID> {

	private static final long serialVersionUID = 332043130860626788L;

	public FunctionalIdentifiableDomainObject() {
	}

	@NotBlank
	@XmlElement(required = true)
	public abstract String getFunctionalId();

	public abstract void setFunctionalId(String functionalId);

}
