/**
 *
 */
package bobkubista.examples.utils.services.spring.jpa.converter;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import bobkubista.examples.utils.services.spring.jpa.mocks.MockConverter;
import bobkubista.examples.utils.services.spring.jpa.mocks.MockDomain;
import bobkubista.examples.utils.services.spring.jpa.mocks.MockDomainCollection;
import bobkubista.examples.utils.services.spring.jpa.mocks.MockEntity;

/**
 * @author Bob Kubista
 *
 */
public class AbstractEntityToDomainConverterTest {

	MockConverter converter = new MockConverter();

	private MockDomain buildMockDomain() {
		final MockDomain domain = new MockDomain();
		domain.setActive(true);
		domain.setFunctionalId("functionalId");
		domain.setId(1L);
		return domain;
	}

	/**
	 * @return
	 */
	private MockEntity buildMockEntity() {
		final MockEntity entity = new MockEntity();
		entity.setActive(true);
		entity.setFunctionalId("functionalId");
		entity.setId(1L);
		return entity;
	}

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.converter. AbstractEntityToDomainConverter#convertToDomainObject(java.util. Collection)}
	 * .
	 */
	@Test
	public void testConvertToDomainObjectCollectionOfEO() {
		final Collection<MockEntity> entities = new ArrayList<>();
		entities.add(this.buildMockEntity());

		final MockDomainCollection result = this.converter.convertToDomainObject(entities);

		Assert.assertNotNull(result);
		final Collection<MockDomain> domainCollection = result.getDomainCollection();
		Assert.assertNotNull(domainCollection);
		Assert.assertEquals(entities.size(), domainCollection.size());
	}

	@Test
	public void testConvertToDomainObjectCollectionOfEONull() {
		final Collection<MockEntity> entities = null;
		final MockDomainCollection result = this.converter.convertToDomainObject(entities);

		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getDomainCollection());
		Assert.assertEquals(0, result.getDomainCollection().size());
	}

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.converter. AbstractEntityToDomainConverter#convertToDomainObject(bobkubista.examples .utils.service.jpa.persistance.entity.IdentifiableEntity)}
	 * .
	 */
	@Test
	public void testConvertToDomainObjectEO() {
		final MockEntity entity = this.buildMockEntity();

		final MockDomain domain = this.converter.convertToDomainObject(entity);

		Assert.assertNotNull(domain);
		Assert.assertEquals(entity.getFunctionalId(), domain.getFunctionalId());
		Assert.assertEquals(entity.getId(), domain.getId());
		Assert.assertEquals(entity.isActive(), domain.isActive());
	}

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.converter. AbstractEntityToDomainConverter#convertToEntity(bobkubista.examples.utils .domain.model.domainmodel.identification.IdentifiableDomainObject)}
	 * .
	 */
	@Test
	public void testConvertToEntityDMO() {
		final MockDomain domain = this.buildMockDomain();

		final MockEntity entity = this.converter.convertToEntity(domain);

		Assert.assertNotNull(entity);
		Assert.assertEquals(domain.getFunctionalId(), entity.getFunctionalId());
		Assert.assertEquals(domain.getId(), entity.getId());
		Assert.assertEquals(domain.isActive(), entity.isActive());
	}

	@Test
	public void testConvertToEntityDomainObjectCollectionNull() {
		final MockDomainCollection domainObjects = null;
		final Collection<MockEntity> result = this.converter.convertToEntity(domainObjects);

		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.size());
	}

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.converter. AbstractEntityToDomainConverter#convertToEntity(bobkubista.examples.utils .domain.model.domainmodel.identification.DomainObjectCollection)}
	 * .
	 */
	@Test
	public void testConvertToEntityDomainObjectCollectionOfDMO() {
		final MockDomainCollection domainObjects = new MockDomainCollection();
		domainObjects.getDomainCollection().add(this.buildMockDomain());

		final Collection<MockEntity> result = this.converter.convertToEntity(domainObjects);

		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertEquals(domainObjects.getDomainCollection().size(), result.size());
	}

	@Test
	public void testConvertToEntityDomainObjectNull() {
		final MockDomain mock = null;
		final MockEntity result = this.converter.convertToEntity(mock);
		Assert.assertNull(result);
	}

}
