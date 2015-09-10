/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.facade;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import bobkubista.examples.utils.service.jpa.persistance.mocks.MockDomain;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockDomainCollection;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockFacade;

/**
 * @author Bob Kubista
 *
 */
public class GenericActiveFacadeTest {

	MockFacade facade = new MockFacade();

	/**
	 * Test method for {@link
	 * bobkubista.examples.utils.service.jpa.persistance.facade.
	 * GenericIdentifiableFacade#create(bobkubista.examples.utils.domain.model.
	 * domainmodel.identification.DomainObject)}.
	 */
	@Test
	public void testCreate() {
		final Response result = this.facade.create(new MockDomain());
		Assert.assertNotNull(result);
		Assert.assertEquals(201, result.getStatus());
	}

	@Test
	public void testCreateNull() {
		final Response result = this.facade.create(null);
		Assert.assertNotNull(result);
		Assert.assertEquals(500, result.getStatus());
	}

	/**
	 * Test method for {@link
	 * bobkubista.examples.utils.service.jpa.persistance.facade.
	 * GenericIdentifiableFacade#delete(java.io.Serializable)}.
	 */
	@Test
	public void testDelete() {
		final Response result = this.facade.delete(1L);
		Assert.assertNotNull(result);
		Assert.assertEquals(200, result.getStatus());
	}

	@Test
	public void testDeleteNotFound() {
		final Response result = this.facade.delete(2L);
		Assert.assertNotNull(result);
		Assert.assertEquals(404, result.getStatus());
	}

	/**
	 * Test method for {@link
	 * bobkubista.examples.utils.service.jpa.persistance.facade.
	 * GenericIdentifiableFacade#getAll()}.
	 */
	@Test
	public void testGetAll() {
		final Response result = this.facade.getAll();
		Assert.assertNotNull(result);
		final MockDomainCollection collection = result.readEntity(MockDomainCollection.class);
		Assert.assertNotNull(collection);
	}

	/**
	 * Test method for {@link
	 * bobkubista.examples.utils.service.jpa.persistance.facade.
	 * GenericActiveFacade#getAllActive()}.
	 */
	@Test
	public void testGetAllActive() {
		final Response result = this.facade.getAllActive();
		Assert.assertNotNull(result);
		final MockDomainCollection collection = result.readEntity(MockDomainCollection.class);
		Assert.assertNotNull(collection);
	}

	/**
	 * Test method for {@link
	 * bobkubista.examples.utils.service.jpa.persistance.facade.
	 * GenericFunctionalIdentifiableFacade#getByFunctionalId(java.lang.String)}.
	 */
	@Test
	public void testGetByFunctionalId() {
		final Response result = this.facade.getByFunctionalId("Bla");
		Assert.assertNotNull(result);
		Assert.assertEquals(200, result.getStatus());
		Assert.assertNotNull(result.readEntity(MockDomain.class));
	}

	@Test
	public void testGetByFunctionalIdNull() {
		final Response result = this.facade.getByFunctionalId("");
		Assert.assertNotNull(result);
		Assert.assertEquals(404, result.getStatus());
		Assert.assertNotNull(result.readEntity(MockDomain.class));
	}

	/**
	 * Test method for {@link
	 * bobkubista.examples.utils.service.jpa.persistance.facade.
	 * GenericIdentifiableFacade#getByID(java.io.Serializable)}.
	 */
	@Test
	public void testGetByID() {
		final Response result = this.facade.getByFunctionalId("Bla");
		Assert.assertNotNull(result);
		Assert.assertEquals(200, result.getStatus());
		Assert.assertNotNull(result.readEntity(MockDomain.class));
	}

	@Test
	public void testGetByIDNull() {
		final Response result = this.facade.getByID(2L);
		Assert.assertNotNull(result);
		Assert.assertEquals(404, result.getStatus());
	}

	/**
	 * Test method for {@link
	 * bobkubista.examples.utils.service.jpa.persistance.facade.
	 * GenericFunctionalIdentifiableFacade#getIdByFunctionalId(java.lang.String)
	 * }.
	 */
	@Test
	public void testGetIdByFunctionalId() {
		final Response result = this.facade.getIdByFunctionalId("Bla");
		Assert.assertNotNull(result);
		Assert.assertEquals(200, result.getStatus());
		Assert.assertNotNull(result.readEntity(Long.class));
	}

	@Test
	public void testGetIdByFunctionalIdNull() {
		final Response result = this.facade.getIdByFunctionalId("");
		Assert.assertNotNull(result);
		Assert.assertEquals(404, result.getStatus());
	}

	/**
	 * Test method for {@link
	 * bobkubista.examples.utils.service.jpa.persistance.facade.
	 * GenericFunctionalIdentifiableFacade#searchByFunctionalID(java.lang.String
	 * )}.
	 */
	@Test
	public void testSearchByFunctionalID() {
		Assert.fail("Not yet implemented");
	}

	/**
	 * Test method for {@link
	 * bobkubista.examples.utils.service.jpa.persistance.facade.
	 * GenericIdentifiableFacade#update(bobkubista.examples.utils.domain.model.
	 * domainmodel.identification.DomainObject)}.
	 */
	@Test
	public void testUpdate() {
		Assert.fail("Not yet implemented");
	}

}
