/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.mocks;

import java.time.Instant;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import org.mockito.Matchers;
import org.mockito.Mockito;

import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestActiveProxy;
import bobkubista.examples.utils.rest.utils.service.AbstractActiveService;
import bobkubista.examples.utils.rest.utils.service.ActiveService;

/**
 * @author Bob
 *
 */
@Deprecated
public class MockActiveService extends AbstractActiveService<MockActiveDomainObject, Integer, MockDomainCollection>
        implements ActiveService<MockActiveDomainObject, Integer, MockDomainCollection> {

    @Override
    protected MockDomainCollection getEmptyCollection() {
        return new MockDomainCollection();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected AbstractGenericRestActiveProxy<MockActiveDomainObject, Integer> getProxy() {
        final AbstractGenericRestActiveProxy<MockActiveDomainObject, Integer> mockProxy = Mockito.mock(AbstractGenericRestActiveProxy.class);

        final Response getAllResponse = Mockito.mock(Response.class);
        final MockDomainCollection mockDomainCollection = new MockDomainCollection();
        mockDomainCollection.getDomainCollection()
                .add(new MockActiveDomainObject(1, "F1"));
        mockDomainCollection.getDomainCollection()
                .add(new MockActiveDomainObject(2, "F2"));
        Mockito.when(getAllResponse.readEntity(MockDomainCollection.class))
                .thenReturn(mockDomainCollection);
        Mockito.when(getAllResponse.getStatus())
                .thenReturn(200);

        final Response mockSingleResponse = Mockito.mock(Response.class);
        Mockito.when(mockSingleResponse.readEntity(MockActiveDomainObject.class))
                .thenReturn(new MockActiveDomainObject(1, "F1"));
        Mockito.when(mockProxy.getByFunctionalId("F1"))
                .thenReturn(mockSingleResponse);
        Mockito.when(mockSingleResponse.getHeaderString(HttpHeaders.ETAG))
                .thenReturn(new EntityTag("tag").toString());
        Mockito.when(mockSingleResponse.getHeaderString(HttpHeaders.LAST_MODIFIED))
                .thenReturn(Instant.EPOCH.toString());

        final Response mockModifiedSingleResponse = Mockito.mock(Response.class);
        Mockito.when(mockModifiedSingleResponse.readEntity(MockActiveDomainObject.class))
                .thenReturn(new MockActiveDomainObject(1, "F1"));
        Mockito.when(mockModifiedSingleResponse.getStatus())
                .thenReturn(Status.OK.getStatusCode());
        Mockito.when(mockModifiedSingleResponse.getHeaderString(HttpHeaders.ETAG))
                .thenReturn(new EntityTag("tag").toString());
        Mockito.when(mockModifiedSingleResponse.getHeaderString(HttpHeaders.LAST_MODIFIED))
                .thenReturn(Instant.EPOCH.toString());

        final Response mockSingleNotModifiedResponse = Mockito.mock(Response.class);
        Mockito.when(mockSingleNotModifiedResponse.getStatus())
                .thenReturn(Status.NOT_MODIFIED.getStatusCode());

        final Response mockIdResponse = Mockito.mock(Response.class);
        Mockito.when(mockIdResponse.readEntity(Integer.class))
                .thenReturn(1);
        Mockito.when(mockProxy.getIdByFunctionalId("F1"))
                .thenReturn(mockIdResponse);

        Mockito.when(mockProxy.getAll(Matchers.any(SearchBean.class)))
                .thenReturn(getAllResponse);
        Mockito.when(mockProxy.getAllActive(Matchers.any(SearchBean.class)))
                .thenReturn(getAllResponse);

        Mockito.when(mockProxy.getByID(1))
                .thenReturn(mockSingleResponse);
        Mockito.when(mockProxy.update(new MockActiveDomainObject(1, "F1")))
                .thenReturn(mockSingleResponse);
        Mockito.when(mockProxy.getByID(1, new EntityTag("tag"), Instant.EPOCH))
                .thenReturn(mockModifiedSingleResponse);
        Mockito.when(mockProxy.getByID(Matchers.eq(1), Matchers.eq(new EntityTag("tag")), Matchers.eq(Instant.MAX)))
                .thenReturn(mockSingleNotModifiedResponse);

        final Response mockErrorResponse = Mockito.mock(Response.class);
        Mockito.when(mockErrorResponse.getStatus())
                .thenReturn(Status.INTERNAL_SERVER_ERROR.getStatusCode());
        final StatusType mockStatusType = Mockito.mock(StatusType.class);
        Mockito.when(mockStatusType.getReasonPhrase())
                .thenReturn("blaat");
        Mockito.when(mockErrorResponse.getStatusInfo())
                .thenReturn(mockStatusType);
        Mockito.when(mockProxy.getByID(Matchers.eq(1), Matchers.eq(new EntityTag("tag")), Matchers.eq(Instant.MIN)))
                .thenReturn(mockErrorResponse);

        Mockito.when(mockProxy.update(new MockActiveDomainObject(1, "F1"), new EntityTag("tag"), Instant.MAX))
                .thenReturn(mockModifiedSingleResponse);
        Mockito.when(mockProxy.update(new MockActiveDomainObject(1, "F1"), new EntityTag("tag"), Instant.MIN))
                .thenReturn(mockErrorResponse);

        return mockProxy;
    }

}
