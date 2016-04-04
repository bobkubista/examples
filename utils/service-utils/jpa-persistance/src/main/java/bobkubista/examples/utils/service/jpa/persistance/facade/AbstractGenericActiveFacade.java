package bobkubista.examples.utils.service.jpa.persistance.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import javax.ws.rs.BeanParam;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.annotation.http.cache.CacheMaxAge;
import bobkubista.examples.utils.domain.model.annotation.http.cache.CachePrivate;
import bobkubista.examples.utils.domain.model.annotation.http.cache.CacheTransform;
import bobkubista.examples.utils.domain.model.api.ActiveServerApi;
import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;
import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

/**
 * @author bkubista
 * @param <DMO>
 *            {@link AbstractGenericActiveDomainObject}
 * @param <ID>
 *            The idertifier
 * @param <TYPE>
 *            {@link AbstractGenericActiveEntity}
 * @param <COL>
 *            {@link AbstractGenericDomainObjectCollection}
 */
public abstract class AbstractGenericActiveFacade<DMO extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable, TYPE extends AbstractGenericActiveEntity<ID>, COL extends AbstractGenericDomainObjectCollection<DMO>>
        extends AbstractGenericFunctionalIdentifiableFacade<DMO, TYPE, ID, COL>implements ActiveServerApi<DMO, ID> {

    @CacheTransform
    @CachePrivate
    @CacheMaxAge(time = 10, unit = TimeUnit.MINUTES)
    @Override
    public Response getAllActive(@BeanParam final SearchBean search) {
        final List<Link> links = new ArrayList<>(2);
        final Stream<TYPE> allEntities = this.getService()
                .getAllActive(search);
        final Long amount = this.getService()
                .countActive();

        this.buildNextCollectionLink(search, allEntities, amount, links);
        this.buildPreviousCollectionLink(search, links);

        return Response.ok(this.getConverter()
                .convertToDomainObject(allEntities, amount, links))
                .build();
    }

    @Override
    protected abstract ActiveEntityService<TYPE, ID> getService();

}
