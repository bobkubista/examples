/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.mocks;

import java.util.ArrayList;
import java.util.Collection;

import org.mockito.Matchers;
import org.mockito.Mockito;

import bobkubista.examples.utils.rest.utils.cache.AbstractFunctionalAutoCache;
import bobkubista.examples.utils.rest.utils.service.FunctionalIdentifiableService;

/**
 * @author Bob
 *
 */
public class MockCache extends AbstractFunctionalAutoCache<Integer, MockActiveDomainObject> {

	@Override
	protected FunctionalIdentifiableService<MockActiveDomainObject, Integer> getFunctionalService() {
		@SuppressWarnings("unchecked")
		final FunctionalIdentifiableService<MockActiveDomainObject, Integer> mockService = Mockito.mock(FunctionalIdentifiableService.class);
		final Collection<MockActiveDomainObject> activeObjects = new ArrayList<>();
		activeObjects.add(this.buildActiveDomainObjectMock(1, "F1"));
		activeObjects.add(this.buildActiveDomainObjectMock(2, "F2"));
		Mockito.when(mockService.getAll(Matchers.anyString(), Matchers.anyInt(), Matchers.anyInt())).thenReturn(activeObjects);

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
