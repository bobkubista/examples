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
 *
 * @param <TYPE>
 *            The {@link AbstractGenericActiveEntity}
 * @param <ID>
 *            the identifier of the {@link AbstractGenericActiveEntity}
 */
public abstract class AbstractGenericActiveEntityDao<TYPE extends AbstractGenericActiveEntity<ID>, ID extends Serializable>
        extends AbstractGenericFunctionalIdentifiableEntityDao<TYPE, ID>implements ActiveDAO<TYPE, ID> {

    @Override
    public Collection<TYPE> findAllActive(final List<String> sortFields, final Integer page, final Integer maxResults) {
        final BiFunction<Root<TYPE>, CriteriaBuilder, Predicate> where = (root, build) -> build.equal(root.get("active"), true);
        final Optional<BiFunction<Root<TYPE>, CriteriaBuilder, Predicate>> optionalWhere = Optional.of(where);
        return this.getAll(sortFields, page, maxResults, optionalWhere);
    }
}
