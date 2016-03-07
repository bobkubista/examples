/**
 *
 */
package bobkubista.examples.services.rest.user;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.google.common.collect.Ordering;

import bobkubista.examples.services.api.user.domain.User;
import bobkubista.examples.services.api.user.domain.UserCollection;
import bobkubista.examples.utils.service.jpa.persistance.spring.jersey.dbunit.AbstractActiveJerseyIT;

/**
 * @author Bob
 *
 */
public class UserFacadeIT extends AbstractActiveJerseyIT<User, Long, UserCollection> {

    private static final String FUNCTIONALID = "bla@foo.bar";

    private static final Long ID = 1L;

    private static final String PARTIAL_FUNCTIONAL_ID = "bla@";

    @Override
    public ResourceConfig configure(final ResourceConfig rc) {
        return rc.register(UserFacade.class);
    }

    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void isAuthenticatedTest() {
        final Response response = this.target("1/admin")
                .request()
                .get();

        Assert.assertNotNull(response.getHeaderString(HttpHeaders.CACHE_CONTROL));
        Assert.assertEquals("no-cache", response.getHeaderString(HttpHeaders.CACHE_CONTROL));

        Assert.assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void isNotAuthenticatedTest() {
        Assert.assertEquals(Status.UNAUTHORIZED.getStatusCode(), this.target("1/user")
                .request()
                .get()
                .getStatus());
    }

    @Override
    protected void checkResponseGetAll(final UserCollection response, final int size) {
        Assert.assertNotNull(response);
        Assert.assertEquals(size, response.getDomainCollection()
                .size());
    }

    @Override
    protected void checkSingle(final User response) {
        Assert.assertNotNull(response);
        Assert.assertEquals(this.getFunctionalId(), response.getFunctionalId());
        Assert.assertTrue(response.isActive());
    }

    @Override
    protected void checkSorting(final UserCollection response, final boolean reverse) {
        if (reverse) {
            Assert.assertTrue(Ordering.from((o1, o2) -> (int) (((User) o2).getId() - ((User) o1).getId()))
                    .isOrdered(response.getDomainCollection()));
        } else {
            Assert.assertTrue(Ordering.from((o1, o2) -> (int) (((User) o1).getId() - ((User) o2).getId()))
                    .isOrdered(response.getDomainCollection()));
        }
    }

    @Override
    protected void checkUpdated(final User response) {
        Assert.assertNotNull(response);
        Assert.assertEquals("123", response.getEncryptedPassword());
    }

    @Override
    protected User create() {
        final User user = new User();

        user.setActive(true);
        user.setEncryptedPassword("123");
        user.setFunctionalId("bla2@foo.bar");
        user.setId(3L);
        user.setName("Foo Bar2");

        return user;
    }

    @Override
    protected int expectedSize() {
        return 2;
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
    protected String getIdField() {
        return "id";
    }

    @Override
    protected String getPartionFunctionalId() {
        return PARTIAL_FUNCTIONAL_ID;
    }

    @Override
    protected User update(final User response) {
        response.setEncryptedPassword("123");
        return response;
    }

}
