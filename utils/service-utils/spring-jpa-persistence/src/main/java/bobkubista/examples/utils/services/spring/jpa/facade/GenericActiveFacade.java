package bobkubista.examples.utils.services.spring.jpa.facade;

import java.io.Serializable;

import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.api.ActiveApi;
import bobkubista.examples.utils.domain.model.domainmodel.identification.ActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;
import bobkubista.examples.utils.services.spring.jpa.entity.ActiveEntity;
import bobkubista.examples.utils.services.spring.jpa.services.ActiveEntityService;

/**
 * @author bkubista
 * @param <DMO>
 *            {@link ActiveDomainObject}
 * @param <ID>
 *            The idertifier
 * @param <TYPE>
 *            {@link ActiveEntity}
 * @param <DMOL>
 *            {@link DomainObjectCollection}
 */
public abstract class GenericActiveFacade<DMO extends ActiveDomainObject<ID>, ID extends Serializable, TYPE extends ActiveEntity<ID>, DMOL extends DomainObjectCollection<DMO>>
        extends GenericFunctionalIdentifiableFacade<DMO, TYPE, ID, DMOL>implements ActiveApi<DMO, ID> {

	@Override
	public Response getAllActive() {
		return Response.ok(this.getConverter().convertToDomainObject(this.getService().getAllActive())).build();
	}

	@Override
	protected abstract ActiveEntityService<TYPE, ID> getService();

}
