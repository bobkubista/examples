package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractIdentifiableEntity;

/**
 *
 * @author Bob
 *
 * @param <TYPE>
 *            {@link AbstractIdentifiableEntity}
 */
public abstract class AbstractGenericDao<TYPE extends AbstractIdentifiableEntity> implements GenericDao<TYPE> {

	private final Class<TYPE> entityClass;

	@PersistenceContext(name = "jpaData")
	private EntityManager entityManager;

	@Deprecated
	private final Class<Long> identifierClass;

	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public AbstractGenericDao() {
		super();
		final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<TYPE>) genericSuperclass.getActualTypeArguments()[0];
		this.identifierClass = (Class<Long>) genericSuperclass.getActualTypeArguments()[1];

	}

	@Override
	public void flush() {
		this.entityManager.flush();
	}

	@Override
	public Class<TYPE> getEntityClass() {
		return this.entityClass;
	}

	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	@Deprecated
	public Class<Long> getIdentifierClass() {
		return this.identifierClass;
	}

}
