/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericFunctionalIdentifiableEntity;

/**
 * @author Bob
 *
 * @param <TYPE>
 *            {@link AbstractGenericFunctionalIdentifiableEntity}
 * @param <ID>
 *            identifier
 */
public interface GenericFunctionalIdentifiableDao<TYPE extends AbstractGenericFunctionalIdentifiableEntity>
		extends GenericIdentifiableDao<TYPE> {

	/**
	 * get a <code>TYPE</code> object by its <code>ID</code> id
	 *
	 * @param id
	 *            the <code>ID</code> id to search for
	 * @return the <code>TYPE</code> object
	 */
	public default Optional<TYPE> getByFunctionalId(final String id) {
		getLogger().debug("Get object with functional id {}", id);

		try {
			final EntityManager entityManager = this.getEntityManager();
			final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			final CriteriaQuery<TYPE> cq = criteriaBuilder.createQuery(this.getEntityClass());
			final Root<TYPE> entity = cq.from(this.getEntityClass());
			cq.where(criteriaBuilder.equal(this.getFunctionalIdField(entity), id));
			final TypedQuery<TYPE> tp = entityManager.createQuery(cq);
			return Optional.ofNullable(tp.getSingleResult());
		} catch (final NoResultException ex) {
			getLogger().debug(String.format("object with id '%s' not found", id), ex);
			return Optional.empty();
		}
	}

	/**
	 *
	 * @param entity
	 *            {@link Root}
	 * @return {@link Path}
	 */
	public Path<String> getFunctionalIdField(Root<TYPE> entity);

	/**
	 *
	 * @param fId
	 *            functional identifier
	 * @return identifier
	 */
	public default Optional<Long> getIdByFunctionalId(final String fId) {
		final CriteriaBuilder builder = this.getEntityManager()
				.getCriteriaBuilder();
		final CriteriaQuery<Long> query = builder.createQuery(Long.class);
		final Root<TYPE> root = query.from(this.getEntityClass());
		query.where(builder.equal(this.getFunctionalIdField(root), fId));
		query.multiselect(root.get("id"));
		return Optional.ofNullable(this.getEntityManager()
				.createQuery(query)
				.getSingleResult());
	}

}
