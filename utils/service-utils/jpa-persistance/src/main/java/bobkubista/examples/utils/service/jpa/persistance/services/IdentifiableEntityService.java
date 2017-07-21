package bobkubista.examples.utils.service.jpa.persistance.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.service.jpa.persistance.dao.GenericIdentifiableDao;
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
public interface IdentifiableEntityService<TYPE extends AbstractIdentifiableEntity> {

	/**
	 * Check if object of {@link AbstractIdentifiableEntity} exists
	 *
	 * @param id
	 *            the id to check
	 * @return true if exists
	 */
	public default boolean contains(final Long id) {
		return this.getDAO()
				.contains(id);
	}

	/**
	 * Check if object of {@link AbstractIdentifiableEntity} exists
	 *
	 * @param object
	 *            the object to check
	 * @return true if exists
	 */
	public default boolean contains(final TYPE object) {
		return this.getDAO()
				.contains(object);
	}

	/**
	 *
	 * @return Return the amount found
	 */
	public default Long count() {
		return this.getDAO()
				.count();
	}

	/**
	 * Create the object of {@link AbstractIdentifiableEntity} type
	 *
	 * @param object
	 *            the object to create
	 * @return <code>TYPE</code> that was created
	 */
	public default Optional<TYPE> create(final TYPE object) {
		return this.getDAO()
				.create(object);
	}

	/**
	 * delete the specific object of {@link AbstractIdentifiableEntity}
	 *
	 * @param object
	 *            the object to delete
	 */
	public default void delete(final TYPE object) {
		this.getDAO()
				.delete(object);
	}

	/**
	 * get all known {@link AbstractIdentifiableEntity} of that type
	 *
	 * @param search
	 *            {@link SearchBean}
	 * @return a {@link Collection} of {@link AbstractIdentifiableEntity} of the
	 *         same type
	 */
	public default Collection<TYPE> getAll(final SearchBean search) {
		return this.getDAO()
				.getAll(search);
	}

	/**
	 * get the {@link AbstractIdentifiableEntity}
	 *
	 * @param identifier
	 *            the identfier
	 * @return the {@link AbstractIdentifiableEntity}
	 */
	public default Optional<TYPE> getById(final Long identifier) {
		return this.getDAO()
				.getById(identifier);
	}

	/**
	 * update the object of {@link AbstractIdentifiableEntity}
	 *
	 * @param object
	 *            the object to update
	 * @return the updated object
	 */
	public default Optional<TYPE> update(final TYPE object) {
		return this.getDAO()
				.update(object);
	}

	/**
	 * Get the {@link GenericIdentifiableDao}
	 *
	 * @return A subtype of {@link GenericIdentifiableDao}
	 */
	GenericIdentifiableDao<TYPE> getDAO();

}
