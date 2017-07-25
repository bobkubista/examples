/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.mocks;

import java.util.Optional;

import org.mockito.Matchers;
import org.mockito.Mockito;

import bobkubista.examples.utils.domain.model.api.ActiveServerApi;
import bobkubista.examples.utils.service.jpa.persistance.converter.AbstractEntityToDomainConverter;
import bobkubista.examples.utils.service.jpa.persistance.services.IdentifiableEntityService;

/**
 * @author Bob Kubista
 *
 */
public class MockConverter extends AbstractEntityToDomainConverter<MockDomain, MockDomainCollection, MockEntity>
		implements ActiveServerApi<MockDomain> {
	IdentifiableEntityService<MockEntity> mock;

	public IdentifiableEntityService<MockEntity> getMockService() {
		return this.mock;
	}

	@Override
	protected MockDomain doConvertToDomainObject(final MockEntity entity) {
		final MockDomain domain = new MockDomain();

		domain.setActive(entity.isActive());
		domain.setFunctionalId(entity.getFunctionalId());
		domain.setId(entity.getId());

		return domain;
	}

	@Override
	protected MockEntity doConvertToEntity(final MockDomain domainModelObject) {
		final MockEntity entity = new MockEntity();
		this.doConvertToEntity(domainModelObject, entity);
		return entity;
	}

	@Override
	protected void doConvertToEntity(final MockDomain domainModelObject, final MockEntity entityObject) {
		entityObject.setActive(domainModelObject.isActive());
		entityObject.setFunctionalId(domainModelObject.getFunctionalId());
		entityObject.setId(domainModelObject.getId());
	}

	@Override
	protected MockDomainCollection getNewDomainObjectCollection() {
		return new MockDomainCollection();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected IdentifiableEntityService<MockEntity> getService() {
		this.mock = Mockito.mock(IdentifiableEntityService.class);
		Mockito.when(mock.getById(Matchers.anyLong()))
				.thenReturn(Optional.of(new MockEntity()));
		Mockito.when(mock.contains(1L))
				.thenReturn(true);
		return mock;
	}
}
