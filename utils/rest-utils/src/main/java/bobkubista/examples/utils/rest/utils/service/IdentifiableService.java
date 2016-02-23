/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject;

/**
 * @author bkubista
 *
 *         This interface is intended for controller applications only. Do not
 *         use it in webservices which access the database. We want the actual
 *         implementation to to make us of
 *         {@link AbstractGenericIdentifiableDomainObject}s and the
 *         {@link Collection} of them as return types.
 *
 * @param <TYPE>
 *            The {@link AbstractGenericIdentifiableDomainObject}
 * @param <ID>
 *            The identifier of the
 *            {@link AbstractGenericIdentifiableDomainObject}
 * @param <COL>
 *            The {@link AbstractGenericDomainObjectCollection}
 *
 */
public interface IdentifiableService<TYPE extends AbstractGenericIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>> {

    /**
     *
     * @param object
     *            {@link AbstractGenericIdentifiableDomainObject} to create
     * @return TODO
     */
    boolean create(TYPE object);

    /**
     *
     * @param id
     *            identifier of the
     *            {@link AbstractGenericIdentifiableDomainObject} to delete
     * @return TODO
     */
    boolean delete(ID id);

    /**
     * Get a {@link AbstractGenericDomainObjectCollection}
     *
     * @param sort
     *            field to sort
     * @param page
     *            the page of the results. page * maxResults will be skipped
     * @param maxResults
     *            the amount of results to return
     *
     * @return all instances of that type
     */
    COL getAll(List<String> sort, Integer page, Integer maxResults);

    /**
     * Get a {@link CompletableFuture} with
     * {@link AbstractGenericDomainObjectCollection}
     *
     * @param sort
     *            field to sort
     * @param page
     *            the page of the results. page * maxResults will be skipped
     * @param maxResults
     *            the amount of results to return
     *
     * @return {@link CompletableFuture} of all instances of that type
     */
    CompletableFuture<COL> getAllAsync(List<String> sort, Integer page, Integer maxResults);

    /**
     * Get a new <code>TYPE</code> if it is modified.
     *
     * @param object
     *            the old value
     * @return the current value
     */
    GenericETagModifiedDateDomainObjectDecorator<TYPE> getByID(GenericETagModifiedDateDomainObjectDecorator<TYPE> object);

    /**
     * Get a <code>TYPE</code> with <code>ID</code>
     *
     * @param id
     *            the identifier
     * @return the single instance of the <code>TYPE</code>
     */
    TYPE getByID(ID id);

    /**
     *
     * @param object
     *            the {@link GenericETagModifiedDateDomainObjectDecorator} of
     *            TYPE to update
     *
     * @return the updated {@link GenericETagModifiedDateDomainObjectDecorator}
     *         of TYPE
     */
    GenericETagModifiedDateDomainObjectDecorator<TYPE> update(GenericETagModifiedDateDomainObjectDecorator<TYPE> object);

    /**
     *
     * @param object
     *            the TYPE to update
     * @return the updated TYPE
     */
    TYPE update(TYPE object);

}
