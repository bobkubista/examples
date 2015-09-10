package bobkubista.examples.utils.service.jpa.persistance.mocks;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import bobkubista.examples.utils.service.jpa.persistance.dao.ActiveEntityDao;

public class MockDao extends ActiveEntityDao<MockEntity, Long> {

	@Override
	protected Path<String> getFunctionalIdField(Root<MockEntity> entity) {
		return entity.<String> get("functionalId");
	}

}
