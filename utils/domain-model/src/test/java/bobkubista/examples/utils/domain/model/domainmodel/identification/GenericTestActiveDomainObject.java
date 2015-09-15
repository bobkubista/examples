package bobkubista.examples.utils.domain.model.domainmodel.identification;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;

/**
 * Abstract domain object for testing
 *
 * @author bkubista
 *
 */
public class GenericTestActiveDomainObject extends AbstractGenericActiveDomainObject<Integer> {

	private static final long serialVersionUID = 1L;

	private final Integer id = 1;
	private final boolean active = true;
	private final String functionalId = "testObject";

	@Override
	public String getFunctionalId() {
		return this.functionalId;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public boolean isActive() {
		return this.active;
	}

	@Override
	public void setActive(final boolean active) {

	}

	@Override
	public void setFunctionalId(final String functionalId) {

	}

	@Override
	public void setId(final Integer id) {

	}
}