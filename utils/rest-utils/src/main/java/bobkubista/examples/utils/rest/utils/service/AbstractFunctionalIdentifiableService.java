/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericFunctionalIdentifiableDomainObject;
import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestFunctionalIdentifiableProxy;

/**
 * @author Bob Kubista
 * @param <TYPE>
 *            {@link AbstractGenericFunctionalIdentifiableDomainObject}
 * @param <ID>
 *            Identifier
 * @param <COL>
 *            {@link AbstractGenericDomainObjectCollection} for TYPE
 */
public abstract class AbstractFunctionalIdentifiableService<TYPE extends AbstractGenericFunctionalIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends AbstractIdentifiableService<TYPE, ID, COL>implements FunctionalIdentifiableService<TYPE, ID> {

    @Override
    public TYPE getByFunctionalId(final String functionalId) {
        return this.getProxy().getByFunctionalId(functionalId).readEntity(this.getDomainClass());
    }

    @Override
    public ID getIdByFunctionalId(final String fId) {
        return this.getProxy().getIdByFunctionalId(fId).readEntity(this.getIdClass());
    }

    @Override
    public Collection<TYPE> searchByFunctionalID(final String identifier) {
        try {
            return this.searchByFunctionalIdAsync(identifier).get();
        } catch (InterruptedException | ExecutionException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public CompletableFuture<Collection<TYPE>> searchByFunctionalIdAsync(final String identifier) {
        final CompletableFuture<Collection<TYPE>> future = CompletableFuture.supplyAsync(() -> AbstractFunctionalIdentifiableService.this.getProxy()
                .searchByFunctionalID(identifier).readEntity(AbstractFunctionalIdentifiableService.this.getCollectionClass()).getDomainCollection());
        return future;
    }

    @Override
    protected abstract AbstractGenericRestFunctionalIdentifiableProxy<TYPE, ID> getProxy();
}
