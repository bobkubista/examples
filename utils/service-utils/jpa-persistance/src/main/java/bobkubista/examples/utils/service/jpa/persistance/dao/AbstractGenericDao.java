package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import bobkubista.examples.utils.service.jpa.persistance.entity.IdentifiableEntity;

/**
 * The abstract implementation for all Dao's with read only capability
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            the {@link IdentifiableEntity}
 * @param <ID>
 *            the id object type of the {@link IdentifiableEntity}
 */
public abstract class AbstractGenericDao<TYPE extends IdentifiableEntity<ID>, ID extends Serializable> implements GenericDao<TYPE, ID> {

	private final Class<TYPE> entityClass;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public AbstractGenericDao() {
		final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.entityClass = (Class<TYPE>) genericSuperclass.getActualTypeArguments()[0];

	}

	protected boolean contains(final TYPE entity) {
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

	@SuppressWarnings("unchecked")
	protected Collection<TYPE> findByCriteria(final int firstResult, final int maxResults, final Criterion... criterions) {
		final Session session = (Session) this.getEntityManager().getDelegate();
		final Criteria crit = session.createCriteria(this.getEntityClass());

		for (final Criterion c : criterions) {
			crit.add(c);
		}

		if (firstResult > 0) {
			crit.setFirstResult(firstResult);
		}

		if (maxResults > 0) {
			crit.setMaxResults(maxResults);
		}

		return crit.list();
	}

	protected void flush() {
		this.entityManager.flush();
	}

	@Override
	public abstract TYPE getByFunctionalId(Object id);

	@Override
	public TYPE getById(final ID id) {
		return this.entityManager.find(this.entityClass, id);
	}

	protected Class<TYPE> getEntityClass() {
		return this.entityClass;
	}

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public Collection<TYPE> getList() {
		final TypedQuery<TYPE> query = this.entityManager.createQuery("from " + this.getEntityClass().getName(), this.getEntityClass());
		return query.getResultList();
	}

	@Override
	public abstract Collection<TYPE> searchByFunctionalId(Object id);

	@Override
	public TYPE update(final TYPE object) {
		return this.entityManager.merge(object);
	}
}
