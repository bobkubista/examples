/**
 *
 */
package bobkubista.examples.utils.rest.utils.cache.generic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.common.util.concurrent.ListenableFuture;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.FunctionalIdentifiableDomainObject;
import bobkubista.examples.utils.rest.utils.FunctionalIdentifiableService;
import bobkubista.examples.utils.rest.utils.IdentifiableService;

/**
 * @author bkubista
 * @param <K>
 *            key, which is {@link Serializable}
 * @param <V>
 *            value, which is {@link FunctionalIdentifiableDomainObject}
 */
public abstract class AbstractFunctionalAutoCache<K extends Serializable, V extends FunctionalIdentifiableDomainObject<K>> extends AbstractIdentifiableAutoCache<K, V> {

	private final Map<String, K> functionalToKeyMap;

	/**
	 * Constructor
	 */
	public AbstractFunctionalAutoCache() {
		super();
		this.functionalToKeyMap = new HashMap<String, K>(INITIAL_CAPACITY);
	}

	/**
	 * Get a value based on the functional ID
	 *
	 * @param functionalId
	 *            functional Id
	 * @return value of type <code>V</code>
	 * @throws ExecutionException
	 *             ExecutionException - if a checked exception was thrown while
	 *             loading the value. (ExecutionException is thrown even if
	 *             computation was interrupted by an InterruptedException.)
	 *             UncheckedExecutionException - if an unchecked exception was
	 *             thrown while loading the value ExecutionError - if an error
	 *             was thrown while loading the value
	 */
	public V get(final String functionalId) throws ExecutionException {
		return this.get(this.functionalToKeyMap.computeIfAbsent(functionalId, fId -> this.getFunctionalService().getIdByFunctionalId(fId)));
	}

	@Override
	public ListenableFuture<V> reload(final K key, final V oldValue) throws Exception {
		final ListenableFuture<V> reload = super.reload(key, oldValue);
		this.functionalToKeyMap.put(reload.get().getFunctionalId(), key);
		return reload;
	}

	/**
	 * @param <T>
	 *            {@link FunctionalIdentifiableService}
	 * @return the {@link FunctionalIdentifiableService}
	 */
	protected abstract FunctionalIdentifiableService<V, K, ? extends DomainObjectCollection<V>> getFunctionalService();

	@Override
	protected IdentifiableService<V, K, ? extends DomainObjectCollection<V>> getIdentifiableService() {
		return this.getFunctionalService();
	}

}
