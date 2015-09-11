package bobkubista.example.utils.property;

import java.io.Serializable;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebAppListener implements ServletContextListener, Serializable {

	/**
	 * Version location
	 */
	public static final String VERSION = "version";

	private static final long serialVersionUID = -5627199127957835773L;

	@Override
	public void contextDestroyed(final ServletContextEvent servletContextEvent) {
		// CHECKSTYLE IGNORE
	}

	@Override
	public void contextInitialized(final ServletContextEvent servletContextEvent) {
	}

}
