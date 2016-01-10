package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractIdentifiableEntity;

/**
 * The abstract implementation for all Dao's with read only capability
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            the {@link AbstractIdentifiableEntity} @param <ID> the id object
 *            type of the {@link AbstractIdentifiableEntity}
 */
public abstract class AbstractGenericDao<TYPE extends AbstractIdentifiableEntity<ID>, ID extends Serializable> implements GenericDao<TYPE, ID> {

	private final Class<TYPE> entityClass;

	@PersistenceContext(name = "jpaData")
	private EntityManager entityManager;

	private final Class<ID> identifierClass;

	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public AbstractGenericDao() {
		final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
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
	public Collection<TYPE> getAll(final String sort, final int page, final int maxResult) {
		String select = "from " + this.getEntityClass().getName();
		if (StringUtils.isNotBlank(sort)) {
			select = select + " order by " + sort;
		}
		final TypedQuery<TYPE> query = this.entityManager.createQuery(select, this.getEntityClass());
		// page
		query.setFirstResult(page * maxResult);
		// max results for page
		query.setMaxResults(maxResult);
		return query.getResultList();
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
}
