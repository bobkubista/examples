/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;

/**
 * @author bkubista
 *
 * @param <TYPE>
 *            The {@link AbstractGenericActiveEntity}
 * @param <ID>
 *            the identifier of the {@link AbstractGenericActiveEntity}
 */
public abstract class AbstractGenericActiveEntityDao<TYPE extends AbstractGenericActiveEntity<ID>, ID extends Serializable>
        extends AbstractGenericFunctionalIdentifiableEntityDao<TYPE, ID> implements ActiveDAO<TYPE, ID> {

	@Override
	public Collection<TYPE> findAllActive(final List<String> sortFields, final Integer page, final Integer maxResults) {
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<TYPE> query = builder.createQuery(this.getEntityClass());
		final Root<TYPE> queryRoot = query.from(this.getEntityClass());
		query.where(builder.equal(queryRoot.get("active"), true));
		return this.orderedBy(sortFields, page, maxResults, query, builder, queryRoot);
	}
}
