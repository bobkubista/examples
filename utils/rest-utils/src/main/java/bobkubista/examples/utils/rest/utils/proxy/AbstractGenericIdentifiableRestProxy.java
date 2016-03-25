/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.proxy;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.example.utils.property.ServerProperties;
import bobkubista.examples.utils.domain.model.api.ApiConstants;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject;
import bobkubista.examples.utils.rest.utils.service.GenericETagModifiedDateDomainObjectDecorator;
import bobkubista.examples.utils.rest.utils.service.IdentifiableService;

/**
 *
 * @author Bob
 *
 * @param <TYPE>
 *            {@link AbstractGenericIdentifiableDomainObject}
 * @param <ID>
 *            {@link Serializable}
 * @param <COL>
 *            {@link AbstractGenericDomainObjectCollection}
 */
public abstract class AbstractGenericIdentifiableRestProxy<TYPE extends AbstractGenericIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends AbstractRestProxy implements IdentifiableService<TYPE, ID, COL> {

    private static final int COLLECTION_CLASS_TYPE_ARGUMENT_NUMBER = 2;

    private static final int DOMAINOBJECT_CLASS_TYPE_ARGUMENT_NUMBER = 0;

    private static final int IDENTIFIER_CLASS_TYPE_ARGUMENT_NUMBER = 1;

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenericIdentifiableRestProxy.class);

    private final Class<COL> collectionClass;

    private final Class<TYPE> domainClass;

    private final Class<ID> identifierClass;

    /**
     * Constructor
     */
    @SuppressWarnings("unchecked")
    public AbstractGenericIdentifiableRestProxy() {
        final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        this.domainClass = (Class<TYPE>) genericSuperclass.getActualTypeArguments()[DOMAINOBJECT_CLASS_TYPE_ARGUMENT_NUMBER];
        this.identifierClass = (Class<ID>) genericSuperclass.getActualTypeArguments()[IDENTIFIER_CLASS_TYPE_ARGUMENT_NUMBER];
        this.collectionClass = (Class<COL>) genericSuperclass.getActualTypeArguments()[COLLECTION_CLASS_TYPE_ARGUMENT_NUMBER];
    }

    @Override
    public boolean create(final TYPE object) {
        final Response response = this.getRequest(this.getServiceWithPaths())
                .post(Entity.entity(object, MediaType.APPLICATION_JSON));
        try {
            return response.getHeaderString(HttpHeaders.LOCATION) != null && response.getStatus() == Status.CREATED.getStatusCode();
        } finally {
            response.close();
        }
    }

    @Override
    public boolean delete(final ID id) {
        final Response response = this.getRequest(this.getServiceWithPaths(id.toString()))
                .delete();
        try {
            return response.getStatus() == Status.NO_CONTENT.getStatusCode();
        } finally {
            response.close();
        }
    }

    @Override
    public COL getAll(final List<String> sort, final Integer page, final Integer maxResults) {
        try {
            final Long serverTimeout = (Long) ServerProperties.getProperies()
                    .getOrDefault("server.timeout", 1L);
            return this.getAllAsync(sort, page, maxResults)
                    .get(serverTimeout, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            LOGGER.warn(e.getMessage(), e);
            return this.getAllFallback();
        }
    }

    @Override
    public CompletableFuture<COL> getAllAsync(final List<String> sort, final Integer page, final Integer maxResults) {
        return CompletableFuture.supplyAsync(() -> {
            final Map<String, Object> params = this.getQueryparameters(sort, page, maxResults);
            final Response response = this.getRequest(this.getServiceWithQueryParams(params))
                    .get();
            try {
                if (response.getStatus() == Status.OK.getStatusCode()) {
                    return response.readEntity(this.getCollectionClass());
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
        final Date modified = Date.from(object.getModifiedDate());
        final Response byID = this.getRequest(this.getServiceWithPaths(object.getObject()
                .getId()
                .toString()))
                .header(HttpHeaders.ETAG, object.getETag())
                .header(HttpHeaders.LAST_MODIFIED, modified)
                .get();
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
        final Response byID = this.getRequest(this.getServiceWithPaths(id.toString()))
                .get();
        try {
            return new GenericETagModifiedDateDomainObjectDecorator<TYPE>(new EntityTag(byID.getHeaderString(HttpHeaders.ETAG)),
                    Instant.parse(byID.getHeaderString(HttpHeaders.LAST_MODIFIED)), byID.readEntity(this.domainClass), null);
        } finally {
            byID.close();
        }
    }

    @Override
    public GenericETagModifiedDateDomainObjectDecorator<TYPE> update(final GenericETagModifiedDateDomainObjectDecorator<TYPE> object) {
        final Response update = this.getRequest(this.getServiceWithPaths())
                .header(HttpHeaders.ETAG, object.getETag())
                .header(HttpHeaders.LAST_MODIFIED, Date.from(object.getModifiedDate()))
                .put(Entity.entity(object.getObject(), MediaType.APPLICATION_JSON));
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
        return this.getRequest(this.getServiceWithPaths())
                .put(Entity.entity(object, MediaType.APPLICATION_JSON))
                .readEntity(this.domainClass);
    }

    /**
     * Fallback method when something goes wrong. Default throws a
     * {@link WebApplicationException} with {@link Status#GATEWAY_TIMEOUT}
     * 
     * @return
     */
    protected COL getAllFallback() {
        throw new WebApplicationException(Status.GATEWAY_TIMEOUT);
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

    protected Map<String, Object> getQueryparameters(final List<String> sort, final Integer page, final Integer maxResults) {
        final Map<String, Object> params = new HashMap<>();
        if (CollectionUtils.isNotEmpty(sort)) {
            params.put(ApiConstants.SORT, sort);
        }
        if (page != null) {
            params.put(ApiConstants.PAGE, page);
        }
        if (maxResults != null) {
            params.put(ApiConstants.MAX, maxResults);
        }
        return params;
    }
}
