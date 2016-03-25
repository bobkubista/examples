/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;

/**
 * @author Bob Kubista
 *
 * @param <TYPE>
 *            The {@link AbstractGenericActiveDomainObject}
 * @param <ID>
 *            The identifier of the {@link AbstractGenericActiveDomainObject}
 * @param <COL>
 *            The {@link AbstractGenericDomainObjectCollection}
 */
public interface ActiveService<TYPE extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends FunctionalIdentifiableService<TYPE, ID, COL> {

    /**
     * Get all active {@link AbstractGenericActiveDomainObject}. It has a
     * default timeout of 1 second, but can be configured. If the fallback isn't
     * overridden, then a {@link WebApplicationException} is thrown with
     * {@link Status#GATEWAY_TIMEOUT}
     *
     * @param sort
     *            which fields to sort
     * @param page
     *            how many to skip: page * maxResults
     * @param maxResults
     *            how many results to give back
     * @return Get all active {@link AbstractGenericActiveDomainObject}
     */
    COL getAllActive(final List<String> sort, final Integer page, final Integer maxResults);

    /**
     * @param sort
     *            which fields to sort
     * @param page
     *            how many to skip: page * maxResults
     * @param maxResults
     *            how many results to give back
     * @return Get all active {@link AbstractGenericActiveDomainObject}
     */
    CompletableFuture<COL> getAllActiveASync(final List<String> sort, final Integer page, final Integer maxResults);
}
