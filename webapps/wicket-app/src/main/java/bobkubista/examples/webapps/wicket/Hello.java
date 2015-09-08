/**
 *
 */
package bobkubista.examples.webapps.wicket;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * @author Bob Kubista
 *
 */
public class Hello extends WebPage {

	public Hello(final PageParameters parameters) {

		this.add(new Label("message", "Hello World, Wicket"));

	}
}
