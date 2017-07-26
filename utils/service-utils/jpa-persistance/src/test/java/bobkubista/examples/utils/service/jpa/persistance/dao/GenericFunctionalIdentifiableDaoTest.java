package bobkubista.examples.utils.service.jpa.persistance.dao;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import bobkubista.examples.utils.service.jpa.persistance.mocks.MockDao;

public class GenericFunctionalIdentifiableDaoTest {

	MockDao mockDao = new MockDao();

	@Ignore
	@Test
	public void testGetByFunctionalId() {
		Assert.assertTrue(mockDao.getByFunctionalId("fid")
				.isPresent());
	}

	@Test
	public void testGetIdByFunctionalId() {
		Assert.assertEquals(new Long(3L), mockDao.getIdByFunctionalId("fId")
				.get());
	}

}
