package bobkubista.examples.utils.service.jpa.persistance.mocks;

import java.util.Collection;
import java.util.LinkedList;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;

public class MockDomainCollection extends AbstractGenericDomainObjectCollection<MockDomain> {

    private static final long serialVersionUID = 2190490622518942077L;
    private final Collection<MockDomain> domainCollection = new LinkedList<>();

    @Override
    public Collection<MockDomain> getDomainCollection() {
        return this.domainCollection;
    }

}
