package bobkubista.examples.utils.service.jpa.persistance.mocks;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;

public class MockDomain extends AbstractGenericActiveDomainObject<Long> {

    private static final long serialVersionUID = -3472971306874956522L;
    private Long id;

    public MockDomain() {
        super(true, "blaat");
    }

    @Override

    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

}
