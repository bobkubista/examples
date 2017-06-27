/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.service.jpa.persistance.dao;

import javax.persistence.EntityManager;

import org.slf4j.Logger;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractIdentifiableEntity;

/**
 * @author Bob
 * @param <TYPE>
 *            {@link AbstractIdentifiableEntity}
 */
public interface GenericDao<TYPE extends AbstractIdentifiableEntity> {

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
	 * @return {@link Logger}
	 */
	public Logger getLogger();
}
