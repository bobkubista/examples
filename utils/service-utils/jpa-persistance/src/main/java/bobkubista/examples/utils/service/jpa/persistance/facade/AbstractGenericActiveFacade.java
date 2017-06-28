package bobkubista.examples.utils.service.jpa.persistance.facade;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.ws.rs.BeanParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import bobkubista.examples.utils.domain.model.annotation.http.cache.CacheMaxAge;
import bobkubista.examples.utils.domain.model.annotation.http.cache.CachePrivate;
import bobkubista.examples.utils.domain.model.annotation.http.cache.CacheTransform;
import bobkubista.examples.utils.domain.model.api.ActiveSearchBean;
import bobkubista.examples.utils.domain.model.api.ActiveServerApi;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;
import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

/**
 * @author bkubista
 * @param <DMO>
 *            {@link AbstractGenericActiveDomainObject}
 * @param <TYPE>
 *            {@link AbstractGenericActiveEntity}
 * @param <DMOL>
 *            {@link AbstractGenericDomainObjectCollection}
 */
public abstract class AbstractGenericActiveFacade<DMO extends AbstractGenericActiveDomainObject, TYPE extends AbstractGenericActiveEntity, DMOL extends AbstractGenericDomainObjectCollection<DMO>>
		extends AbstractGenericFunctionalIdentifiableFacade<DMO, TYPE, DMOL> implements ActiveServerApi<DMO> {

	@CacheTransform
	@CachePrivate
	@CacheMaxAge(time = 10, unit = TimeUnit.MINUTES)
	@Override
	public Response getAll(@BeanParam final ActiveSearchBean search) {
		final CompletableFuture<Collection<TYPE>> allEntities = CompletableFuture.supplyAsync(() -> this.getService()
				.getAll(search));
		final CompletableFuture<Long> amount = CompletableFuture.supplyAsync(() -> this.getService()
				.count(search));

		try {
			final List<Link> links = buildCollectionLinks(search, allEntities, amount);

			return Response.ok(this.getConverter()
					.convertToDomainObject(allEntities.get(1, TimeUnit.SECONDS), amount.get(1, TimeUnit.SECONDS),
							links))
					.build();
		} catch (InterruptedException | ExecutionException e) {
			throw new ServerErrorException("Could not access the needed data", Status.BAD_GATEWAY);
		} catch (final TimeoutException e) {
			throw new ServiceUnavailableException(5L, e);
		}
	}

	@Override
	protected abstract ActiveEntityService<TYPE> getService();

}
