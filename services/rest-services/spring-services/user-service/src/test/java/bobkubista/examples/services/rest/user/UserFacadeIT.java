/**
 *
 */
package bobkubista.examples.services.rest.user;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;

import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.services.api.user.domain.UserCollection;
import bobkubista.examples.utils.service.jpa.persistance.AbstractFunctionalJerseyIT;

/**
 * @author Bob
 *
 */
public class UserFacadeIT extends AbstractFunctionalJerseyIT<User, Long, UserCollection> {

    private static final String FUNCTIONALID = "bla@foo.bar";
    private static final Long ID = 1L;
    private static final String PARTIAL_FUNCTIONAL_ID = "bla@";

    @Override
    public ResourceConfig configure(ResourceConfig rc) {
        return rc.register(UserFacade.class);
    }

    @Override
    protected void checkResponseGetAll(UserCollection response, int size) {
        Assert.assertNotNull(response);
        Assert.assertEquals(size, response.getDomainCollection().size());
        Assert.assertEquals(this.getId(), response.getDomainCollection().iterator().next().getId());

    }

    @Override
    protected void checkSingle(User response) {
        Assert.assertNotNull(response);
        Assert.assertEquals(this.getFunctionalId(), response.getFunctionalId());
        Assert.assertTrue(response.isActive());

    }

    @Override
    protected void checkUpdated(User response) {
        Assert.assertNotNull(response);
        Assert.assertEquals("123", response.getEncryptedPassword());
    }

    @Override
    protected User create() {
        final User user = new User();

        user.setActive(true);
        user.setEncryptedPassword("123");
        user.setFunctionalId("bla2@foo.bar");
        user.setId(2L);
        user.setName("Foo Bar2");

        return user;
    }

    @Override
    protected String getFunctionalId() {
        return FUNCTIONALID;
    }

    @Override
    protected Long getId() {
        return ID;
    }

    @Override
    protected String getPartionFunctionalId() {
        return PARTIAL_FUNCTIONAL_ID;
    }

    @Override
    protected User update(User response) {
        response.setEncryptedPassword("123");
        return response;
    }

}
