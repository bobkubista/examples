package bobkubista.examples.utils.service.jpa.persistance.mocks;

import bobkubista.examples.utils.service.jpa.persistance.dao.GenericActiveDAO;
import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;
import bobkubista.examples.utils.service.jpa.persistance.services.FunctionalIdentifiableEntityService;

public class MockEntityService implements ActiveEntityService<MockEntity, Long>, FunctionalIdentifiableEntityService<MockEntity, Long> {

    @Override
    public GenericActiveDAO<MockEntity, Long> getDAO() {
        return null;
    }

}
