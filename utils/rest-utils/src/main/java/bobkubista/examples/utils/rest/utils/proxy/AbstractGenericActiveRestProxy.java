/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.proxy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.rest.utils.service.ActiveService;

/**
 *
 * @author Bob
 *
 * @param <TYPE>
 *            {@link AbstractGenericActiveDomainObject}
 * @param <ID>
 *            {@link Serializable}
 * @param <COL>
 *            {@link AbstractGenericDomainObjectCollection}
 */
public abstract class AbstractGenericActiveRestProxy<TYPE extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends AbstractGenericFunctionalIdentifiableRestProxy<TYPE, ID, COL>implements ActiveService<TYPE, ID, COL> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenericActiveRestProxy.class);

    @Override
    public COL getAllActive(final List<String> sort, final Integer page, final Integer maxResults) {
        try {
            final Long serverTimeout = (Long) ServerProperties.getProperies()
                    .getOrDefault("server.timeout", 1L);
            return this.getAllActiveASync(sort, page, maxResults)
                    .get(serverTimeout, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            LOGGER.warn(e.getMessage(), e);
            return this.getAllFallback();
        }
    }

    @Override
    public CompletableFuture<COL> getAllActiveASync(final List<String> sort, final Integer page, final Integer maxResults) {
        return CompletableFuture.supplyAsync(() -> {

            final Map<String, Object> params = AbstractGenericIdentifiableRestProxy.getQueryparameters(sort, page, maxResults);
            return call(t -> this.getRequest(this.getServiceWithQueryParams(params, "active"))
                    .get(this.getCollectionClass()), params);
        });
    }
}
