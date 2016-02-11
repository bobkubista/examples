/**
 *
 */
package bobkubista.examples.utils.domain.model.api;

import java.io.Serializable;
import java.util.Collection;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.NotImplementedException;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObject;

/**
 * An interface discribing the rest paths, accepted {@link MediaType} and http
 * request methodes. This facade should be used by all applications, except
 * admin applications.
 *
 * The interface extends the {@link IdentifiableServerApi} which describes the
 * input and return types possible. For Rest services, we need to return only
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
public interface IdentifiableServerApi<DMO extends DomainObject, ID extends Serializable> {

    /**
     * @param entity
     *            array of entities
     * @return a response with Status.NOT_IMPLEMENTED as status
     */
    static Response buildMethodNotAllowedResponse(final Object... entity) {
        return Response.status(Status.METHOD_NOT_ALLOWED)
                .entity(entity)
                .build();
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
    default Response create(@Valid final DMO object) {
        return IdentifiableServerApi.buildMethodNotAllowedResponse(object);
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
    default Response delete(@Valid @PathParam("id") final ID identifier) {
        return IdentifiableServerApi.buildMethodNotAllowedResponse(identifier);
    }

    /**
     * get all known {@link DomainObject} of that type. Delegate this methode to
     * {@link IdentifiableServerApi#getAll(Integer, Integer)} and use set the
     * sort field as queryparameters. This methode is used to construct the
     * {@link GET}.
     *
     * @param search
     *            {@link SearchBean} filled with criteria, sorts and limits
     *
     * @return a {@link Collection} of {@link DomainObject} of the same type
     */
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    default Response getAll(@Valid @BeanParam final SearchBean search) {
        return IdentifiableServerApi.buildMethodNotAllowedResponse(search);
    }

    /**
     * Conditional get the {@link DomainObject}
     *
     * @param identifier
     *            the identfier
     * @param request
     *            the request, to do a conditional {@link GET}
     * @return {@link Response}, 200 if if-Modified-since header is not filled
     *         in or the object has been modified since. 304 in the header is
     *         filled in and the object isn't modified
     */
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("{id}")
    default Response getByID(@Valid @PathParam("id") final ID identifier, @Context final Request request) {
        return IdentifiableServerApi.buildMethodNotAllowedResponse(identifier, request);
    }

    /**
     * Conditional update the object of {@link DomainObject}.
     *
     * @param object
     *            the object to update
     * @param request
     *            the request, to do a conditional {@link PUT}
     * @return {@link Response}, 200 if modified, 412 if the object has been
     *         modified since and the object has to be refreshed by the client
     *         first
     */
    @PUT
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    default Response update(@Valid final DMO object, @Context final Request request) {
        return IdentifiableServerApi.buildMethodNotAllowedResponse(object, request);
    }
}
