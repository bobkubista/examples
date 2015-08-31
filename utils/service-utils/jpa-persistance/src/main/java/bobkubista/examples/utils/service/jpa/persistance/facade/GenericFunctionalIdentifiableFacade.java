/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.facade;

import java.io.Serializable;

import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.api.FunctionalIdentifiableFacade;
import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.FunctionalIdentifiableDomainObject;
import bobkubista.examples.utils.service.jpa.persistance.entity.FunctionalIdentifiableEntity;
import bobkubista.examples.utils.service.jpa.persistance.services.FunctionalIdentifiableEntityService;

/**
 * @author bkubista
 * @param <DMO>
 *            {@link FunctionalIdentifiableDomainObject}
 * @param <TYPE>
 *            {@link FunctionalIdentifiableEntity}
 * @param <ID>
 *            Identifier
 * @param <DMOL>
 *            {@link DomainObjectCollection}
 */
public abstract class GenericFunctionalIdentifiableFacade<DMO extends FunctionalIdentifiableDomainObject<ID>, TYPE extends FunctionalIdentifiableEntity<ID>, ID extends Serializable, DMOL extends DomainObjectCollection<DMO>>
		extends GenericIdentifiableFacade<DMO, DMOL, TYPE, ID>implements FunctionalIdentifiableFacade<DMO, ID> {

	@Override
	public Response getByFunctionalId(final String identifier) {
		final TYPE result = this.getService().getByFunctionalId(identifier);
		return Response.ok(this.getConverter().convertToDomainObject(result)).build();
	}

	@Override
	protected abstract FunctionalIdentifiableEntityService<TYPE, ID> getService();

	@Override
	public Response searchByFunctionalID(final String identifier) {
		return Response.ok(this.getConverter().convertToDomainObject(this.getService().searchByFunctionalID(identifier))).build();
	}

}
