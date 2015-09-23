package bobkubista.examples.utils.service.jpa.persistance.mocks;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;

public class MockDomain extends AbstractGenericActiveDomainObject<Long> {

    private static final long serialVersionUID = -3472971306874956522L;
    private String functionalId;
    private Long id;

    public MockDomain() {
        super(true);
    }

    @Override
    public String getFunctionalId() {
        return this.functionalId;
    }

    @Override

    public Long getId() {
        return this.id;
    }

    @Override

    public void setFunctionalId(final String functionalId) {
        this.functionalId = functionalId;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

}
