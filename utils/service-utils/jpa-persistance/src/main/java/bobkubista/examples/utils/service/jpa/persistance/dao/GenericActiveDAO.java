/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.util.Collection;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import bobkubista.examples.utils.domain.model.api.ActiveSearchBean;
import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;

/**
 * @author bkubista
 * @param <TYPE>
 *            {@link AbstractGenericActiveEntity}
 */
public interface GenericActiveDAO<TYPE extends AbstractGenericActiveEntity>
		extends GenericFunctionalIdentifiableDao<TYPE> {

	/**
	 *
	 * @return the amount of active entities
	 */
	public default Long count(final ActiveSearchBean search) {
		return this.count(this.getActiveCriteria(search));
	};

	/**
	 * Find all active {@link AbstractGenericActiveEntity}s
	 *
	 * @param search
	 *            {@link SearchBean}
	 * @return a {@link Collection} of {@link AbstractGenericActiveEntity}
	 */
	default Collection<TYPE> findAll(final ActiveSearchBean search) {
		return this.getAll(search, this.getActiveCriteria(search));
	}

	/**
	 *
	 * @param search
	 * @return the active Criteria
	 */
	default BiFunction<Root<TYPE>, CriteriaBuilder, Predicate> getActiveCriteria(final ActiveSearchBean search) {
		return (root, build) -> build.equal(root.get("active"), search.isActive());
	}
}
