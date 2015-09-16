package bobkubista.examples.utils.service.jpa.persistance;

import java.io.Serializable;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import bobkubista.examples.utils.service.jpa.persistance.dao.FunctionalIdentifiableEntityDao;
import bobkubista.examples.utils.service.jpa.persistance.entity.FunctionalIdentifiableEntity;

/**
 * Abstract IT test for Dao
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            {@link FunctionalIdentifiableEntity}
 * @param <ID>
 *            The identifier
 */
public abstract class AbstractFunctionalDaoIT<TYPE extends FunctionalIdentifiableEntity<ID>, ID extends Serializable> extends AbstractIdentifiableDaoIT<TYPE, ID> {

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
        final TYPE entity = this.getDao().getByFunctionalId(this.getFunctionalId());
        this.checkAssertion(entity);
    }

    /**
     * Get the {@link FunctionalIdentifiableEntityDao}
     */
    @Override
    protected abstract FunctionalIdentifiableEntityDao<TYPE, ID> getDao();

    /**
     *
     * @return the functional Id
     */
    protected abstract String getFunctionalId();
}
