package bobkubista.example.utils.property;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebAppListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO load all server props?
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// CHECKSTYLE IGNORE
	}

}
