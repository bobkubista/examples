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
    public void testEqualsDifferentObjects() {
        final GenericTestActiveDomainObject classToTest = new GenericTestActiveDomainObject();
        Assert.assertFalse(classToTest.equals(new GenericTestFunctionalDomainObject()));
    }

    @Test
    public void testEqualsDifferentObjects2() {
        final GenericTestFunctionalDomainObject classToTest = new GenericTestFunctionalDomainObject();
        Assert.assertFalse(classToTest.equals(new GenericTestActiveDomainObject()));
    }

    @Test
    public void testEqualsNull() {
        final GenericTestActiveDomainObject classToTest = new GenericTestActiveDomainObject();
        Assert.assertFalse(classToTest.equals(null));
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
        Assert.assertEquals("GenericTestActiveDomainObject[id=1,functionalId=testObject,active=true]", classToTest.toString());
    }
}
