package bobkubista.examples.utils.service.jpa.persistance.mocks;

import java.util.Arrays;
import java.util.Optional;

import org.mockito.Mockito;

import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.service.jpa.persistance.dao.GenericActiveDAO;
import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;
import bobkubista.examples.utils.service.jpa.persistance.services.FunctionalIdentifiableEntityService;

public class MockEntityService
		implements ActiveEntityService<MockEntity>, FunctionalIdentifiableEntityService<MockEntity> {

	@Override
	public GenericActiveDAO<MockEntity> getDAO() {
		final MockDao mockDao = Mockito.mock(MockDao.class);

		Mockito.when(mockDao.contains(Mockito.anyLong()))
				.thenReturn(true);
		Mockito.when(mockDao.contains(Mockito.any(MockEntity.class)))
				.thenReturn(true);

		Mockito.when(mockDao.count())
				.thenReturn(1L);

		Mockito.when(mockDao.create(Mockito.any(MockEntity.class)))
				.thenReturn(Optional.of(new MockEntity()));

		Mockito.doAnswer(invocation -> null)
				.when(mockDao)
				.delete(Mockito.any(MockEntity.class));

		Mockito.when(mockDao.getAll(new SearchBean()))
				.thenReturn(Arrays.asList(new MockEntity()));

		Mockito.when(mockDao.getById(Mockito.anyLong()))
				.thenReturn(Optional.of(new MockEntity()));

		Mockito.when(mockDao.update(Mockito.any(MockEntity.class)))
				.thenReturn(Optional.of(new MockEntity()));

		return mockDao;
	}
}
