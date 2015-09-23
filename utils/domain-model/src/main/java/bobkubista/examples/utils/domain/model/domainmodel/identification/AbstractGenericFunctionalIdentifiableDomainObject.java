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
public abstract class AbstractGenericFunctionalIdentifiableDomainObject<ID extends Serializable> extends AbstractGenericIdentifiableDomainObject<ID> {

    private static final long serialVersionUID = 332043130860626788L;

    /**
     * Constructor
     */
    public AbstractGenericFunctionalIdentifiableDomainObject() {
    }

    /**
     * @return
     */
    @NotBlank
    @XmlElement(required = true)
    public abstract String getFunctionalId();

    /**
     * @param functionalId
     */
    public abstract void setFunctionalId(String functionalId);

}