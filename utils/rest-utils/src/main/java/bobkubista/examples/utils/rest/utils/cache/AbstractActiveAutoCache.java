/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.cache;

import java.io.Serializable;
import java.util.Collection;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;
import bobkubista.examples.utils.rest.utils.service.ActiveService;
import bobkubista.examples.utils.rest.utils.service.FunctionalIdentifiableService;

/**
 * @author Bob
 *
 */
public abstract class AbstractActiveAutoCache<K extends Serializable, V extends AbstractGenericActiveDomainObject<K>> extends AbstractFunctionalAutoCache<K, V> {

    protected abstract ActiveService<V, K> getActiveService();

    @Override
    protected Collection<V> getAllObjects() {
        return this.getActiveService().getAllActive();
    }

    @Override
    protected FunctionalIdentifiableService<V, K> getFunctionalService() {
        return this.getActiveService();
    }
}
