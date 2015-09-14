/**
 *
 */
package bobkubista.examples.utils.services.spring.jpa.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * An {@link IdentifiableEntity} with name, description and active flag
 *
 * @author bkubista
 * @param <ID>
 *            the type of {@link IdentifiableEntity}
 */
@MappedSuperclass
public abstract class FunctionalIdentifiableEntity<ID extends Serializable> extends IdentifiableEntity<ID> {

	private static final long serialVersionUID = 8921787384841632428L;

	public FunctionalIdentifiableEntity() {
	}

	/**
	 * @return the name
	 */
	public abstract String getFunctionalId();

	/**
	 *
	 * @param functionalId
	 *            the functional id
	 */
	public abstract void setFunctionalId(final String functionalId);

}
