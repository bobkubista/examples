/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractActiveService.class);

    @Override
    public Collection<TYPE> getAllActive() {
        try {
            return this.getAllActiveASync().get();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.warn(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    @Override
    public CompletableFuture<Collection<TYPE>> getAllActiveASync() {

        return CompletableFuture
                .supplyAsync(() -> AbstractActiveService.this.getProxy().getAllActive().readEntity(AbstractActiveService.this.getCollectionClass()).getDomainCollection());
    }

    @Override
    protected abstract AbstractGenericRestActiveProxy<TYPE, ID> getProxy();
}
