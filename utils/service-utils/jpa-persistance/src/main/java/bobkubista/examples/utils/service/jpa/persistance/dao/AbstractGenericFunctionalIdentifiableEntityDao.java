/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericFunctionalIdentifiableEntity;

/**
 * @author bkubista
 * @param <TYPE>
 *            The {@link AbstractGenericFunctionalIdentifiableEntity}
 * @param <ID>
 *            the identifier of the
 *            {@link AbstractGenericFunctionalIdentifiableEntity}
 */
public abstract class AbstractGenericFunctionalIdentifiableEntityDao<TYPE extends AbstractGenericFunctionalIdentifiableEntity<ID>, ID extends Serializable>
        extends AbstractGenericDao<TYPE, ID> implements FunctionalIdentifiableDao<TYPE, ID> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenericFunctionalIdentifiableEntityDao.class);

	@Override
	public TYPE getByFunctionalId(final Object id) {
		LOGGER.debug("Get object with functional id {}", id);

		final EntityType<TYPE> entityType = this.getEntityManager().getMetamodel().entity(this.getEntityClass());
		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<TYPE> cq = criteriaBuilder.createQuery(this.getEntityClass());
		final Root<TYPE> entity = cq.from(entityType);
		cq.where(criteriaBuilder.equal(this.getFunctionalIdField(entity), id));
		final TypedQuery<TYPE> tp = this.getEntityManager().createQuery(cq);
		try {
			return tp.getSingleResult();
		} catch (final NoResultException ex) {
			LOGGER.debug(String.format("object with id '%s' not found", id), ex);
			return null;
		}
	}

	/**
	 *
	 * @param fId
	 *            functional identifier
	 * @return identifier
	 */
	public ID getIdByFunctionalId(final String fId) {
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<ID> query = builder.createQuery(this.getIdentifierClass());
		final Root<TYPE> root = query.from(this.getEntityClass());
		query.where(builder.equal(this.getFunctionalIdField(root), fId));
		query.multiselect(root.get("id"));
		return this.getEntityManager().createQuery(query).getSingleResult();
	}

	@Override
	public Collection<TYPE> searchByFunctionalId(final Object id) {
		AbstractGenericFunctionalIdentifiableEntityDao.LOGGER.debug("Searching for object with {} in the functional id", id);
		final EntityType<TYPE> entityType = this.getEntityManager().getMetamodel().entity(this.getEntityClass());
		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<TYPE> cq = criteriaBuilder.createQuery(this.getEntityClass());
		final Root<TYPE> entity = cq.from(entityType);
		cq.where(criteriaBuilder.like(this.getFunctionalIdField(entity), "%" + id + "%"));
		final TypedQuery<TYPE> tp = this.getEntityManager().createQuery(cq);
		return tp.getResultList();
	}

	protected abstract Path<String> getFunctionalIdField(Root<TYPE> entity);

}
