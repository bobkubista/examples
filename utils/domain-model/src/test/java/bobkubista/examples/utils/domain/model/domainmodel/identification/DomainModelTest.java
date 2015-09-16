/**
 *
 */
package bobkubista.examples.utils.domain.model.domainmodel.identification;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Testing standard domain model stuff
 *
 * @author bkubista
 *
 */
public class DomainModelTest {

	@Test
	public void testEquals() {
		final GenericTestActiveDomainObject classToTest = new GenericTestActiveDomainObject();
		Assert.assertTrue(classToTest.equals(new GenericTestActiveDomainObject()));
	}

	@Test
	public void testEqualsNull() {
		final GenericTestActiveDomainObject classToTest = new GenericTestActiveDomainObject();
		Assert.assertFalse(classToTest.equals(null));
	}

	@Test
	public void testEqualsObject() {
		final GenericTestActiveDomainObject classToTest = new GenericTestActiveDomainObject();
		Assert.assertFalse(classToTest.equals(new Object()));
	}

	@Test
	public void testHashCode() {
		final GenericTestActiveDomainObject classToTest = new GenericTestActiveDomainObject();
		Assert.assertEquals(new GenericTestActiveDomainObject().hashCode(), classToTest.hashCode());
	}

	@Test
	public void testToString() {
		final GenericTestActiveDomainObject classToTest = new GenericTestActiveDomainObject();
		Assert.assertTrue(StringUtils.isNotBlank(classToTest.toString()));
		Assert.assertEquals("GenericTestActiveDomainObject[id=1,active=true,functionalId=testObject]", classToTest.toString());
	}
}
