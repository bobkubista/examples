package bobkubista.examples.utils.service.jpa.persistance.facade;

import java.io.Serializable;

import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.api.ActiveApi;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;
import bobkubista.examples.utils.service.jpa.persistance.entity.ActiveEntity;
import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

/**
 * @author bkubista
 * @param <DMO>
 *            {@link AbstractGenericActiveDomainObject}
 * @param <ID>
 *            The idertifier
 * @param <TYPE>
 *            {@link ActiveEntity}
 * @param <DMOL>
 *            {@link DomainObjectCollection}
 */
public abstract class GenericActiveFacade<DMO extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable, TYPE extends ActiveEntity<ID>, DMOL extends DomainObjectCollection<DMO>>
        extends GenericFunctionalIdentifiableFacade<DMO, TYPE, ID, DMOL>implements ActiveApi<DMO, ID> {

    @Override
    public Response getAllActive() {
        return Response.ok(this.getConverter().convertToDomainObject(this.getService().getAllActive())).build();
    }

    @Override
    protected abstract ActiveEntityService<TYPE, ID> getService();

}
