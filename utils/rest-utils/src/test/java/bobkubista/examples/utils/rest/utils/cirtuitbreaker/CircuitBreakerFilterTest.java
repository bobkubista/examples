package bobkubista.examples.utils.rest.utils.cirtuitbreaker;

import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;

import org.junit.Test;

public class CircuitBreakerFilterTest {

    @Test
    public void testFilterClientRequestContext() throws CircuitOpenedException, IOException, URISyntaxException {
        final CircuitBreakerFilter filter = new CircuitBreakerFilter();

        final ClientRequestContext requestContext = mock(ClientRequestContext.class);
        final URI mockUri = new URI("http", "host", "", "");
        when(requestContext.getUri()).thenReturn(mockUri);

        filter.filter(requestContext);

        final ClientResponseContext responseContext = mock(ClientResponseContext.class);
        when(responseContext.getStatus()).thenReturn(500);

        filter.filter(requestContext, responseContext);
    }

    @Test
    public void testFilterClientRequestContextClose() throws CircuitOpenedException, IOException, URISyntaxException {
        final CircuitBreakerFilter filter = new CircuitBreakerFilter();

        final ClientRequestContext requestContext = mock(ClientRequestContext.class);
        final URI mockUri = new URI("http", "host", "", "");
        when(requestContext.getUri()).thenReturn(mockUri);

        filter.filter(requestContext);

        final ClientResponseContext responseContext = mock(ClientResponseContext.class);
        when(responseContext.getStatus()).thenReturn(500);

        filter.filter(requestContext, responseContext);

        filter.filter(requestContext);
    }
}
