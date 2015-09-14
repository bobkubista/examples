package bobkubista.examples.utils.services.spring.jpa.mocks;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import bobkubista.examples.utils.services.spring.jpa.dao.ActiveEntityDao;

public class MockDao extends ActiveEntityDao<MockEntity, Long> {

	@Override
	protected Path<String> getFunctionalIdField(Root<MockEntity> entity) {
		return entity.<String> get("functionalId");
	}

}
