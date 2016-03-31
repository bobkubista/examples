package bobkubista.examples.utils.service.jpa.persistance.spring.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import bobkubista.examples.utils.service.jpa.persistance.dao.GenericIdentifiableDao;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractIdentifiableEntity;

/**
 *
 * @author Bob Kubista
 *
 * @param <TYPE>
 *            {@link AbstractIdentifiableEntity}
 * @param <ID>
 *            identifier
 */
public abstract class AbstractIdentifiableDaoIT<TYPE extends AbstractIdentifiableEntity<ID>, ID extends Serializable> implements BaseIntegrationDaoTest {

    @PersistenceContext
    private EntityManager em;

    /**
     * Constructor
     */
    public AbstractIdentifiableDaoIT() {
        super();
    }

    /**
     * Clear the cache
     */
    @Before
    public void clearCache() {
        this.em.getEntityManagerFactory()
                .getCache()
                .evictAll();
    }

    /**
     * Test is delete works
     */
    @Test
    @Transactional
    @DatabaseSetup(value = "/dataset/given/DaoIT.xml")
    public void shouldDeleteEntity() {
        // GIVEN
        final TYPE entity = this.getDao()
                .getById(this.getId())
                .orElseThrow(NotFoundException::new);
        // WHEN
        this.getDao()
                .delete(entity);
        // THEN
        Assert.assertNull(this.getDao()
                .getById(this.getId()));
    }

    /**
     * Test if getById works
     */
    @Test
    @Transactional
    @DatabaseSetup(value = "/dataset/given/DaoIT.xml")
    public void shouldGetById() {
        // GIVEN
        // WHEN
        final TYPE entity = this.getDao()
                .getById(this.getId())
                .orElseThrow(NotFoundException::new);
        // THEN
        this.checkAssertion(entity);
    }

    /**
     * Test is update works
     */
    @Test
    @Transactional
    @DatabaseSetup(value = "/dataset/given/DaoIT.xml")
    public void shouldUpdateEntity() {
        TYPE entity = this.getDao()
                .getById(this.getId())
                .orElseThrow(NotFoundException::new);
        this.updateEntity(entity);
        entity = this.getDao()
                .update(entity)
                .get();
        this.checkAssertionUpdated(entity);
    }

    /**
     *
     * @param entity
     *            Check the TYPE
     */
    protected abstract void checkAssertion(TYPE entity);

    /**
     *
     * @param entity
     *            check if the TYPE is updated
     */
    protected abstract void checkAssertionUpdated(TYPE entity);

    /**
     *
     * @return get the {@link AbstractGenericIdentifiableDao}
     */
    protected abstract GenericIdentifiableDao<TYPE, ID> getDao();

    /**
     *
     * @return the id
     */
    protected abstract ID getId();

    /**
     *
     * @param entity
     *            update some fields for updating
     */
    protected abstract void updateEntity(TYPE entity);

}
