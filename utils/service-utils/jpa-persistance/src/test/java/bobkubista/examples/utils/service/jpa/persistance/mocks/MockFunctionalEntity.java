/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.mocks;

import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericFunctionalIdentifiableEntity;

/**
 * @author Bob
 *
 */
public class MockFunctionalEntity extends AbstractGenericFunctionalIdentifiableEntity {

	private static final long serialVersionUID = -5916180822928979766L;

	private final String functionalId = "foo";
	private final Long id = 1L;

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

	}

	@Override
	public void setId(final Long id) {

	}

}
