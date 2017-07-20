/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.proxy;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
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
public abstract class AbstractGenericIdentifiableRestProxy<TYPE extends AbstractGenericIdentifiableDomainObject, COL extends AbstractGenericDomainObjectCollection<TYPE>>
		extends AbstractRestProxy implements IdentifiableService<TYPE, COL> {

	private static final int COLLECTION_CLASS_TYPE_ARGUMENT_NUMBER = 1;

	private static final int DOMAINOBJECT_CLASS_TYPE_ARGUMENT_NUMBER = 0;

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenericIdentifiableRestProxy.class);

	protected static Map<String, Object> getQueryparameters(final List<String> sort, final Integer page,
			final Integer maxResults) {
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

	private final Class<COL> collectionClass;

	private final Class<TYPE> domainClass;

	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public AbstractGenericIdentifiableRestProxy() {
		final ParameterizedType genericSuperclass = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		this.domainClass = (Class<TYPE>) genericSuperclass
				.getActualTypeArguments()[DOMAINOBJECT_CLASS_TYPE_ARGUMENT_NUMBER];
		this.collectionClass = (Class<COL>) genericSuperclass
				.getActualTypeArguments()[COLLECTION_CLASS_TYPE_ARGUMENT_NUMBER];
	}

	@Override
	public String create(final TYPE object) {
		final Function<TYPE, Response> webServiceCall = this::doCreateCall;
		final Predicate<Response> statusChecker = this::checkCreateStatus;
		final Function<Response, String> responseProcessor = this::processCreateResponse;
		final Supplier<String> fallback = () -> StringUtils.EMPTY;

		return AbstractRestProxy.call(webServiceCall, statusChecker, responseProcessor, fallback, object);
	}

	@Override
	public boolean delete(final Long id) {
		final Function<Response, Boolean> responseProcessor = this::isResponseStatusNoContent;
		final Function<Long, Response> webServiceCall = this::doDeleteCall;

		return AbstractRestProxy.call(webServiceCall, responseProcessor, id);
	}

	@Override
	public COL getAll(final List<String> sort, final Integer page, final Integer maxResults) {
		try {
			final Long serverTimeout = ServerProperties.get()
					.getLong("server.timeout", 1L);
			return this.getAllAsync(sort, page, maxResults)
					.get(serverTimeout, TimeUnit.SECONDS);
		} catch (InterruptedException | TimeoutException e) {
			LOGGER.warn(e.getMessage(), e);
			return this.getAllFallback();
		} catch (final ExecutionException e) {
			LOGGER.warn(e.getMessage(), e);
			return this.getAllFallback(e.getCause());
		}
	}

	@Override
	public CompletableFuture<COL> getAllAsync(final List<String> sort, final Integer page, final Integer maxResults) {
		return CompletableFuture.supplyAsync(() -> {
			final Map<String, Object> params = AbstractGenericIdentifiableRestProxy.getQueryparameters(sort, page,
					maxResults);

			return call(this::doGetCall, this::processResponse, params);
		});
	}

	@Override
	public GenericETagModifiedDateDomainObjectDecorator<TYPE> getByID(
			final GenericETagModifiedDateDomainObjectDecorator<TYPE> object) {
		final Function<Response, GenericETagModifiedDateDomainObjectDecorator<TYPE>> responseProcessor = byID -> processResponse(
				object, byID);
		final Function<GenericETagModifiedDateDomainObjectDecorator<TYPE>, Response> webServiceCall = this::doGetCall;

		return call(webServiceCall, responseProcessor, object);

	}

	@Override
	public GenericETagModifiedDateDomainObjectDecorator<TYPE> getByID(final Long id) {
		return call(this::doGetCall, this::processGetResponse, id);
	}

	@Override
	public GenericETagModifiedDateDomainObjectDecorator<TYPE> update(
			final GenericETagModifiedDateDomainObjectDecorator<TYPE> object) {
		return call(this::doUpdateCall, this::processUpdateResponse, object);
	}

	@Override
	public TYPE update(final TYPE object) {
		return call(this::doUpdateCall, this::readEntity, object);
	}

	/**
	 * Fallback method when something goes wrong. Default throws a
	 * {@link WebApplicationException} with {@link Status#GATEWAY_TIMEOUT}
	 *
	 * @return throws a {@link WebApplicationException} with
	 *         {@link Status#GATEWAY_TIMEOUT}
	 */
	protected COL getAllFallback() {
		throw new WebApplicationException(Status.GATEWAY_TIMEOUT);
	}

	/**
	 * Fallback method when something goes wrong. Default throws a
	 * {@link WebApplicationException} with {@link Status#GATEWAY_TIMEOUT}
	 *
	 * @param cause
	 *            the cause of going to the fallback
	 *
	 * @return throws a {@link WebApplicationException} with the cause
	 */
	protected COL getAllFallback(final Throwable cause) {
		throw new WebApplicationException(cause);
	}

	protected Class<COL> getCollectionClass() {
		return this.collectionClass;
	}

	protected Class<TYPE> getDomainClass() {
		return this.domainClass;
	}

	protected abstract COL getEmptyCollection();

	private boolean checkCreateStatus(Response response) {
		return response.getStatus() == Status.CREATED.getStatusCode();
	}

	private Response doCreateCall(TYPE object) {
		return this.getRequest(this.getServiceWithPaths())
				.post(Entity.entity(object, MediaType.APPLICATION_JSON));
	}

	private Response doDeleteCall(Long id) {
		return this.getRequest(this.getServiceWithPaths(id.toString()))
				.delete();
	}

	private Response doGetCall(GenericETagModifiedDateDomainObjectDecorator<TYPE> object) {
		return this.getRequest(this.getServiceWithPaths(object.getObject()
				.getId()
				.toString()))
				.header(HttpHeaders.ETAG, object.getETag())
				.header(HttpHeaders.LAST_MODIFIED, Date.from(object.getModifiedDate()))
				.get();
	}

	private Response doGetCall(Long id) {
		return this.getRequest(this.getServiceWithPaths(id.toString()))
				.get();
	}

	private Response doGetCall(Map<String, Object> params) {
		return this.getRequest(this.getServiceWithQueryParams(params))
				.get();
	}

	private Response doUpdateCall(GenericETagModifiedDateDomainObjectDecorator<TYPE> object) {
		return this.getRequest(this.getServiceWithPaths())
				.header(HttpHeaders.ETAG, object.getETag())
				.header(HttpHeaders.LAST_MODIFIED, Date.from(object.getModifiedDate()))
				.put(Entity.json(object.getObject()));
	}

	private Response doUpdateCall(TYPE object) {
		return this.getRequest(this.getServiceWithPaths())
				.put(Entity.json(object));
	}

	private boolean isResponseStatusNoContent(Response response) {
		return response.getStatus() == Status.NO_CONTENT.getStatusCode();
	}

	private boolean isResponseStatusNotModified(Response response) {
		return response.getStatus() == Status.NOT_MODIFIED.getStatusCode();
	}

	private boolean isResponseStatusOk(Response response) {
		return response.getStatus() == Status.OK.getStatusCode();
	}

	private String processCreateResponse(Response response) {
		return response.getHeaderString(HttpHeaders.LOCATION);
	}

	private GenericETagModifiedDateDomainObjectDecorator<TYPE> processGetResponse(Response response) {
		return new GenericETagModifiedDateDomainObjectDecorator<>(
				EntityTag.valueOf(response.getHeaderString(HttpHeaders.ETAG)),
				DateUtils.parseDate(response.getHeaderString(HttpHeaders.LAST_MODIFIED))
						.toInstant(),
				readEntity(response), null);
	}

	private GenericETagModifiedDateDomainObjectDecorator<TYPE> processResponse(
			final GenericETagModifiedDateDomainObjectDecorator<TYPE> object, Response response) {
		if (isResponseStatusOk(response)) {
			return processGetResponse(response);
		} else if (isResponseStatusNotModified(response)) {
			return object;
		} else {
			throw new WebApplicationException(response);
		}
	}

	private COL processResponse(Response response) {
		if (isResponseStatusOk(response)) {
			return response.readEntity(this.getCollectionClass());
		} else {
			return this.getEmptyCollection();
		}
	}

	private GenericETagModifiedDateDomainObjectDecorator<TYPE> processUpdateResponse(Response update) {
		if (isResponseStatusOk(update)) {
			return processGetResponse(update);
		} else {
			throw new WebApplicationException(update);
		}
	}

	private TYPE readEntity(Response response) {
		return response.readEntity(this.domainClass);
	}
}
