package bobkubista.examples.utils.rest.utils.mocks;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;

public class MockActiveDomainObject extends AbstractGenericActiveDomainObject<Integer> {

    private static final long serialVersionUID = 1L;

    private Integer id = 1;

    /**
     * constructor
     */
    public MockActiveDomainObject() {
        super(true, "testObject");
    }

    public MockActiveDomainObject(final Integer id, final String functionalId) {
        super(true, functionalId);
        this.id = id;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(final Integer id) {
        this.id = id;
    }
}
