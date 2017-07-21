package bobkubista.examples.utils.rest.utils.proxy;

import java.time.Instant;
import java.util.ArrayList;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import bobkubista.examples.utils.rest.utils.mocks.MockActiveDomainObject;
import bobkubista.examples.utils.rest.utils.mocks.MockActiveProxy;
import bobkubista.examples.utils.rest.utils.mocks.MockDomainCollection;
import bobkubista.examples.utils.rest.utils.service.GenericETagModifiedDateDomainObjectDecorator;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ClientBuilder.class)
public class AbstractGenericActiveRestProxyTest {

	final Client mockClient = Mockito.mock(Client.class);

	final Response mockResponse = Mockito.mock(Response.class);

	private final AbstractGenericActiveRestProxy<MockActiveDomainObject, MockDomainCollection> proxy = new MockActiveProxy();

	@Before
	public void start() {
		final Builder mockBuilder = Mockito.mock(Builder.class);
		Mockito.when(mockBuilder.get())
				.thenReturn(this.mockResponse);
		Mockito.when(mockBuilder.post(Matchers.any()))
				.thenReturn(this.mockResponse);
		Mockito.when(mockBuilder.put(Matchers.any()))
				.thenReturn(this.mockResponse);
		Mockito.when(mockBuilder.delete())
				.thenReturn(this.mockResponse);

		final MockDomainCollection mockDomainCollection = new MockDomainCollection();
		mockDomainCollection.getDomainCollection()
				.add(new MockActiveDomainObject(1L, "F1"));
		mockDomainCollection.getDomainCollection()
				.add(new MockActiveDomainObject(2L, "F2"));
		Mockito.when(this.mockResponse.readEntity(Long.class))
				.thenReturn(1L);
		Mockito.when(this.mockResponse.getStatusInfo())
				.thenReturn(Status.OK);
		Mockito.when(this.mockResponse.readEntity(MockDomainCollection.class))
				.thenReturn(mockDomainCollection);
		Mockito.when(this.mockResponse.readEntity(MockActiveDomainObject.class))
				.thenReturn(new MockActiveDomainObject(1L, "F1"));

		Mockito.when(mockBuilder.header(Matchers.anyString(), Matchers.any()))
				.thenReturn(mockBuilder);

		final WebTarget mockWebTarget = Mockito.mock(WebTarget.class);
		Mockito.when(mockWebTarget.path(Matchers.anyString()))
				.thenReturn(mockWebTarget);
		Mockito.when(mockWebTarget.queryParam(Matchers.anyString(), Matchers.any()))
				.thenReturn(mockWebTarget);
		Mockito.when(mockWebTarget.request(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON))
				.thenReturn(mockBuilder);

		Mockito.when(this.mockClient.target(Matchers.anyString()))
				.thenReturn(mockWebTarget);

		PowerMockito.mockStatic(ClientBuilder.class);
		PowerMockito.when(ClientBuilder.newClient())
				.thenReturn(this.mockClient);
	}

	@Test
	public void testCreate() {
		this.mockResponse();
		Mockito.when(this.mockResponse.getStatus())
				.thenReturn(201);

		final String create = this.proxy.create(new MockActiveDomainObject());
		Assert.assertNotNull(create);
		Assert.assertNotEquals("", create);

	}

	@Test
	public void testDelete() {
		this.mockResponse();

		Mockito.when(this.mockResponse.getStatus())
				.thenReturn(Status.NO_CONTENT.getStatusCode());

		Assert.assertTrue(this.proxy.delete(1L));
	}

	@Test
	public void testGetAll() {
		this.mockResponse();

		Mockito.when(this.mockResponse.getStatus())
				.thenReturn(200);
		this.mockCollectionResponse();

		final MockDomainCollection result = this.proxy.getAll(new ArrayList<>(), 0, 2);
		Assert.assertNotNull(result);
		Assert.assertEquals(2, result.getDomainCollection()
				.size());
	}

	@Test
	public void testGetAllActive() {
		Mockito.when(this.mockResponse.getStatus())
				.thenReturn(200);

		final MockDomainCollection result = this.proxy.getAll(new ArrayList<>(), null, null);
		Assert.assertNotNull(result);
		Assert.assertEquals(2, result.getDomainCollection()
				.size());
	}

	@Test
	public void testGetByFunctionalId() {
		this.mockResponse();
		Mockito.when(this.mockResponse.getStatus())
				.thenReturn(200);

		final MockActiveDomainObject result = this.proxy.getByFunctionalId("blaat");
		Assert.assertNotNull(result);
		Assert.assertEquals("F1", result.getFunctionalId());
	}

	@Test
	public void testGetByID() {
		this.mockResponse();

		Mockito.when(this.mockResponse.getStatus())
				.thenReturn(200);
		Mockito.when(this.mockResponse.getHeaderString(HttpHeaders.LAST_MODIFIED))
				.thenReturn("Wed, 1 Jan 2015 00:00:00 GMT");

		final GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject> result = this.proxy.getByID(1L);
		Assert.assertNotNull(result);
		Assert.assertEquals(new Long(1), result.getObject()
				.getId());
	}

	@Test(expected = WebApplicationException.class)
	public void testGetByIdEtagError() {
		this.mockError();
		final MockActiveDomainObject mockDomainObject = new MockActiveDomainObject();
		final GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject> object = new GenericETagModifiedDateDomainObjectDecorator<>(
				new EntityTag("tag"), Instant.now()
						.minusMillis(5),
				mockDomainObject, null);
		this.proxy.getByID(object)
				.getObject();
		Assert.fail();
	}

	@Test
	public void testGetByIdEtagModified() {
		this.mockResponse();
		Mockito.when(this.mockResponse.getStatus())
				.thenReturn(Status.OK.getStatusCode());

		final GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject> object = new GenericETagModifiedDateDomainObjectDecorator<>(
				new EntityTag("tag"), Instant.now()
						.minusMillis(5),
				new MockActiveDomainObject(), null);
		Assert.assertEquals(object.getObject(), this.proxy.getByID(object)
				.getObject());
	}

	@Test
	public void testGetByIdEtagUnModified() {
		Mockito.when(this.mockResponse.getStatus())
				.thenReturn(Status.NOT_MODIFIED.getStatusCode());
		final MockActiveDomainObject mockDomainObject = new MockActiveDomainObject();
		final GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject> object = new GenericETagModifiedDateDomainObjectDecorator<>(
				new EntityTag("tag"), Instant.now()
						.plusMillis(5),
				mockDomainObject, null);
		Assert.assertEquals(object, this.proxy.getByID(object));
	}

	@Test
	public void testGetIdByFunctionalId() {
		Mockito.when(this.mockResponse.getStatus())
				.thenReturn(200);
		Mockito.when(this.mockResponse.getStatusInfo())
				.thenReturn(Status.OK);

		final Long result = this.proxy.getIdByFunctionalId("blaat");
		Assert.assertNotNull(result);
		Assert.assertEquals(new Long(1), result);
	}

	@Test
	public void testUpdate() {
		this.mockResponse();

		Mockito.when(this.mockResponse.getStatus())
				.thenReturn(200);

		final MockActiveDomainObject result = this.proxy.update(new MockActiveDomainObject());
		Assert.assertNotNull(result);
		Assert.assertEquals(new Long(1), result.getId());
	}

	@Test(expected = WebApplicationException.class)
	public void testUpdateModified() {
		this.mockError();
		final MockActiveDomainObject mockDomainObject = new MockActiveDomainObject();
		final GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject> object = new GenericETagModifiedDateDomainObjectDecorator<>(
				new EntityTag("tag"), Instant.now()
						.minusMillis(5),
				mockDomainObject, null);
		this.proxy.update(object);
		Assert.fail();
	}

	@Test
	public void testUpdateUnModified() {
		this.mockResponse();
		Mockito.when(this.mockResponse.getStatus())
				.thenReturn(Status.OK.getStatusCode());
		Mockito.when(this.mockResponse.getHeaderString(HttpHeaders.LAST_MODIFIED))
				.thenReturn("Wed, 1 Jan 2015 00:00:00 GMT");
		final MockActiveDomainObject mockDomainObject = new MockActiveDomainObject();
		final GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject> object = new GenericETagModifiedDateDomainObjectDecorator<>(
				new EntityTag("tag"), Instant.now()
						.plusMillis(5),
				mockDomainObject, null);
		Assert.assertEquals(new Long(1), this.proxy.update(object)
				.getObject()
				.getId());
	}

	protected void mockError() {
		final StatusType mockStatusType = Mockito.mock(StatusType.class);
		Mockito.when(mockStatusType.getReasonPhrase())
				.thenReturn("blaat");
		Mockito.when(mockStatusType.getFamily())
				.thenReturn(Family.CLIENT_ERROR);
		Mockito.when(this.mockResponse.getStatusInfo())
				.thenReturn(mockStatusType);
	}

	protected void mockResponse() {
		Mockito.when(this.mockResponse.getHeaderString(HttpHeaders.LOCATION))
				.thenReturn("location");
		Mockito.when(this.mockResponse.getHeaderString(HttpHeaders.ETAG))
				.thenReturn(new EntityTag("tag").toString());
		Mockito.when(this.mockResponse.getHeaderString(HttpHeaders.LAST_MODIFIED))
				.thenReturn("Wed, 1 Jan 2015 00:00:00 GMT");
		Mockito.when(this.mockResponse.getStatusInfo())
				.thenReturn(Status.OK);

		Mockito.when(this.mockResponse.readEntity(MockActiveDomainObject.class))
				.thenReturn(new MockActiveDomainObject(1L, "F1"));
	}

	private void mockCollectionResponse() {
		final MockDomainCollection mockDomainCollection = new MockDomainCollection();
		mockDomainCollection.getDomainCollection()
				.add(new MockActiveDomainObject(1L, "F1"));
		mockDomainCollection.getDomainCollection()
				.add(new MockActiveDomainObject(2L, "F2"));

		Mockito.when(this.mockResponse.readEntity(MockDomainCollection.class))
				.thenReturn(mockDomainCollection);
	}

}
