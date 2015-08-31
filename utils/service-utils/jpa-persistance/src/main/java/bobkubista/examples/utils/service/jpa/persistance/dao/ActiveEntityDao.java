/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import bobkubista.examples.utils.service.jpa.persistance.entity.ActiveEntity;

/**
 * @author bkubista
 *
 * @param <TYPE>
 *            The {@link ActiveEntity}
 * @param <ID>
 *            the identifier of the {@link ActiveEntity}
 */
public abstract class ActiveEntityDao<TYPE extends ActiveEntity<ID>, ID extends Serializable> extends FunctionalIdentifiableEntityDao<TYPE, ID>implements ActiveDAO<TYPE, ID> {

	@Override
	public Collection<TYPE> findAllActive(final String sortField) {
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<TYPE> query = builder.createQuery(this.getEntityClass());
		final Root<TYPE> queryRoot = query.from(this.getEntityClass());
		query.where(builder.equal(queryRoot.get("active"), true));
		return this.orderedBy(sortField, query, builder, queryRoot);
	}
}
