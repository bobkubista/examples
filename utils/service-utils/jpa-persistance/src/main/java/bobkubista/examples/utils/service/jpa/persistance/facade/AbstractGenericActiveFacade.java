package bobkubista.examples.utils.service.jpa.persistance.facade;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import bobkubista.examples.utils.domain.model.api.ActiveApi;
import bobkubista.examples.utils.domain.model.api.ApiConstants;
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
 * @param <DMOL>
 *            {@link AbstractGenericDomainObjectCollection}
 */
public abstract class AbstractGenericActiveFacade<DMO extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable, TYPE extends AbstractGenericActiveEntity<ID>, DMOL extends AbstractGenericDomainObjectCollection<DMO>>
        extends AbstractGenericFunctionalIdentifiableFacade<DMO, TYPE, ID, DMOL>implements ActiveApi<DMO, ID> {

    @Override
    public Response getAllActive(@BeanParam final SearchBean search) {
        final List<Link> links = new ArrayList<>(2);
        final Collection<TYPE> allEntities = this.getService()
                .getAllActive(search.getSort(), search.getPage(), search.getMaxResults());
        final Long amount = this.getService()
                .countActive();

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

    @Override
    protected abstract ActiveEntityService<TYPE, ID> getService();

}
