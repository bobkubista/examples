/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.domain.model.domainmodel.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Ignore;
import org.junit.Test;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericFunctionalIdentifiableDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.GenericTestActiveDomainObject;

/**
 * @author Bob
 *
 */
public class CollectionReducerTest {

    @Ignore
    @Test(expected = IllegalStateException.class)
    public void testReducerException() {
        final Stream<AbstractGenericFunctionalIdentifiableDomainObject<Integer>> stream = Stream.of(new GenericTestActiveDomainObject(), new GenericTestActiveDomainObject());
        CollectionReducer.findOnlyOne("1", stream, t -> t.getId(), IllegalStateException::new);
    }

    @Test
    public void testReducerOkay() {
        final List<AbstractGenericFunctionalIdentifiableDomainObject<Integer>> stream = new ArrayList<>(
                Arrays.asList(new GenericTestActiveDomainObject(), new GenericTestActiveDomainObject()));
        CollectionReducer.findOnlyOne("1", stream.stream(), t -> t.getId(), IllegalStateException::new);
    }
}
