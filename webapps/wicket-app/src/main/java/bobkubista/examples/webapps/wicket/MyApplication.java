/**
 *
 */
package bobkubista.examples.webapps.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * @author Bob Kubista
 *
 */
public class MyApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return Hello.class;
	}

}
