/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;
import java.util.Collection;

import bobkubista.examples.utils.service.jpa.persistance.entity.ActiveEntity;

/**
 * @author bkubista
 * @param <TYPE>
 *            {@link ActiveEntity}
 * @param <ID>
 *            Identifier of the {@link ActiveEntity}
 */
public interface ActiveDAO<TYPE extends ActiveEntity<ID>, ID extends Serializable> extends GenericDao<TYPE, ID> {

    /**
     * Find all active {@link ActiveEntity}s
     *
     * @param sortField
     *            the field to sort for
     * @return a {@link Collection} of {@link ActiveEntity}
     */
    Collection<TYPE> findAllActive(String sortField);

}
