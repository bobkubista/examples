/**
 *
 */
package bobkubista.examples.utils.domain.model.api;

import java.time.Instant;
import java.util.Collection;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.EntityTag;
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
 * The interface extends the {@link IdentifiableClientApi} which describes the
 * input and return types possible. For Rest services, we need to return only
 * {@link Response} types as the single objects and collection of objects. The
 * identifier stays the same.
 *
 * @param <DMO>
 *            The {@link DomainObject}
 * @author bkubista
 *
 */
public interface IdentifiableClientApi<DMO extends DomainObject> {

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
	default Response create(@Valid final DMO object) {
		return IdentifiableClientApi.buildMethodNotAllowedResponse(object);
	}

	/**
	 * delete the specific object of {@link DomainObject}
	 *
	 * @param identifier
	 *            the identfier
	 * @return Response. Default is {@link NotImplementedException} @return
	 *         {@link Response}
	 */
	default Response delete(@Valid @PathParam("id") final Long identifier) {
		return IdentifiableClientApi.buildMethodNotAllowedResponse(identifier);
	}

	/**
	 * get all known {@link DomainObject} of that type. Delegate this methode to
	 * {@link IdentifiableClientApi#getAll(SearchBean)} and use set the sort
	 * field as queryparameters. This methode is used to construct the
	 * {@link GET}.
	 *
	 * @param search
	 *            {@link SearchBean} filled with criteria, sorts and limits
	 *
	 * @return a {@link Collection} of {@link DomainObject} of the same type
	 */
	default Response getAll(@Valid @BeanParam final SearchBean search) {
		return IdentifiableClientApi.buildMethodNotAllowedResponse(search);
	}

	/**
	 * get the {@link DomainObject}
	 *
	 * @param identifier
	 *            the identfier
	 * @return {@link Response}
	 */
	default Response getByID(@Valid @PathParam("id") final Long identifier) {
		return IdentifiableClientApi.buildMethodNotAllowedResponse(identifier);
	}

	/**
	 * get the {@link DomainObject}
	 *
	 * @param id
	 *            the identfier
	 * @param eTag
	 *            {@link EntityTag}
	 * @param modifiedDate
	 *            {@link Instant}
	 * @return {@link Response}
	 */
	default Response getByID(final Long id, final EntityTag eTag, final Instant modifiedDate) {
		return IdentifiableClientApi.buildMethodNotAllowedResponse(id, eTag, modifiedDate);
	}

	/**
	 * update the object of {@link DomainObject}
	 *
	 * @param object
	 *            the object to update
	 * @return {@link Response}
	 */
	default Response update(@Valid final DMO object) {
		return IdentifiableClientApi.buildMethodNotAllowedResponse(object);
	}

	/**
	 * update the object of {@link DomainObject}
	 *
	 * @param object
	 *            the object to update
	 * @param eTag
	 *            {@link EntityTag}
	 * @param modifiedDate
	 *            {@link Instant}
	 * @return {@link Response}
	 */
	default Response update(final DMO object, final EntityTag eTag, final Instant modifiedDate) {
		return IdentifiableClientApi.buildMethodNotAllowedResponse(object, eTag, modifiedDate);
	}
}
