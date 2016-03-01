package bobkubista.examples.utils.service.jpa.persistance.mocks;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericDao;
import bobkubista.examples.utils.service.jpa.persistance.dao.GenericActiveDAO;
import bobkubista.examples.utils.service.jpa.persistance.dao.GenericFunctionalIdentifiableDao;

public class MockDao extends AbstractGenericDao<MockEntity, Long>implements GenericActiveDAO<MockEntity, Long>, GenericFunctionalIdentifiableDao<MockEntity, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockDao.class);

    @Override
    public Path<String> getFunctionalIdField(final Root<MockEntity> entity) {
        return entity.<String> get("functionalId");
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

}
