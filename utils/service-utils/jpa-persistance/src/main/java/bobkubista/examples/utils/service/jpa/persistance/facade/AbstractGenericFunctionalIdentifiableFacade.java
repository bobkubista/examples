/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.facade;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.utils.domain.model.annotation.http.cache.CacheMaxAge;
import bobkubista.examples.utils.domain.model.annotation.http.cache.CachePrivate;
import bobkubista.examples.utils.domain.model.annotation.http.cache.CacheTransform;
import bobkubista.examples.utils.domain.model.api.FunctionalIdentifiableServerApi;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericFunctionalIdentifiableDomainObject;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericFunctionalIdentifiableEntity;
import bobkubista.examples.utils.service.jpa.persistance.services.FunctionalIdentifiableEntityService;

/**
 * @author bkubista
 *
 * @param <DMO>
 *            {@link AbstractGenericFunctionalIdentifiableDomainObject}
 * @param <TYPE>
 *            {@link AbstractGenericFunctionalIdentifiableEntity}
 * @param <ID>
 *            Identifier
 * @param <DMOL>
 *            {@link AbstractGenericDomainObjectCollection}
 */
public abstract class AbstractGenericFunctionalIdentifiableFacade<DMO extends AbstractGenericFunctionalIdentifiableDomainObject<ID>, TYPE extends AbstractGenericFunctionalIdentifiableEntity<ID>, ID extends Serializable, DMOL extends AbstractGenericDomainObjectCollection<DMO>>
        extends AbstractGenericIdentifiableFacade<DMO, DMOL, TYPE, ID>implements FunctionalIdentifiableServerApi<DMO, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenericFunctionalIdentifiableFacade.class);

    @CacheTransform
    @CachePrivate
    @CacheMaxAge(time = 10, unit = TimeUnit.MINUTES)
    @Override
    public Response getByFunctionalId(final String identifier) {
        final TYPE result = this.getService()
                .getByFunctionalId(identifier);
        if (result == null) {
            throw new NotFoundException();
        } else {
            try {
                return Response.ok(this.getConverter()
                        .convertToDomainObject(result))
                        .location(new URI(identifier))
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

    @CacheTransform
    @CachePrivate
    @CacheMaxAge(time = 10, unit = TimeUnit.MINUTES)
    @Override
    public Response getIdByFunctionalId(final String fId) {
        final ID result = this.getService()
                .getIdByFunctionalId(fId);

        if (result == null) {
            throw new NotFoundException();
        } else {
            try {
                return Response.ok(result.toString())
                        .location(new URI(fId))
                        .build();
            } catch (final URISyntaxException e) {
                LOGGER.warn(e.getMessage(), e);
                return Response.serverError()
                        .build();
            }
        }
    }

    @Override
    protected abstract FunctionalIdentifiableEntityService<TYPE, ID> getService();

}
