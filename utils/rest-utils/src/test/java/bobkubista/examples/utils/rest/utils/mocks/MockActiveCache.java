/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.mocks;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.EntityTag;

import org.mockito.Matchers;
import org.mockito.Mockito;

import bobkubista.examples.utils.rest.utils.cache.AbstractActiveAutoCache;
import bobkubista.examples.utils.rest.utils.service.ActiveService;
import bobkubista.examples.utils.rest.utils.service.GenericETagModifiedDateDomainObjectDecorator;

/**
 * @author Bob
 *
 */
public class MockActiveCache extends AbstractActiveAutoCache<Integer, MockActiveDomainObject, MockDomainCollection> {

    @Override
    protected ActiveService<MockActiveDomainObject, Integer, MockDomainCollection> getActiveService() {
        @SuppressWarnings("unchecked")
        final ActiveService<MockActiveDomainObject, Integer, MockDomainCollection> mockService = Mockito.mock(ActiveService.class);
        final Collection<MockActiveDomainObject> activeObjects = new ArrayList<>();
        activeObjects.add(this.buildActiveDomainObjectMock(1, "F1"));
        activeObjects.add(this.buildActiveDomainObjectMock(2, "F2"));
        final MockDomainCollection col = new MockDomainCollection();
        col.setDomainCollection(activeObjects);
        Mockito.when(mockService.getAllActive(Matchers.anyListOf(String.class), Matchers.anyInt(), Matchers.anyInt()))
                .thenReturn(col);

        Mockito.when(mockService.getIdByFunctionalId("F1"))
                .thenReturn(1);
        Mockito.when(mockService.getByID(1))
                .thenReturn(new GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject>(new EntityTag("tag"), Instant.now(), this.buildActiveDomainObjectMock(1, "F1"),
                        null));
        Mockito.when(mockService.getByID(2))
                .thenReturn(new GenericETagModifiedDateDomainObjectDecorator<MockActiveDomainObject>(new EntityTag("tag"), Instant.now(), this.buildActiveDomainObjectMock(2, "F2"),
                        null));
        return mockService;
    }

    private MockActiveDomainObject buildActiveDomainObjectMock(final Integer id, final String functionalId) {
        final MockActiveDomainObject result = new MockActiveDomainObject(id, functionalId);
        return result;
    }

}
