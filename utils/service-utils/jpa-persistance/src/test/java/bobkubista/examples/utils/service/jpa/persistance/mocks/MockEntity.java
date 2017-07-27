package bobkubista.examples.utils.service.jpa.persistance.mocks;

import java.sql.Timestamp;

import bobkubista.examples.utils.service.jpa.persistance.annotation.SearchField;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;

public class MockEntity extends AbstractGenericActiveEntity {

	private static final long serialVersionUID = -1029339275802750276L;
	@SearchField(fieldName = "fid")
	private String functionalId = "foo";
	@SearchField(fieldName = "id")
	private Long id = 1L;

	@Override
	public String getFunctionalId() {
		return this.functionalId;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public Timestamp getUpdatedDate() {
		return new Timestamp(0);
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
