/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * Basic implementation of an {@link AbstractGenericActiveEntity}
 *
 * @author bkubista
 * @param <ID>
 *            Identifier of the {@link AbstractGenericActiveEntity}
 */
@MappedSuperclass
public abstract class AbstractGenericActiveEntity<ID extends Serializable> extends AbstractGenericFunctionalIdentifiableEntity<ID> {

    private static final long serialVersionUID = -6184924216288636653L;

    /**
     * Constructor
     */
    public AbstractGenericActiveEntity() {
    }

    /**
     * @return isActive active flag
     */
    public abstract boolean isActive();

    /**
     * @param active
     *            flag value
     */
    public abstract void setActive(boolean active);

}
