package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;
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
 * @param <ID>
 *            {@link Serializable}
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
        super();
        final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<TYPE>) genericSuperclass.getActualTypeArguments()[0];
        this.identifierClass = (Class<ID>) genericSuperclass.getActualTypeArguments()[1];

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
    public Class<ID> getIdentifierClass() {
        return this.identifierClass;
    }
    
    public boolean isConnected() {
    	return this.entityManager.isOpen();
    }

}
