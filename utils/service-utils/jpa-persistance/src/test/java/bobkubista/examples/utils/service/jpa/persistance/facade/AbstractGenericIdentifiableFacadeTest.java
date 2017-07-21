package bobkubista.examples.utils.service.jpa.persistance.facade;

import java.util.Date;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockDomain;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockDomainCollection;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockEntity;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockFacade;

public class AbstractGenericIdentifiableFacadeTest {

	AbstractGenericIdentifiableFacade<MockDomain, MockDomainCollection, MockEntity> facade = new MockFacade();

	@Test
	public void testCreate() {
		final Response result = this.facade.create(new MockDomain());
		Assert.assertNotNull(result);
		Assert.assertEquals(201, result.getStatus());
		Assert.assertNotNull(result.getLocation());
	}

	@Test(expected = NullPointerException.class)
	public void testCreateNull() {
		this.facade.create(null);
	}

	@Test
	public void testDelete() {
		final Response result = this.facade.delete(1L);
		Assert.assertNotNull(result);
		Assert.assertEquals(204, result.getStatus());
	}

	@Test(expected = NotFoundException.class)
	public void testDeleteNotFound() {
		this.facade.delete(2L);
		Assert.fail();
	}

	@Test
	public void testGetAll() {
		final SearchBean search = new SearchBean().setMaxResults(2)
				.setPage(0);
		final Response result = this.facade.getAll(search);
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getEntity());
	}

	@Test(expected = ServerErrorException.class)
	public void testGetAllException() {
		this.facade.getAll(new SearchBean().setPage(4)
				.setMaxResults(14));
	}

	@Test
	public void testGetAllSecondPageWithMoreResults() {
		final SearchBean search = new SearchBean().setMaxResults(1)
				.setPage(1);
		final Response result = this.facade.getAll(search);
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getEntity());
	}

	@Test(expected = ServiceUnavailableException.class)
	public void testGetAllTimedOut() {
		this.facade.getAll(new SearchBean().setPage(3));
	}

	@Test
	public void testGetByID() {
		final Request request = Mockito.mock(Request.class);
		Mockito.when(request.evaluatePreconditions(Matchers.any(Date.class)))
				.thenReturn(null);
		final Response result = this.facade.getByID(1L, request);
		Assert.assertNotNull(result);
		Assert.assertEquals(Status.OK.getStatusCode(), result.getStatus());
		Assert.assertNotNull(result.getEntity());
	}

	@Test
	public void testGetByIDNotUpdated() {
		final Request request = Mockito.mock(Request.class);
		Mockito.when(request.evaluatePreconditions(Matchers.any(Date.class)))
				.thenReturn(Response.notModified());
		final Response result = this.facade.getByID(1L, request);
		Assert.assertNotNull(result);
		Assert.assertEquals(Status.NOT_MODIFIED.getStatusCode(), result.getStatus());
	}

	@Test(expected = NotFoundException.class)
	public void testGetByIDNull() {
		final Request request = Mockito.mock(Request.class);
		Mockito.when(request.evaluatePreconditions(Matchers.any(Date.class)))
				.thenReturn(null);
		this.facade.getByID(2L, request);
		Assert.fail();
	}

	@Test
	public void testUpdate() {
		final MockDomain object = new MockDomain();
		final Request request = Mockito.mock(Request.class);
		Mockito.when(request.evaluatePreconditions(Matchers.any(Date.class)))
				.thenReturn(null);
		final Response result = this.facade.update(object, request);

		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getEntity());
	}

	@Test
	public void testUpdateShouldNotUpdate() {
		final MockDomain object = new MockDomain();
		final Request request = Mockito.mock(Request.class);
		Mockito.when(request.evaluatePreconditions(Matchers.any(Date.class)))
				.thenReturn(Response.status(412));
		final Response result = this.facade.update(object, request);

		Assert.assertNotNull(result);
		Assert.assertEquals(412, result.getStatus());
	}
}
