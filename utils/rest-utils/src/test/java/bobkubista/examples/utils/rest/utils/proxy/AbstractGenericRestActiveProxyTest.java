/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.proxy;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.rest.utils.mocks.MockActiveDomainObject;
import bobkubista.examples.utils.rest.utils.mocks.MockActiveProxy;

/**
 * @see <a href=
 *      "http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/test/web/client/MockRestServiceServer.html">
 *      mock rest server</a>
 * @author Bob
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ClientBuilder.class)
public class AbstractGenericRestActiveProxyTest {

    final Client mockClient = Mockito.mock(Client.class);
    final Response mockResponse = Mockito.mock(Response.class);

    private final AbstractGenericRestActiveProxy<MockActiveDomainObject, Integer> proxy = new MockActiveProxy();

    @Before
    public void start() {
        Mockito.when(this.mockResponse.getStatus())
                .thenReturn(200);

        final Builder mockBuilder = Mockito.mock(Builder.class);
        Mockito.when(mockBuilder.get())
                .thenReturn(this.mockResponse);
        Mockito.when(mockBuilder.post(Matchers.any()))
                .thenReturn(this.mockResponse);
        Mockito.when(mockBuilder.put(Matchers.any()))
                .thenReturn(this.mockResponse);
        Mockito.when(mockBuilder.delete())
                .thenReturn(this.mockResponse);

        final WebTarget mockWebTarget = Mockito.mock(WebTarget.class);
        Mockito.when(mockWebTarget.path(Matchers.anyString()))
                .thenReturn(mockWebTarget);
        Mockito.when(mockWebTarget.queryParam(Matchers.anyString(), Matchers.any()))
                .thenReturn(mockWebTarget);
        Mockito.when(mockWebTarget.request(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON))
                .thenReturn(mockBuilder);

        Mockito.when(this.mockClient.target(Matchers.anyString()))
                .thenReturn(mockWebTarget);

        PowerMockito.mockStatic(ClientBuilder.class);
        PowerMockito.when(ClientBuilder.newClient())
                .thenReturn(this.mockClient);
        this.proxy.base();
    }

    @After
    public void stop() {
        this.proxy.close();
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestIdentifiableProxy#create(bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject)}
     * .
     */
    @Test
    public void testCreate() {
        Mockito.when(this.mockResponse.getStatus())
                .thenReturn(201);

        final Response result = this.proxy.create(new MockActiveDomainObject());
        Assert.assertEquals(201, result.getStatus());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestIdentifiableProxy#delete(java.io.Serializable)}
     * .
     */
    @Test
    public void testDelete() {
        Mockito.when(this.mockResponse.getStatus())
                .thenReturn(200);

        final Response result = this.proxy.delete(1);
        Assert.assertEquals(200, result.getStatus());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestIdentifiableProxy#getAll()}
     * .
     */
    @Test
    public void testGetAll() {
        Mockito.when(this.mockResponse.getStatus())
                .thenReturn(200);

        final Response result = this.proxy.getAll(new SearchBean());
        Assert.assertEquals(200, result.getStatus());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestActiveProxy#getAllActive()}
     * .
     */
    @Test
    public void testGetAllActive() {
        Mockito.when(this.mockResponse.getStatus())
                .thenReturn(200);

        final Response result = this.proxy.getAllActive(null, 0, 100);
        Assert.assertEquals(200, result.getStatus());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestFunctionalIdentifiableProxy#getByFunctionalId(java.lang.String)}
     * .
     */
    @Test
    public void testGetByFunctionalId() {
        Mockito.when(this.mockResponse.getStatus())
                .thenReturn(200);

        final Response result = this.proxy.getByFunctionalId("blaat");
        Assert.assertEquals(200, result.getStatus());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestIdentifiableProxy#getByID(java.io.Serializable)}
     * .
     */
    @Test
    public void testGetByID() {
        Mockito.when(this.mockResponse.getStatus())
                .thenReturn(200);

        final Response result = this.proxy.getByID(1);
        Assert.assertEquals(200, result.getStatus());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestFunctionalIdentifiableProxy#getIdByFunctionalId(java.lang.String)}
     * .
     */
    @Test
    public void testGetIdByFunctionalId() {
        Mockito.when(this.mockResponse.getStatus())
                .thenReturn(200);

        final Response result = this.proxy.getIdByFunctionalId("blaat");
        Assert.assertEquals(200, result.getStatus());
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestIdentifiableProxy#update(bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject)}
     * .
     */
    @Test
    public void testUpdate() {
        Mockito.when(this.mockResponse.getStatus())
                .thenReturn(200);

        final Response result = this.proxy.update(new MockActiveDomainObject());
        Assert.assertEquals(200, result.getStatus());
    }
}
