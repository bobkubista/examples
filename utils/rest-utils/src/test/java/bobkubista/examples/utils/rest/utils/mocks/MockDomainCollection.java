/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.mocks;

import java.util.ArrayList;
import java.util.Collection;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;

/**
 * @author Bob
 *
 */
public class MockDomainCollection extends AbstractGenericDomainObjectCollection<MockActiveDomainObject> {

    private static final long serialVersionUID = -7107996031356278730L;
    private final Collection<MockActiveDomainObject> collection = new ArrayList<>();

    @Override
    public Collection<MockActiveDomainObject> getDomainCollection() {
        return this.collection;
    }

}
