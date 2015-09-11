package bobkubista.examples.utils.service.jpa.persistance;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.filter.RequestContextFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/jersey-config.xml" })
public abstract class BaseJerseyTest extends JerseyTest {

	public abstract ResourceConfig configure(ResourceConfig rc);

	@Override
	protected Application configure() {
		final ResourceConfig rc = new ResourceConfig().register(RequestContextFilter.class);

		rc.property("contextConfigLocation", "classpath:jersey-config.xml");
		return this.configure(rc);
	}

}
