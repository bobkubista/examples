package bobkubista.examples.utils.service.jpa.persistance.facade;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.utils.domain.model.annotation.http.cache.CacheMaxAge;
import bobkubista.examples.utils.domain.model.annotation.http.cache.CacheNo;
import bobkubista.examples.utils.domain.model.annotation.http.cache.CachePublic;
import bobkubista.examples.utils.domain.model.api.ApiConstants;
import bobkubista.examples.utils.domain.model.api.IdentifiableApi;
import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObject;
import bobkubista.examples.utils.service.jpa.persistance.converter.EntityToDomainConverter;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractIdentifiableEntity;
import bobkubista.examples.utils.service.jpa.persistance.services.IdentifiableEntityService;

/**
 * A generic implementation of the {@link IdentifiableApi}. In general, only get
 * opperations are supported. Create, update and delete should only be used in
 * admin applications. If you want to create, update or delete from a webapp,
 * override the methodes and implement them seperatly.
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
        implements IdentifiableApi<DMO, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenericIdentifiableFacade.class);

    @CacheNo
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

    @Override
    public Response getAll(final SearchBean search) {
        final Collection<TYPE> allEntities = this.getService()
                .getAll(search.getSort(), search.getPage(), search.getMaxResults());

        final Long amount = this.getService()
                .count();
        final List<Link> links = new ArrayList<>(2);

        if (allEntities.size() == search.getMaxResults() && search.getPage() * search.getMaxResults() + search.getMaxResults() < amount) {
            final URI nextUri = UriBuilder.fromResource(this.getClass())
                    .queryParam(ApiConstants.SORT, search.getSort()
                            .toArray())
                    .queryParam(ApiConstants.MAX, search.getMaxResults())
                    .queryParam(ApiConstants.PAGE, search.getPage() + 1)
                    .build();
            final Link next = Link.fromUri(nextUri)
                    .rel("next")
                    .build();
            links.add(next);
        }
        if (search.getPage() != 0) {
            final URI previousUri = UriBuilder.fromResource(this.getClass())
                    .queryParam(ApiConstants.SORT, search.getSort()
                            .toArray())
                    .queryParam(ApiConstants.MAX, search.getMaxResults())
                    .queryParam(ApiConstants.PAGE, search.getPage() - 1)
                    .build();
            final Link previous = Link.fromUri(previousUri)
                    .rel("previous")
                    .build();
            links.add(previous);
        }
        return Response.ok(this.getConverter()
                .convertToDomainObject(allEntities, amount, links))
                .build();
    }

    @CacheMaxAge(time = 5, unit = TimeUnit.MINUTES)
    @CachePublic
    @Override
    public Response getByID(final ID identifier) {
        final TYPE result = this.getService()
                .getById(identifier);
        if (result == null) {
            LOGGER.debug("resource {} not found", identifier);
            throw new NotFoundException();
        } else {
            try {
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
    public Response update(final DMO object) {
        final TYPE entity = this.getConverter()
                .convertToEntity(object);
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
