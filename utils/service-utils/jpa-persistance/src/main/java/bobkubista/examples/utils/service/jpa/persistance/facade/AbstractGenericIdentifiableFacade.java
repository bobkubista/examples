package bobkubista.examples.utils.service.jpa.persistance.facade;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.IntSupplier;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
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
public abstract class AbstractGenericIdentifiableFacade<DMO extends DomainObject, DMOL extends AbstractGenericDomainObjectCollection<DMO>, TYPE extends AbstractIdentifiableEntity<ID>, ID extends Serializable>
        implements IdentifiableServerApi<DMO, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenericIdentifiableFacade.class);

    @Override
    public Response create(final DMO object) {
        Validate.notNull(object);
        final TYPE entity = this.getConverter()
                .convertToEntity(object);
        final TYPE result = this.getService()
                .create(entity);
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
    public Response delete(final ID identifier) {
        final TYPE entity = this.getService()
                .getById(identifier);
        if (entity != null) {
            this.getService()
                    .delete(entity);
            return Response.noContent()
                    .build();
        } else {
            LOGGER.debug("resource {} not found", identifier);
            throw new NotFoundException();
        }
    }

    @CacheTransform
    @CachePrivate
    @CacheMaxAge(time = 10, unit = TimeUnit.SECONDS)
    @Override
    public Response getAll(final SearchBean search) {
        final Collection<TYPE> allEntities = this.getService()
                .getAll(search.getSort(), search.getPage(), search.getMaxResults());

        final Long amount = this.getService()
                .count();
        final List<Link> links = new ArrayList<>(2);

        this.buildNextCollectionLink(search, allEntities, amount, links);
        this.buildPreviousCollectionLink(search, links);

        return Response.ok(this.getConverter()
                .convertToDomainObject(allEntities, amount, links))
                .build();
    }

    @CacheMaxAge(time = 5, unit = TimeUnit.MINUTES)
    @CacheTransform
    @Override
    public Response getByID(final ID identifier, final Request request) {
        final TYPE result = this.getService()
                .getById(identifier);
        if (result == null) {
            LOGGER.debug("resource {} not found", identifier);
            throw new NotFoundException();
        } else {
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
                        .build();
            } catch (final URISyntaxException e) {
                LOGGER.warn(e.getMessage(), e);
                return Response.serverError()
                        .build();
            }
        }
    }

    @Override
    public Response update(final DMO object, final Request request) {
        final TYPE entity = this.getConverter()
                .convertToEntity(object);

        final ResponseBuilder response = request.evaluatePreconditions(entity.getUpdatedDate());

        if (response != null) {
            response.build();
        }

        this.getService()
                .update(entity);
        try {
            final TYPE result = this.getService()
                    .getById(entity.getId());
            return Response.ok(this.getConverter()
                    .convertToDomainObject(result))
                    .location(new URI(entity.getId()
                            .toString()))
                    .lastModified(new Date(result.getUpdatedDate()
                            .getTime()))
                    .build();
        } catch (final URISyntaxException e) {
            LOGGER.warn(e.getMessage(), e);
            return Response.serverError()
                    .build();
        }
    }

    /**
     * Build a link for a collection result
     *
     * @param search
     *            {@link SearchBean}, used to fill in some blanks
     * @param links
     *            a {@link List} of {@link Link}s to add the created to.
     * @param rel
     *            the rel value of the {@link Link}
     * @param page
     *            {@link IntSupplier}, to calculate the page
     */
    protected void buildCollectionLink(final SearchBean search, final List<Link> links, final String rel, final IntSupplier page) {
        final URI nextUri = UriBuilder.fromResource(this.getClass())
                .queryParam(ApiConstants.SORT, search.getSort()
                        .toArray())
                .queryParam(ApiConstants.MAX, search.getMaxResults())
                .queryParam(ApiConstants.PAGE, page.getAsInt())
                .build();
        final Link next = Link.fromUri(nextUri)
                .rel(rel)
                .build();
        links.add(next);
    }

    /**
     * If a previous link is valid, it will be added to the links
     *
     * @param search
     *            {@link SearchBean}, used to determain if the link should be
     *            added
     * @param links
     *            a {@link List} of {@link Link}s to add the previous link to.
     * @param allEntities
     *            {@link Collection} of <code>TYPE</code>, used to determain if
     *            the link should be added
     * @param amount
     *            amount of total results to be able to be returned. Used to
     *            determain if the link should be added
     */
    protected void buildNextCollectionLink(final SearchBean search, final Collection<TYPE> allEntities, final Long amount, final List<Link> links) {
        if (allEntities.size() == search.getMaxResults() && search.getPage() * search.getMaxResults() + search.getMaxResults() < amount) {
            this.buildCollectionLink(search, links, "next", () -> search.getPage() + 1);
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
     */
    protected void buildPreviousCollectionLink(final SearchBean search, final List<Link> links) {
        if (search.getPage() != 0) {
            this.buildCollectionLink(search, links, "previous", () -> search.getPage() - 1);
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
    protected abstract IdentifiableEntityService<TYPE, ID> getService();
}
