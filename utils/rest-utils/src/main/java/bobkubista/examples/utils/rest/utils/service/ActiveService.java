/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;

/**
 * @author Bob Kubista
 *
 * @param <TYPE>
 *            The {@link AbstractGenericActiveDomainObject}
 * @param <ID>
 *            The identifier of the {@link AbstractGenericActiveDomainObject}
 */
public interface ActiveService<TYPE extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable> extends FunctionalIdentifiableService<TYPE, ID> {

    /**
     * @param sort
     *            which fields to sort
     * @param page
     *            how many to skip: page * maxResults
     * @param how
     *            many results to give back
     * @return Get all active {@link AbstractGenericActiveDomainObject}
     */
    Collection<TYPE> getAllActive(final List<String> sort, final Integer page, final Integer maxResults);

    /**
     * @param sort
     *            which fields to sort
     * @param page
     *            how many to skip: page * maxResults
     * @param how
     *            many results to give back
     * @return Get all active {@link AbstractGenericActiveDomainObject}
     */
    CompletableFuture<Collection<TYPE>> getAllActiveASync(final List<String> sort, final Integer page, final Integer maxResults);
}
