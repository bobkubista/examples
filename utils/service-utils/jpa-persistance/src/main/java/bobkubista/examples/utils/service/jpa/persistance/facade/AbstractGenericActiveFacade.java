package bobkubista.examples.utils.service.jpa.persistance.facade;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.api.ActiveApi;
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
    public Response getAllActive(final List<String> sort, final Integer page, final Integer maxResults) {
        return Response.ok(this.getConverter().convertToDomainObject(this.getService().getAllActive(sort, page, maxResults))).build();
    }

    @Override
    protected abstract ActiveEntityService<TYPE, ID> getService();

}
