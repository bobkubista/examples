/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.domain.model.domainmodel.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericFunctionalIdentifiableDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.GenericTestActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.GenericTestFunctionalDomainObject;

/**
 * @author Bob
 *
 */
public class CollectionReducerTest {

    @Test(expected = IllegalStateException.class)
    public void testReducerException() {
        final Stream<AbstractGenericFunctionalIdentifiableDomainObject<Integer>> stream = Stream.of(new GenericTestActiveDomainObject(), new GenericTestActiveDomainObject());
        CollectionReducer.findOnlyOne(1, stream, t -> t.getId(), IllegalStateException::new);
    }

    @Test
    public void testReducerOkay() {
        final GenericTestActiveDomainObject result = new GenericTestActiveDomainObject();
        final List<AbstractGenericFunctionalIdentifiableDomainObject<Integer>> stream = new ArrayList<>(Arrays.asList(result, new GenericTestFunctionalDomainObject()));
        Assert.assertEquals(Optional.of(result), CollectionReducer.findOnlyOne(1, stream.stream(), t -> t.getId(), IllegalStateException::new));
    }
}
