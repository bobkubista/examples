package bobkubista.examples.utils.services.spring.jpa.mocks;

import bobkubista.examples.utils.services.spring.jpa.entity.ActiveEntity;

public class MockEntity extends ActiveEntity<Long> {

	private static final long serialVersionUID = -1029339275802750276L;
	private boolean active;
	private String functionalId;
	private Long id;

	@Override
	public String getFunctionalId() {
		return this.functionalId;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public boolean isActive() {
		return this.active;
	}

	@Override
	public void setActive(final boolean active) {
		this.active = active;
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
