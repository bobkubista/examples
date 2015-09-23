/**
 *
 */
package bobkubista.examples.utils.domain.model.domainmodel.identification;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author bkubista
 * @param <ID>
 *            identifier
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractGenericFunctionalIdentifiableDomainObject<ID extends Serializable> extends AbstractGenericIdentifiableDomainObject<ID> {

    private static final long serialVersionUID = 332043130860626788L;
    @XmlElement(required = true)
    private String functionalId;

    /**
     * Constructor
     */
    public AbstractGenericFunctionalIdentifiableDomainObject() {
        super();
    }

    /**
     * Constructor
     *
     * @param functionalId
     *            functionalId
     */
    public AbstractGenericFunctionalIdentifiableDomainObject(final String functionalId, final ID id) {
        super(id);
        this.functionalId = functionalId;
    }

    /**
     * @return
     */
    public final String getFunctionalId() {
        return this.functionalId;
    }

    /**
     * @param functionalId
     */
    public final void setFunctionalId(final String functionalId) {
        this.functionalId = functionalId;
    }

}
