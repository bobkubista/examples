/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.slf4j.Logger;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractIdentifiableEntity;

/**
 * @author Bob
 * @param <TYPE>
 *            {@link AbstractIdentifiableEntity}
 * @param <ID>
 *            {@link Serializable}
 */
public interface GenericDao<TYPE extends AbstractIdentifiableEntity<ID>, ID extends Serializable> {

    /**
     * Flush
     */
    public void flush();

    /**
     *
     * @return the Entity {@link Class}
     */
    public Class<TYPE> getEntityClass();

    /**
     *
     * @return {@link EntityManager}
     */
    public EntityManager getEntityManager();

    /**
     *
     * @return identifier {@link Class}
     */
    public Class<ID> getIdentifierClass();

    /**
     *
     * @return {@link Logger}
     */
    public Logger getLogger();
}
