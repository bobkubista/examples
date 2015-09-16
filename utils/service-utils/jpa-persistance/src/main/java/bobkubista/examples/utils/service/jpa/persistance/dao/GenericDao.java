package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.io.Serializable;
import java.util.Collection;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractIdentifiableEntity;

/**
 * Generic dao interface
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            an {@link AbstractIdentifiableEntity}
 * @param <ID>
 *            identifier which is {@link Serializable}
 */
public interface GenericDao<TYPE extends AbstractIdentifiableEntity<ID>, ID extends Serializable> {

    /**
     * insert a <code>TYPE</code> instance
     *
     * @param object
     *            the <code>TYPE</code> object to insert
     * @return the created <code>TYPE</code>
     */
    public abstract TYPE create(TYPE object);

    /**
     * delete the <code>TYPE</code> object
     *
     * @param object
     *            the <code>TYPE</code> object to update
     */
    public abstract void delete(TYPE object);

    /**
     * get a <code>TYPE</code> object by its <code>ID</code> id
     *
     * @param id
     *            the <code>ID</code> id to search for
     * @return the <code>TYPE</code> object
     */
    public abstract TYPE getByFunctionalId(Object id);

    /**
     * get a <code>TYPE</code> object by its <code>ID</code> id
     *
     * @param id
     *            the <code>ID</code> id to search for
     * @return the <code>TYPE</code> object
     */
    public abstract TYPE getById(ID id);

    /**
     * Get a {@link Collection} of all the <code>TYPE</code>, order by
     * <code>ID</code>
     *
     * @return a {@link Collection} of <code>TYPE</code>
     */
    public abstract Collection<TYPE> getList();

    /**
     * searchByFunctionalId the <code>TYPE</code> object
     *
     * @param id
     *            the <code>ID</code> id to search for
     *
     * @return the objects matching the search id
     */
    public Collection<TYPE> searchByFunctionalId(Object id);

    /**
     * Update an object of <code>TYPE</code>
     *
     * @param object
     *            the <code>TYPE</code> object to update
     * @return the number of objects affected
     */
    public abstract TYPE update(TYPE object);

}
