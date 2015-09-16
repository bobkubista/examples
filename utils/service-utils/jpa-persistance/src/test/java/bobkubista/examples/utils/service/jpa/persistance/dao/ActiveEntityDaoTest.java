/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.dao;

import org.junit.Assert;
import org.junit.Test;

import bobkubista.examples.utils.service.jpa.persistance.mocks.MockDao;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockEntity;

/**
 * @author Bob
 *
 */
public class ActiveEntityDaoTest {

    MockDao dao = new MockDao();

    /**
     * Test method for
     * {@link bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericDao#getEntityClass()}
     * .
     */
    @Test
    public void testGetEntityClass() {
        Assert.assertEquals(MockEntity.class, this.dao.getEntityClass());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.service.jpa.persistance.dao.AbstractGenericDao#getIdentifierClass()}
     * .
     */
    @Test
    public void testGetIdentifierClass() {
        Assert.assertEquals(Long.class, this.dao.getIdentifierClass());
    }

}
