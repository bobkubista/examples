/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericFunctionalIdentifiableEntity;

/**
 * @author Bob
 *
 * @param <TYPE>
 *            {@link AbstractGenericFunctionalIdentifiableEntity}
 * @param <ID>
 *            identifier
 */
public interface FunctionalIdentifiableDao<TYPE extends AbstractGenericFunctionalIdentifiableEntity<ID>, ID extends Serializable> extends GenericIdentifiableDao<TYPE, ID> {

    /**
     * get a <code>TYPE</code> object by its <code>ID</code> id
     *
     * @param id
     *            the <code>ID</code> id to search for
     * @return the <code>TYPE</code> object
     */
    public default TYPE getByFunctionalId(final Object id) {
        getLogger().debug("Get object with functional id {}", id);

        final EntityType<TYPE> entityType = this.getEntityManager()
                .getMetamodel()
                .entity(this.getEntityClass());
        final CriteriaBuilder criteriaBuilder = this.getEntityManager()
                .getCriteriaBuilder();
        final CriteriaQuery<TYPE> cq = criteriaBuilder.createQuery(this.getEntityClass());
        final Root<TYPE> entity = cq.from(entityType);
        cq.where(criteriaBuilder.equal(this.getFunctionalIdField(entity), id));
        final TypedQuery<TYPE> tp = this.getEntityManager()
                .createQuery(cq);
        try {
            return tp.getSingleResult();
        } catch (final NoResultException ex) {
            getLogger().debug(String.format("object with id '%s' not found", id), ex);
            return null;
        }
    }

    public Path<String> getFunctionalIdField(Root<TYPE> entity);

    /**
     *
     * @param fId
     *            functional identifier
     * @return identifier
     */
    public default ID getIdByFunctionalId(final String fId) {
        final CriteriaBuilder builder = this.getEntityManager()
                .getCriteriaBuilder();
        final CriteriaQuery<ID> query = builder.createQuery(this.getIdentifierClass());
        final Root<TYPE> root = query.from(this.getEntityClass());
        query.where(builder.equal(this.getFunctionalIdField(root), fId));
        query.multiselect(root.get("id"));
        return this.getEntityManager()
                .createQuery(query)
                .getSingleResult();
    }

}
