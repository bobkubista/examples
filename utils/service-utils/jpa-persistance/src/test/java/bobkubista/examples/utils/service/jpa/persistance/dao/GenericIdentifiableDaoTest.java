package bobkubista.examples.utils.service.jpa.persistance.dao;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiFunction;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockDao;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockEntity;

public class GenericIdentifiableDaoTest {

	GenericIdentifiableDao<MockEntity> mockDao = new MockDao();

	@Test
	public void testContainsLong() {
		Assert.assertTrue(mockDao.contains(1L));
	}

	@Test
	public void testContainsTYPE() {
		Assert.assertTrue(mockDao.contains(new MockEntity()));
	}

	@Test
	public void testCount() {
		Assert.assertEquals(new Long(3L), mockDao.count());
	}

	@Test
	public void testCountBiFunctionOfRootOfTYPECriteriaBuilderPredicate() {
		final BiFunction<Root<MockEntity>, CriteriaBuilder, Predicate> whereClause = (t, u) -> Mockito
				.mock(Predicate.class);
		Assert.assertEquals(new Long(3L), mockDao.count(whereClause));
	}

	@Test
	public void testCreate() {
		final MockEntity mockEntity = new MockEntity();
		mockEntity.setId(1L);

		Assert.assertEquals(mockEntity, mockDao.create(mockEntity)
				.get());
	}

	@Test
	public void testDelete() {
		final MockEntity mockEntity = new MockEntity();
		mockEntity.setId(1L);

		mockDao.delete(mockEntity);

		final EntityManager entityManager = this.mockDao.getEntityManager();
		Mockito.verify(entityManager)
				.find(this.mockDao.getEntityClass(), mockEntity.getId());
		Mockito.verify(entityManager)
				.remove(mockEntity);
	}

	@Test
	public void testGetAllSearchBean() {
		final Collection<MockEntity> result = mockDao.getAll(new SearchBean());

		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
	}

	@Test
	public void testGetAllSearchBeanBiFunctionOfRootOfTYPECriteriaBuilderPredicate() {
		final BiFunction<Root<MockEntity>, CriteriaBuilder, Predicate> whereClause = (t, u) -> Mockito
				.mock(Predicate.class);

		final Collection<MockEntity> result = mockDao.getAll(new SearchBean(), whereClause);

		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
	}

	@Test
	public void testGetById() {
		Assert.assertEquals(new MockEntity(), mockDao.getById(1L)
				.get());
	}

	@Test
	public void testOrderedBy() {
		final SearchBean search = new SearchBean();
		search.setSort(Arrays.asList("id", "-fid"));

		final Collection<MockEntity> result = mockDao.getAll(search);

		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
	}

	@Test
	public void testUpdate() {
		Assert.assertEquals(new MockEntity(), mockDao.update(new MockEntity())
				.get());
	}

}
