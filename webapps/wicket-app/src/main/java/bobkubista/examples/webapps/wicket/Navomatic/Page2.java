package bobkubista.examples.webapps.wicket.Navomatic;

import org.apache.wicket.markup.html.WebPage;

public class Page2 extends WebPage {
	public Page2() {
		add(new NavomaticBorder("navomaticBorder"));
	}
}
