package bobkubista.examples.utils.service.jpa.persistance.facade;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.IntSupplier;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.utils.domain.model.annotation.http.cache.CacheMaxAge;
import bobkubista.examples.utils.domain.model.annotation.http.cache.CachePrivate;
import bobkubista.examples.utils.domain.model.annotation.http.cache.CacheTransform;
import bobkubista.examples.utils.domain.model.api.ApiConstants;
import bobkubista.examples.utils.domain.model.api.IdentifiableServerApi;
import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObject;
import bobkubista.examples.utils.service.jpa.persistance.converter.EntityToDomainConverter;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractIdentifiableEntity;
import bobkubista.examples.utils.service.jpa.persistance.services.IdentifiableEntityService;

/**
 * A generic implementation of the {@link IdentifiableServerApi}. In general,
 * only get opperations are supported. Create, update and delete should only be
 * used in admin applications. If you want to create, update or delete from a
 * webapp, override the methodes and implement them seperatly.
 *
 * @param <DMO>
 *            A {@link DomainObject}
 * @param <TYPE>
 *            An {@link AbstractIdentifiableEntity}
 * @param <ID>
 *            An {@link Serializable} identifier
 * @param <DMOL>
 *            A {@link AbstractGenericDomainObjectCollection}
 *
 * @author bkubista
 *
 */
public abstract class AbstractGenericIdentifiableFacade<DMO extends DomainObject, DMOL extends AbstractGenericDomainObjectCollection<DMO>, TYPE extends AbstractIdentifiableEntity>
		implements IdentifiableServerApi<DMO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenericIdentifiableFacade.class);

	@Override
	public Response create(@Valid final DMO object) {
		Validate.notNull(object);
		final TYPE entity = this.getConverter()
				.convertToEntity(object);
		final TYPE result = this.getService()
				.create(entity)
				.orElseThrow(NotFoundException::new);
		try {
			return Response.created(new URI(result.getId()
					.toString()))
					.build();
		} catch (final URISyntaxException e) {
			LOGGER.warn(e.getMessage(), e);
			return Response.serverError()
					.build();
		}
	}

	@Override
	public Response delete(final Long identifier) {
		final TYPE entity = this.getService()
				.getById(identifier)
				.orElseThrow(NotFoundException::new);
		this.getService()
				.delete(entity);
		return Response.noContent()
				.build();
	}

	@CacheTransform
	@CachePrivate
	@CacheMaxAge(time = 10, unit = TimeUnit.SECONDS)
	@Override
	public Response getAll(final SearchBean search) {
		final CompletableFuture<Collection<TYPE>> allEntriesFuture = CompletableFuture
				.supplyAsync(() -> this.getService()
						.getAll(search));

		final CompletableFuture<Long> amountFuture = CompletableFuture.supplyAsync(() -> this.getService()
				.count());

		try {
			final List<Link> links = buildCollectionLinks(search, allEntriesFuture, amountFuture);
			return Response.ok(this.getConverter()
					.convertToDomainObject(allEntriesFuture.get(1, TimeUnit.SECONDS),
							amountFuture.get(1, TimeUnit.SECONDS),
							links))
					.build();
		} catch (InterruptedException | ExecutionException e) {
			throw new ServerErrorException("Could not access the needed data", Status.BAD_GATEWAY);
		} catch (final TimeoutException e) {
			throw new ServiceUnavailableException(5L, e);
		}
	}

	@CacheMaxAge(time = 5, unit = TimeUnit.MINUTES)
	@CacheTransform
	@Override
	public Response getByID(final Long identifier, final Request request) {
		final TYPE result = this.getService()
				.getById(identifier)
				.orElseThrow(NotFoundException::new);
		try {
			final ResponseBuilder response = request.evaluatePreconditions(result.getUpdatedDate());
			if (response != null) {
				return response.build();
			}

			return Response.ok(this.getConverter()
					.convertToDomainObject(result))
					.location(new URI(this.getClass()
							.getDeclaredAnnotation(Path.class)
							.value() + identifier.toString()))
					.lastModified(new Date(result.getUpdatedDate()
							.getTime()))
					.tag(new EntityTag(Integer.toString(result.hashCode())))
					.build();
		} catch (final URISyntaxException e) {
			LOGGER.warn(e.getMessage(), e);
			return Response.serverError()
					.build();
		}
	}

	@Override
	public Response update(@Valid final DMO object, final Request request) {
		final TYPE entity = this.getConverter()
				.convertToEntity(object);

		final ResponseBuilder response = request.evaluatePreconditions(entity.getUpdatedDate());

		if (response != null) {
			return response.build();
		}

		this.getService()
				.update(entity);
		try {
			final TYPE result = this.getService()
					.getById(entity.getId())
					.orElseThrow(NotFoundException::new);
			return Response.ok(this.getConverter()
					.convertToDomainObject(result))
					.location(new URI(entity.getId()
							.toString()))
					.lastModified(new Date(result.getUpdatedDate()
							.getTime()))
					// TODO use java.security.MessageDigest.getInstance("MD5")
					.tag(new EntityTag(Integer.toString(result.hashCode())))
					.build();
		} catch (final URISyntaxException e) {
			LOGGER.warn(e.getMessage(), e);
			return Response.serverError()
					.build();
		}
	}

	protected List<Link> buildCollectionLinks(final SearchBean search,
			final CompletableFuture<Collection<TYPE>> allEntriesFuture, final CompletableFuture<Long> amountFuture)
			throws InterruptedException, ExecutionException, TimeoutException {
		final List<Link> links = new ArrayList<>(2);
		this.buildNextCollectionLink(search,
				allEntriesFuture.get(1, TimeUnit.SECONDS),
				amountFuture.get(1, TimeUnit.SECONDS))
				.ifPresent(links::add);
		this.buildPreviousCollectionLink(search)
				.ifPresent(links::add);
		return links;
	}

	/**
	 * Build a link for a collection result
	 *
	 * @param search
	 *            {@link SearchBean}, used to fill in some blanks
	 * @param rel
	 *            the rel value of the {@link Link}
	 * @param page
	 *            {@link IntSupplier}, to calculate the page
	 * @return the link
	 */
	protected Link buildLink(final SearchBean search, final String rel, final IntSupplier page) {
		final URI nextUri = UriBuilder.fromResource(this.getClass())
				.queryParam(ApiConstants.SORT, search.getSort()
						.toArray())
				.queryParam(ApiConstants.MAX, search.getMaxResults())
				.queryParam(ApiConstants.PAGE, page.getAsInt())
				.build();
		return Link.fromUri(nextUri)
				.rel(rel)
				.build();
	}

	/**
	 * If a previous link is valid, it will be added to the links
	 *
	 * @param search
	 *            {@link SearchBean}, used to determain if the link should be
	 *            added
	 * @param allEntities
	 *            {@link Collection} of <code>TYPE</code>, used to determain if
	 *            the link should be added
	 * @param amount
	 *            amount of total results to be able to be returned. Used to
	 *            determain if the link should be added
	 * @return
	 */
	protected Optional<Link> buildNextCollectionLink(final SearchBean search, final Collection<TYPE> allEntities,
			final Long amount) {
		if (allEntities.size() == search.getMaxResults()
				&& search.getPage() * search.getMaxResults() + search.getMaxResults() < amount) {
			return Optional.of(this.buildLink(search, "next", () -> search.getPage() + 1));
		} else {
			return Optional.empty();
		}
	}

	/**
	 * If a previous link is valid, it will be added to the links
	 *
	 * @param search
	 *            {@link SearchBean}, used to determain if the link should be
	 *            added
	 * @param links
	 *            a {@link List} of {@link Link}s to add the previous link to.
	 * @return
	 * @return
	 */
	protected Optional<Link> buildPreviousCollectionLink(final SearchBean search) {
		if (search.getPage() != 0) {
			return Optional.of(this.buildLink(search, "previous", () -> search.getPage() - 1));
		} else {
			return Optional.empty();
		}
	}

	/**
	 * Get the {@link EntityToDomainConverter}
	 *
	 * @return {@link EntityToDomainConverter}
	 */
	protected abstract EntityToDomainConverter<DMO, DMOL, TYPE> getConverter();

	/**
	 * Get the {@link IdentifiableEntityService}
	 *
	 * @return {@link IdentifiableEntityService}
	 */
	protected abstract IdentifiableEntityService<TYPE> getService();
}
