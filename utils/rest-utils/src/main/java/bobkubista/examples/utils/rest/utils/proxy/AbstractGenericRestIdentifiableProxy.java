package bobkubista.examples.utils.rest.utils.proxy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.collections.CollectionUtils;

import bobkubista.examples.utils.domain.model.api.IdentifiableApi;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject;

/**
 * An abstract service to get {@link AbstractGenericIdentifiableDomainObject}s
 * from cache
 *
 * @author bkubista
 * @param <ID>
 *            the identifier of the
 *            {@link AbstractGenericIdentifiableDomainObject}
 * @param <TYPE>
 *            the type of {@link AbstractGenericIdentifiableDomainObject}
 */
public abstract class AbstractGenericRestIdentifiableProxy<TYPE extends AbstractGenericIdentifiableDomainObject<ID>, ID extends Serializable> extends AbstractRestProxy
        implements IdentifiableApi<TYPE, ID> {

    /**
     * Create the object of <code>TYPE</code>
     *
     * @param object
     *            the object to be created @return the created object
     */
    @Override
    public Response create(final TYPE object) {
        return this.getRequest(this.getServiceWithPaths()).post(Entity.entity(object, MediaType.APPLICATION_JSON));
    }

    /**
     * Deleting is not possible, override this methode
     *
     * @param identifier
     *            the <code>ID</code> to delete
     */
    @Override
    public Response delete(final ID identifier) {
        return this.getRequest(this.getServiceWithPaths(identifier.toString())).delete();
    }

    @Override
    public Response getAll(final List<String> sort, final Integer page, final Integer maxResults) {
        final Map<String, Object> params = new HashMap<>();
        if (CollectionUtils.isNotEmpty(sort)) {
            params.put(SORT, sort);
        }
        if (page != null) {
            params.put(PAGE, page);
        }
        if (maxResults != null) {
            params.put(MAX, maxResults);
        }
        return this.getRequest(this.getServiceWithQueryParams(params)).get();
    }

    @Override
    public Response getByID(final ID identifier) {
        return this.getRequest(this.getServiceWithPaths(identifier.toString())).get();
    }

    /**
     * Updating is not possible, override this methode
     *
     * @param object
     *            the <code>TYPE</code> to update @return the updated object
     */
    @Override
    public Response update(final TYPE object) {
        return this.getRequest(this.getServiceWithPaths()).put(Entity.entity(object, MediaType.APPLICATION_JSON));
    }

}
