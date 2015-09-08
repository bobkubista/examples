package bobkubista.examples.webapps.wicket.Navomatic;

import org.apache.wicket.markup.html.WebPage;

public class Page1 extends WebPage {

	private static final long serialVersionUID = -1539811639921742468L;

	public Page1() {
		this.add(new NavomaticBorder("navomaticBorder"));
	}
}
