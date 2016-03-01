package bobkubista.examples.utils.service.jpa.persistance.spring.dao;

import java.io.Serializable;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import bobkubista.examples.utils.service.jpa.persistance.dao.GenericFunctionalIdentifiableDao;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericFunctionalIdentifiableEntity;

/**
 * Abstract IT test for Dao
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            {@link AbstractGenericFunctionalIdentifiableEntity}
 * @param <ID>
 *            The identifier
 */
public abstract class AbstractFunctionalDaoIT<TYPE extends AbstractGenericFunctionalIdentifiableEntity<ID>, ID extends Serializable> extends AbstractIdentifiableDaoIT<TYPE, ID> {

    /**
     * Default constructor
     */
    public AbstractFunctionalDaoIT() {
        super();
    }

    /**
     * Test getEntityByFunctionalId
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/DaoIT.xml")
    public void shouldGetEntityByFunctionalId() {
        final TYPE entity = this.getDao()
                .getByFunctionalId(this.getFunctionalId());
        this.checkAssertion(entity);
    }

    /**
     * Get the {@link AbstractGenericFunctionalIdentifiableEntityDao}
     */
    @Override
    protected abstract GenericFunctionalIdentifiableDao<TYPE, ID> getDao();

    /**
     *
     * @return the functional Id
     */
    protected abstract String getFunctionalId();
}
