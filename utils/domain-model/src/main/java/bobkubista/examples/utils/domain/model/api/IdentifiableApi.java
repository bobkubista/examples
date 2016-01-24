/**
 *
 */
package bobkubista.examples.utils.domain.model.api;

import java.io.Serializable;
import java.util.Collection;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.NotImplementedException;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObject;

/**
 * An interface discribing the rest paths, accepted {@link MediaType} and http
 * request methodes. This facade should be used by all applications, except
 * admin applications.
 *
 * The interface extends the {@link IdentifiableApi} which describes the input
 * and return types possible. For Rest services, we need to return only
 * {@link Response} types as the single objects and collection of objects. The
 * identifier stays the same.
 *
 * @param <DMO>
 *            The {@link DomainObject}
 * @param <ID>
 *            The {@link Serializable} identifier
 * @author bkubista
 *
 */
public interface IdentifiableApi<DMO extends DomainObject, ID extends Serializable> {

    /**
     * @param entity
     *            array of entities
     * @return a response with Status.NOT_IMPLEMENTED as status
     */
    static Response buildNotImplementedResponse(final Object... entity) {
        return Response.status(Status.NOT_IMPLEMENTED).entity(entity).build();
    }

    /**
     * Create the object of {@link DomainObject} type
     *
     * @param object
     *            the object to create
     * @return {@link Response}
     */
    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    default Response create(final DMO object) {
        return IdentifiableApi.buildNotImplementedResponse(object);
    }

    /**
     * delete the specific object of {@link DomainObject}
     *
     * @param identifier
     *            the identfier
     * @return Response. Default is {@link NotImplementedException} @return
     *         {@link Response}
     */
    @DELETE
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    default Response delete(@PathParam("id") final ID identifier) {
        return IdentifiableApi.buildNotImplementedResponse(identifier);
    }

    /**
     * get all known {@link DomainObject} of that type. Delegate this methode to
     * {@link IdentifiableApi#getAll(Integer, Integer)} and use set the sort
     * field as queryparameters. This methode is used to construct the
     * {@link GET}.
     *
     * @param search
     *            {@link SearchBean} filled with criteria, sorts and limits
     *
     * @return a {@link Collection} of {@link DomainObject} of the same type
     */
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    default Response getAll(@BeanParam final SearchBean search) {
        return IdentifiableApi.buildNotImplementedResponse(search);
    }

    /**
     * get the {@link DomainObject}
     *
     * @param identifier
     *            the identfier @return the {@link DomainObject} @return
     *            {@link Response}
     */
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("{id}")
    default Response getByID(@PathParam("id") final ID identifier) {
        return IdentifiableApi.buildNotImplementedResponse(identifier);
    }

    /**
     * update the object of {@link DomainObject}
     *
     * @param object
     *            the object to update @return the updated object @return
     *            {@link Response}
     */
    @PUT
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    default Response update(final DMO object) {
        return IdentifiableApi.buildNotImplementedResponse(object);
    }
}
