/**
 *
 */
package bobkubista.examples.utils.services.spring.jpa.mocks;

import java.util.Collections;

import org.mockito.Matchers;
import org.mockito.Mockito;

import bobkubista.examples.utils.services.spring.jpa.converter.EntityToDomainConverter;
import bobkubista.examples.utils.services.spring.jpa.facade.GenericActiveFacade;
import bobkubista.examples.utils.services.spring.jpa.services.ActiveEntityService;

/**
 * @author Bob Kubista
 *
 */
public class MockFacade extends GenericActiveFacade<MockDomain, Long, MockEntity, MockDomainCollection> {

	@SuppressWarnings("unchecked")
	@Override
	protected EntityToDomainConverter<MockDomain, MockDomainCollection, MockEntity> getConverter() {
		final EntityToDomainConverter<MockDomain, MockDomainCollection, MockEntity> mock = Mockito.mock(EntityToDomainConverter.class);
		Mockito.when(mock.convertToEntity((MockDomain) null)).thenReturn(null);

		final MockEntity entity = this.buildMockEntity();
		Mockito.when(mock.convertToEntity(Matchers.any(MockDomain.class))).thenReturn(entity);
		Mockito.when(mock.convertToDomainObject(Matchers.any(MockEntity.class))).thenReturn(this.buildMockDomain());

		Mockito.when(mock.convertToDomainObject(Matchers.anyCollection())).thenReturn(new MockDomainCollection());

		return mock;
	}

	@Override
	protected ActiveEntityService<MockEntity, Long> getService() {
		@SuppressWarnings("unchecked")
		final ActiveEntityService<MockEntity, Long> mock = Mockito.mock(ActiveEntityService.class);
		Mockito.when(mock.create(null)).thenReturn(null);

		final MockEntity entity = this.buildMockEntity();
		Mockito.when(mock.create(Matchers.any(MockEntity.class))).thenReturn(entity);

		Mockito.when(mock.getById(1L)).thenReturn(this.buildMockEntity());
		Mockito.when(mock.getById(2L)).thenReturn(null);

		Mockito.when(mock.getAll()).thenReturn(Collections.singletonList(this.buildMockEntity()));
		Mockito.when(mock.getAllActive()).thenReturn(Collections.singletonList(this.buildMockEntity()));

		Mockito.when(mock.getByFunctionalId("Bla")).thenReturn(this.buildMockEntity());
		Mockito.when(mock.getByFunctionalId("")).thenReturn(null);

		Mockito.when(mock.getIdByFunctionalId("Bla")).thenReturn(1L);

		Mockito.when(mock.searchByFunctionalID("B")).thenReturn(Collections.singletonList(this.buildMockEntity()));

		Mockito.when(mock.update(Matchers.any(MockEntity.class))).thenReturn(this.buildMockEntity());

		return mock;
	}

	private MockDomain buildMockDomain() {
		final MockDomain domainObject = new MockDomain();
		domainObject.setId(1L);
		domainObject.setFunctionalId("Bla");
		return domainObject;
	}

	/**
	 * @return
	 */
	private MockEntity buildMockEntity() {
		final MockEntity entity = new MockEntity();
		entity.setId(1L);
		entity.setFunctionalId("Bla");
		return entity;
	}

}
