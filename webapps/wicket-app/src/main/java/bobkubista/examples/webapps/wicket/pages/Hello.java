package bobkubista.examples.webapps.wicket.pages;

import org.apache.wicket.markup.html.basic.Label;

import bobkubista.examples.webapps.wicket.pages.base.BasePage;

public class Hello extends BasePage {

	private static final long serialVersionUID = -3796141589395048837L;

	public Hello() {
		this.add(new Label("body", "This is in the body"));
	}

}
