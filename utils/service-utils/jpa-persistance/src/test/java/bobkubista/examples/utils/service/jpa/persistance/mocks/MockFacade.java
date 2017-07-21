/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.mocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import javax.ws.rs.Path;
import javax.ws.rs.core.Link;

import org.mockito.Matchers;
import org.mockito.Mockito;

import bobkubista.examples.utils.domain.model.api.ActiveSearchBean;
import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.service.jpa.persistance.converter.EntityToDomainConverter;
import bobkubista.examples.utils.service.jpa.persistance.facade.AbstractGenericActiveFacade;
import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

/**
 * @author Bob Kubista
 *
 */
@Path("/")
public class MockFacade extends AbstractGenericActiveFacade<MockDomain, MockEntity, MockDomainCollection> {

	@SuppressWarnings("unchecked")
	@Override
	protected EntityToDomainConverter<MockDomain, MockDomainCollection, MockEntity> getConverter() {
		final EntityToDomainConverter<MockDomain, MockDomainCollection, MockEntity> mock = Mockito
				.mock(EntityToDomainConverter.class);
		Mockito.when(mock.convertToEntity((MockDomain) null))
				.thenReturn(null);

		final MockEntity entity = this.buildMockEntity();
		Mockito.when(mock.convertToEntity(Matchers.any(MockDomain.class)))
				.thenReturn(entity);
		Mockito.when(mock.convertToDomainObject(Matchers.any(MockEntity.class)))
				.thenReturn(this.buildMockDomain());

		Mockito.when(mock.convertToDomainObject(Matchers.anyCollection(), Matchers.anyLong(),
				Matchers.anyListOf(Link.class)))
				.thenReturn(new MockDomainCollection());

		return mock;
	}

	@Override
	protected ActiveEntityService<MockEntity> getService() {

		final MockEntityService mock = Mockito.mock(MockEntityService.class);
		Mockito.when(mock.create(null))
				.thenReturn(null);

		Mockito.when(mock.create(Matchers.any(MockEntity.class)))
				.thenReturn(Optional.of(this.buildMockEntity()));

		Mockito.when(mock.getById(1L))
				.thenReturn(Optional.of(this.buildMockEntity()));
		Mockito.when(mock.getById(2L))
				.thenReturn(Optional.empty());

		Mockito.when(mock.getAll(new SearchBean().setSort(Collections.singletonList("id"))
				.setPage(0)
				.setMaxResults(100)))
				.thenReturn(Collections.singletonList(this.buildMockEntity()));
		Mockito.when(mock.getAll(new SearchBean().setSort(new ArrayList<>())
				.setPage(1)
				.setMaxResults(1)))
				.thenReturn(Collections.singletonList(this.buildMockEntity()));
		Mockito.when(mock.getAll(new SearchBean().setPage(3)))
				.thenAnswer(invocation -> {
					Thread.sleep(1100L);
					return null;
				});
		Mockito.when(mock.getAll(new SearchBean().setPage(3)))
				.thenAnswer(invocation -> {
					Thread.sleep(1100L);
					return null;
				});
		Mockito.when(mock.getAll(new SearchBean().setPage(4)
				.setMaxResults(14)))
				.thenThrow(new RuntimeException("Just to test this"));
		Mockito.when(mock.getAll(new ActiveSearchBean().setPage(4)
				.setMaxResults(14)))
				.thenThrow(new RuntimeException("Just to test this"));
		Mockito.when(mock.getAll(new ActiveSearchBean().setSort(new ArrayList<>())
				.setPage(0)
				.setMaxResults(100)))
				.thenReturn(Collections.singletonList(this.buildMockEntity()));
		Mockito.when(mock.getAll(new ActiveSearchBean().setSort(new ArrayList<>())
				.setPage(1)
				.setMaxResults(1)))
				.thenReturn(Collections.singletonList(this.buildMockEntity()));

		Mockito.when(mock.count())
				.thenReturn(100L);
		Mockito.when(mock.count(Mockito.any(ActiveSearchBean.class)))
				.thenReturn(100L);

		Mockito.when(mock.getByFunctionalId("Bla"))
				.thenReturn(Optional.ofNullable(this.buildMockEntity()));
		Mockito.when(mock.getByFunctionalId(""))
				.thenReturn(Optional.empty());

		Mockito.when(mock.getIdByFunctionalId("Bla"))
				.thenReturn(Optional.ofNullable(1L));
		Mockito.when(mock.getIdByFunctionalId(""))
				.thenReturn(Optional.empty());

		Mockito.when(mock.update(Matchers.any(MockEntity.class)))
				.thenReturn(Optional.of(this.buildMockEntity()));

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
