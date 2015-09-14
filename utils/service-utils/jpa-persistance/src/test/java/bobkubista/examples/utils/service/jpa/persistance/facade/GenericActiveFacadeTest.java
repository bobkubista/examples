/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.facade;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import bobkubista.examples.utils.service.jpa.persistance.mocks.MockDomain;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockFacade;

/**
 * @author Bob Kubista
 *
 */
public class GenericActiveFacadeTest {

	MockFacade facade = new MockFacade();

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.facade. GenericIdentifiableFacade#create(bobkubista.examples.utils.domain.model. domainmodel.identification.DomainObject)}
	 * .
	 */
	@Test
	public void testCreate() {
		final Response result = this.facade.create(new MockDomain());
		Assert.assertNotNull(result);
		Assert.assertEquals(201, result.getStatus());
	}

	@Test(expected = NullPointerException.class)
	public void testCreateNull() {
		this.facade.create(null);
	}

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.facade. GenericIdentifiableFacade#delete(java.io.Serializable)}
	 * .
	 */
	@Test
	public void testDelete() {
		final Response result = this.facade.delete(1L);
		Assert.assertNotNull(result);
		Assert.assertEquals(200, result.getStatus());
	}

	@Test(expected = NotFoundException.class)
	public void testDeleteNotFound() {
		this.facade.delete(2L);
		Assert.fail();
	}

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.facade. GenericIdentifiableFacade#getAll()}
	 * .
	 */
	@Test
	public void testGetAll() {
		final Response result = this.facade.getAll();
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getEntity());
	}

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.facade. GenericActiveFacade#getAllActive()}
	 * .
	 */
	@Test
	public void testGetAllActive() {
		final Response result = this.facade.getAllActive();
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getEntity());
	}

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.facade. GenericFunctionalIdentifiableFacade#getByFunctionalId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetByFunctionalId() {
		final Response result = this.facade.getByFunctionalId("Bla");
		Assert.assertNotNull(result);
		Assert.assertEquals(200, result.getStatus());
		Assert.assertNotNull(result.getEntity());
	}

	@Test(expected = NotFoundException.class)
	public void testGetByFunctionalIdNull() {
		this.facade.getByFunctionalId("");
	}

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.facade. GenericIdentifiableFacade#getByID(java.io.Serializable)}
	 * .
	 */
	@Test
	public void testGetByID() {
		final Response result = this.facade.getByFunctionalId("Bla");
		Assert.assertNotNull(result);
		Assert.assertEquals(200, result.getStatus());
		Assert.assertNotNull(result.getEntity());
	}

	@Test(expected = NotFoundException.class)
	public void testGetByIDNull() {
		this.facade.getByID(2L);
		Assert.fail();
	}

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.facade. GenericFunctionalIdentifiableFacade#getIdByFunctionalId(java.lang.String) }
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

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.facade. GenericFunctionalIdentifiableFacade#searchByFunctionalID(java.lang.String )}
	 * .
	 */
	@Test
	public void testSearchByFunctionalID() {
		final Response result = this.facade.searchByFunctionalID("B");
		Assert.assertNotNull(result);
		Assert.assertEquals(200, result.getStatus());
		Assert.assertNotNull(result.getEntity());
	}

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.facade. GenericIdentifiableFacade#update(bobkubista.examples.utils.domain.model. domainmodel.identification.DomainObject)}
	 * .
	 */
	@Test
	public void testUpdate() {
		final MockDomain object = new MockDomain();
		final Response result = this.facade.update(object);

		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getEntity());
	}

}
