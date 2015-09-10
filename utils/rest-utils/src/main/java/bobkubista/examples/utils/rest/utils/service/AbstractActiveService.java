/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.util.Collection;

import bobkubista.examples.utils.domain.model.domainmodel.identification.ActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;
import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestActiveProxy;

/**
 * @author Bob Kubista
 *
 */
public abstract class AbstractActiveService<TYPE extends ActiveDomainObject<ID>, ID extends Serializable, COL extends DomainObjectCollection<TYPE>>
		extends AbstractFunctionalIdentifiableService<TYPE, ID, COL>implements ActiveService<TYPE, ID, COL> {

	@Override
	public Collection<TYPE> getAllActive() {
		return this.getProxy().getAllActive().readEntity(this.getCollectionClass()).getDomainCollection();
	}

	@Override
	protected abstract AbstractGenericRestActiveProxy<TYPE, ID> getProxy();

}