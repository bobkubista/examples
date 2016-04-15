/**
 *
 */
package bobkubista.examples.utils.clients.todo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Bob Kubista
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ClientBuilder.class)
public class TodoProxyTest {

    final Client mockClient = Mockito.mock(Client.class);

    final Response mockResponse = Mockito.mock(Response.class);

    private final TodoProxy proxy = new TodoProxy();

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
        Mockito.when(mockWebTarget.request(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON))
                .thenReturn(mockBuilder);

        Mockito.when(this.mockClient.target(Matchers.anyString()))
                .thenReturn(mockWebTarget);
        Mockito.when(mockWebTarget.queryParam(Matchers.anyString(), Matchers.any()))
                .thenReturn(mockWebTarget);

        PowerMockito.mockStatic(ClientBuilder.class);
        PowerMockito.when(ClientBuilder.newClient())
                .thenReturn(this.mockClient);
    }

    @Test
    public void testGetBasePath() {
        Assert.assertEquals("", this.proxy.getBasePath());
    }

}
