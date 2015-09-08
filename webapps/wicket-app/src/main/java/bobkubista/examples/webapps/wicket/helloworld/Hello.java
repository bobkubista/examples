/**
 *
 */
package bobkubista.examples.webapps.wicket.helloworld;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * @author Bob Kubista
 *
 */
public class Hello extends WebPage {

	private static final long serialVersionUID = 8569314481611244857L;

	public Hello() {

		this.add(new Label("message", "Hello World, Wicket"));

	}
}
