package bobkubista.examples.utils.service.jpa.persistance.services;

import org.junit.Assert;
import org.junit.Test;

import bobkubista.examples.utils.service.jpa.persistance.mocks.MockEntityService;

public class FunctionalIdentifiableEntityServiceTest {

	private final MockEntityService mockService = new MockEntityService();

	@Test
	public void testGetByFunctionalId() {
		Assert.assertTrue(this.mockService.getByFunctionalId("ID")
				.isPresent());
	}

	@Test
	public void testGetIdByFunctionalId() {
		Assert.assertTrue(this.mockService.getIdByFunctionalId("ID")
				.isPresent());
	}

}
