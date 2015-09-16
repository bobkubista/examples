/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.FunctionalIdentifiableDomainObject;
import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestFunctionalIdentifiableProxy;

/**
 * @author Bob Kubista
 * @param <TYPE>
 *            {@link FunctionalIdentifiableDomainObject}
 * @param <ID>
 *            Identifier
 * @param <COL>
 *            {@link DomainObjectCollection} for TYPE
 */
public abstract class AbstractFunctionalIdentifiableService<TYPE extends FunctionalIdentifiableDomainObject<ID>, ID extends Serializable, COL extends DomainObjectCollection<TYPE>>
        extends AbstractIdentifiableService<TYPE, ID, COL>implements FunctionalIdentifiableService<TYPE, ID> {

	@Override
	public TYPE getByFunctionalId(final String functionalId) {
		return this.getProxy().getByFunctionalId(functionalId).readEntity(this.getDomainClass());
	}

	@Override
	public ID getIdByFunctionalId(final String fId) {
		return this.getProxy().getIdByFunctionalId(fId).readEntity(this.getIdClass());
	}

	@Override
	protected abstract AbstractGenericRestFunctionalIdentifiableProxy<TYPE, ID> getProxy();
}
