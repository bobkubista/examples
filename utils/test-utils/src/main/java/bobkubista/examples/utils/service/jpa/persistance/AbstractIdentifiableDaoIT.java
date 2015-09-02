package bobkubista.examples.utils.service.jpa.persistance;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericDao;
import bobkubista.examples.utils.service.jpa.persistance.entity.IdentifiableEntity;

public abstract class AbstractIdentifiableDaoIT<TYPE extends IdentifiableEntity<ID>, ID extends Serializable> extends BaseIntegrationTest {

	@PersistenceContext
	private EntityManager em;

	public AbstractIdentifiableDaoIT() {
		super();
	}

	@Before
	public void clearCache() {
		this.em.getEntityManagerFactory().getCache().evictAll();
	}

	@Test
	@Transactional
	@DatabaseSetup(value = "/dataset/given/DaoIT.xml")
	public void shouldDeleteEntity() {
		// GIVEN
		final TYPE entity = this.getDao().getById(this.getId());
		// WHEN
		this.getDao().delete(entity);
		// THEN
		Assert.assertNull(this.getDao().getById(this.getId()));
	}

	@Test
	@Transactional
	@DatabaseSetup(value = "/dataset/given/DaoIT.xml")
	public void shouldGetById() {
		// GIVEN
		// WHEN
		final TYPE entity = this.getDao().getById(this.getId());
		// THEN
		this.checkAssertion(entity);
	}

	@Test
	@Transactional
	@DatabaseSetup(value = "/dataset/given/DaoIT.xml")
	public void shouldUpdateEntity() {
		TYPE entity = this.getDao().getById(this.getId());
		this.updateEntity(entity);
		entity = this.getDao().update(entity);
		this.checkAssertionUpdated(entity);
	}

	protected abstract void checkAssertion(TYPE entity);

	protected abstract void checkAssertionUpdated(TYPE entity);

	protected abstract AbstractGenericDao<TYPE, ID> getDao();

	protected abstract ID getId();

	protected abstract void updateEntity(TYPE entity);

}