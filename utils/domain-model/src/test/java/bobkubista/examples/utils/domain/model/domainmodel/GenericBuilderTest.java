/**
 *
 */
package bobkubista.examples.utils.domain.model.domainmodel;

import org.junit.Assert;
import org.junit.Test;

import bobkubista.examples.utils.domain.model.domainmodel.identification.GenericTestActiveDomainObject;

/**
 * @author Bob
 *
 */
public class GenericBuilderTest {

    @Test
    public void test() {
        Assert.assertNotNull(GenericBuilder.of(GenericTestActiveDomainObject::new).with(GenericTestActiveDomainObject::setFunctionalId, "funct").build());
    }

}
