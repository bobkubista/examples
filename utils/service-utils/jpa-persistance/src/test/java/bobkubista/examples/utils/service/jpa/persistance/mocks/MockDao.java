package bobkubista.examples.utils.service.jpa.persistance.mocks;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericDao;
import bobkubista.examples.utils.service.jpa.persistance.dao.GenericActiveDAO;
import bobkubista.examples.utils.service.jpa.persistance.dao.GenericFunctionalIdentifiableDao;

public class MockDao extends AbstractGenericDao<MockEntity>
		implements GenericActiveDAO<MockEntity>, GenericFunctionalIdentifiableDao<MockEntity> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MockDao.class);

	@SuppressWarnings("unchecked")
	@Override
	public EntityManager getEntityManager() {
		final EntityManager mockEntityManager = Mockito.mock(EntityManager.class);

		Mockito.when(mockEntityManager.find(MockEntity.class, 1L))
				.thenReturn(buildMockEntity());

		Mockito.when(mockEntityManager.contains(Mockito.any(MockEntity.class)))
				.thenReturn(true);

		Mockito.when(mockEntityManager.merge(Mockito.any(MockEntity.class)))
				.thenAnswer(invocation -> invocation.getArgumentAt(0, MockEntity.class));

		Mockito.doAnswer(invocation -> null)
				.when(mockEntityManager)
				.remove(Mockito.any(MockEntity.class));

		final CriteriaQuery<Long> buildMockQuery = buildMockLongQuery();
		final CriteriaBuilder buildMockCriteriaBuilder = buildMockCriteriaBuilder(buildMockQuery);
		Mockito.when(mockEntityManager.getCriteriaBuilder())
				.thenReturn(buildMockCriteriaBuilder);

		final TypedQuery<?> mockTypedLongQuery = mockTypedLongQuery();
		final TypedQuery<?> mockTypedEntityQuery = mockTypedEntityQuery();
		// TODO remove me
		System.out.println(mockTypedEntityQuery);

		Mockito.when(mockEntityManager.createQuery(Mockito.any(CriteriaQuery.class)))
				.thenReturn(mockTypedLongQuery);

		return mockEntityManager;
	}

	@Override
	public Path<String> getFunctionalIdField(final Root<MockEntity> entity) {
		return entity.<String>get("functionalId");
	}

	@Override
	public Logger getLogger() {
		return LOGGER;
	}

	@SuppressWarnings("unchecked")
	private CriteriaBuilder buildMockCriteriaBuilder(CriteriaQuery<Long> buildMockQuery) {
		final CriteriaBuilder mockBuilder = Mockito.mock(CriteriaBuilder.class);

		Mockito.when(mockBuilder.createQuery(Long.class))
				.thenReturn(buildMockQuery);
		final CriteriaQuery<MockEntity> buildMockEntityQuery = buildMockEntityQuery();
		Mockito.when(mockBuilder.createQuery(MockEntity.class))
				.thenReturn(buildMockEntityQuery);
		Mockito.when(mockBuilder.count(Mockito.any(Expression.class)))
				.thenReturn(Mockito.mock(Expression.class));

		return mockBuilder;
	}

	private MockEntity buildMockEntity() {
		final MockEntity mockEntity = new MockEntity();

		mockEntity.setId(1L);
		mockEntity.setFunctionalId("functionalId");
		mockEntity.setActive(true);

		return mockEntity;
	}

	@SuppressWarnings("unchecked")
	private CriteriaQuery<MockEntity> buildMockEntityQuery() {
		final CriteriaQuery<MockEntity> mockQuery = Mockito.mock(CriteriaQuery.class);

		final Root<MockEntity> buildMockRoot = buildMockRoot();
		Mockito.when(mockQuery.from(MockEntity.class))
				.thenReturn(buildMockRoot);
		Mockito.when(mockQuery.select(Mockito.any(Selection.class)))
				.thenReturn(mockQuery);
		Mockito.when(mockQuery.where(Mockito.any(Predicate.class)))
				.thenReturn(mockQuery);

		return mockQuery;
	}

	@SuppressWarnings("unchecked")
	private CriteriaQuery<Long> buildMockLongQuery() {
		final CriteriaQuery<Long> mockQuery = Mockito.mock(CriteriaQuery.class);

		final Root<MockEntity> buildMockRoot = buildMockRoot();
		Mockito.when(mockQuery.from(MockEntity.class))
				.thenReturn(buildMockRoot);
		Mockito.when(mockQuery.select(Mockito.any(Selection.class)))
				.thenReturn(mockQuery);
		Mockito.when(mockQuery.where(Mockito.any(Predicate.class)))
				.thenReturn(mockQuery);

		return mockQuery;
	}

	private Root<MockEntity> buildMockRoot() {
		@SuppressWarnings("unchecked")
		final Root<MockEntity> mockRoot = Mockito.mock(Root.class);

		return mockRoot;
	}

	private TypedQuery<MockEntity> mockTypedEntityQuery() {
		@SuppressWarnings("unchecked")
		final TypedQuery<MockEntity> mockQuery = Mockito.mock(TypedQuery.class);

		return mockQuery;
	}

	@SuppressWarnings({ "rawtypes" })
	private TypedQuery mockTypedLongQuery() {
		final TypedQuery mockQuery = Mockito.mock(TypedQuery.class);

		Mockito.when(mockQuery.getSingleResult())
				.thenReturn(3L);
		Mockito.when(mockQuery.setFirstResult(Mockito.anyInt()))
				.thenReturn(mockQuery);
		Mockito.when(mockQuery.setMaxResults(Mockito.anyInt()))
				.thenReturn(mockQuery);

		Mockito.when(mockQuery.getResultList())
				.thenReturn(Arrays.asList(new MockEntity(), new MockEntity()));
		return mockQuery;
	}
}
