package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractIdentifiableEntity;

/**
 * Generic dao interface
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            an {@link AbstractIdentifiableEntity}
 * @param <ID>
 *            identifier which is {@link Serializable}
 */
public interface GenericDao<TYPE extends AbstractIdentifiableEntity<ID>, ID extends Serializable> {

    /**
     *
     * @param entity
     *            the TYPE to check for @return does the entity exist
     */
    public boolean contains(TYPE entity);

    /**
     * Count the total amount of results
     *
     * @return the amount of results found
     */
    public default Long count() {
        return count(Optional.empty());
    }

    /**
     * Count the total amount of results
     *
     * @param whereClause
     *            An {@link Optional} of a where clause
     * @return the amount of results found
     */
    public Long count(Optional<BiFunction<Root<TYPE>, CriteriaBuilder, Predicate>> whereClause);

    /**
     * insert a <code>TYPE</code> instance
     *
     * @param object
     *            the <code>TYPE</code> object to insert
     * @return the created <code>TYPE</code>
     */
    public abstract TYPE create(TYPE object);

    /**
     * delete the <code>TYPE</code> object
     *
     * @param object
     *            the <code>TYPE</code> object to update
     */
    public abstract void delete(TYPE object);

    /**
     * Get a {@link Collection} of all the <code>TYPE</code>, order by
     * <code>ID</code>
     *
     * @param sortFields
     *            the sorting field
     * @param page
     *            which page
     * @param maxResults
     *            the amount of result per page
     * @return a {@link Collection} of <code>TYPE</code>
     */
    public default Collection<TYPE> getAll(final List<String> sortFields, final int page, final int maxResults) {
        return getAll(sortFields, page, maxResults, Optional.empty());
    }

    /**
     * Get a {@link Collection} of all the <code>TYPE</code>, order by
     * <code>ID</code>
     *
     * @param sortFields
     *            the sorting field
     * @param page
     *            which page
     * @param maxResult
     *            the amount of result per page
     * @param whereClause
     *            An {@link Optional} of a where clause
     * @return a {@link Collection} of <code>TYPE</code>
     */
    public abstract Collection<TYPE> getAll(List<String> sortFields, int page, int maxResult, Optional<BiFunction<Root<TYPE>, CriteriaBuilder, Predicate>> whereClause);

    /**
     * get a <code>TYPE</code> object by its <code>ID</code> id
     *
     * @param id
     *            the <code>ID</code> id to search for
     * @return the <code>TYPE</code> object
     */
    public abstract TYPE getById(ID id);

    /**
     * Update an object of <code>TYPE</code>
     *
     * @param object
     *            the <code>TYPE</code> object to update
     * @return the number of objects affected
     */
    public abstract TYPE update(TYPE object);
}
