package bobkubista.examples.utils.services.spring.jpa.services;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import bobkubista.examples.utils.services.spring.jpa.dao.GenericDao;
import bobkubista.examples.utils.services.spring.jpa.entity.IdentifiableEntity;

/**
 * The interface to describe read functions in services
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            the {@link IdentifiableEntity} @param <ID> the identifier of the
 *            {@link IdentifiableEntity}
 */
@Transactional
public interface IdentifiableEntityService<TYPE extends IdentifiableEntity<ID>, ID extends Serializable> {

	/**
	 * Create the object of {@link IdentifiableEntity} type
	 *
	 * @param object
	 *            the object to create @return <code>TYPE</code> that was
	 *            created
	 */
	public default TYPE create(final TYPE object) {
		this.getDAO().create(object);
		return this.getDAO().getById(object.getId());
	}

	/**
	 * delete the specific object of {@link IdentifiableEntity}
	 *
	 * @param object
	 *            the object to delete
	 */
	public default void delete(final TYPE object) {
		this.getDAO().delete(object);
	}

	/**
	 * get all known {@link IdentifiableEntity} of that type
	 *
	 * @return a {@link Collection} of {@link IdentifiableEntity} of the same
	 *         type
	 */
	public default Collection<TYPE> getAll() {
		return this.getDAO().getList();
	}

	/**
	 * get the {@link IdentifiableEntity}
	 *
	 * @param identifier
	 *            the identfier @return the {@link IdentifiableEntity}
	 */
	public default TYPE getById(final ID identifier) {
		return this.getDAO().getById(identifier);
	}

	/**
	 * Get the {@link GenericDao}
	 *
	 * @return
	 */
	GenericDao<TYPE, ID> getDAO();

	/**
	 * update the object of {@link IdentifiableEntity}
	 *
	 * @param object
	 *            the object to update @return the updated object
	 */
	default public TYPE update(final TYPE object) {
		this.getDAO().update(object);
		return this.getDAO().getById(object.getId());
	}

}