package bobkubista.examples.utils.service.jpa.persistance.converter;

import java.io.Serializable;

import org.junit.Assert;
import org.junit.Test;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject;
import bobkubista.examples.utils.service.jpa.persistance.entity.EntityObject;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractIdentifiableEntity;

/**
 * An abstract resources utility class to resources converters. Extend the
 * resources class with this utility class and you have a basic set of unit
 * tests
 *
 * @author bkubista
 *
 * @param <DMO>
 *            An {@link AbstractGenericIdentifiableDomainObject}
 * @param <DMOL>
 *            A {@link DomainObjectCollection}
 * @param <EO>
 *            An {@link AbstractIdentifiableEntity}
 * @param <ID>
 *            the identifier
 */
public abstract class AbstractTestEntityToDomainConverter<DMO extends AbstractGenericIdentifiableDomainObject<ID>, DMOL extends DomainObjectCollection<DMO>, EO extends AbstractIdentifiableEntity<ID>, ID extends Serializable> {

	/**
	 * Test ConvertToDomain with null id
	 */
	@Test
	public void shouldConvertDomainObjectWithNullId() {
		final DMO domainModelObject = this.getValidDomainObject();
		domainModelObject.setId(null);
		final EO entityObject = this.getValidEntity();
		entityObject.setId(null);
		this.getConverter().doConvertToEntity(domainModelObject, entityObject);
		this.assertEntity(domainModelObject, entityObject);
	}

	/**
	 * Test when you want to convert a {@link EntityObject} into an
	 * {@link DomainObject}, you get valid output.
	 */
	@Test
	public void testConvertToDomainObject() {
		final EO entity = this.getValidEntity();
		final DMO domainModelObject = this.getConverter().doConvertToDomainObject(entity);
		this.assertDomainObject(domainModelObject, entity);
	}

	/**
	 * Test that there is a nullpointer when you want to convert a
	 * {@link EntityObject} into an {@link DomainObject} and the input is null
	 */
	@Test
	public void testDoConvertToDomainObjectNullEntity() {
		final EO entities = null;

		final DMO result = this.getConverter().doConvertToDomainObject(entities);

		Assert.assertNotNull(result);
	}

	/**
	 * Test when you want to convert a {@link DomainObject} into an
	 * {@link EntityObject}, you get valid output.
	 */
	@Test
	public void testDoConvertToEntity() {
		final DMO domainModelObject = this.getValidDomainObject();
		final EO entity = this.getConverter().doConvertToEntity(domainModelObject);
		this.assertEntity(domainModelObject, entity);
	}

	/**
	 * Test that there is a nullpointer when you want to convert a
	 * {@link DomainObject} into an {@link EntityObject} and the input is null
	 */
	@Test(expected = NullPointerException.class)
	public void testDoConvertToEntityNullDomainObject() {
		final DMO domainModelObject = null;
		this.getConverter().doConvertToEntity(domainModelObject);
		Assert.fail();
	}

	/**
	 * Test that there is a nullpointer when you want to convert a
	 * {@link DomainObject} into an {@link EntityObject} and the input is null
	 */
	@Test(expected = NullPointerException.class)
	public void testDoConvertToEntityNullDomainObjectAndEntity() {
		final DMO domainModelObject = null;
		final EO entityObject = null;
		this.getConverter().doConvertToEntity(domainModelObject, entityObject);
		Assert.fail();
	}

	/**
	 * Test that there is a nullpointer when you want to convert a
	 * {@link DomainObject} into an {@link EntityObject} and the input is null
	 */
	@Test(expected = NullPointerException.class)
	public void testDoConvertToEntityNullEntity() {
		final DMO domainModelObject = this.getEmptyDomainObject();
		final EO entityObject = null;
		this.getConverter().doConvertToEntity(domainModelObject, entityObject);
		Assert.fail();
	}

	/**
	 * Test when you want to convert a {@link DomainObject} into an
	 * {@link EntityObject}, you get valid output.
	 */
	@Test
	public void testDoConvertToEntityWithEntity() {
		final DMO domainModelObject = this.getValidDomainObject();
		final EO entityObject = this.getValidEntity();

		this.getConverter().doConvertToEntity(domainModelObject, entityObject);

		this.assertEntity(domainModelObject, entityObject);
	}

	/**
	 * Test the get new {@link DomainObjectCollection}. Expect an object with an
	 * empty list
	 */
	@Test
	public void testGetNewDomainObjectCollection() {
		final DomainObjectCollection<DMO> domainObjectCollection = this.getConverter().getNewDomainObjectCollection();

		Assert.assertNotNull(domainObjectCollection);
		Assert.assertNotNull(domainObjectCollection.getDomainCollection());
		Assert.assertTrue(domainObjectCollection.getDomainCollection().isEmpty());
	}

	/**
	 * assert a {@link DomainObject}
	 *
	 * @param domainModelObject
	 *            the {@link DomainObject} to check @param entity the
	 *            {@link EntityObject} to check with
	 */
	protected abstract void assertDomainObject(DMO domainModelObject, EO entity);

	/**
	 * assert an {@link EntityObject}
	 *
	 * @param domainModelObject
	 *            the {@link DomainObject} to check with @param entity the
	 *            {@link EntityObject} to check
	 */
	protected abstract void assertEntity(DMO domainModelObject, EO entity);

	/**
	 * get the right converter, injected into the resources class
	 *
	 * @return an {@link AbstractEntityToDomainConverter}
	 */
	protected abstract AbstractEntityToDomainConverter<DMO, DMOL, EO, ID> getConverter();

	/**
	 * Get an empty {@link DomainObject}
	 *
	 * @return an empty {@link DomainObject}
	 */
	protected abstract DMO getEmptyDomainObject();

	/**
	 * Get a valid {@link DomainObject}
	 *
	 * @return a valid {@link DomainObject}
	 */
	protected abstract DMO getValidDomainObject();

	/**
	 * Get a valid {@link EntityObject}
	 *
	 * @return a valid {@link EntityObject}
	 */
	protected abstract EO getValidEntity();

}
