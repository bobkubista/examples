package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.utils.service.jpa.persistance.annotation.SearchField;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractIdentifiableEntity;

/**
 * The abstract implementation for all Dao's with read only capability
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            the {@link AbstractIdentifiableEntity}
 * @param <ID>
 *            the id object type of the {@link AbstractIdentifiableEntity}
 */
public abstract class AbstractGenericDao<TYPE extends AbstractIdentifiableEntity<ID>, ID extends Serializable> implements GenericDao<TYPE, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenericDao.class);

    private final Class<TYPE> entityClass;

    @PersistenceContext(name = "jpaData")
    private EntityManager entityManager;

    private final Class<ID> identifierClass;

    /**
     * Constructor
     */
    @SuppressWarnings("unchecked")
    public AbstractGenericDao() {
        final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<TYPE>) genericSuperclass.getActualTypeArguments()[0];
        this.identifierClass = (Class<ID>) genericSuperclass.getActualTypeArguments()[1];

    }

    @Override
    public boolean contains(final TYPE entity) {
        return this.entityManager.contains(entity);
    }

    @Override
    public TYPE create(final TYPE object) {
        this.entityManager.persist(object);
        return this.entityManager.find(this.entityClass, object.getId());
    }

    @Override
    public void delete(final TYPE object) {
        final TYPE attachedEntity = this.entityManager.find(this.entityClass, object.getId());
        this.entityManager.remove(attachedEntity);
    }

    @Override
    public Collection<TYPE> getAll(final List<String> sortFields, final int page, final int maxResult,
            final Optional<BiFunction<Root<TYPE>, CriteriaBuilder, Predicate>> whereClause) {
        final CriteriaBuilder criteriaBuilder = this.getEntityManager()
                .getCriteriaBuilder();
        final CriteriaQuery<TYPE> cq = criteriaBuilder.createQuery(this.getEntityClass());
        final Root<TYPE> entity = cq.from(this.getEntityClass());
        if (whereClause.isPresent()) {
            cq.where(whereClause.get()
                    .apply(entity, criteriaBuilder));
            // cq.where(criteriaBuilder.<operator>(entity.get(<field>),<value>));
        }
        return this.orderedBy(sortFields, page, maxResult, cq, criteriaBuilder, entity);
    }

    @Override
    public TYPE getById(final ID id) {
        return this.entityManager.find(this.entityClass, id);
    }

    @Override
    public TYPE update(final TYPE object) {
        return this.entityManager.merge(object);
    }

    protected void flush() {
        this.entityManager.flush();
    }

    protected Class<TYPE> getEntityClass() {
        return this.entityClass;
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    protected Class<ID> getIdentifierClass() {
        return this.identifierClass;
    }

    /**
     *
     * @param fields
     *            fields to order by. Fields with prefix "-" are ordered
     *            descending
     * @param query
     *            the {@link CriteriaQuery}
     * @param builder
     *            the {@link CriteriaBuilder}
     * @param queryRoot
     *            {@link Root}
     * @return {@link Collection} of the given <code>TYPE</code>
     */
    private <T, U> Collection<T> orderedBy(final List<String> fields, final int startPositon, final int maxResults, final CriteriaQuery<T> query, final CriteriaBuilder builder,
            final Root<U> queryRoot) {

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
                                LOGGER.debug("ordering query by {}", field);
                            }));
        }
        return this.getEntityManager()
                .createQuery(query)
                .setFirstResult(startPositon)
                .setMaxResults(maxResults)
                .getResultList();
    }
}
