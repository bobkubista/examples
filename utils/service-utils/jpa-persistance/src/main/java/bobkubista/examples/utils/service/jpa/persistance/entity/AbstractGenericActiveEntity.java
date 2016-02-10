/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import bobkubista.examples.utils.service.jpa.persistance.annotation.SearchField;

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

    @Column(nullable = false)
    @SearchField(fieldName = "active")
    private boolean active;

    /**
     * Constructor
     */
    public AbstractGenericActiveEntity() {
    }

    /**
     * @return isActive active flag
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * @param active
     *            flag value
     */
    public void setActive(final boolean active) {
        this.active = active;
    }
}
