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

    protected static Map<String, Object> getQueryparameters(final List<String> sort, final Integer page, final Integer maxResults) {
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

    @Override
    public boolean create(final TYPE object) {
        return AbstractRestProxy.call(t -> this.getRequest(this.getServiceWithPaths())
                .post(Entity.entity(t, MediaType.APPLICATION_JSON)),
                response -> response.getHeaderString(HttpHeaders.LOCATION) != null && response.getStatus() == Status.CREATED.getStatusCode(), object);
    }

    @Override
    public boolean delete(final ID id) {
        return AbstractRestProxy.call(t -> this.getRequest(this.getServiceWithPaths(t.toString()))
                .delete(), response -> response.getStatus() == Status.NO_CONTENT.getStatusCode(), id);
    }

    @Override
    public COL getAll(final List<String> sort, final Integer page, final Integer maxResults) {
        try {
            final Long serverTimeout = (Long) ServerProperties.getProperies()
                    .getOrDefault("server.timeout", 1L);
            return this.getAllAsync(sort, page, maxResults)
                    .get(serverTimeout, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            LOGGER.warn(e.getMessage(), e.getCause());
            return this.getAllFallback();
        }
    }

    @Override
    public CompletableFuture<COL> getAllAsync(final List<String> sort, final Integer page, final Integer maxResults) {
        return CompletableFuture.supplyAsync(() -> {
            final Map<String, Object> params = AbstractGenericIdentifiableRestProxy.getQueryparameters(sort, page, maxResults);
            return call(t -> this.getRequest(this.getServiceWithQueryParams(t))
                    .get(), response -> {
                if (response.getStatus() == Status.OK.getStatusCode()) {
                    return response.readEntity(this.getCollectionClass());
                } else {
                    return this.getEmptyCollection();
                }
            } , params);
        });
    }

    @Override
    public GenericETagModifiedDateDomainObjectDecorator<TYPE> getByID(final GenericETagModifiedDateDomainObjectDecorator<TYPE> object) {
        return call(t -> {
            return this.getRequest(this.getServiceWithPaths(object.getObject()
                    .getId()
                    .toString()))
                    .header(HttpHeaders.ETAG, object.getETag())
                    .header(HttpHeaders.LAST_MODIFIED, Date.from(object.getModifiedDate()))
                    .get();
        } , byID -> {
            if (byID.getStatus() == Status.OK.getStatusCode()) {
                return new GenericETagModifiedDateDomainObjectDecorator<TYPE>(EntityTag.valueOf(byID.getHeaderString(HttpHeaders.ETAG)),
                        Instant.parse(byID.getHeaderString(HttpHeaders.LAST_MODIFIED)), byID.readEntity(this.domainClass), null);
            } else if (byID.getStatus() == Status.NOT_MODIFIED.getStatusCode()) {
                return object;
            }
            throw new WebApplicationException(byID);
        } , object);

    }

    @Override
    public GenericETagModifiedDateDomainObjectDecorator<TYPE> getByID(final ID id) {
        return call(t -> this.getRequest(this.getServiceWithPaths(id.toString()))
                .get(),
                byID -> new GenericETagModifiedDateDomainObjectDecorator<TYPE>(new EntityTag(byID.getHeaderString(HttpHeaders.ETAG)),
                        Instant.parse(byID.getHeaderString(HttpHeaders.LAST_MODIFIED)), byID.readEntity(this.domainClass), null),
                id);
    }

    @Override
    public GenericETagModifiedDateDomainObjectDecorator<TYPE> update(final GenericETagModifiedDateDomainObjectDecorator<TYPE> object) {
        return call(t -> this.getRequest(this.getServiceWithPaths())
                .header(HttpHeaders.ETAG, t.getETag())
                .header(HttpHeaders.LAST_MODIFIED, Date.from(t.getModifiedDate()))
                .put(Entity.entity(t.getObject(), MediaType.APPLICATION_JSON)), update -> {
                    if (update.getStatus() == Status.OK.getStatusCode()) {
                        return new GenericETagModifiedDateDomainObjectDecorator<TYPE>(EntityTag.valueOf(update.getHeaderString(HttpHeaders.ETAG)),
                                Instant.parse(update.getHeaderString(HttpHeaders.LAST_MODIFIED)), update.readEntity(this.domainClass), null);
                    } else {
                        throw new WebApplicationException(update);
                    }
                } , object);
    }

    @Override
    public TYPE update(final TYPE object) {
        return call(t -> this.getRequest(this.getServiceWithPaths())
                .put(Entity.entity(t, MediaType.APPLICATION_JSON)), t -> t.readEntity(this.domainClass), object);
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
}
