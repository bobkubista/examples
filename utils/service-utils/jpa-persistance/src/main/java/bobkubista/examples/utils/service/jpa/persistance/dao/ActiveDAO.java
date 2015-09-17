/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;
import java.util.Collection;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;

/**
 * @author bkubista
 * @param <TYPE>
 *            {@link AbstractGenericActiveEntity}
 * @param <ID>
 *            Identifier of the {@link AbstractGenericActiveEntity}
 */
public interface ActiveDAO<TYPE extends AbstractGenericActiveEntity<ID>, ID extends Serializable> extends FunctionalIdentifiableDao<TYPE, ID> {

    /**
     * Find all active {@link AbstractGenericActiveEntity}s
     *
     * @param sortField
     *            the field to sort for
     * @return a {@link Collection} of {@link AbstractGenericActiveEntity}
     */
    Collection<TYPE> findAllActive(String sortField);

}
