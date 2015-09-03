package bobkubista.examples.utils.domain.model.domainmodel.identification;

import bobkubista.examples.utils.domain.model.domainmodel.identification.ActiveDomainObject;

/**
 * Abstract domain object for testing
 *
 * @author bkubista
 *
 */
public class GenericTestActiveDomainObject extends ActiveDomainObject<Integer> {

	private static final long serialVersionUID = 1L;

	private final boolean active = true;
	private final String functionalId = "testObject";
	private final Integer id = 1;

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