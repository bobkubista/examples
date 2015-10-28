/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.mocks;

import java.util.ArrayList;
import java.util.Collection;

import org.mockito.Mockito;

import bobkubista.examples.utils.rest.utils.cache.AbstractActiveAutoCache;
import bobkubista.examples.utils.rest.utils.service.ActiveService;

/**
 * @author Bob
 *
 */
public class MockActiveCache extends AbstractActiveAutoCache<Integer, MockActiveDomainObject> {

    @Override
    protected ActiveService<MockActiveDomainObject, Integer> getActiveService() {
        @SuppressWarnings("unchecked")
        final ActiveService<MockActiveDomainObject, Integer> mockService = Mockito.mock(ActiveService.class);
        final Collection<MockActiveDomainObject> activeObjects = new ArrayList<>();
        activeObjects.add(this.buildActiveDomainObjectMock(1, "F1"));
        activeObjects.add(this.buildActiveDomainObjectMock(2, "F2"));
        Mockito.when(mockService.getAllActive()).thenReturn(activeObjects);

        Mockito.when(mockService.getIdByFunctionalId("F1")).thenReturn(1);
        Mockito.when(mockService.getByID(1)).thenReturn(this.buildActiveDomainObjectMock(1, "F1"));
        Mockito.when(mockService.getByID(2)).thenReturn(this.buildActiveDomainObjectMock(2, "F2"));
        return mockService;
    }

    private MockActiveDomainObject buildActiveDomainObjectMock(final Integer id, final String functionalId) {
        final MockActiveDomainObject result = new MockActiveDomainObject(id, functionalId);
        return result;
    }

}
