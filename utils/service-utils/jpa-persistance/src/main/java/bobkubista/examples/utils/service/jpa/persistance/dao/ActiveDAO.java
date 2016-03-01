/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;

/**
 * @author bkubista
 * @param <TYPE>
 *            {@link AbstractGenericActiveEntity}
 * @param <ID>
 *            Identifier of the {@link AbstractGenericActiveEntity}
 */
public interface ActiveDAO<TYPE extends AbstractGenericActiveEntity<ID>, ID extends Serializable> extends FunctionalIdentifiableDao<TYPE, ID> {

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
     * @param page
     *            amount to skip: page * maxResults
     * @param maxResults
     *            amount of results to return
     *
     * @param sortFields
     *            the field to sort for
     * @return a {@link Collection} of {@link AbstractGenericActiveEntity}
     */
    default Collection<TYPE> findAllActive(final List<String> sortFields, final Integer page, final Integer maxResults) {
        return this.getAll(sortFields, page, maxResults, this.getActiveCriteria());
    }

    default Optional<BiFunction<Root<TYPE>, CriteriaBuilder, Predicate>> getActiveCriteria() {
        return Optional.of((root, build) -> build.equal(root.get("active"), true));
    }
}
