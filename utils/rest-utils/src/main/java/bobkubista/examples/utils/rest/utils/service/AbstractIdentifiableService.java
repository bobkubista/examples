/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import bobkubista.examples.utils.domain.model.api.IdentifiableApi;
import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.IdentifiableDomainObject;

/**
 * @author Bob Kubista
 *
 */
public abstract class AbstractIdentifiableService<TYPE extends IdentifiableDomainObject<ID>, ID extends Serializable, COL extends DomainObjectCollection<TYPE>>
		implements IdentifiableService<TYPE, ID, COL> {

	private final Class<COL> collectionClass;
	private final Class<TYPE> domainClass;
	private final Class<ID> identifierClass;

	@SuppressWarnings("unchecked")
	public AbstractIdentifiableService() {
		final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.domainClass = (Class<TYPE>) genericSuperclass.getActualTypeArguments()[0];
		this.identifierClass = (Class<ID>) genericSuperclass.getActualTypeArguments()[1];
		this.collectionClass = (Class<COL>) genericSuperclass.getActualTypeArguments()[2];
	}

	@Override
	public void create(final TYPE object) {
		this.getProxy().create(object);
	}

	@Override
	public void delete(final ID id) {
		this.getProxy().delete(id);
	}

	@Override
	public Collection<TYPE> getAll() {
		return this.getProxy().getAll().readEntity(this.collectionClass).getDomainCollection();
	}

	@Override
	public TYPE getByID(final ID id) {
		return this.getProxy().getByID(id).readEntity(this.domainClass);
	}

	@Override
	public TYPE update(final TYPE object) {
		return this.getProxy().update(object).readEntity(this.domainClass);
	}

	protected Class<COL> getCollectionClass() {
		return this.collectionClass;
	}

	protected Class<TYPE> getDomainClass() {
		return this.domainClass;
	}

	protected Class<ID> getIdClass() {
		return this.identifierClass;
	}

	protected abstract IdentifiableApi<TYPE, ID> getProxy();
}
