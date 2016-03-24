package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.service.jpa.persistance.annotation.SearchField;
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
public interface GenericIdentifiableDao<TYPE extends AbstractIdentifiableEntity<ID>, ID extends Serializable> extends GenericDao<TYPE, ID> {

    /**
     *
     * @param id
     *            the ID to check for
     * @return does the entity exist
     */
    public default boolean contains(final ID id) {
        return this.getEntityManager()
                .find(getEntityClass(), id) != null;
    }

    /**
     *
     * @param entity
     *            the TYPE to check for
     * @return does the entity exist
     */
    public default boolean contains(final TYPE entity) {
        return this.getEntityManager()
                .contains(entity);
    }

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
    public default Long count(final Optional<BiFunction<Root<TYPE>, CriteriaBuilder, Predicate>> whereClause) {
        final CriteriaBuilder criteriaBuilder = this.getEntityManager()
                .getCriteriaBuilder();
        final CriteriaQuery<Long> cq = criteriaBuilder.createQuery(Long.class);

        cq.select(criteriaBuilder.count(cq.from(this.getEntityClass())));

        final Root<TYPE> entity = cq.from(this.getEntityClass());
        if (whereClause.isPresent()) {
            cq.where(whereClause.get()
                    .apply(entity, criteriaBuilder));
        }
        return this.getEntityManager()
                .createQuery(cq)
                .getSingleResult();

    }

    /**
     * insert a <code>TYPE</code> instance
     *
     * @param object
     *            the <code>TYPE</code> object to insert
     * @return the created <code>TYPE</code>
     */
    public default Optional<TYPE> create(final TYPE object) {
        this.getEntityManager()
                .persist(object);
        return Optional.ofNullable(this.getEntityManager()
                .find(this.getEntityClass(), object.getId()));
    }

    /**
     * delete the <code>TYPE</code> object
     *
     * @param object
     *            the <code>TYPE</code> object to update
     */
    public default void delete(final TYPE object) {
        final TYPE attachedEntity = this.getEntityManager()
                .find(this.getEntityClass(), object.getId());
        this.getEntityManager()
                .remove(attachedEntity);
    }

    /**
     * Get a {@link Collection} of all the <code>TYPE</code>, order by
     * <code>ID</code>
     *
     * @param search
     *            {@link SearchBean}
     * @return a {@link Collection} of <code>TYPE</code>
     */
    public default Collection<TYPE> getAll(final SearchBean search) {
        return getAll(search, Optional.empty());
    }

    /**
     * Get a {@link Collection} of all the <code>TYPE</code>, order by
     * <code>ID</code>
     *
     * @param search
     *            {@link SearchBean}
     * @param whereClause
     *            An {@link Optional} of a where clause
     * @return a {@link Collection} of <code>TYPE</code>
     */
    public default Collection<TYPE> getAll(final SearchBean search, final Optional<BiFunction<Root<TYPE>, CriteriaBuilder, Predicate>> whereClause) {
        final CriteriaBuilder criteriaBuilder = this.getEntityManager()
                .getCriteriaBuilder();
        final CriteriaQuery<TYPE> cq = criteriaBuilder.createQuery(this.getEntityClass());
        final Root<TYPE> entity = cq.from(this.getEntityClass());
        if (whereClause.isPresent()) {
            cq.where(whereClause.get()
                    .apply(entity, criteriaBuilder));
            // cq.where(criteriaBuilder.<operator>(entity.get(<field>),<value>))
        }
        return this.orderedBy(search.getSort(), search.getPage(), search.getMaxResults(), cq, criteriaBuilder, entity);
    }

    /**
     * get a <code>TYPE</code> object by its <code>ID</code> id
     *
     * @param id
     *            the <code>ID</code> id to search for
     * @return the <code>TYPE</code> object
     */
    public default Optional<TYPE> getById(final ID id) {
        return Optional.ofNullable(this.getEntityManager()
                .find(this.getEntityClass(), id));
    }

    /**
     *
     * @param fields
     *            fields to order by. Fields with prefix "-" are ordered
     *            descending
     * @param startPositon
     *            amount of elements to skip
     * @param maxResults
     *            the amount of results to return
     * @param query
     *            the {@link CriteriaQuery}
     * @param builder
     *            the {@link CriteriaBuilder}
     * @param queryRoot
     *            {@link Root}
     * @return {@link Collection} of the given <code>TYPE</code>
     */
    public default <T, U> Collection<T> orderedBy(final List<String> fields, final int startPositon, final int maxResults, final CriteriaQuery<T> query,
            final CriteriaBuilder builder, final Root<U> queryRoot) {

        // TODO refactor to make use of http://use-the-index-luke.com/no-offset
        // There must be a better way to do this
        try (final Stream<Field> declaredFields = Stream.of(this.getEntityClass()
                .getDeclaredFields())) {
            declaredFields.filter(field -> field.isAnnotationPresent(SearchField.class))
                    .forEach(searchableField -> fields.stream()
                            .filter(field -> field.endsWith(searchableField.getAnnotation(SearchField.class)
                                    .fieldName()))
                            .forEachOrdered(field -> {
                                if (field.startsWith("-")) {
                                    query.orderBy(builder.desc(queryRoot.get(searchableField.getName())));
                                } else {
                                    query.orderBy(builder.asc(queryRoot.get(searchableField.getName())));
                                }
                            }));
        }
        return this.getEntityManager()
                .createQuery(query)
                .setFirstResult(startPositon)
                .setMaxResults(maxResults)
                .getResultList();
    }

    /**
     * Update an object of <code>TYPE</code>
     *
     * @param object
     *            the <code>TYPE</code> object to update
     * @return the number of objects affected
     */
    public default TYPE update(final TYPE object) {
        return this.getEntityManager()
                .merge(object);
    }

}
