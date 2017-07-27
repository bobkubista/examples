/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.entity;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import bobkubista.examples.utils.service.jpa.persistance.mocks.MockEntity;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockFunctionalEntity;

/**
 * @author Bob
 *
 */
public class AbstractGenericActiveEntityTest {

	@Test
	public void testActive() {
		final MockEntity classToTest = new MockEntity();
		classToTest.setActive(false);
		Assert.assertFalse(classToTest.isActive());
	}

	@Test
	public void testEquals() {
		final MockEntity classToTest = new MockEntity();
		Assert.assertTrue(classToTest.equals(new MockEntity()));
	}

	@Test
	public void testEqualsDifferentObjects() {
		final MockEntity classToTest = new MockEntity();
		Assert.assertFalse(classToTest.equals(new MockFunctionalEntity()));
	}

	@Test
	public void testEqualsDifferentObjects2() {
		final MockFunctionalEntity classToTest = new MockFunctionalEntity();
		Assert.assertFalse(classToTest.equals(new MockEntity()));
	}

	@Test
	public void testEqualsNull() {
		final MockEntity classToTest = new MockEntity();
		Assert.assertFalse(classToTest.equals(null));
	}

	@Test
	public void testHashCode() {
		final MockEntity classToTest = new MockEntity();
		Assert.assertEquals(new MockEntity().hashCode(), classToTest.hashCode());
	}

	@Test
	public void testToString() {
		final MockEntity classToTest = new MockEntity();
		Assert.assertTrue(StringUtils.isNotBlank(classToTest.toString()));
		Assert.assertEquals("MockEntity[functionalId=foo,id=1,active=false,insertedDate=<null>,updatedDate=<null>]",
				classToTest.toString());
	}
}
