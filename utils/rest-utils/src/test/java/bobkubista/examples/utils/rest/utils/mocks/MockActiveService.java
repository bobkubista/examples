/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.mocks;

import javax.ws.rs.core.Response;

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
public class MockActiveService extends AbstractActiveService<MockActiveDomainObject, Integer, MockDomainCollection>implements ActiveService<MockActiveDomainObject, Integer> {

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
        Mockito.when(mockProxy.getAllActive(Matchers.anyListOf(String.class), Matchers.anyInt(), Matchers.anyInt()))
                .thenReturn(getAllResponse);

        final Response mockSingleResponse = Mockito.mock(Response.class);
        Mockito.when(mockSingleResponse.readEntity(MockActiveDomainObject.class))
                .thenReturn(new MockActiveDomainObject(1, "F1"));
        Mockito.when(mockProxy.getByFunctionalId("F1"))
                .thenReturn(mockSingleResponse);

        final Response mockIdResponse = Mockito.mock(Response.class);
        Mockito.when(mockIdResponse.readEntity(Integer.class))
                .thenReturn(1);
        Mockito.when(mockProxy.getIdByFunctionalId("F1"))
                .thenReturn(mockIdResponse);

        Mockito.when(mockProxy.getAll(Matchers.any(SearchBean.class)))
                .thenReturn(getAllResponse);

        Mockito.when(mockProxy.getByID(1))
                .thenReturn(mockSingleResponse);
        Mockito.when(mockProxy.update(new MockActiveDomainObject(1, "F1")))
                .thenReturn(mockSingleResponse);

        return mockProxy;
    }

}
