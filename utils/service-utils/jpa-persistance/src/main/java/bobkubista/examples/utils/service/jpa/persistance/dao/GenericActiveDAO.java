/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;

/**
 * @author bkubista
 * @param <TYPE>
 *            {@link AbstractGenericActiveEntity}
 * @param <ID>
 *            Identifier of the {@link AbstractGenericActiveEntity}
 */
public interface GenericActiveDAO<TYPE extends AbstractGenericActiveEntity<ID>, ID extends Serializable> extends GenericFunctionalIdentifiableDao<TYPE, ID> {

    /**
     *
     * @return the amount of active entities
     */
    public default Long countActive() {
        return this.count(this.getActiveCriteria());
    };

    /**
     * Find all active {@link AbstractGenericActiveEntity}s
     *
     * @param search
     *            {@link SearchBean}
     * @return a {@link Collection} of {@link AbstractGenericActiveEntity}
     */
    default Collection<TYPE> findAllActive(final SearchBean search) {
        return this.getAll(search, this.getActiveCriteria());
    }

    /**
     *
     * @return the active Criteria
     */
    default BiFunction<Root<TYPE>, CriteriaBuilder, Predicate> getActiveCriteria() {
        return (root, build) -> build.equal(root.get("active"), true);
    }
}
