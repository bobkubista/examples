/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import bobkubista.examples.utils.domain.model.api.IdentifiableApi;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject;

/**
 * @author Bob Kubista
 * @param <TYPE>
 *            {@link AbstractGenericIdentifiableDomainObject}
 * @param <ID>
 *            Identifier
 * @param <COL>
 *            {@link AbstractGenericDomainObjectCollection} for TYPE
 */
public abstract class AbstractIdentifiableService<TYPE extends AbstractGenericIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        implements IdentifiableService<TYPE, ID> {

    private static final int COLLECTION_CLASS_TYPE_ARGUMENT_NUMBER = 2;
    private static final int DOMAINOBJECT_CLASS_TYPE_ARGUMENT_NUMBER = 0;
    private static final int IDENTIFIER_CLASS_TYPE_ARGUMENT_NUMBER = 1;
    private final Class<COL> collectionClass;
    private final Class<TYPE> domainClass;
    private final Class<ID> identifierClass;

    /**
     * Constructor
     */
    @SuppressWarnings("unchecked")
    public AbstractIdentifiableService() {
        final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.domainClass = (Class<TYPE>) genericSuperclass.getActualTypeArguments()[DOMAINOBJECT_CLASS_TYPE_ARGUMENT_NUMBER];
        this.identifierClass = (Class<ID>) genericSuperclass.getActualTypeArguments()[IDENTIFIER_CLASS_TYPE_ARGUMENT_NUMBER];
        this.collectionClass = (Class<COL>) genericSuperclass.getActualTypeArguments()[COLLECTION_CLASS_TYPE_ARGUMENT_NUMBER];
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
