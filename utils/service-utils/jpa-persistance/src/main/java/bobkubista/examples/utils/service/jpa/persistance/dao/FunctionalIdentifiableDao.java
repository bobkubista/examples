/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;
import java.util.Collection;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericFunctionalIdentifiableEntity;

/**
 * @author Bob
 *
 * @param <TYPE>
 *            {@link AbstractGenericFunctionalIdentifiableEntity}
 * @param <ID>
 *            identifier
 */
public interface FunctionalIdentifiableDao<TYPE extends AbstractGenericFunctionalIdentifiableEntity<ID>, ID extends Serializable> extends GenericDao<TYPE, ID> {

    /**
     * get a <code>TYPE</code> object by its <code>ID</code> id
     *
     * @param id
     *            the <code>ID</code> id to search for
     * @return the <code>TYPE</code> object
     */
    public abstract TYPE getByFunctionalId(Object id);

    /**
     * searchByFunctionalId the <code>TYPE</code> object
     *
     * @param id
     *            the <code>ID</code> id to search for
     *
     * @return the objects matching the search id
     */
    public Collection<TYPE> searchByFunctionalId(Object id);
}
