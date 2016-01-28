/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.facade;

import java.io.Serializable;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.api.FunctionalIdentifiableApi;
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
        extends AbstractGenericIdentifiableFacade<DMO, DMOL, TYPE, ID>implements FunctionalIdentifiableApi<DMO, ID> {

    @Override
    public Response getByFunctionalId(final String identifier) {
        final TYPE result = this.getService()
                .getByFunctionalId(identifier);
        if (result == null) {
            throw new NotFoundException();
        } else {
            return Response.ok(this.getConverter()
                    .convertToDomainObject(result))
                    .build();
        }
    }

    @Override
    public Response getIdByFunctionalId(final String fId) {
        final ID result = this.getService()
                .getIdByFunctionalId(fId);

        if (result == null) {
            throw new NotFoundException();
        } else {
            return Response.ok(result.toString())
                    .build();
        }
    }

    @Override
    protected abstract FunctionalIdentifiableEntityService<TYPE, ID> getService();

}
