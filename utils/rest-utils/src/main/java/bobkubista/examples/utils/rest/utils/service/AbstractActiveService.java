/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.util.Collection;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;
import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestActiveProxy;

/**
 * @author Bob Kubista
 * @param <TYPE>
 *            {@link AbstractGenericActiveDomainObject}
 * @param <ID>
 *            identifier
 * @param <COL>
 *            {@link DomainObjectCollection}
 */
public abstract class AbstractActiveService<TYPE extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable, COL extends DomainObjectCollection<TYPE>>
        extends AbstractFunctionalIdentifiableService<TYPE, ID, COL>implements ActiveService<TYPE, ID> {

    @Override
    public Collection<TYPE> getAllActive() {
        return this.getProxy().getAllActive().readEntity(this.getCollectionClass()).getDomainCollection();
    }

    @Override
    protected abstract AbstractGenericRestActiveProxy<TYPE, ID> getProxy();

}
