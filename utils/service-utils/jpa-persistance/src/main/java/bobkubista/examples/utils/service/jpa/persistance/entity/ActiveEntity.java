/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * Basic implementation of an {@link ActiveEntity}
 *
 * @author bkubista
 * @param <ID>
 *            Identifier of the {@link ActiveEntity}
 */
@MappedSuperclass
public abstract class ActiveEntity<ID extends Serializable> extends FunctionalIdentifiableEntity<ID> {

	private static final long serialVersionUID = -6184924216288636653L;

	public ActiveEntity() {
	}

	public abstract boolean isActive();

	public abstract void setActive(boolean active);

}
