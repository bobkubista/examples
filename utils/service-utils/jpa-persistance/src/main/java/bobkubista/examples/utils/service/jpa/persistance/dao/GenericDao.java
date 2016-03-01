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
 *
 */
public interface GenericDao<TYPE extends AbstractIdentifiableEntity<ID>, ID extends Serializable> {

    public void flush();

    public Class<TYPE> getEntityClass();

    public EntityManager getEntityManager();

    public Class<ID> getIdentifierClass();

    public Logger getLogger();
}
