package bobkubista.examples.webapps.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Test bean
 *
 * @author Bob
 *
 */
@ManagedBean
@RequestScoped
public class TestBean {

    private final String message = "JSF is running";

    /**
     *
     * @return
     */
    public String getMessage() {
        return this.message;
    }

}
