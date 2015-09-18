package bobkubista.examples.webapps.wicket.pages.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 *
 * @author Bob Kubista
 *
 */
public abstract class BasePage extends WebPage {

    private static final long serialVersionUID = -1613992789574719544L;

    /**
     * Constructor
     */
    public BasePage() {
        this.add(new Label("header", "This is in the header"));
        this.add(new Label("footer", "This is in the footer"));
    }

}
