/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.utils.domain.model.api.IdentifiableApi;
import bobkubista.examples.utils.domain.model.api.SearchBean;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractIdentifiableService.class);
    private final Class<COL> collectionClass;
    private final Class<TYPE> domainClass;
    private final Class<ID> identifierClass;

    /**
     * Constructor
     */
    @SuppressWarnings("unchecked")
    public AbstractIdentifiableService() {
        final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        this.domainClass = (Class<TYPE>) genericSuperclass.getActualTypeArguments()[DOMAINOBJECT_CLASS_TYPE_ARGUMENT_NUMBER];
        this.identifierClass = (Class<ID>) genericSuperclass.getActualTypeArguments()[IDENTIFIER_CLASS_TYPE_ARGUMENT_NUMBER];
        this.collectionClass = (Class<COL>) genericSuperclass.getActualTypeArguments()[COLLECTION_CLASS_TYPE_ARGUMENT_NUMBER];
    }

    @Override
    public boolean create(final TYPE object) {
        final Response response = this.getProxy()
                .create(object);
        try {
            final boolean result = response.getStatus() == 201;
            return result;
        } finally {
            response.close();
        }
    }

    @Override
    public boolean delete(final ID id) {
        final Response response = this.getProxy()
                .delete(id);
        try {
            final boolean result = response.getStatus() == 204;
            return result;
        } finally {
            response.close();
        }
    }

    @Override
    public Collection<TYPE> getAll(final List<String> sort, final Integer page, final Integer maxResults) {
        try {
            return this.getAllAsync(sort, page, maxResults)
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.warn(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    @Override
    public CompletableFuture<Collection<TYPE>> getAllAsync(final List<String> sort, final Integer page, final Integer maxResults) {
        return CompletableFuture.supplyAsync(() -> {
            final SearchBean searchBean = new SearchBean().setMaxResults(maxResults)
                    .setPage(page)
                    .setSort(sort);
            final Response response = AbstractIdentifiableService.this.getProxy()
                    .getAll(searchBean);
            try {
                if (response.getStatus() == Status.OK.getStatusCode()) {
                    return response.readEntity(AbstractIdentifiableService.this.getCollectionClass())
                            .getDomainCollection();
                } else {
                    return Collections.emptyList();
                }
            } finally {
                response.close();
            }
        });
    }

    @Override
    public TYPE getByID(final ID id) {
        final Response byID = this.getProxy()
                .getByID(id);
        try {
            return byID.readEntity(this.domainClass);
        } finally {
            byID.close();
        }
    }

    @Override
    public TYPE update(final TYPE object) {
        final Response update = this.getProxy()
                .update(object);
        try {
            return update.readEntity(this.domainClass);
        } finally {
            update.close();
        }
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
