/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

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
 *
 */
public interface IdentifiableService<TYPE extends AbstractGenericIdentifiableDomainObject<ID>, ID extends Serializable> {

	/**
	 *
	 * @param object
	 *            {@link AbstractGenericIdentifiableDomainObject} to create
	 */
	void create(TYPE object);

	/**
	 *
	 * @param id
	 *            identifier of the
	 *            {@link AbstractGenericIdentifiableDomainObject} to delete
	 */
	void delete(ID id);

	/**
	 * Get a {@link Collection} of <code>TYPE</code>
	 *
	 * @return all instances of that type
	 */
	Collection<TYPE> getAll(String sort, Integer page, Integer maxResults);

	/**
	 * Get a {@link CompletableFuture} with {@link Collection} of
	 * <code>TYPE</code>
	 *
	 * @return {@link CompletableFuture} of all instances of that type
	 */
	CompletableFuture<Collection<TYPE>> getAllAsync(String sort, Integer page, Integer maxResults);

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
	 *            the TYPE to update
	 * @return the updated TYPE
	 */
	TYPE update(TYPE object);

}
