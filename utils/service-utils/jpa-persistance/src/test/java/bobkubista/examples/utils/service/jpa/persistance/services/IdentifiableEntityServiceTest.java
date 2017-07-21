package bobkubista.examples.utils.service.jpa.persistance.services;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockEntity;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockEntityService;

public class IdentifiableEntityServiceTest {

	private final MockEntityService mockService = new MockEntityService();

	@Test
	public void testContainsId() {
		Assert.assertTrue(this.mockService.contains(1L));
	}

	@Test
	public void testContainsObject() {
		Assert.assertTrue(this.mockService.contains(new MockEntity()));
	}

	@Test
	public void testCount() {
		Assert.assertEquals(new Long(1L), this.mockService.count());
	}

	@Test
	public void testCreate() {
		Assert.assertTrue(this.mockService.create(new MockEntity())
				.isPresent());
	}

	@Test
	public void testDelete() {
		final MockEntity object = new MockEntity();
		this.mockService.delete(object);
		Mockito.verifyNoMoreInteractions(this.mockService.getDAO());
	}

	@Test
	public void testGetAll() {
		Assert.assertEquals(1, this.mockService.getAll(new SearchBean())
				.size());
	}

	@Test
	public void testGetById() {
		Assert.assertTrue(this.mockService.getById(1L)
				.isPresent());
	}

	@Test
	public void testUpdate() {
		Assert.assertTrue(this.mockService.update(new MockEntity())
				.isPresent());
	}
}
