/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.facade;

import java.io.Serializable;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.api.FunctionalIdentifiableApi;
import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.FunctionalIdentifiableDomainObject;
import bobkubista.examples.utils.service.jpa.persistance.entity.FunctionalIdentifiableEntity;
import bobkubista.examples.utils.service.jpa.persistance.services.FunctionalIdentifiableEntityService;

/**
 * @author bkubista
 *
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
        extends GenericIdentifiableFacade<DMO, DMOL, TYPE, ID>implements FunctionalIdentifiableApi<DMO, ID> {

    @Override
    public Response getByFunctionalId(final String identifier) {
        final TYPE result = this.getService().getByFunctionalId(identifier);
        if (result == null) {
            throw new NotFoundException();
        } else {
            return Response.ok(this.getConverter().convertToDomainObject(result)).build();
        }
    }

    @Override
    public Response getIdByFunctionalId(final String fId) {
        final ID result = this.getService().getIdByFunctionalId(fId);

        if (result == null) {
            throw new NotFoundException();
        } else {
            return Response.ok(result).build();
        }
    }

    @Override
    public Response searchByFunctionalID(final String identifier) {
        return Response.ok(this.getConverter().convertToDomainObject(this.getService().searchByFunctionalID(identifier))).build();
    }

    @Override
    protected abstract FunctionalIdentifiableEntityService<TYPE, ID> getService();

}
