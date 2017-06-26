package bobkubista.examples.utils.service.jpa.persistance.spring.dao;

import java.util.Optional;

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
 */
public abstract class AbstractFunctionalDaoIT<TYPE extends AbstractGenericFunctionalIdentifiableEntity>
		extends AbstractIdentifiableDaoIT<TYPE> {

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
		final Optional<TYPE> entity = this.getDao()
				.getByFunctionalId(this.getFunctionalId());
		this.checkAssertion(entity.orElseThrow(AssertionError::new));
	}

	/**
	 * Get the {@link AbstractGenericFunctionalIdentifiableEntityDao}
	 */
	@Override
	protected abstract GenericFunctionalIdentifiableDao<TYPE> getDao();

	/**
	 *
	 * @return the functional Id
	 */
	protected abstract String getFunctionalId();
}
