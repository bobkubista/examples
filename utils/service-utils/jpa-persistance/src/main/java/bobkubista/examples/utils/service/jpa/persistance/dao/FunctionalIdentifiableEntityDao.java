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

import bobkubista.examples.utils.service.jpa.persistance.entity.FunctionalIdentifiableEntity;

/**
 * @author bkubista
 * @param <TYPE>
 *            The {@link FunctionalIdentifiableEntity}
 * @param <ID>
 *            the identifier of the {@link FunctionalIdentifiableEntity}
 */
public abstract class FunctionalIdentifiableEntityDao<TYPE extends FunctionalIdentifiableEntity<ID>, ID extends Serializable> extends AbstractGenericDao<TYPE, ID>
        implements GenericDao<TYPE, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionalIdentifiableEntityDao.class);

    @Override
    public TYPE getByFunctionalId(final Object id) {
        FunctionalIdentifiableEntityDao.LOGGER.debug("Get object with functional id {}", id);
        final EntityType<TYPE> entityType = this.getEntityManager().getMetamodel().entity(this.getEntityClass());
        final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<TYPE> cq = criteriaBuilder.createQuery(this.getEntityClass());
        final Root<TYPE> entity = cq.from(entityType);
        cq.where(criteriaBuilder.equal(this.getFunctionalIdField(entity), id));
        final TypedQuery<TYPE> tp = this.getEntityManager().createQuery(cq);
        try {
            return tp.getSingleResult();
        } catch (final NoResultException ex) {
            FunctionalIdentifiableEntityDao.LOGGER.debug(String.format("object with id '%s' not found", id), ex);
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
        FunctionalIdentifiableEntityDao.LOGGER.debug("Searching for object with {} in the functional id", id);
        final EntityType<TYPE> entityType = this.getEntityManager().getMetamodel().entity(this.getEntityClass());
        final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<TYPE> cq = criteriaBuilder.createQuery(this.getEntityClass());
        final Root<TYPE> entity = cq.from(entityType);
        cq.where(criteriaBuilder.like(this.getFunctionalIdField(entity), "%" + id + "%"));
        final TypedQuery<TYPE> tp = this.getEntityManager().createQuery(cq);
        return tp.getResultList();
    }

    protected abstract Path<String> getFunctionalIdField(Root<TYPE> entity);

    /**
     *
     * @param field
     *            field to order by
     * @param query
     *            the {@link CriteriaQuery}
     * @param builder
     *            the {@link CriteriaBuilder}
     * @param queryRoot
     *            {@link Root}
     * @param startPositon
     *            amount of results to skip, for pagination for example
     * @param maxResults
     *            the amount of results returned
     * @return {@link Collection} of the given <code>ID</code>
     */
    protected Collection<ID> orderedBy(final String field, final CriteriaQuery<ID> query, final CriteriaBuilder builder, final Root<TYPE> queryRoot, final int startPositon,
            final int maxResults) {

        query.orderBy(builder.asc(queryRoot.get(field)));
        FunctionalIdentifiableEntityDao.LOGGER.debug("ordering query by field {} with {} results", field, maxResults);
        return this.getEntityManager().createQuery(query).setFirstResult(startPositon).setMaxResults(maxResults).getResultList();
    }

    /**
     *
     * @param field
     *            field to order by
     * @param query
     *            the {@link CriteriaQuery}
     * @param builder
     *            the {@link CriteriaBuilder}
     * @param queryRoot
     *            {@link Root}
     * @return {@link Collection} of the given <code>TYPE</code>
     */
    protected Collection<TYPE> orderedBy(final String field, final CriteriaQuery<TYPE> query, final CriteriaBuilder builder, final Root<TYPE> queryRoot) {

        query.orderBy(builder.desc(queryRoot.get(field)));
        FunctionalIdentifiableEntityDao.LOGGER.debug("ordering query by {}", field);
        return this.getEntityManager().createQuery(query).getResultList();
    }
}
