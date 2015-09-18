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
 * @param <ID>
 *            identifier
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractGenericActiveDomainObject<ID extends Serializable> extends AbstractGenericFunctionalIdentifiableDomainObject<ID> {

    private static final long serialVersionUID = -7516244737080941032L;

    /**
     * Constructor
     */
    public AbstractGenericActiveDomainObject() {
    }

    /**
     * @return
     */
    @NotBlank
    @XmlElement(required = true)
    public abstract boolean isActive();

    /**
     * @param active
     */
    public abstract void setActive(boolean active);

}
