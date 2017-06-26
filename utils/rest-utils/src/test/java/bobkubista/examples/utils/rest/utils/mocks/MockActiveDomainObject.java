package bobkubista.examples.utils.rest.utils.mocks;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;

public class MockActiveDomainObject extends AbstractGenericActiveDomainObject {

	private static final long serialVersionUID = 1L;

	private Long id = 1L;

	/**
	 * constructor
	 */
	public MockActiveDomainObject() {
		super(true, "testObject");
	}

	public MockActiveDomainObject(final Long id, final String functionalId) {
		super(true, functionalId);
		this.id = id;
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
