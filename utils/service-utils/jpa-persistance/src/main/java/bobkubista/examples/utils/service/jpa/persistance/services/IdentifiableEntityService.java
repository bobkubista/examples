package bobkubista.examples.utils.service.jpa.persistance.services;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import bobkubista.examples.utils.service.jpa.persistance.dao.GenericDao;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractIdentifiableEntity;

/**
 * The interface to describe read functions in services
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            the {@link AbstractIdentifiableEntity}
 * @param <ID>
 *            the identifier of the {@link AbstractIdentifiableEntity}
 */
@Transactional
@FunctionalInterface
public interface IdentifiableEntityService<TYPE extends AbstractIdentifiableEntity<ID>, ID extends Serializable> {

	/**
	 * Create the object of {@link AbstractIdentifiableEntity} type
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
	 * delete the specific object of {@link AbstractIdentifiableEntity}
	 *
	 * @param object
	 *            the object to delete
	 */
	public default void delete(final TYPE object) {
		this.getDAO().delete(object);
	}

	/**
	 * get all known {@link AbstractIdentifiableEntity} of that type
	 *
	 * @return a {@link Collection} of {@link AbstractIdentifiableEntity} of the
	 *         same type
	 */
	public default Collection<TYPE> getAll() {
		return this.getDAO().getList();
	}

	/**
	 * get the {@link AbstractIdentifiableEntity}
	 *
	 * @param identifier
	 *            the identfier @return the {@link AbstractIdentifiableEntity}
	 */
	public default TYPE getById(final ID identifier) {
		return this.getDAO().getById(identifier);
	}

	/**
	 * update the object of {@link AbstractIdentifiableEntity}
	 *
	 * @param object
	 *            the object to update @return the updated object
	 */
	public default TYPE update(final TYPE object) {
		this.getDAO().update(object);
		return this.getDAO().getById(object.getId());
	}

	/**
	 * Get the {@link GenericDao}
	 *
	 * @return
	 */
	GenericDao<TYPE, ID> getDAO();

}