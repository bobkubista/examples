/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.facade;

import java.sql.Date;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockDomain;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockFacade;

/**
 * @author Bob Kubista
 *
 */
public class GenericActiveFacadeTest {

    MockFacade facade = new MockFacade();

    /**
     * Test method for
     * {@link bobkubista.examples.utils.service.jpa.persistance.facade. AbstractGenericIdentifiableFacade#create(bobkubista.examples.utils.domain.model. domainmodel.identification.DomainObject)}
     * .
     */
    @Test
    public void testCreate() {
        final Response result = this.facade.create(new MockDomain());
        Assert.assertNotNull(result);
        Assert.assertEquals(201, result.getStatus());
        Assert.assertNotNull(result.getLocation());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNull() {
        this.facade.create(null);
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.service.jpa.persistance.facade. AbstractGenericIdentifiableFacade#delete(java.io.Serializable)}
     * .
     */
    @Test
    public void testDelete() {
        final Response result = this.facade.delete(1L);
        Assert.assertNotNull(result);
        Assert.assertEquals(204, result.getStatus());
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() {
        this.facade.delete(2L);
        Assert.fail();
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.service.jpa.persistance.facade.
     * AbstractGenericIdentifiableFacade#getAll(java.util.List, Integer,
     * Integer))} .
     */
    @Test
    public void testGetAll() {
        final SearchBean search = new SearchBean().setMaxResults(2)
                .setPage(0);
        final Response result = this.facade.getAll(search);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getEntity());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.service.jpa.persistance.facade.
     * AbstractGenericActiveFacade#getAllActive(java.util.List, Integer,
     * Integer))} .
     */
    @Test
    public void testGetAllActive() {
        final SearchBean search = new SearchBean();
        final Response result = this.facade.getAllActive(search);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getEntity());
    }

    @Test
    public void testGetAllActiveSecondPageWithMoreResults() {
        final SearchBean search = new SearchBean().setMaxResults(1)
                .setPage(1);
        final Response result = this.facade.getAllActive(search);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getEntity());
    }

    @Test
    public void testGetAllSecondPageWithMoreResults() {
        final SearchBean search = new SearchBean().setMaxResults(1)
                .setPage(1);
        final Response result = this.facade.getAll(search);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getEntity());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.service.jpa.persistance.facade. AbstractGenericFunctionalIdentifiableFacade#getByFunctionalId(java.lang.String)}
     * .
     */
    @Test
    public void testGetByFunctionalId() {
        final Request request = Mockito.mock(Request.class);
        Mockito.when(request.evaluatePreconditions(Matchers.any(Date.class)))
                .thenReturn(null);
        final Response result = this.facade.getByFunctionalId("Bla", request);
        Assert.assertNotNull(result);
        Assert.assertEquals(200, result.getStatus());
        Assert.assertNotNull(result.getEntity());
    }

    /**
     *
     */
    @Test(expected = NotFoundException.class)
    public void testGetByFunctionalIdNull() {
        final Request request = Mockito.mock(Request.class);
        Mockito.when(request.evaluatePreconditions(Matchers.any(Date.class)))
                .thenReturn(null);
        this.facade.getByFunctionalId("", request);
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.service.jpa.persistance.facade. AbstractGenericFunctionalIdentifiableFacade#getByFunctionalId(java.lang.String)}
     * .
     */
    @Test
    public void testGetByFunctionalIdShouldNotGet() {
        final Request request = Mockito.mock(Request.class);
        Mockito.when(request.evaluatePreconditions(Matchers.any(Date.class)))
                .thenReturn(Response.notModified());
        final Response result = this.facade.getByFunctionalId("Bla", request);
        Assert.assertNotNull(result);
        Assert.assertEquals(304, result.getStatus());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.service.jpa.persistance.facade. AbstractGenericIdentifiableFacade#getByID(java.io.Serializable)}
     * .
     */
    @Test
    public void testGetByID() {
        final Request request = Mockito.mock(Request.class);
        Mockito.when(request.evaluatePreconditions(Matchers.any(Date.class)))
                .thenReturn(null);
        final Response result = this.facade.getByID(1L, request);
        Assert.assertNotNull(result);
        Assert.assertEquals(200, result.getStatus());
        Assert.assertNotNull(result.getEntity());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.service.jpa.persistance.facade. AbstractGenericIdentifiableFacade#getByID(java.io.Serializable)}
     * .
     */
    @Test
    public void testGetByIDNotUpdated() {
        final Request request = Mockito.mock(Request.class);
        Mockito.when(request.evaluatePreconditions(Matchers.any(Date.class)))
                .thenReturn(Response.notModified());
        final Response result = this.facade.getByID(1L, request);
        Assert.assertNotNull(result);
        Assert.assertEquals(304, result.getStatus());
    }

    /**
     *
     */
    @Test(expected = NotFoundException.class)
    public void testGetByIDNull() {
        final Request request = Mockito.mock(Request.class);
        Mockito.when(request.evaluatePreconditions(Matchers.any(Date.class)))
                .thenReturn(null);
        this.facade.getByID(2L, request);
        Assert.fail();
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.service.jpa.persistance.facade. AbstractGenericFunctionalIdentifiableFacade#getIdByFunctionalId(java.lang.String) }
     * .
     */
    @Test
    public void testGetIdByFunctionalId() {
        final Response result = this.facade.getIdByFunctionalId("Bla");
        Assert.assertNotNull(result);
        Assert.assertEquals(200, result.getStatus());
        Assert.assertNotNull(result.getEntity());
    }

    @Test(expected = NotFoundException.class)
    public void testGetIdByFunctionalIdNull() {
        this.facade.getIdByFunctionalId("");
        Assert.fail();
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.service.jpa.persistance.facade. AbstractGenericIdentifiableFacade#update(bobkubista.examples.utils.domain.model. domainmodel.identification.DomainObject)}
     * .
     */
    @Test
    public void testUpdate() {
        final MockDomain object = new MockDomain();
        final Request request = Mockito.mock(Request.class);
        Mockito.when(request.evaluatePreconditions(Matchers.any(Date.class)))
                .thenReturn(null);
        final Response result = this.facade.update(object, request);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getEntity());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.service.jpa.persistance.facade. AbstractGenericIdentifiableFacade#update(bobkubista.examples.utils.domain.model. domainmodel.identification.DomainObject)}
     * .
     */
    @Test
    public void testUpdateShouldNotUpdate() {
        final MockDomain object = new MockDomain();
        final Request request = Mockito.mock(Request.class);
        Mockito.when(request.evaluatePreconditions(Matchers.any(Date.class)))
                .thenReturn(Response.status(412));
        final Response result = this.facade.update(object, request);

        Assert.assertNotNull(result);
        Assert.assertEquals(412, result.getStatus());
    }

}
