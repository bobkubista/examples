/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.util.Collection;
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
     *
     * @return Get all active {@link AbstractGenericActiveDomainObject}
     */
    Collection<TYPE> getAllActive();

    /**
     *
     * @return Get all active {@link AbstractGenericActiveDomainObject}
     */
    CompletableFuture<Collection<TYPE>> getAllActiveASync();
}
