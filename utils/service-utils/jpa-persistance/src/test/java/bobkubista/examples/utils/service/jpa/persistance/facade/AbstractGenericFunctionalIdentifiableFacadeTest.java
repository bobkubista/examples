package bobkubista.examples.utils.service.jpa.persistance.facade;

import java.sql.Date;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import bobkubista.examples.utils.service.jpa.persistance.mocks.MockDomain;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockDomainCollection;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockEntity;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockFacade;

public class AbstractGenericFunctionalIdentifiableFacadeTest {

	AbstractGenericFunctionalIdentifiableFacade<MockDomain, MockEntity, MockDomainCollection> facade = new MockFacade();

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.facade. AbstractGenericFunctionalIdentifiableFacade#getByFunctionalId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetByFunctionalId() {
		final Request request = Mockito.mock(Request.class);
		Mockito.when(request.evaluatePreconditions(Matchers.any(Date.class)))
				.thenReturn(null);
		final Response result = this.facade.getByFunctionalId("Bla", request);
		Assert.assertNotNull(result);
		Assert.assertEquals(200, result.getStatus());
		Assert.assertNotNull(result.getEntity());
	}

	/**
	 *
	 */
	@Test(expected = NotFoundException.class)
	public void testGetByFunctionalIdNull() {
		final Request request = Mockito.mock(Request.class);
		Mockito.when(request.evaluatePreconditions(Matchers.any(Date.class)))
				.thenReturn(null);
		this.facade.getByFunctionalId("", request);
		Assert.fail();
	}

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.facade. AbstractGenericFunctionalIdentifiableFacade#getByFunctionalId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetByFunctionalIdShouldNotGet() {
		final Request request = Mockito.mock(Request.class);
		Mockito.when(request.evaluatePreconditions(Matchers.any(Date.class)))
				.thenReturn(Response.notModified());
		final Response result = this.facade.getByFunctionalId("Bla", request);
		Assert.assertNotNull(result);
		Assert.assertEquals(304, result.getStatus());
	}

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.facade. AbstractGenericFunctionalIdentifiableFacade#getIdByFunctionalId(java.lang.String) }
	 * .
	 */
	@Test
	public void testGetIdByFunctionalId() {
		final Response result = this.facade.getIdByFunctionalId("Bla");
		Assert.assertNotNull(result);
		Assert.assertEquals(200, result.getStatus());
		Assert.assertNotNull(result.getEntity());
	}

	@Test(expected = NotFoundException.class)
	public void testGetIdByFunctionalIdNull() {
		this.facade.getIdByFunctionalId("");
		Assert.fail();
	}

}
