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

    /**
     *
     * @return
     */
    public String getMessage() {
        return "JSF is running";
    }

}
