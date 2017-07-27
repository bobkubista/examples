package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockDao;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockEntity;

public class GenericActiveDAOTest {

	MockDao mockDao = new MockDao();

	@Test
	public void testCountActive() {
		Assert.assertEquals(new Long(3L), mockDao.countActive());
	}

	@Test
	public void testFindAllActive() {
		final Collection<MockEntity> result = mockDao.findAllActive(new SearchBean());

		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
	}
}
