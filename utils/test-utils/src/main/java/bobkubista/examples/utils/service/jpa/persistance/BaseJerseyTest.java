package bobkubista.examples.utils.service.jpa.persistance;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
//import org.junit.runner.RunWith;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/jersey-config.xml" })
public abstract class BaseJerseyTest extends JerseyTest {

	public abstract ResourceConfig configure(ResourceConfig rc);

	@Override
	protected Application configure() {
		final ResourceConfig rc = new ResourceConfig();
		// final ResourceConfig rc = new
		// ResourceConfig().register(RequestContextFilter.class);

		rc.property("contextConfigLocation", "classpath:jersey-config.xml");
		return this.configure(rc);
	}

}
