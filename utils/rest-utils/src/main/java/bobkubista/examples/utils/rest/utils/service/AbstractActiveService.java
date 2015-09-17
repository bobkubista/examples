/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestActiveProxy;

/**
 * @author Bob Kubista
 * @param <TYPE>
 *            {@link AbstractGenericActiveDomainObject}
 * @param <ID>
 *            identifier
 * @param <COL>
 *            {@link AbstractGenericDomainObjectCollection}
 */
public abstract class AbstractActiveService<TYPE extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends AbstractFunctionalIdentifiableService<TYPE, ID, COL>implements ActiveService<TYPE, ID> {

    @Override
    public Collection<TYPE> getAllActive() {
        try {
            return this.getAllActiveASync().get();
        } catch (InterruptedException | ExecutionException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public CompletableFuture<Collection<TYPE>> getAllActiveASync() {

        final CompletableFuture<Collection<TYPE>> future = CompletableFuture
                .supplyAsync(() -> AbstractActiveService.this.getProxy().getAllActive().readEntity(AbstractActiveService.this.getCollectionClass()).getDomainCollection());
        return future;
    }

    @Override
    protected abstract AbstractGenericRestActiveProxy<TYPE, ID> getProxy();
}
