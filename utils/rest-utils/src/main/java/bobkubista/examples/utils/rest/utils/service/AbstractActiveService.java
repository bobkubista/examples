/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.utils.domain.model.api.ActiveClientApi;
import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;

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
        extends AbstractFunctionalIdentifiableService<TYPE, ID, COL>implements ActiveService<TYPE, ID, COL> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractActiveService.class);

    @Override
    public COL getAllActive(final List<String> sort, final Integer page, final Integer maxResults) {
        try {
            return this.getAllActiveASync(sort, page, maxResults)
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.warn(e.getMessage(), e);
            return this.getEmptyCollection();
        }
    }

    @Override
    public CompletableFuture<COL> getAllActiveASync(final List<String> sort, final Integer page, final Integer maxResults) {

        return CompletableFuture.supplyAsync(() -> {
            final Response allActive = AbstractActiveService.this.getProxy()
                    .getAllActive(new SearchBean().setMaxResults(maxResults)
                            .setPage(page)
                            .setSort(sort));
            try {
                return allActive.readEntity(AbstractActiveService.this.getCollectionClass());
            } finally {
                allActive.close();
            }
        });
    }

    @Override
    protected abstract ActiveClientApi<TYPE, ID> getProxy();
}
