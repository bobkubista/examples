/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.cache;

import java.util.Collection;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.rest.utils.service.ActiveService;
import bobkubista.examples.utils.rest.utils.service.FunctionalIdentifiableService;

/**
 * An auto refreshing cache for {@link AbstractGenericActiveDomainObject}
 *
 * @param <K>
 *            the identifier
 * @param <V>
 *            the {@link AbstractGenericActiveDomainObject}
 * @param <COL>
 *            The {@link AbstractGenericDomainObjectCollection}
 * @author Bob
 *
 */
public abstract class AbstractActiveAutoCache<V extends AbstractGenericActiveDomainObject, COL extends AbstractGenericDomainObjectCollection<V>>
		extends AbstractFunctionalAutoCache<V, COL> {

	/**
	 * @return get an {@link ActiveService}
	 */
	protected abstract ActiveService<V, COL> getActiveService();

	@Override
	protected Collection<V> getAllObjects() {
		return this.getActiveService()
				.getAllActive(null, null, null)
				.getDomainCollection();
	}

	@Override
	protected final FunctionalIdentifiableService<V, COL> getFunctionalService() {
		return this.getActiveService();
	}
}
