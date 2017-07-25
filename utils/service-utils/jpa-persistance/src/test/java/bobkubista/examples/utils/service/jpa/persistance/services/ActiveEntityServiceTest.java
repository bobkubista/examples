package bobkubista.examples.utils.service.jpa.persistance.services;

import org.junit.Assert;
import org.junit.Test;

import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockEntityService;

public class ActiveEntityServiceTest {

	private final MockEntityService mockService = new MockEntityService();

	@Test
	public void testCountActive() {
		Assert.assertEquals(new Long(1L), this.mockService.countActive());
	}

	@Test
	public void testGetAllActive() {
		Assert.assertEquals(1, this.mockService.getAllActive(new SearchBean())
				.size());
	}

}
