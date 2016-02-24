/**
 *
 */
package bobkubista.examples.utils.rest.utils.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.utils.domain.model.api.IdentifiableClientApi;
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
        implements IdentifiableService<TYPE, ID, COL> {

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
            return response.getStatus() == Status.CREATED.getStatusCode();
        } finally {
            response.close();
        }
    }

    @Override
    public boolean delete(final ID id) {
        final Response response = this.getProxy()
                .delete(id);
        try {
            return response.getStatus() == Status.NO_CONTENT.getStatusCode();
        } finally {
            response.close();
        }
    }

    @Override
    public COL getAll(final List<String> sort, final Integer page, final Integer maxResults) {
        try {
            return this.getAllAsync(sort, page, maxResults)
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.warn(e.getMessage(), e);
            return this.getEmptyCollection();
        }
    }

    @Override
    public CompletableFuture<COL> getAllAsync(final List<String> sort, final Integer page, final Integer maxResults) {
        return CompletableFuture.supplyAsync(() -> {
            final SearchBean searchBean = new SearchBean().setMaxResults(maxResults)
                    .setPage(page)
                    .setSort(sort);
            final Response response = AbstractIdentifiableService.this.getProxy()
                    .getAll(searchBean);
            try {
                if (response.getStatus() == Status.OK.getStatusCode()) {
                    return response.readEntity(AbstractIdentifiableService.this.getCollectionClass());
                } else {
                    return this.getEmptyCollection();
                }
            } finally {
                response.close();
            }
        });
    }

    @Override
    public GenericETagModifiedDateDomainObjectDecorator<TYPE> getByID(final GenericETagModifiedDateDomainObjectDecorator<TYPE> object) {
        final Response byID = this.getProxy()
                .getByID(object.getObject()
                        .getId(), object.getETag(), object.getModifiedDate());
        try {
            if (byID.getStatus() == Status.OK.getStatusCode()) {
                return new GenericETagModifiedDateDomainObjectDecorator<TYPE>(EntityTag.valueOf(byID.getHeaderString(HttpHeaders.ETAG)),
                        Instant.parse(byID.getHeaderString(HttpHeaders.LAST_MODIFIED)), byID.readEntity(this.domainClass), null);
            } else if (byID.getStatus() == Status.NOT_MODIFIED.getStatusCode()) {
                return object;
            }
            throw new WebApplicationException(byID);
        } finally {
            byID.close();
        }
    }

    @Override
    public GenericETagModifiedDateDomainObjectDecorator<TYPE> getByID(final ID id) {
        final Response byID = this.getProxy()
                .getByID(id);
        try {
            return new GenericETagModifiedDateDomainObjectDecorator<TYPE>(new EntityTag(byID.getHeaderString(HttpHeaders.ETAG)),
                    Instant.parse(byID.getHeaderString(HttpHeaders.LAST_MODIFIED)), byID.readEntity(this.domainClass), null);
        } finally {
            byID.close();
        }
    }

    @Override
    public GenericETagModifiedDateDomainObjectDecorator<TYPE> update(final GenericETagModifiedDateDomainObjectDecorator<TYPE> object) {
        final Response update = this.getProxy()
                .update(object.getObject(), object.getETag(), object.getModifiedDate());
        try {
            if (update.getStatus() == Status.OK.getStatusCode()) {
                return new GenericETagModifiedDateDomainObjectDecorator<TYPE>(EntityTag.valueOf(update.getHeaderString(HttpHeaders.ETAG)),
                        Instant.parse(update.getHeaderString(HttpHeaders.LAST_MODIFIED)), update.readEntity(this.domainClass), null);
            } else {
                throw new WebApplicationException(update);
            }
        } finally {
            update.close();
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

    protected abstract COL getEmptyCollection();

    protected Class<ID> getIdClass() {
        return this.identifierClass;
    }

    protected abstract IdentifiableClientApi<TYPE, ID> getProxy();
}
