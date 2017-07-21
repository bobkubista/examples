/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.facade;

import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import bobkubista.examples.utils.domain.model.api.ActiveSearchBean;
import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.service.jpa.persistance.mocks.MockFacade;

/**
 * @author Bob Kubista
 *
 */
public class GenericActiveFacadeTest {

	MockFacade facade = new MockFacade();

	/**
	 * Test method for
	 * {@link bobkubista.examples.utils.service.jpa.persistance.facade.
	 * AbstractGenericActiveFacade#getAllActive(java.util.List, Integer,
	 * Integer))} .
	 */
	@Test
	public void testGetAllActive() {
		final SearchBean search = new ActiveSearchBean();
		final Response result = this.facade.getAll(search);
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getEntity());
	}

	@Test
	public void testGetAllActiveSecondPageWithMoreResults() {
		final SearchBean search = new ActiveSearchBean().setMaxResults(1)
				.setPage(1);
		final Response result = this.facade.getAll(search);
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getEntity());
	}

	@Test(expected = ServerErrorException.class)
	public void testGetAllException() {
		this.facade.getAll(new ActiveSearchBean().setPage(4)
				.setMaxResults(14));
	}
}
