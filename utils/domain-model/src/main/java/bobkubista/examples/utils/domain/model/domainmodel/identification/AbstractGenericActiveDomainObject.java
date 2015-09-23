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

    @NotBlank
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
     */
    public AbstractGenericActiveDomainObject(final boolean active, final String functionalId) {
        super(functionalId);
        this.active = active;
    }

    /**
     * @return
     */
    public final boolean isActive() {
        return this.active;
    }

    /**
     * @param active
     */
    public final void setActive(final boolean active) {
        this.active = active;
    }

}
